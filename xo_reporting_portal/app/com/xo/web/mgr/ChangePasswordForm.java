package com.xo.web.mgr;

import com.xo.web.controllers.UserI18NLabels;
import com.xo.web.util.XoUtil;
import play.data.validation.Constraints.MinLength;
import play.i18n.Messages;
import play.mvc.Http.Context;

public class ChangePasswordForm extends LoginForm {

	public ChangePasswordForm() {
	}

	@MinLength(5)
	protected String newPassword;

	@MinLength(5)
	protected String repeatPassword;

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}

	public String validate() {
		if(XoUtil.isNotNull(this.newPassword, this.repeatPassword, this.password)) {
			final TokenActionLogic tokenActionLogic = new TokenActionLogic();
			final UserLogic userLogic = new UserLogic();
			String authToken = Context.current().request().getHeader(XoUtil.HEADER_AUTH_TOKEN);
			this.user = tokenActionLogic.getRestConnectedUser(authToken);
			if (user == null) {
				return Messages.get(UserI18NLabels.ERR_CHECK_MAIL_PASS_AND_ACTIVE);
			} else {
				if (!user.isIsEmailVerified()) {
					return Messages.get(UserI18NLabels.ERR_LINK_EXPIRED);
				} else {
					if (!userLogic.validateEncryptedString(user.getPassword(), this.password)) {
						// Password was correct, Place one auth token in session.		
						return Messages.get(UserI18NLabels.ERR_PASS_REQUIRED);
					} else if(!this.newPassword.equalsIgnoreCase(this.repeatPassword)){
						return Messages.get(UserI18NLabels.ERR_REPEAT_PASS_MATCH);
					} else {
						// if you don't return here,
						// you would allow the user to have
						// multiple passwords defined
						// usually we don't want this
						return null;
					}
				}
			}
		} else {
			return Messages.get(UserI18NLabels.ERR_PASS_REQUIRED);
		}
	}

}