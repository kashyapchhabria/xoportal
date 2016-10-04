package com.xo.web.models.dao;

import com.xo.web.models.system.TokenAction;
import com.xo.web.models.system.TokenType;
import com.xo.web.models.system.User;
import com.xo.web.util.XoUtil;
import org.hibernate.Query;

import java.util.Date;
import java.util.List;

@SuppressWarnings("unchecked")
public class TokenActionDAOImpl extends GenericDAOImpl<TokenAction, Integer> implements TokenActionDAO {

	private static final String PARAM_TOKEN = "token";
	private static final String PARAM_TOKEN_TYPE = "tokenType";
	private static final String PARAM_USER = "user";
	private static final String QUERY_BY_TOKEN_AND_TOKEN_TYPE = "findByTokenAndType";
	private static final String DELETE_BY_USER_AND_TYPE_TOKEN = "deleteByUserAndType";
	private static final String DELETE_BY_EXPIRES_DATE_AND_TYPE_TOKEN = "deleteByTokenTypeAndExpiresDate";
	private static final String UPDATE_LAST_MODIFIED_DT = "updateLastModifiedDt";
	private static final String DELETE_BY_EXPIRED_DATE = "deleteByExpiredDate";

	/**
 	 * Verification time frame (until the user clicks on the link in the email)
 	 * in seconds
 	 * Defaults to one week
 	 */
 	private final static long VERIFICATION_TIME = 7 * 24 * 3600;

	public User deleteByUserAndType(User user, TokenType tokenType) {
    	try{
			Query query = getNamedQuery(DELETE_BY_USER_AND_TYPE_TOKEN);
			query.setParameter(PARAM_USER, user);
			query.setParameter(PARAM_TOKEN_TYPE, tokenType);
			query.executeUpdate();
    	} catch(Exception exception) {
    		exception.printStackTrace();
    	}
		return user;
	}

	public void deleteByTokenAndType(String token,TokenType tokenType) {
    	try{
			TokenAction tokenAction = this.findByTokenAndType(token, tokenType);
			if(tokenAction!=null){
				this.remove(tokenAction);
			}
    	} catch(Exception exception) {
    		exception.printStackTrace();
    	}
	}

	public TokenAction findByTokenAndType(String token, TokenType tokenType) {

		Query query = getNamedQuery(QUERY_BY_TOKEN_AND_TOKEN_TYPE);
		query.setParameter(PARAM_TOKEN, token);
		query.setParameter(PARAM_TOKEN_TYPE, tokenType);
		List<TokenAction> results = query.list();
		if (XoUtil.hasData(results)) {
		    return results.get(0);
		} else {
		    return null; // handle no-results case
		}
	}

	public TokenAction create(final TokenType type, final String token, final User targetUser) {

		final TokenAction tokenAction = new TokenAction();
		tokenAction.setUser(targetUser);
		tokenAction.setToken(token);
		tokenAction.setType(type);

		Date newDate = new Date();
		tokenAction.setCreatedDate(newDate);
		tokenAction.setLastModifiedDate(newDate);
		
		final Date created = new Date();
		created.setTime(created.getTime() + VERIFICATION_TIME * 1000);
		tokenAction.setExpiresDate(created);

		return save(tokenAction);
	}

	public void deleteByTokenTypeAndExpiresDate(TokenType tokenType) {
		Query query = getNamedQuery(DELETE_BY_EXPIRES_DATE_AND_TYPE_TOKEN);
		query.setParameter(PARAM_TOKEN_TYPE, tokenType);
		query.executeUpdate();
	}

	public void deleteAllByExpiredDate() {
		Query query = getNamedQuery(DELETE_BY_EXPIRED_DATE);
		query.executeUpdate();
	}
	
	public void updateLastModifiedDt(String token, TokenType tokenType) {
		Query query = getNamedQuery(UPDATE_LAST_MODIFIED_DT);
		query.setParameter("lastPingedTime", new Date());
		query.setParameter(PARAM_TOKEN, token);
		query.setParameter(PARAM_TOKEN_TYPE, tokenType);
		query.executeUpdate();
	}
}
