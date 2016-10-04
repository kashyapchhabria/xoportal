package com.xo.web.models.dao;

import com.xo.web.models.system.TokenType;
import com.xo.web.models.system.User;
import com.xo.web.util.XoUtil;
import org.hibernate.Query;
import play.Logger;

import java.util.Date;
import java.util.List;

@SuppressWarnings("unchecked")
public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO {

	private static final String PARAM_HASHED_PASSWORD = "hashedPassword";
	private static final String PARAM_ACTIVE = "active";
	private static final String PARAM_EMAIL = "email";
	private static final String QUERY_FIND_BY_USERNAME_PASSWORD = "findByUsernamePassword";
	private static final String QUERY_FIND_BY_EMAIL_AND_ACTIVE = "findByEmailAndActive";
	private static final String QUERY_FIND_BY_EMAIL = "findByEmail";
	private static final String QUERY_UPDATE_BY_USERNAME_PASSWORD = "updateByUsernamePassword";
	
	private TokenActionDAOImpl tokenActionDAOImpl = new TokenActionDAOImpl();

	public User findByUsernamePassword(final String userEmail, final String hashedPassword) {
		Query query = getNamedQuery(QUERY_FIND_BY_USERNAME_PASSWORD);
		query.setParameter(PARAM_EMAIL, userEmail);
		query.setParameter(PARAM_HASHED_PASSWORD, hashedPassword);
		List<User> results = query.list();
		if (XoUtil.hasData(results)) {
		    return results.get(0);
		}
	    return null; // handle no-results case
	}

	public void setLastLoginDate(final String email) {
		final User user = this.findByEmail(email);
		user.setLastLoginDt(new Date());
		super.merge(user);
	}

	public User findByEmail(final String email) {
		User result = null;
		Query query = getNamedQuery(QUERY_FIND_BY_EMAIL);
		query.setParameter(PARAM_EMAIL, email);
		result = (User) query.uniqueResult();
		return result;
	}
	
	public User findByEmailAndActive(final boolean active, final String email) {
		User result = null;
		Query query = getNamedQuery(QUERY_FIND_BY_EMAIL_AND_ACTIVE);
		query.setParameter(PARAM_ACTIVE, active);
		query.setParameter(PARAM_EMAIL, email);
		result = (User) query.uniqueResult();
		return result;
	}

	public void verify(final User unverified) {
		unverified.setIsEmailVerified(true);
		super.merge(unverified);
		this.tokenActionDAOImpl.deleteByUserAndType(unverified, TokenType.EMAIL_VERIFICATION);
	}

	public void resetPassword(final String userEmail, final String password, final boolean create) {
		User user = findByUsernamePassword(userEmail, password);
		this.tokenActionDAOImpl.deleteByUserAndType(user, TokenType.PASSWORD_RESET);
	}
	public void updateByUsernamePassword(final String userEmail,final String oldPassword,final String newPassword) {
		Query query = getNamedQuery(QUERY_UPDATE_BY_USERNAME_PASSWORD);
		query.setParameter(PARAM_EMAIL, userEmail);
		query.setParameter("oldPassword", oldPassword);
		query.setParameter("newPassword", newPassword);
		query.executeUpdate();
		Logger.info("query executed---");
	}

}
