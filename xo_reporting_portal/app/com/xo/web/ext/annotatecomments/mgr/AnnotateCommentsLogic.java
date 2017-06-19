package com.xo.web.ext.annotatecomments.mgr;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Set;

import com.xo.web.core.XODAOException;
import com.xo.web.ext.annotatecomments.models.AnnotateComments;
import com.xo.web.ext.annotatecomments.models.AnnotateCommentsDao;
import com.xo.web.ext.annotatecomments.models.AnnotateCommentsDaoImpl;
import com.xo.web.ext.annotatecomments.viewdtos.AnnotateCommentsDto;
import com.xo.web.mgr.BaseLogic;
import com.xo.web.models.dao.UserDAO;
import com.xo.web.models.dao.UserDAOImpl;
import com.xo.web.models.system.User;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoMailSender;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.MailDto;

public class AnnotateCommentsLogic extends BaseLogic<AnnotateComments, Integer> {
	
	private final AnnotateCommentsDao AnnotateCommentsDAO;
	private final UserDAO userDAO;
	
	public AnnotateCommentsLogic() {
		super(new AnnotateCommentsDaoImpl());
		this.AnnotateCommentsDAO = (AnnotateCommentsDao) entityDao;       
		this.userDAO = new UserDAOImpl();
	}

	public AnnotateComments save(AnnotateCommentsDto AnnotateCommentsDto) throws ParseException {
		AnnotateComments annotateComments = null;
		Date datetime=null;	
		if(AnnotateCommentsDto != null) {
			User user = userDAO.findByEmail(AnnotateCommentsDto.user);
			annotateComments = new AnnotateComments(AnnotateCommentsDto.annotateId,user,AnnotateCommentsDto.reportName,AnnotateCommentsDto.fieldName1,AnnotateCommentsDto.fieldName2,AnnotateCommentsDto.workbookName,AnnotateCommentsDto.status,AnnotateCommentsDto.comment);
			annotateComments = this.AnnotateCommentsDAO.save(annotateComments);
		}
		return annotateComments;
	}
	
	

}
