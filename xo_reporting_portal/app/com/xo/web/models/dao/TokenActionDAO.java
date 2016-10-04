package com.xo.web.models.dao;

import com.xo.web.models.system.TokenAction;
import com.xo.web.models.system.TokenType;
import com.xo.web.models.system.User;

/**
 * Provides CRUD functionality for accessing people. Spring Data auto-magically takes care of many standard
 * operations here.
 */
public interface TokenActionDAO extends GenericDAO<TokenAction, Integer>{

	TokenAction create(final TokenType type, final String token, final User targetUser);
	User deleteByUserAndType(final User user, final TokenType type);
	TokenAction findByTokenAndType(String token, TokenType tokenType);
	void deleteByTokenTypeAndExpiresDate(TokenType tokenType);
	void deleteByTokenAndType(String token,TokenType tokenType);
	void updateLastModifiedDt(String token, TokenType tokenType);
	void deleteAllByExpiredDate();
}