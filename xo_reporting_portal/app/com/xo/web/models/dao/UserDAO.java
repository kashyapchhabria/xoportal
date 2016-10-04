package com.xo.web.models.dao;

import com.xo.web.models.system.User;

public interface UserDAO extends GenericDAO<User, Integer> {

	User findByUsernamePassword(final String email, final String hashedPassword);
	void setLastLoginDate(final String email);
	User findByEmail(final String email);
	User findByEmailAndActive(final boolean active, final String email);
	void verify(final User unverified);
	void updateByUsernamePassword(final String userEmail,final String oldPassword,final String newPassword);
}