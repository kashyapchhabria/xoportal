package com.xo.web.ext.chat.mgr;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import sun.misc.BASE64Encoder;

import com.xo.web.core.XODAOException;
import com.xo.web.ext.chat.models.Chat;
import com.xo.web.ext.chat.models.ChatRoom;
import com.xo.web.ext.chat.models.dao.ChatDAO;
import com.xo.web.ext.chat.models.dao.ChatDAOImpl;
import com.xo.web.ext.chat.models.dao.ChatRoomDAO;
import com.xo.web.ext.chat.models.dao.ChatRoomDAOImpl;
import com.xo.web.ext.chat.viewdtos.ChatDto;
import com.xo.web.ext.chat.viewdtos.ChatRoomDTO;
import com.xo.web.mgr.BaseLogic;
import com.xo.web.models.dao.UserDAO;
import com.xo.web.models.dao.UserDAOImpl;
import com.xo.web.models.system.User;
import com.xo.web.util.XoUtil;

public class ChatLogic  extends BaseLogic<Chat, Integer>{

	private final ChatDAO chatDAO;
	private final ChatRoomDAO chatroomDAO;
	private final UserDAO userDAO;
	
 	public ChatLogic() {
		super(new ChatDAOImpl());
		this.chatDAO = (ChatDAO) entityDao;       
		this.chatroomDAO = new ChatRoomDAOImpl();
		this.userDAO = new UserDAOImpl();
	}
 	

	public Set<ChatRoomDTO> findAllRooms() {
		Collection<ChatRoom> allRooms = this.chatroomDAO.findAll();
		return convertEntitiesToDtos(allRooms);
	}

	private Set<ChatRoomDTO> convertEntitiesToDtos(Collection<ChatRoom> allRooms) {
		Set<ChatRoomDTO> chatroomDtos = new HashSet<ChatRoomDTO>();
		if(XoUtil.hasData(allRooms)) {
			for(ChatRoom chatRoom : allRooms) {
				chatroomDtos.add(new ChatRoomDTO(chatRoom));				
			}
		}
		return chatroomDtos;
	}

	public Set<ChatDto> findChatsByRoom(Integer roomid) {
		Collection<Chat> allChats=null;
		try {
			allChats = this.chatDAO.findAllChatsByRoom(roomid);
		} catch (XODAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertChatEntitiesToDtos(allChats);
	}
	
	public Set<ChatDto> limitChatsByRoom(Integer roomid,Integer rowLimit) {
		List<Object[]> allChats=null;
		try {
			allChats = this.chatDAO.limitChatsByRoom(roomid,rowLimit);
		} catch (XODAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return convertChatEntitiesToDtos(allChats);
	}

	private Set<ChatDto> convertChatEntitiesToDtos(Collection<Chat> allChats) {
		Set<ChatDto> chatDtos = new HashSet<ChatDto>();
		if(XoUtil.hasData(allChats)) {
			for(Chat chat : allChats) {		
				chatDtos.add(new ChatDto(chat.getMessageId(),chat.getChatroom().getChatroomId(),chat.getMessage(),chat.getTs(),chat.getType(),chat.getUser().getFirstName(),chat.getImagedata()));				
			}
		}
		return chatDtos;
	}
		
	private Set<ChatDto> convertChatEntitiesToDtos(List<Object[]> allChats) {
		Set<ChatDto> chatDtos = new HashSet<ChatDto>();
		if(XoUtil.hasData(allChats)) {
			for(Object[] chat : allChats) {		
				int colIndex = -1;
				Integer chatroomid=(Integer)chat[++colIndex];
				Integer messageId = (Integer)chat[++colIndex];
				String message = (String)chat[++colIndex];
				byte[] imagedata = (byte[])chat[++colIndex];
				Date ts = (Date) chat[++colIndex];
				String type = chat[++colIndex].toString();
				String user = (String)chat[++colIndex];
				
				ChatDto chatDto = new ChatDto(messageId,chatroomid,message,ts,type,user,imagedata);
				chatDtos.add(chatDto);
			}
		}
		return chatDtos;
	}


	public Chat save(ChatDto chatDto) throws XODAOException, ParseException {
		Chat chat = null;
		Date datetime=null;	
		byte[] image=null;
		if(chatDto != null) {
			ChatRoom chatroom = chatroomDAO.find(chatDto.chatroom);
			User user = userDAO.findByEmail(chatDto.user);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(chatDto.imagedata!=null){
				image=chatDto.imagedata;
			}
			datetime =df.parse(chatDto.ts);			
			chat = new Chat(chatroom,chatDto.message,chatDto.type,image,datetime,user);
			chat = this.chatDAO.save(chat);
		}
		return chat;
	}

}
