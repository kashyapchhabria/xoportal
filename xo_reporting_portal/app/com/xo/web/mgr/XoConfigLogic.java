package com.xo.web.mgr;

import com.xo.web.core.XODAOException;
import com.xo.web.models.dao.XoConfigDAOImpl;
import com.xo.web.models.system.XoConfig;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.ConfigurationDto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class XoConfigLogic<T extends XoConfig> extends BaseLogic<T, Integer> {

	public XoConfigLogic(XoConfigDAOImpl<T> entityDao) {
		super(entityDao);
	}

	public ConfigurationDto read(Integer id) {
		ConfigurationDto configurationDto = null;
		if(id > 0) {
			T config = this.entityDao.find(id);
			if(config != null) {
				configurationDto = new ConfigurationDto(config);
			}
		}
		return configurationDto;
	}

	public Set<ConfigurationDto> findAllConfigs() {
        Collection<T> allConfigurations = this.entityDao.findAll();
        return convertEntitiesToDtos(allConfigurations);
    }

	protected Set<ConfigurationDto> convertEntitiesToDtos(Collection<T> allConfigurations) {
	    Set<ConfigurationDto> configurationDtos = new HashSet<ConfigurationDto>();
	    if(XoUtil.hasData(allConfigurations)) {
	        for(XoConfig configuration : allConfigurations) {
	            configurationDtos.add(new ConfigurationDto(configuration));
	        }
	    }
	    return configurationDtos;
	}

	public ConfigurationDto findByName(String configShortName) throws XODAOException {
		ConfigurationDto configurationDto = null;
		T xoConfig = ((XoConfigDAOImpl<T>) this.entityDao).findByName(configShortName);
		if(xoConfig != null) {
			configurationDto = new ConfigurationDto(xoConfig);
		}
		return configurationDto;
	}

}