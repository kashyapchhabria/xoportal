package com.xo.web.mgr;

import com.xo.web.controllers.UserI18NLabels;
import com.xo.web.models.system.TokenType;
import com.xo.web.models.system.User;
import com.xo.web.util.XoUtil;
import play.i18n.Messages;

public class ResetPasswordForm extends ChangePasswordForm {

	protected Integer userId;

	protected String authToken;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public ResetPasswordForm() {

	}

	public String validate() {
		if(userId != null && userId > 0) {
			this.user = new UserLogic().find(this.userId);
		} else if(XoUtil.isNotNull(authToken)) {
			TokenActionLogic tokenActionLogic = new TokenActionLogic();
			this.user = tokenActionLogic.getConnectedUser(authToken, TokenType.PASSWORD_RESET);
		}
		if (user == null) {
			return Messages.get(UserI18NLabels.ERR_LINK_EXPIRED);
		} else {
			if (!user.isIsEmailVerified()) {
				return Messages.get(UserI18NLabels.ERR_EMAIL_NOT_VERIFIED);
			} else if(XoUtil.isNotNull(this.newPassword, this.repeatPassword)) {
				if(!this.newPassword.equalsIgnoreCase(this.repeatPassword)){
					return Messages.get(UserI18NLabels.ERR_REPEAT_PASS_MATCH);
				}
			} else {
				return "Please enter the password & reset password.";
			}
		}
		return null;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}