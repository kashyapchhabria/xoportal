package com.xo.web.ext.chat.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import play.Logger;
import play.i18n.Messages;
import play.libs.EventSource;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;
import java.awt.image.BufferedImage;
import java.io.IOException;
import sun.misc.BASE64Decoder;
import com.fasterxml.jackson.databind.JsonNode;
import com.xo.web.controllers.BaseController;
import com.xo.web.core.XODAOException;
import com.xo.web.core.XOException;
import com.xo.web.ext.chat.ChatI18NLabels;
import com.xo.web.ext.chat.mgr.ChatLogic;
import com.xo.web.ext.chat.models.Chat;
import com.xo.web.ext.chat.viewdtos.ChatDto;

public class ChatController extends BaseController<Chat, Integer> implements ChatI18NLabels{

  private final ChatLogic chatLogic;
  
  public ChatController() {
    super(new ChatLogic());
    this.chatLogic = (ChatLogic) this.entityLogic;
  }
  
  /** Keeps track of all connected browsers per room **/
  private static Map<String, List<EventSource>> socketsPerRoom = new HashMap<String, List<EventSource>>();

  public  Result postMessage() {
	  JsonNode json = request().body().asJson();
		JsonNode jsonResponse = null;
		if (json == null) {
			Logger.error(BAD_REQUEST_UNKNOWN_DATA);
			return badRequest(generateErrorResponse(BAD_REQUEST_UNKNOWN_DATA));
		}
		try {
			ChatDto chatDto = Json.fromJson(json, ChatDto.class);
			Chat chat =this.chatLogic.save(chatDto);
			ChatDto chatdtocontent=new ChatDto(chat.getMessageId(),chat.getChatroom().getChatroomId(),chat.getMessage(),chat.getTs(),chat.getType(),chat.getUser().getFirstName(),chat.getImagedata());
			jsonResponse = generateSuccessResponse(Messages.get(MSG_SAVED_SUCCESSFULLY),chatdtocontent);
			sendEvent(request().body().asJson());
		}catch (XODAOException e) {
			Logger.error("Error while udpating the User details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_MESSAGE));
			throw new XOException(e);
		}catch(Exception e) {
			Logger.error("Error while udpating the User details.", e);
			jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_MESSAGE));
			throw new XOException(e);
		} finally{			
			return ok(jsonResponse);
		}
      }
  @BodyParser.Of(value = BodyParser.Json.class, maxLength = 10000 * 1024)
  public Result saveImg() throws IOException {
	  JsonNode json = request().body().asJson();
	  JsonNode jsonResponse = null;
	  ChatDto chatDto = Json.fromJson(json.get("chatdto"), ChatDto.class);
	  String imgdata=json.get("imgdata").toString();
	  String dataurl=imgdata.split(",")[1];
	   BufferedImage image = null;
	   byte[] imageByte;
	   BASE64Decoder decoder = new BASE64Decoder();
	   imageByte = decoder.decodeBuffer(dataurl);
	   chatDto.imagedata=imageByte;
	   try {
		Chat chat =this.chatLogic.save(chatDto);
		ChatDto chatdtocontent=new ChatDto(chat.getMessageId(),chat.getChatroom().getChatroomId(),chat.getMessage(),chat.getTs(),chat.getType(),chat.getUser().getFirstName(),chat.getImagedata());
		jsonResponse = generateSuccessResponse(Messages.get(MSG_SAVED_SUCCESSFULLY),chatdtocontent);
	} catch (XODAOException e) {
		Logger.error("Error while udpating the User details.", e);
		jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_MESSAGE));
		throw new XOException(e);
	}catch(Exception e) {
		Logger.error("Error while udpating the User details.", e);
		jsonResponse = generateErrorResponse(Messages.get(ERR_SAVING_MESSAGE));
		throw new XOException(e);
	} finally{			
		return ok(jsonResponse);
	}
  }
  
  public Result readAllRooms() {
    JsonNode jsonResponse = null;
    try {
      jsonResponse = Json.toJson(this.chatLogic.findAllRooms());    
    } catch (Exception e) {
      jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_ROOM));
      throw new XOException(e);
    } finally{
      return ok(jsonResponse);
    }
  }


  public Result readAllChats(Integer roomid) {
    JsonNode jsonResponse = null;
    try {
      jsonResponse = Json.toJson(this.chatLogic.findChatsByRoom(roomid));    
    } catch (Exception e) {
      jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_ROOM));
      throw new XOException(e);
    } finally{
      return ok(jsonResponse);
    }
  }

  public Result limitChats(Integer roomid,Integer rowLimit) {
	    JsonNode jsonResponse = null;
	    try {
	      jsonResponse = Json.toJson(this.chatLogic.limitChatsByRoom(roomid,rowLimit));    
	    } catch (Exception e) {
	      jsonResponse = generateErrorResponse(Messages.get(ERR_UNABLE_TO_LOAD_ROOM));
	      throw new XOException(e);
	    } finally{
	      return ok(jsonResponse);
	    }
	  }
  
  /**
   * Send event to all channels (browsers) which are connected to the room
   */
  public static void sendEvent(JsonNode msg) {
    String room  = msg.findPath("chatroom").asText();
    if(socketsPerRoom.containsKey(room)) {
      socketsPerRoom.get(room).stream().forEach(es -> es.send(EventSource.Event.event(msg)));
    }
  }

  /**
   * Establish the HTTP 1.1 connection.
   * The new EventSource socket is stored in the socketsPerRoom Map
   * to keep track of which browser is in which room.
   *
   * onDisconnected removes the browser from the socketsPerRoom Map if the
   * browser window has been exited.
   * @return
   */
  public  Result chatFeed(final String room) {
    String remoteAddress = request().remoteAddress();
    Logger.info(remoteAddress + " : Chat room :" + room + " - conntected");

    return ok(new EventSource() {
      @Override
      public void onConnected() {
        EventSource currentSocket = this;

        this.onDisconnected(() -> {
          Logger.info(remoteAddress + " : Chat room :" + room + " - disConntected");
          socketsPerRoom.compute(room, (key, value) -> {
            if(value.contains(currentSocket)) {
              value.remove(currentSocket);
              Logger.info(remoteAddress + " : Chat room :" + key + " - disConntected");
            }
            return value;
          });
        });

        // Add socket to room
        socketsPerRoom.compute(room, (key, value) -> {
          if(value == null)
            return new ArrayList<EventSource>() {{ add(currentSocket); }};
          else
            value.add(currentSocket); 
          return value;
        });
      }
    });
  }
}
