package com.xo.web.controllers;

import static play.data.Form.form;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.mgr.ChangePasswordForm;
import com.xo.web.mgr.LoginForm;
import com.xo.web.mgr.ResetPasswordForm;
import com.xo.web.mgr.UserIdentity;
import com.xo.web.mgr.UserLogic;
import com.xo.web.models.system.PermissionEnum;
import com.xo.web.models.system.RoleEnum;
import com.xo.web.models.system.TokenAction;
import com.xo.web.models.system.TokenType;
import com.xo.web.models.system.User;
import com.xo.web.security.authorization.action.Authroize;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoAppConstant;
import com.xo.web.util.XoMailContentProvider;
import com.xo.web.util.XoMailSender;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.AppLoginResponseDTO;
import com.xo.web.viewdtos.MailDto;
import com.xo.web.viewdtos.UserDto;

import play.Logger;
import play.data.Form;
import play.i18n.Messages;
import play.libs.Json;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import play.twirl.api.Html;
import play.utils.UriEncoding;

/**
 * @author sekar
 *
 */
@SuppressWarnings({"serial", "finally"})
public class UserController extends BaseController<User, Integer> implements UserI18NLabels {

	private static final String XOSSO_URL_LINK = XoUtil.getConfig(XoAppConfigKeys.XOSSO_URL);
	private static final String CALLBACK_URL = "?callbackurl=";
	private static final String APP_TYPE = "&app=xoportal";

	private final XoMailContentProvider xoMailContentProvider = new XoMailContentProvider();

	private final UserLogic userLogic;

	public UserController() {
		super(new UserLogic());
		this.userLogic = (UserLogic) this.entityLogic;
	}

	private final Form<LoginForm> LOGIN_FORM = form(LoginForm.class);
	private final Form<ChangePasswordForm> CHANGE_PASSWORD_FORM = form(ChangePasswordForm.class);
	private final Form<ResetPasswordForm> RESET_PASSWORD_FORM = form(ResetPasswordForm.class);
	private final Form<UserIdentity> FORGOT_PASSWORD_FORM = form(UserIdentity.class);

	public Result renderLoginPage() {
		boolean xossoStatus = Boolean.parseBoolean(XoUtil.getConfig(XoAppConfigKeys.XOSSO_STATUS));
		if(xossoStatus && XoUtil.isNotNull(XOSSO_URL_LINK)) {
			XoUtil.getAndSetRemoteAddress();
			String callbackurl = com.xo.web.controllers.routes.UserController.xossoCallBackHandler(null, null, null, null, null).
					absoluteURL(request(), false);
			StringBuilder loginUrlStr = new StringBuilder(XOSSO_URL_LINK);
			loginUrlStr.append(CALLBACK_URL);
			loginUrlStr.append(UriEncoding.encodePathSegment(callbackurl, XoUtil.STRING_UTF_8));
			loginUrlStr.append(APP_TYPE);
			return seeOther(loginUrlStr.toString());			
		} else {
			String forgotPassurl = com.xo.web.controllers.routes.UserController.forgotPasswordPage().absoluteURL(request(), false);
			return ok(com.xo.web.views.html.login.render(LOGIN_FORM, forgotPassurl));
		}
	}

	public Result login() {
		final Form<LoginForm> filledForm = LOGIN_FORM.bindFromRequest();
		Result result = null;
		if (filledForm.hasErrors()) {	// User did not fill everything properly
			String forgotPassurl = com.xo.web.controllers.routes.UserController.forgotPasswordPage().absoluteURL(request(), false);
			result = ok(com.xo.web.views.html.login.render(filledForm, forgotPassurl));
		} else {	// Everything was filled & Success
			String authToken = session().get(XoUtil.HEADER_AUTH_TOKEN);
			result = redirect(com.xo.web.controllers.routes.UserController.pageDispatcher(authToken, null));
		}
		return result;
	}

	public Result logout(String authToken) {
		Result result = null;		
		TOKEN_ACTION_LOGIC.logout(authToken);
		session().remove(XoUtil.HEADER_AUTH_TOKEN);
		result = redirect(com.xo.web.controllers.routes.UserController.renderLoginPage());
		return result;
	}

	@Authroize(permissions = {PermissionEnum.UPDATE_USER, PermissionEnum.UPDATE_PASSWORD})
	public Result changePassword()
	{   JsonNode jsonResponse = null;
		final Form<ChangePasswordForm> filledForm = CHANGE_PASSWORD_FORM.bindFromRequest();
		if (filledForm.hasErrors()) {	// User did not fill everything properly
			jsonResponse = generateErrorResponse(filledForm.error("").message());
		} else {	// Everything was filled & Success
			ChangePasswordForm resetForm=filledForm.get();
			String newPassword = resetForm.getNewPassword();
			userLogic.changePassword(resetForm.getUser(), newPassword);
			jsonResponse = generateSuccessResponse(Messages.get(MSG_PASS_UPDATE_SUCCESS));
		}
		return ok(jsonResponse);
	}

	public Result renderResetPasswordPage(String authToken) {
		return ok(com.xo.web.views.html.reset_password.render(RESET_PASSWORD_FORM, authToken));
	}

	public Result resetPassword(String authToken)
	{
		Result result = null;
		final Form<ResetPasswordForm> filledForm = RESET_PASSWORD_FORM.bindFromRequest();
		if (filledForm.hasErrors()) {	// User did not fill everything properly
			result = ok(com.xo.web.views.html.reset_password.render(filledForm, authToken));
		} else {	// Everything was filled & Success
			ResetPasswordForm resetForm = filledForm.get();
			String newPassword = resetForm.getNewPassword();
			User user = resetForm.getUser();
			userLogic.changePassword(user, newPassword);
			this.TOKEN_ACTION_LOGIC.deleteToken(user, TokenType.PASSWORD_RESET);
			result = redirect(com.xo.web.controllers.routes.UserController.renderLoginPage());
		}
		return result;
	}

	public Result resetPasswordForUser(Integer userId)
	{
		JsonNode jsonResponse = null;
		if(userId != null && userId > 0) {
			User user = this.userLogic.find(userId);
			if(user != null) {
				this.sendVerifyEmailMailForPassReset(user);
				jsonResponse = generateSuccessResponse(Messages.get(MSG_RESET_MAIL_SENT_SUCCESS));
			} else {
				jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_USER_ID));
			}
		} else {
			jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_USER_ID));
		}
		return ok(jsonResponse);
	}

	public Result forgotPasswordPage() {
		return ok(com.xo.web.views.html.forgot_password.render(FORGOT_PASSWORD_FORM));
	}

	public Result forgotPassword()
	{   
		Result result = null;
		final Form<UserIdentity> filledForm = FORGOT_PASSWORD_FORM.bindFromRequest();
		if (filledForm.hasErrors()) {	// User did not fill everything properly
			result = ok(com.xo.web.views.html.forgot_password.render(filledForm));
		} else {	// Everything was filled & Success
			UserIdentity resetForm = filledForm.get();
			this.sendVerifyEmailMailForPassReset(resetForm.getUser());
			result = ok(com.xo.web.views.html.forgot_password_result.render(Messages.get(MSG_FORGOT_PASS_MAIL_SENT_SUCCESS)));
		}
		return result;
	}

	public Result xossoCallBackHandler(String token, String company, String domain, String name, String email) {
		Result result = null;
		if(!XoUtil.isNotNull(token)) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse("Token expected."));
		}
		try {
			AppLoginResponseDTO appLoginResponseDTO = new AppLoginResponseDTO();
			appLoginResponseDTO.company = company;
			appLoginResponseDTO.domain = domain;
			appLoginResponseDTO.email = email;
			appLoginResponseDTO.name = name;
			appLoginResponseDTO.token = token;
			this.TOKEN_ACTION_LOGIC.save(appLoginResponseDTO);
			result = redirect(com.xo.web.controllers.routes.UserController.pageDispatcher(appLoginResponseDTO.token, null));
		} catch(Exception e) {
			Logger.error("Error while saving the user details.", e);
			throw new XOException(e);
		} finally {
			return result;
		}
	}
	
	public Result pageDispatcher(String authToken, String currentPage) {
		Result result = null;
		if(XoUtil.isNotNull(authToken)) {
			User user = TOKEN_ACTION_LOGIC.getRestConnectedUser(authToken);
			if(user != null) {
				session(XoUtil.HEADER_AUTH_TOKEN, authToken);
				if(XoUtil.isNotNull(currentPage)) {
					session(XoUtil.CURRENT_PAGE, currentPage);
				}
				result = ok(com.xo.web.views.html.xoapp_base.render(this.getUIPages(user)));
			}
		}
		return result != null ? result : redirect(com.xo.web.controllers.routes.UserController.renderLoginPage());
	}

	@Authroize(permissions = {PermissionEnum.CREATE_USER})
	public Result create() {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
			if (XoUtil.jsonValidate(json, "firstName")) {
				UserDto userDto = Json.fromJson(json, UserDto.class);
				if(this.userLogic.validateUserDetails(userDto)) {
					User user = this.userLogic.save(userDto);
					this.sendVerifyEmailMailAfterSignup(user);
					jsonResponse = generateSuccessResponse(Messages.get(CREATED_SUCCESSFULLY, userDto.firstName));
				} else {
					Logger.error("User validation failed. Please check the User details json.");
					jsonResponse = generateErrorResponse(Messages.get(ERR_USER_JSON_INVALID));
				}
			} else {
				Logger.error("Json validation failed. Please check the User details json.");
				jsonResponse = generateErrorResponse(Messages.get(ERR_USER_JSON_INVALID));
			}
		} catch (XODAOException e) {
			Logger.error("Error while saving the User details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_USER));
			throw new XOException(e);
		} catch (XOException e) {
			Logger.error("Email has been already registered", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_EMAIL_ALREADY_REGISTERED));
			throw new XOException(e);
		} catch(Exception e) {
			Logger.error("Error while saving the user details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_USER));
			throw new XOException(e);
		} finally {
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_USER})
	public Result read(Integer userId) {
		JsonNode jsonResponse = generateErrorResponse(Messages.get(ERR_INVALID_USER_ID));
		if(userId > 0) {
			UserDto userDto = this.userLogic.read(userId);
			if(userDto != null) {
				jsonResponse = Json.toJson(userDto);
			}
		}
		return ok(jsonResponse);
	}

	@Authroize(permissions = {PermissionEnum.READ_USER, PermissionEnum.UPDATE_USER})
	public Result update(Integer userId) {
		JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
			if (XoUtil.jsonValidate(json, "userId")) {
				UserDto userDto = Json.fromJson(json, UserDto.class);
				if(this.userLogic.validateUserDetails(userDto)) {
					this.userLogic.update(userDto);
					jsonResponse = generateSuccessResponse(Messages.get(MSG_USER_UPDATE_SUCCESS));
				}
			} else {
				Logger.error("Json validation failed. Please check the User details json.");
				jsonResponse = generateErrorResponse(Messages.get(ERR_USER_JSON_INVALID));
			}
		} catch (XODAOException e) {
			Logger.error("Error while udpating the User details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_USER));
			throw new XOException(e);
		} catch (XOException e) {
			Logger.error("Email has been already registered", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_EMAIL_ALREADY_REGISTERED));
			throw new XOException(e);
		} catch(Exception e) {
			Logger.error("Error while udpating the User details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_USER));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_USER, PermissionEnum.DELETE_USER})
	public Result delete(Integer userId) {
		return super.delete(userId);
	}

	@Authroize(permissions = {PermissionEnum.READ_USER})
	public Result readAll() {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.userLogic.findAllUsers());
		} catch (Exception e) {
			Logger.error("Error while reading the users list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_USERS));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_USER})
	public Result readAllAsKeyValue() {
		JsonNode jsonResponse = null;
		try {
			jsonResponse = Json.toJson(this.userLogic.readAllAsKeyValues());
		} catch (Exception e) {
			Logger.error("Error while reading the role list.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_USERS));
			throw new XOException(e);
		} finally{
			return ok(jsonResponse);
		}
	}

	@Authroize(permissions = {PermissionEnum.READ_USER, PermissionEnum.ACTIVATE_USER, PermissionEnum.DEACTIVATE_USER})
	public Result toggleActiveStatus(Integer userId) throws XOException {
		JsonNode jsonResponse = generateErrorResponse(Messages.get(ERR_USER_STATUS_INVALID_ID));
		try {
			if(userId > 0) {
				this.userLogic.toggleActiveStatus(userId);
				jsonResponse = generateSuccessResponse(Messages.get(MSG_USER_STATUS_CHANGED_SUCCESSFULLY));
			}
		} catch (Exception e) {
			Logger.error("Error while changing the User status.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_USER_STATUS_CHANGE_FAILED));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
	}

	private final List<Html> getAuthorizedUIPages(final User user) {

		return new ArrayList<Html>() {{
			Set<PermissionEnum> permissionEnums = user.getPermissionEnums();
			boolean isSuperUser = user != null && user.isSuperUser();
			String emailAddress = user != null ? user.getEmail() : "admin-noreply@xoanonanalytics.com";

			if(isSuperUser) {
				session(XoAppConstant.HEADER_X_SUPER_CLIENT, Boolean.TRUE.toString());
				session(ROLE_NAME, RoleEnum.Admin.name());
				add(com.xo.web.views.html.useradmin_controls.render());
				add(com.xo.web.views.html.chat.render());
			} else {
				session(ROLE_NAME, RoleEnum.Viewer.name());
			}
			session(USER_EMAIL, emailAddress);
			if(isSuperUser || permissionEnums.containsAll(PermissionEnum.getRoleMgmtPermissions())) {
				add(com.xo.web.views.html.create_role.render());
				add(com.xo.web.views.html.role_mgmt.render());
			}
			if(isSuperUser || permissionEnums.containsAll(PermissionEnum.getRolePermissionMgmtPermissions())) {
				add(com.xo.web.views.html.role_permission_mgmt.render());
			}
			if(isSuperUser || permissionEnums.containsAll(PermissionEnum.getUserMgmtPermissions())) {
				add(com.xo.web.views.html.user_mgmt.render());
				add(com.xo.web.views.html.create_user.render());
				add(com.xo.web.views.html.users_import.render());
			}
			if(isSuperUser || permissionEnums.containsAll(PermissionEnum.getPermissionMgmtPermissions())) {
				add(com.xo.web.views.html.permission_mgmt.render());
			}
			if(isSuperUser || permissionEnums.containsAll(PermissionEnum.getUserRoleMgmtPermissions())) {
				add(com.xo.web.views.html.user_role_mgmt.render());
			}
			
			if(isSuperUser || permissionEnums.containsAll(PermissionEnum.getUserPermissionMgmtPermissions())) {
				add(com.xo.web.views.html.user_perm_mgmt.render());
			}

			if(isSuperUser || permissionEnums.containsAll(PermissionEnum.getTableauPermissions())) {
				add(com.xo.web.views.html.dashboard_projects.render());
			}

			boolean isParentPageAdded = false;
            if(isSuperUser || permissionEnums.containsAll(PermissionEnum.getConfigTemplatePermissions())) {
            	if(!isParentPageAdded) {
            		add(com.xo.web.views.html.system_settings.render());
            	}
            	isParentPageAdded = true;
                add(com.xo.web.views.html.configuration.render());
                add(com.xo.web.views.html.configtemp_mgmt.render());
				add(com.xo.web.views.html.reports_mgmt.render());
				add(com.xo.web.views.html.reports_grp_mgmt.render());
			}

            if(isSuperUser || permissionEnums.containsAll(PermissionEnum.getConfigInstancePermissions())) {
            	if(!isParentPageAdded) {
            		add(com.xo.web.views.html.system_settings.render());
            	}
            	isParentPageAdded = true;
                add(com.xo.web.views.html.configinstance_mgmt.render());
            }

            if(isSuperUser || permissionEnums.containsAll(PermissionEnum.getClientJobConfigPermissions())) {
            	if(!isParentPageAdded) {
            		add(com.xo.web.views.html.system_settings.render());
            	}
            	isParentPageAdded = true;
            	add(com.xo.web.views.html.clientjobconfig_mgmt.render());
            }

            isParentPageAdded = false;
            if(isSuperUser || permissionEnums.containsAll(PermissionEnum.getUserRowLevePermissions())) {
            	if(!isParentPageAdded) {
            		add(com.xo.web.views.html.row_level_mgmt.render());
            	}
            	isParentPageAdded = true;
            	add(com.xo.web.views.html.user_row_level_mgmt.render());
            }
            if(isSuperUser || permissionEnums.containsAll(PermissionEnum.getRoleRowLevePermissions())) {
            	if(!isParentPageAdded) {
            		add(com.xo.web.views.html.row_level_mgmt.render());
            	}
            	isParentPageAdded = true;
            	add(com.xo.web.views.html.role_row_level_mgmt.render());
            }

		    add(com.xo.web.views.html.changepassword.render(CHANGE_PASSWORD_FORM));
		}};
	}

	private List<Html> getUIPages(User user) {
		return getAuthorizedUIPages(user);
	}

	private void sendVerifyEmailMailAfterSignup(final User... users) {

		try{
			if(XoUtil.hasData(users)) {
				Set<MailDto> mailDtos = new HashSet<MailDto>();
				final String subject = getVerifyEmailMailSubject();
				final Method emailTemplateRenderer = xoMailContentProvider.getEmailTemplateRenderer(EMAIL_TEMPLATE_HTML_ACCOUNT_VERIFY, 
						new Class<?>[]{String.class, String.class});
				String url = null;
				String body = null;
				for(User user : users) {
					url = getActivationLink(user);
					body = xoMailContentProvider.getEmailTemplate(emailTemplateRenderer, url, user.getFirstName());
					mailDtos.add(new MailDto(body, subject, user.getEmail()));
				}
				new XoMailSender(mailDtos);
			}
		} catch(XOException e) {
			Logger.error("Error while sending mail.", e);
		}
	}

	private void sendVerifyEmailMailForPassReset(final User... users) {

		try{
			if(XoUtil.hasData(users)) {
				Set<MailDto> mailDtos = new HashSet<MailDto>();
				final String subject = this.getPassResetEmailMailSubject();
				final Method emailTemplateRenderer = xoMailContentProvider.getEmailTemplateRenderer(EMAIL_TEMPLATE_HTML_PASSWORD_RESET, 
						new Class<?>[]{String.class, String.class});
				String url = null;
				String body = null;
				for(User user : users) {
					url = this.getPasswordResetLink(user);
					body = xoMailContentProvider.getEmailTemplate(emailTemplateRenderer, url, user.getFirstName());
					mailDtos.add(new MailDto(body, subject, user.getEmail()));
				}
				new XoMailSender(mailDtos);
			}
		} catch(XOException e) {
			Logger.error("Error while sending mail.", e);
		}
	}

	private String getVerifyEmailMailSubject() {
		return Messages.get("user.password.verify_email.subject");
	}

	private String getPassResetEmailMailSubject() {
		return Messages.get("user.password.reset_email.subject");
	}

	private String getActivationLink(final User user) throws XOException {

		final boolean isSecure = false;
		final String token = this.userLogic.createAuthToken(user, TokenType.EMAIL_VERIFICATION);
		final String url = com.xo.web.controllers.routes.UserController.verify(token).absoluteURL(request(), isSecure);
		return url;
	}

	private String getPasswordResetLink(final User user) throws XOException {

		final boolean isSecure = false;
		final String token = this.userLogic.createAuthToken(user, TokenType.PASSWORD_RESET);
		final String url = com.xo.web.controllers.routes.UserController.renderResetPasswordPage(token).absoluteURL(request(), isSecure);
		return url;
	}
	
	public Result verify(final String token)  throws XOException {
		Result result = null;
		try {
			final TokenAction tokenAction = this.userLogic.verifyTokenAction(token, TokenType.EMAIL_VERIFICATION);
			if (tokenAction == null) {
				result = badRequest("Bad request.");
			}
			User targetUser = tokenAction.getUser();
			if(this.userLogic.verify(targetUser, tokenAction)) {
				String authToken = session().get(XoUtil.AUTH_TOKEN_PASSWORD_RESET);
				result = ok(com.xo.web.views.html.reset_password.render(RESET_PASSWORD_FORM, authToken));	
			} else {
				result = ok(com.xo.web.views.html.not_valid_activation_link.render());
			}
		} catch(Exception e) {
			result = internalServerError("Error while validating the email.");
			throw new XOException(e);
		} finally {
			return result;
		}
	}

	@Authroize(permissions = {PermissionEnum.CREATE_USER})
	public Result uploadUsers() {
		JsonNode responseJson = null;
		try {
	        MultipartFormData body = request().body().asMultipartFormData();
			List<FilePart> userFiles = body.getFiles();
			User[] validUsers = null;
			Set<UserDto> validEntries = new HashSet<UserDto>();
			Set<Integer> invalidEntries = new HashSet<Integer>();
			this.userLogic.validateUserEntryFiles(userFiles, validEntries, invalidEntries);

			if(XoUtil.hasData(validEntries)) {
				validUsers = new User[validEntries.size()];
				int userIndex = 0;
				for(UserDto userDto : validEntries) {
					User user = this.userLogic.save(userDto);
					validUsers[userIndex] = user;
					userIndex++;
				}
				this.sendVerifyEmailMailAfterSignup(validUsers);
			}
			responseJson = generateSuccessResponse(Messages.get(UPLOAD_SUCCESSFULLY, validEntries.size(), invalidEntries.size()));

		} catch (FileNotFoundException e) {
			Logger.error("FileNotFoundException while parsing users entries.", e);
			responseJson = generateErrorResponse(Messages.get(PLEASE_CHECK_THE_UPLOADED_FILE));
		} catch (IOException e) {
			Logger.error("IOException while reading users entries from file.", e);
			responseJson = generateErrorResponse(Messages.get(ERROR_WHILE_READING_THE_FILE_CONTENT));
		} catch (XODAOException e) {
			Logger.error("Error while parsing user entries.", e);
			responseJson = generateErrorResponse(Messages.get(ERROR_WHILE_PARSING_USERS_LISTS));
		} catch (Exception e) {
			Logger.error("Error while parsing user entries file.", e);
			responseJson = generateErrorResponse(Messages.get(ERROR_WHILE_PARSING_USERS_FILE));
		}
		return ok(responseJson);
	}

	public Result jsonAccessDenied() {
		JsonNode jsonResponse = generateErrorResponse(Messages.get(ERR_ACCESS_DENIED));
		return forbidden(jsonResponse);
	}

}
