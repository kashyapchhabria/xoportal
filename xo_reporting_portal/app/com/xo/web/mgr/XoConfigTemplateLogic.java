package com.xo.web.mgr;

import com.xo.web.core.XODAOException;
import com.xo.web.models.dao.XoConfigTemplateDAOImpl;
import com.xo.web.models.system.XoConfigTemplate;
import com.xo.web.viewdtos.ConfigurationDto;

public class XoConfigTemplateLogic extends XoConfigLogic<XoConfigTemplate> {

    private final XoConfigTemplateDAOImpl xoConfigTemplateDAO;

    public XoConfigTemplateLogic() {
        super(new XoConfigTemplateDAOImpl());
        this.xoConfigTemplateDAO = (XoConfigTemplateDAOImpl) entityDao;
    }

    public void update(ConfigurationDto configurationDto) throws XODAOException {
        if(configurationDto != null && configurationDto.configTemplateId != null) {
            XoConfigTemplate xoConfigTemplate = this.find(configurationDto.configTemplateId);
            if(xoConfigTemplate != null) {
                xoConfigTemplate.setConfigJson((configurationDto.configJson));
                xoConfigTemplate.setShortName(configurationDto.shortName);
                xoConfigTemplate.setActive(configurationDto.active);
                this.update(xoConfigTemplate);
            }
        }
    }

	public ConfigurationDto save(ConfigurationDto configurationDto) {
		XoConfigTemplate xoConfigTemplate = null;
		if(configurationDto != null) {
			xoConfigTemplate = new XoConfigTemplate(configurationDto.shortName, configurationDto.configJson);
			xoConfigTemplate = this.save(xoConfigTemplate);
		}
		return xoConfigTemplate != null ? new ConfigurationDto(xoConfigTemplate) : null;
	}

}