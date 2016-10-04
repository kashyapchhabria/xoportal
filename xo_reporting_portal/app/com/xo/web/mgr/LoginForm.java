package com.xo.web.mgr;

import com.xo.web.controllers.UserI18NLabels;
import com.xo.web.models.dao.UserDAO;
import com.xo.web.models.dao.UserDAOImpl;
import com.xo.web.models.system.User;
import play.data.validation.Constraints.MinLength;
import play.i18n.Messages;

import java.util.Date;

public class LoginForm extends UserIdentity{

	public LoginForm() {
	}

	@MinLength(5)
	protected String password;

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public String validate() {
		final UserDAO tempUserDao = new UserDAOImpl();
		final UserLogic userLogic = new UserLogic();
		final User user = tempUserDao.findByEmailAndActive(true, this.email);
		if (user == null) {
			return Messages.get(UserI18NLabels.ERR_CHECK_MAIL_PASS_AND_ACTIVE);
		} else {
			if (!user.isIsEmailVerified()) {
				return Messages.get(UserI18NLabels.ERR_EMAIL_NOT_VERIFIED);
			} else {
				if (userLogic.validateEncryptedString(user.getPassword(), this.password)) {
					// Password was correct, Place one auth token in session.

					// Update user last apperence time.
					Date curDate = new Date();
					user.setLastLoginDt(curDate);
					user.setLastModifiedDate(curDate);

					userLogic.createAuthToken(user);
					return null;
				} else {
					// if you don't return here,
					// you would allow the user to have
					// multiple passwords defined
					// usually we don't want this
					return Messages.get(UserI18NLabels.ERR_CHECK_MAIL_PASS_AND_ACTIVE);
				}
			}
		}
	}
}