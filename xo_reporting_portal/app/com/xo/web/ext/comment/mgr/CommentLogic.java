package com.xo.web.ext.comment.mgr;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Set;

import com.xo.web.core.XODAOException;
import com.xo.web.ext.comment.models.Comment;
import com.xo.web.ext.comment.models.CommentDao;
import com.xo.web.ext.comment.models.CommentDaoImpl;
import com.xo.web.ext.comment.viewdtos.CommentDto;
import com.xo.web.mgr.BaseLogic;
import com.xo.web.models.dao.UserDAO;
import com.xo.web.models.dao.UserDAOImpl;
import com.xo.web.models.system.User;
import com.xo.web.util.XoAppConfigKeys;
import com.xo.web.util.XoMailSender;
import com.xo.web.util.XoUtil;
import com.xo.web.viewdtos.MailDto;

public class CommentLogic extends BaseLogic<Comment, Integer> {
	
	private final CommentDao commentDAO;
	private final UserDAO userDAO;
	
	public CommentLogic() {
		super(new CommentDaoImpl());
		this.commentDAO = (CommentDao) entityDao;       
		this.userDAO = new UserDAOImpl();
	}
	
	public Set<CommentDto> readAllComments() {
		Collection<Comment> allComments=null;
		try {
			allComments = this.commentDAO.readAllComments();
		} catch (XODAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertChatEntitiesToDtos(allComments);
	}
	
	public Set<CommentDto> readSheetComments(String sheetName, String dashboardName) {
		Collection<Comment> sheetComments=null;
		try {
			sheetComments = this.commentDAO.readSheetComments(sheetName,dashboardName);
		} catch (XODAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertChatEntitiesToDtos(sheetComments);
	}

	public Comment save(CommentDto commentDto) throws ParseException {
		Comment comment = null;
		Date datetime=null;	
		if(commentDto != null) {
			User user = userDAO.findByEmail(commentDto.user);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(commentDto.ts);
			datetime =(Date) df.parse(df.format(new Date(Long.parseLong(commentDto.ts))));
			comment = new Comment(commentDto.message,datetime,user,commentDto.sheetName,commentDto.dashboardName);
			comment = this.commentDAO.save(comment);
		}
		return comment;
	}
	
	private Set<CommentDto> convertChatEntitiesToDtos(Collection<Comment> allComments) {
		Set<CommentDto> commentDtos = new HashSet<CommentDto>();
		if(XoUtil.hasData(allComments)) {
			for(Comment comment : allComments) {		
				commentDtos.add(new CommentDto(comment.getMessageId(),comment.getMessage(),comment.getTs(),comment.getUser().getFirstName(),comment.getSheetName(),comment.getDashboardName()));				
			}
		}
		return commentDtos;
	}
	
	@SuppressWarnings("unchecked")
	public void sendEmailToDev(Comment comment) {
		if(!comment.getUser().getEmail().contains("@xo.com")) {
			List<String> emailList = (List<String>) XoUtil.getConfigsAsList(XoAppConfigKeys.EMAIL_LIST);
			String[] emails = emailList.toArray(new String[0]);
			String emailBody = "Comment From: "+comment.getUser().getEmail()+"<br>Comment: "+comment.getMessage();
			String emailSubject = "New Comment Recieved";
			MailDto mailDto = new MailDto(emailBody,emailSubject,emails);
			new XoMailSender(mailDto);
		}
	}

}
