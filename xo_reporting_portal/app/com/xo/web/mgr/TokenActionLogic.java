package com.xo.web.mgr;

import java.util.Date;

import com.xo.web.models.dao.TokenActionDAO;
import com.xo.web.models.dao.TokenActionDAOImpl;
import com.xo.web.models.dao.UserDAO;
import com.xo.web.models.dao.UserDAOImpl;
import com.xo.web.models.system.TokenAction;
import com.xo.web.models.system.TokenType;
import com.xo.web.models.system.User;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.AppLoginResponseDTO;

public class TokenActionLogic extends BaseLogic<TokenAction, Integer> {

	private final TokenActionDAO tokenActionDAO;
	private final UserDAO userDAO;

	public TokenActionLogic() {
		super(new TokenActionDAOImpl());
		this.tokenActionDAO = (TokenActionDAO) this.entityDao;
		this.userDAO = new UserDAOImpl();
	}

	public TokenAction save(AppLoginResponseDTO appLoginResponseDTO) {
		TokenAction tokenAction = null;
		if(appLoginResponseDTO != null && XoUtil.isNotNull(appLoginResponseDTO.email, appLoginResponseDTO.token)) {
			User user = this.userDAO.findByEmail(appLoginResponseDTO.email);
			if(user != null) {
				user.setLastLoginDt(new Date());
				tokenAction = this.tokenActionDAO.create(TokenType.REST_CALL, appLoginResponseDTO.token, user);
			}
		}
		return tokenAction;
	}

	public void deleteAllExpiredTokens() {
		this.tokenActionDAO.deleteAllByExpiredDate();
	}

	public void deleteExpiredRestTokenActions() {
		deleteExpiredTokenActions(TokenType.REST_CALL);
	}

	public void logout(String token){
		this.tokenActionDAO.deleteByTokenAndType(token,TokenType.REST_CALL);
	}

	public void deleteExpiredTokenActions(TokenType tokenType) {
		this.tokenActionDAO.deleteByTokenTypeAndExpiresDate(tokenType);
	}

	public User getRestConnectedUser(String token) {
		return getConnectedUser(token, TokenType.REST_CALL);
	}

	public void deleteToken(User user, TokenType tokenType) {
		this.tokenActionDAO.deleteByUserAndType(user, tokenType);
	}

	public User getConnectedUser(String token, TokenType tokenType) {
		User user = null;
		if(XoUtil.isNotNull(token)) {
			TokenAction tokenAction = this.tokenActionDAO.findByTokenAndType(token, tokenType);
			if(tokenAction != null) {
				user = tokenAction.getUser();
			}
		}
		return user;
	}

	public void updateRestTokenPingTime(String authToken) {
		if(XoUtil.isNotNull(authToken)) {
			this.tokenActionDAO.updateLastModifiedDt(authToken, TokenType.REST_CALL);
		}
	}
}
