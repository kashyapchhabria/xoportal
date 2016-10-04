package com.xo.web.mgr;

import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.models.dao.XoConfigInstanceDAOImpl;
import com.xo.web.models.dao.XoConfigTemplateDAOImpl;
import com.xo.web.models.system.XoConfigInstance;
import com.xo.web.models.system.XoConfigTemplate;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.ConfigurationDto;

import play.Logger;

import java.util.Collection;
import java.util.Set;

public class XoConfigInstanceLogic extends XoConfigLogic<XoConfigInstance> {

    private final XoConfigInstanceDAOImpl xoConfigInstanceDAO;
    private final XoConfigTemplateDAOImpl xoConfigTemplateDAO;

    public XoConfigInstanceLogic() {
        super(new XoConfigInstanceDAOImpl());
        this.xoConfigInstanceDAO = (XoConfigInstanceDAOImpl) entityDao;
        this.xoConfigTemplateDAO = new XoConfigTemplateDAOImpl();
    }

    public Set<ConfigurationDto> readAll(Integer configTemplateId) {
    	Collection<XoConfigInstance> configInstances = this.xoConfigInstanceDAO.findByXoConfigTemplateId(configTemplateId);
    	return convertEntitiesToDtos(configInstances);
    }

    public Set<ConfigurationDto> readAll() {
        Collection<XoConfigInstance> configInstances = this.xoConfigInstanceDAO.findAll();
        return convertEntitiesToDtos(configInstances);
    }

    
    public void update(ConfigurationDto configurationDto) throws XODAOException {
        if(configurationDto != null && configurationDto.configInstanceId != null) {
            XoConfigInstance xoConfigInstance = this.entityDao.find(configurationDto.configInstanceId);
            if(xoConfigInstance != null) {
                xoConfigInstance.setConfigJson((configurationDto.configJson));
                xoConfigInstance.setShortName(configurationDto.shortName);
                xoConfigInstance.setActive(configurationDto.active);
                this.update(xoConfigInstance);
            }
        }
    }

    public ConfigurationDto save(ConfigurationDto configurationDto) {
    	XoConfigInstance xoConfigInstance = null;
		if(configurationDto != null) {
			XoConfigTemplate xoConfigTemplate = this.xoConfigTemplateDAO.find(configurationDto.configTemplateId);
			xoConfigInstance = new XoConfigInstance(xoConfigTemplate, configurationDto.shortName, xoConfigTemplate.getConfigJson());
			xoConfigInstance = this.save(xoConfigInstance);
		}
		return xoConfigInstance != null ? new ConfigurationDto(xoConfigInstance) : null;
	}

    public ConfigurationDto findByConfigTemplateAndName(Integer configTemplateId, String configName) throws XODAOException {
		ConfigurationDto configurationDto = null;
		if(XoUtil.isNotNull(configTemplateId, configName) && configTemplateId.intValue() > 0) {
			XoConfigInstance xoConfig = xoConfigInstanceDAO.findByConfigTemplateAndName(configTemplateId, configName);
			if(xoConfig != null) {
				configurationDto = new ConfigurationDto(xoConfig);
			}
		}
		return configurationDto;
	}

    public final ConfigurationDto loadConfig(String configName) throws XOException {
		ConfigurationDto configurationDto = null;
		if(XoUtil.isNotNull(configName)) {
			try {
				configurationDto = this.findByName(configName);
				Logger.info("Successfully Loaded Configurations");
			} catch (Exception e) {
				Logger.error("Failed Loading Configurations");
				throw new XOException(e);
			}
		}
		return configurationDto;
	}
}