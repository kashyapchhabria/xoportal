package com.xo.web.mgr;

import com.xo.web.controllers.UserI18NLabels;
import com.xo.web.models.dao.UserDAO;
import com.xo.web.models.dao.UserDAOImpl;
import com.xo.web.models.system.User;
import com.xo.web.util.XoUtil;
import play.data.validation.Constraints.Email;
import play.i18n.Messages;

public class UserIdentity {

	@Email
	protected String email;

	protected User user;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = XoUtil.isNotNull(email) ? email.trim() : email;
	}

	public String validate() {
		if(XoUtil.isNotNull(this.email)) {
			final UserDAO tempUserDao = new UserDAOImpl();
			this.email = email.trim();
			this.user = tempUserDao.findByEmail(this.email.trim());
			if (this.user == null) {
				return Messages.get(UserI18NLabels.ERR_MAIL_NOT_FOUND);
			} else {
				return null;
			}
		} else {
			return Messages.get(UserI18NLabels.ERR_EMAIL_REQUIRED);
		}
	}
	
}