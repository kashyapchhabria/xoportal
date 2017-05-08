package com.xo.web.ext.teliaexport.mgr;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Set;

import com.xo.web.core.XODAOException;
import com.xo.web.ext.teliaexport.models.TeliaExport;
import com.xo.web.ext.teliaexport.models.TeliaExportDao;
import com.xo.web.ext.teliaexport.models.TeliaExportDaoImpl;
import com.xo.web.ext.teliaexport.viewdtos.TeliaExportDto;
import com.xo.web.mgr.BaseLogic;
import com.xo.web.models.dao.UserDAO;
import com.xo.web.models.dao.UserDAOImpl;
import com.xo.web.models.system.User;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoMailSender;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.MailDto;

public class TeliaExportLogic extends BaseLogic<TeliaExport, Integer> {
	
	private final TeliaExportDao TeliaExportDAO;
	private final UserDAO userDAO;
	
	public TeliaExportLogic() {
		super(new TeliaExportDaoImpl());
		this.TeliaExportDAO = (TeliaExportDao) entityDao;       
		this.userDAO = new UserDAOImpl();
	}

	public TeliaExport save(TeliaExportDto TeliaExportDto) throws ParseException {
		TeliaExport teliaExport = null;
		Date datetime=null;	
		if(TeliaExportDto != null) {
			User user = userDAO.findByEmail(TeliaExportDto.user);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(TeliaExportDto.createdDate);
			datetime =(Date) df.parse(df.format(new Date(Long.parseLong(TeliaExportDto.createdDate))));
			teliaExport = new TeliaExport(datetime,user,TeliaExportDto.dateOfEvent,TeliaExportDto.noOfUsers);
			teliaExport = this.TeliaExportDAO.save(teliaExport);
		}
		return teliaExport;
	}
	
	

}
