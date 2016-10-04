define(['knockout', 'jquery'], function(ko, $) {
	function ChatModel() {
		BaseModel.call(this, ko, $);
		var self = this;
		self.rooms=ko.observableArray([]);
		self.msgs=ko.observableArray([]);
		self.inputText = ko.observable("");
		self.user=ko.observable(xoappusername);
		self.currentRoom=ko.observable();
		self.chatChannelmsgs=ko.observableArray([]);
		
		self.setChatRooms=function(){
			$.ajax({
				'url': xoappcontext + '/chatrooms',
				'type': 'GET',
				'cache':false,
				'success' : function(responseData) {
					for(i=0; i<responseData.length; i++){
						self.rooms.push(responseData[i]);
					}
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message:textStatus, messageType:'alert'},"general");
				}
			});
		}

		self.setCurrentRoom = function (room) {
			if(self.chatFeed!=undefined)
            	self.chatFeed.close();
            self.msgs.removeAll();
			
        };

        self.submitMsg = function () {
        	chatContent={ 
        		chatroom: self.currentRoom(),
        		message: self.inputText(),        		
                ts: (new Date()).toUTCString(),
                user:self.user() 
                } ;
            data=JSON.stringify(chatContent);
	        $.ajax({
				'url': xoappcontext + '/chat',
				'type': 'POST',
				'cache':false,
				'data':data,
				'contentType': "application/json; charset=utf-8",
				'success' : function(responseData) {
					setGlobalMessage(responseData,"general");
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message:textStatus, messageType:'alert'},"general");
				}
			});
	    };

        self.formatMsgData = function(msgdata){
        	usr=msgdata.user.toString().split("@")[0];       
          var messages= {user:usr,ts:msgdata.ts,text:msgdata.message};
          self.chatChannelmsgs.push(messages);
            
        }
        
	     /** handle incoming messages: add to messages array */
        self.addMsg = function (msg) {
            var msgdata=JSON.parse(msg.data);
            self.formatMsgData(msgdata);
        };

        self.listen =function (chatroomId) {
        	if(chatroomId!=undefined){
	        	self.chatFeed = new EventSource(xoappcontext + "/chatFeed/" +chatroomId);
		        self.chatFeed.addEventListener("message", self.addMsg, false);
		    }
        };

	    self.currentRoom.subscribe(function (chatroomid){
	    	if(chatroomid){
	    		// get top 10 history msg 
	    		$.ajax({
					'url': xoappcontext + '/chathistory/'+chatroomid,
					'type': 'GET',
					'cache':false,
					'contentType': "application/json; charset=utf-8",
					'success' : function(chathistory) {
						if(chathistory){
							self.chatChannelmsgs.removeAll();
							for(i=0;i<chathistory.length;i++){
								self.formatMsgData(chathistory[i]);
							}					            
						}
						setGlobalMessage(chathistory,"general");
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({message:textStatus, messageType:'alert'},"general");
					}
				});
		    	self.setCurrentRoom(chatroomid);
		    	self.listen(chatroomid);
		    }
        });

	   //self.listen(self.currentRoom());

        return {
			submitMsg:self.submitMsg,
			currentRoom:self.currentRoom,
			msgs:self.chatChannelmsgs,
			inputText:self.inputText,
			rooms:self.rooms,
			user:self.user(),
			setChatRooms:self.setChatRooms,
			chatChannelmsgs:self.chatChannelmsgs
		};
	}
	ChatModel.prototype = new BaseModel(ko, $);
	ChatModel.prototype.constructor = ChatModel;
	return ChatModel;
});
