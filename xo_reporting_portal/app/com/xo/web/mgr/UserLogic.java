package com.xo.web.mgr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.mindrot.jbcrypt.BCrypt;

import play.Logger;
import play.data.validation.Constraints.EmailValidator;
import play.mvc.Http.Context;
import play.mvc.Http.MultipartFormData.FilePart;

import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.models.dao.RoleDAO;
import com.xo.web.models.dao.RoleDAOImpl;
import com.xo.web.models.dao.TokenActionDAO;
import com.xo.web.models.dao.TokenActionDAOImpl;
import com.xo.web.models.dao.UserDAO;
import com.xo.web.models.dao.UserDAOImpl;
import com.xo.web.models.dao.UserRoleDAO;
import com.xo.web.models.dao.UserRoleDAOImpl;
import com.xo.web.models.dao.XoClientDAO;
import com.xo.web.models.dao.XoClientDAOImpl;
import com.xo.web.models.system.Role;
import com.xo.web.models.system.RoleEnum;
import com.xo.web.models.system.TokenAction;
import com.xo.web.models.system.TokenType;
import com.xo.web.models.system.User;
import com.xo.web.models.system.UserRole;
import com.xo.web.models.system.XoClient;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.UserDto;

public class UserLogic extends BaseLogic<User, Integer>{

	private final TokenActionDAO tokenActionDAO;
	private final UserDAO userDao;
	private final RoleDAO roleDAO;
	private final UserRoleDAO userRoleDAO;
	private final XoClientDAO xoClientDAO;

	public UserLogic() {
		super(new UserDAOImpl());
		this.userDao = (UserDAO) this.entityDao;
		this.tokenActionDAO = new TokenActionDAOImpl();
		this.roleDAO = new RoleDAOImpl();
		this.userRoleDAO = new UserRoleDAOImpl();
		this.xoClientDAO = new XoClientDAOImpl();
	}

	public final String getHashedPassword(String clearString) {
		return this.createEncryptedString(clearString);
	}

	protected final String createEncryptedString(final String clearString) {
		return BCrypt.hashpw(clearString, BCrypt.gensalt());
	}

	public final boolean validateEncryptedString(final String hashed, final String clearString) {
		if (hashed == null || clearString == null) {
			return false;
		}
		return BCrypt.checkpw(clearString, hashed);
	}

	public final void createAuthToken(final User user) {
		if(user != null) {
			String authToken = this.createAuthToken(user, TokenType.REST_CALL);
			Context.current().session().put(XoUtil.HEADER_AUTH_TOKEN, authToken);
		}
	}

	public final String createAuthToken(final User user, TokenType tokenType) {
		String auth_token = null;
		if(user != null) {
			auth_token = this.createEncryptedString(this.generateClearToken(XoUtil.getConfig(XoAppConfigKeys.APP_SECRET)));
			tokenActionDAO.create(tokenType, auth_token, user);
		}
		return auth_token;
	}

	public void changePassword(User user, String newPassword){
		if(XoUtil.isNotNull(user, newPassword)) {
			String changedPassword = getHashedPassword(newPassword);
			user.setPassword(changedPassword);
			this.userDao.merge(user);
		}
	}

	public Set<UserDto> findAllUsers() {
		Collection<User> allUsers = this.userDao.findAll();
		return convertEntitiesToDtos(allUsers);
	}

	private Set<UserDto> convertEntitiesToDtos(Collection<User> allUsers) {
		Set<UserDto> userDtos = new HashSet<UserDto>();
		if(XoUtil.hasData(allUsers)) {
			for(User user : allUsers) {
				if(!user.isSuperUser()) {	// Super users can't be shown to outside world.
					userDtos.add(new UserDto(user));
				}
			}
		}
		return userDtos;
	}

	public boolean validateUserDetails(UserDto userDto) throws XOException {
		boolean isValid = false;
		if(userDto != null) {
			if(XoUtil.isNotNull(userDto.firstName, userDto.secondName, userDto.email, userDto.clientId)) {
				User user = this.userDao.findByEmail(userDto.email);
				if(user != null && userDto.userId != user.getUserId()) {
					throw new XOException("Email has been already registered.");
				} else {
					isValid = true;
				}
			}
		}
		return isValid;
	}

	public User save(UserDto userDto) throws XODAOException {
		User user = null;
		if(userDto != null) {
			user = userDto.asEntityObject();
			XoClient xoClient = null;
			if(userDto.clientId != null) {
				xoClient = this.xoClientDAO.find(userDto.clientId);
			} else if(userDto.clientName != null) {
				xoClient = this.xoClientDAO.findByNameAndActive(userDto.clientName, true);
			}
			if(xoClient != null) {
				user.setXoClient(xoClient);
			}
			user.setActive(true);
			user.setPassword(this.createEncryptedString(userDto.firstName+userDto.email));
			Role adminRole = this.roleDAO.findByName(RoleEnum.Viewer.name());
			user = super.save(user);
			UserRole userRole = new UserRole(adminRole, user);
			user.getUserRoles().add(userRole);
			userRoleDAO.save(userRole);
		}
		return user;
	}

	public TokenAction verifyTokenAction(String token, TokenType tokenType) {
		TokenAction tokenAction = null;
		if(XoUtil.isNotNull(token)) {
			tokenAction = tokenActionDAO.findByTokenAndType(token, tokenType);
		}
		return tokenAction;
	}

	public final boolean verifyAuthToken(String token) {
		boolean isTokenValid = false;
		if(XoUtil.isNotNull(token)) {
			isTokenValid = this.validateEncryptedString(token, this.generateClearToken(XoUtil.getConfig(XoAppConfigKeys.APP_SECRET)));
		}
		return isTokenValid;
	}

	private final String generateClearToken(String secret) {
		StringBuilder clearString = new StringBuilder(secret);
		clearString.append(XoUtil.formatDate(Calendar.getInstance(TimeZone.getTimeZone(XoUtil.STRING_UTC)).getTime(), XoUtil.DATE_FORMAT_YYYYMMDD));
		clearString.append(XoUtil.ipToHex());
		return clearString.toString();
	}

	public UserDto read(Integer id) {
		UserDto userDto = null;
		if(id > 0) {
			User user = this.userDao.find(id);
			if(user != null) {
				userDto = new UserDto(user);
			}
		}
		return userDto;
	}

	public void update(UserDto userDto) throws XODAOException {
		if(userDto != null) {
			User user = this.find(userDto.userId);
			if(user != null) {
				if(userDto.clientId != null) {
					XoClient xoClient = this.xoClientDAO.find(userDto.clientId);
					if(xoClient != null) {
						user.setXoClient(xoClient);
					}
				} else {
					return ;
				}
				user.setFirstName(userDto.firstName);
				user.setSecondName(userDto.secondName);
				user.setEmail(userDto.email);
				
				super.update(user);
			}
		}
	}

	public boolean verify(User user, TokenAction tokenAction) {
		boolean isValidUser = false;
		if(user != null && tokenAction != null) {
			tokenActionDAO.remove(tokenAction);
			user.getTokenActions().clear();
			user.setIsEmailVerified(true);
			Context.current().session().put(XoUtil.AUTH_TOKEN_PASSWORD_RESET, this.createAuthToken(user, TokenType.PASSWORD_RESET));
			super.update(user);
			isValidUser = true;
		}
		return isValidUser;
	}

	public void validateUserEntryFiles(List<FilePart> userEntriesFiles, Set<UserDto> cleanList, Set<Integer> dirtyList) throws FileNotFoundException, IOException, XODAOException {

		if( XoUtil.hasData(userEntriesFiles))
		{
		    for(FilePart filePart : userEntriesFiles) {
		    	Logger.debug("Uploaded File" + filePart.getFilename());
		    	this.validateUserEntries(filePart.getFile(), cleanList, dirtyList);
		    }
		}
	}

	private void validateUserEntries(File customerFile, Set<UserDto> validUserRecords, Set<Integer> invalidUserRecords) 
			throws IOException, FileNotFoundException {

		FileReader reader = null;
		String userRecord = null;
		reader = new FileReader(customerFile);
		BufferedReader br = new BufferedReader(reader);
		int lineNumber = 0;
		EmailValidator emailValidator = new EmailValidator();
		while( (userRecord = br.readLine()) != null) {

			++lineNumber;
			String[] userDetails = userRecord.split(",");
			if(userDetails.length != 4)	// CSV file may contain 4 fields. firstname, lastname, email and client
			{
				invalidUserRecords.add(lineNumber);
				continue;
			} else {
				if(emailValidator.isValid(userDetails[2].trim())) {
					UserDto userDto = new UserDto(userDetails[0].trim(), userDetails[1].trim(), userDetails[2].trim(), userDetails[3].trim());
					try {
						if(this.validateUserDetails(userDto)) {					
							validUserRecords.add(userDto);					
						} else {
							invalidUserRecords.add(lineNumber);
						}
					} catch (XOException e) {
						invalidUserRecords.add(lineNumber);
					}
				} else {
					invalidUserRecords.add(lineNumber);
				}
			}
		}
		br.close();
	}

	public User deleteRemoteEntity(UserDto userDto) throws XODAOException {
		User user = null;
		if(userDto != null) {
			user = this.userDao.findByEmail(userDto.email);
			if(user != null) {
				user.setActive(false);
				user.setDeleted(true);
				user.setLastModifiedDate(new Date());
				super.update(user);
			}
		}
		return user;
	}
}
