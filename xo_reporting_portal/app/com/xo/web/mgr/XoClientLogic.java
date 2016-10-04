package com.xo.web.mgr;

import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.models.dao.XoClientDAO;
import com.xo.web.models.dao.XoClientDAOImpl;
import com.xo.web.models.system.XoClient;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.XoClientDto;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import play.Logger;

public class XoClientLogic extends BaseLogic<XoClient, Integer> {

	private XoClientDAO xoClientDAO;

	public XoClientLogic() {
		super(new XoClientDAOImpl());
		this.xoClientDAO = (XoClientDAO) this.entityDao;
	}

	public Set<XoClientDto> findAllClients() {
		return convertToDtos(this.xoClientDAO.findAll());
	}

	private Set<XoClientDto> convertToDtos(Collection<XoClient> xoClients) {
		Set<XoClientDto> clientDtos = new HashSet<XoClientDto>();
		if(XoUtil.hasData(xoClients)) {
			for(XoClient xoClient : xoClients) {
				clientDtos.add(convertToDto(xoClient));
			}
		}
		return clientDtos;
	}

	private XoClientDto convertToDto(XoClient xoClient) {
		XoClientDto clientDto = null;
		if(xoClient != null) {
			clientDto = new XoClientDto(xoClient);
		}
		return clientDto;
	}

	public boolean validateClientDto(XoClientDto xoClientDto, boolean isUpdate) throws XOException{
		boolean dtoStatus = false;
		if(XoUtil.isNotNull(xoClientDto) && XoUtil.isNotNull(xoClientDto.clientName, xoClientDto.clientSecret)) {
			try {
				XoClient xoClient = this.xoClientDAO.findByNameAndSecret(xoClientDto.clientName, xoClientDto.clientSecret);
				if(!isUpdate && xoClient != null) {
					throw new XOException("Client has been already registered.");
				} else {
					dtoStatus = true;
				}
			} catch (XODAOException e) {
				Logger.error("Error while reading the client object.", e);
				throw e;
			}
		} else {
			throw new XOException("Client name or client secret is empty.");
		}
		return dtoStatus;
	}

	public XoClientDto save(XoClientDto xoClientDto) throws XOException {
		if(xoClientDto != null) {
			if(this.validateClientDto(xoClientDto, false)) {
				XoClient xoClient = xoClientDto.asEntityObject();
				xoClient.setActive(true);
				xoClient.setCreatedDate(new Date());
				xoClient.setPreferredTimeZone(XoUtil.isNotNull(xoClientDto.preferredTimeZone) ? xoClientDto.preferredTimeZone : XoUtil.STRING_UTC);
				xoClientDto = new XoClientDto(this.save(xoClient));
			}
		}
		return xoClientDto;
	}

	public XoClientDto update(XoClientDto xoClientDto) throws XODAOException, XOException {
		if(xoClientDto != null) {
			if(this.validateClientDto(xoClientDto, true)) {
				XoClient xoClient = this.xoClientDAO.findBySecret(xoClientDto.clientSecret);
				xoClient.setActive(xoClientDto.active);
				xoClient.setName(xoClientDto.clientName);
				xoClient.setDeleted(xoClientDto.deleted);
				xoClient.setPreferredTimeZone(XoUtil.isNotNull(xoClientDto.preferredTimeZone) ? xoClientDto.preferredTimeZone : XoUtil.STRING_UTC);
				xoClient.setLastModifiedDate(new Date());
				xoClientDto = new XoClientDto(super.update(xoClient));
			}
		}
		return xoClientDto;
	}

	public XoClientDto delete(XoClientDto xoClientDto) throws XODAOException, XOException {
		if(xoClientDto != null) {
			if(this.validateClientDto(xoClientDto, true)) {
				XoClient xoClient = this.xoClientDAO.findBySecret(xoClientDto.clientSecret);
				xoClient.setActive(false);
				xoClient.setDeleted(true);
				xoClient.setLastModifiedDate(new Date());
				xoClientDto = new XoClientDto(this.update(xoClient));
			}
		}
		return xoClientDto;
	}
}
