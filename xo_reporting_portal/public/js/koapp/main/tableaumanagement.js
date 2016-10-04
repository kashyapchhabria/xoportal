/**
 * Tableau management
 */
define(['knockout', 'jquery'], function(ko, $) {

    function TableauManagerModel() {

        BaseModel.call(this, ko, $);
        var viz = null;
        self.dashboardData = ko.observableArray([]);
        self.breadcrumbData = ko.observableArray([]);
        self.menuData = ko.observableArray([]);
        self.imageUrl = ko.observable('');
        self.placeHolderImageUrl = ko.observable('');
        self.errorText = ko.observable('');
        self.isFullScreenEnabled = ko.observable(false);
        self.isFullScreenAvailable = ko.observable(false);
        self.previousOverFlowValue = null;

        self.visibility = ko.observable(false);
        var workbook = null;
        var activeSheet = null;
        self.allClients = ko.observableArray([]);
        self.selectedSupClient = ko.observable();
        self.isMainDashboard = ko.observable(false);

        self.rooms = ko.observableArray([]);
        self.msgs = ko.observableArray([]);
        self.inputText = ko.observable("");
        self.user = ko.observable(xoappusername);
        self.className = ko.observable();
        self.currentRoom = ko.observable();
        self.chatChannelmsgs = ko.observableArray([]);
        self.imgDataUrl = ko.observable($('#imgtextarea').val());
        self.enlargeImg = ko.observable('');
        self.lastMsgId = ko.observable();
        self.msgCount = ko.observable('10');

        self.setChatRooms = function() {
            $.ajax({
                'url': xoappcontext + '/chatrooms',
                'type': 'GET',
                'cache': false,
                'success': function(responseData) {
                    for (i = 0; i < responseData.length; i++) {
                        self.rooms.push(responseData[i]);
                    }
                },
                'error': function(jqXHR, textStatus, errorThrown) {
                    setGlobalMessage({
                        message: textStatus,
                        messageType: 'alert'
                    }, "general");
                }
            });
        }

        self.setCurrentRoom = function(room) {
            if (self.chatFeed != undefined)
                self.chatFeed.close();
            self.msgs.removeAll();

        };

        self.submitMsg = function() {
        		$(".se-pre-con").show(true);
            var today = new Date();
            var date = today.toISOString().substr(0, 10);
            var time = today.toISOString().substr(11, 8);
            var ts = date + " " + time
            chatContent = {
                chatroom: self.currentRoom(),
                message: self.inputText(),
                ts: ts,
                type: 'text',
                user: self.user()
            };
            data = JSON.stringify(chatContent);
            $.ajax({
                'url': xoappcontext + '/chat',
                'type': 'POST',
                'cache': false,
                'data': data,
                'contentType': "application/json; charset=utf-8",
                'success': function(responseData) {
                    setGlobalMessage(responseData, "general");
                    $('.chat-history').scrollTop($('.chat-history')[0].scrollHeight);
                    self.lastMsgId(responseData.resultobject.messageId);
                    $(".se-pre-con").fadeOut("slow");
                },
                'error': function(jqXHR, textStatus, errorThrown) {
                    setGlobalMessage({
                        message: textStatus,
                        messageType: 'alert'
                    }, "general");
                }
            });
            self.inputText("");
        };


        self.storeImage = function() {
        	if($('#imgtextarea').val() != "") {
        		$(".se-pre-con").show(true);
            var today = new Date();
            var date = today.toISOString().substr(0, 10);
            var time = today.toISOString().substr(11, 8);
            var ts = date + " " + time
            var dataurl = $('#imgtextarea').val();
            chatContent = {
                chatroom: self.currentRoom(),
                ts: ts,
                type: 'image',
                user: self.user()
            };
            imgContent = {
                'chatdto': chatContent,
                'imgdata': dataurl
            };
            data = JSON.stringify(imgContent);

            $.ajax({
                'url': xoappcontext + '/chatDashboardImg',
                'type': 'POST',
                'data': data,
                'cache': false,
                'contentType': "application/json; charset=utf-8",
                'success': function(responsedata) {
                    console.log("success");
                    setGlobalMessage(responsedata, "general");
                    self.lastMsgId(responsedata.resultobject.messageId);
                    self.addMsg(responsedata.resultobject);
                    $('.chat-history').scrollTop($('.chat-history')[0].scrollHeight);
                    $("#screenShotModal").foundation('reveal', 'close');
                    $("#screenShotModal").foundation('reveal', 'reflow');
                    $('#pasteImg').css("background-image","none");
                    $(".se-pre-con").fadeOut("slow");
                },
                'error': function(jqXHR, textStatus, errorThrown) {
                    setGlobalMessage({
                        message: textStatus,
                        messageType: 'alert'
                    }, "general");
                }
            });
					}
					if($('#imgtextarea').val() == "") {
						alert("Kindly paste an image in the given area and then click \"Send Message\".");
					}
        }

        self.formatMsgData = function(msgdata,type) {
            usr=msgdata.user.toString().split("@")[0];       
	        	var messages= {user:usr,ts:msgdata.ts,text:msgdata.message,encodedImgData:msgdata.encodedImgData};
	        	self.chatChannelmsgs.push(messages);

        }

        /** handle incoming messages: add to messages array */
        self.addMsg = function(msg) {
            var msgdata = msg;
            if (msg.data != undefined)
                msgdata = JSON.parse(msg.data);
            self.formatMsgData(msgdata);
        };

        self.listen = function(chatroomId) {
            if (chatroomId != undefined) {
                self.chatFeed = new EventSource(xoappcontext + "/chatFeed/" + chatroomId);
                self.chatFeed.addEventListener("message", self.addMsg, false);
            }
        };

        self.showImage = function(event) {
            self.enlargeImg(event.encodedImgData);
            //$("#showImg").attr("src",$(event.encodedImgData));
            $('#viewImageModal').foundation('reveal', 'open');
        }
        
        $('.chat-history').scroll(function(data,event){
    				if ($('.chat-history').scrollTop() == 0){
    						self.msgCount(self.msgCount()+10);
    						loadMessages(self.currentRoom(),self.msgCount(),"add");
    				}
    		});
    		
    		function changeClass(add, del1, del2) {
    				if ($('#seg-title').hasClass(del1)) {
            		$('#seg-title').removeClass(del1);
            }
            if ($('#seg-title').hasClass(del2)) {
            		$('#seg-title').removeClass(del2);
            }
            $('#seg-title').addClass(add);
    		}
    		
    		function loadMessages(chatroomid, count, type){
    				self.msgCount(count);
    				$(".se-pre-con").show(true);
            if (chatroomid) {
                // get top 10 history msg
                $.ajax({
                    'url': xoappcontext + '/chathistory/' + chatroomid + '/' + count,
                    'type': 'GET',
                    'cache': false,
                    'contentType': "application/json; charset=utf-8",
                    'success': function(chathistory) {
                        if (chathistory) {
                            chathistory.sort(function(a, b) {
                                return a.messageId - b.messageId;
                            });

                            self.chatChannelmsgs.removeAll();
                            for (i = 0; i < chathistory.length; i++) {
                                self.formatMsgData(chathistory[i]);
                            }
                        }
                        if (chatroomid == 1) {
                            changeClass("all","a1","y1");
                            self.className("All Segments");
                        }
                        if (chatroomid == 2) {
                            changeClass("a1","all","y1");
                            self.className("Segment A1");
                        }
                        if (chatroomid == 3) {
                            changeClass("y1","a1","all");
                            self.className("Segment Y1");
                        }
                        setGlobalMessage(chathistory, "general");
                        if(type == "subscribe")
                        		$('.chat-history').scrollTop($('.chat-history')[0].scrollHeight);
                        $(".se-pre-con").fadeOut("slow");
                    },
                    'error': function(jqXHR, textStatus, errorThrown) {
                        setGlobalMessage({
                            message: textStatus,
                            messageType: 'alert'
                        }, "general");
                        $(".se-pre-con").fadeOut("slow");
                    }
                });
                self.setCurrentRoom(chatroomid);
                self.listen(chatroomid);
            }
    		}
    		
        self.currentRoom.subscribe(function(chatroomid) {
        		//alert("caller is " + arguments.callee.caller.toString());
        		if(chatroomid)
			            loadMessages(chatroomid,10,"subscribe");
        });

        self.selectedSupClient.subscribe(function(latestClient) {
            self.loadDashboardData(latestClient);
            self.fullScreenView();
            return true;
        });

        self.loadDashboardData = function(latestClient) {
            self.isFullScreenAvailable(true);
            $(".se-pre-con").show(true);
            $.ajax({
                'url': xoappcontext + '/dashboard',
                'type': 'POST',
                'cache': false,
                headers: {
                    'X-Super-Client': latestClient ? latestClient.clientId : -1
                },
                'success': function(responsedata) {
                    if (responsedata) {
                        self.buildDashboardData(responsedata, true);
                    }
                    $(".se-pre-con").fadeOut("slow");

                },
                'error': function(jqXHR, textStatus, errorThrown) {
                    setGlobalMessage({
                        message: textStatus,
                        messageType: 'alert'
                    }, "general");
                    $(".se-pre-con").fadeOut("slow");
                }
            });
        };

        self.loadPageData = function(data, event) {

            $(".se-pre-con").show(true);
            if (data && event) {
                $.ajax({
                    'url': data.pageUrl,
                    'type': 'POST',
                    'cache': false,
                    'success': function(responsedata) {
                        if (responsedata) {
                            self.buildDashboardData(responsedata, false);
                        } else {
                            setGlobalMessage(responsedata, "general");
                        }
                        $(".se-pre-con").fadeOut("slow");
                    },
                    'error': function(jqXHR, textStatus, errorThrown) {
                        setGlobalMessage({
                            message: textStatus,
                            messageType: 'alert'
                        }, "general");
                        $(".se-pre-con").fadeOut("slow");
                    }
                });
            }
        };

        self.buildDashboardData = function(responsedata, isNewMenus) {

            var dashboardItems = [];
            var breadcrumbItems = [];
            self.dashboardData([]);
            if (isNewMenus) {
                self.menuData([]);
            }

            self.imageUrl(responsedata.imageUrl);
            self.isMainDashboard(responsedata.isMainDashboard);
            self.renderTableauReport(responsedata);
            self.placeHolderImageUrl(responsedata.placeHolderImageUrl);
            self.errorText(responsedata.errorText);

            // Processing breadcrumbs
            if (responsedata.breadCrumbDtos) {
                totalItems = responsedata.breadCrumbDtos.length;
                var breadcrumbItem = null;
                i = 0;
                for (; i < totalItems; i++) {

                    breadcrumbItem = responsedata.breadCrumbDtos[i];
                    self.setloaderMethod(breadcrumbItem);
                    breadcrumbItems.push(breadcrumbItem);
                }
                if (i > 0) {
                    breadcrumbItems[i - 1].active = 'current'
                }
                //var activeMenuItem = breadcrumbItems[i-1].pageUrl;
            }

            // Processing page content items
            if (responsedata.contentDtos) {
                var totalItems = responsedata.contentDtos.length;
                var dashboardItem = null;
                var i = 0;
                for (; i < totalItems; i++) {
                    dashboardItem = responsedata.contentDtos[i];
                    self.setloaderMethod(dashboardItem);
                    dashboardItems.push(dashboardItem);
                }
            }

            // Setting all screen items
            if (isNewMenus) {
                self.menuData(self.buildMenus(responsedata));
                /*self.menuData.sort(function(left, right) {
                		return left.name == right.name ? 0 : (left.name < right.name ? -1 : 1)
                	});*/
            }
            self.breadcrumbData(breadcrumbItems);
            self.dashboardData(dashboardItems);

            $(document).foundation('reflow');
            $(document).foundation();
        };

        self.buildMenus = function(responsedata) {

            var menus = [];
            // Processing menu items
            if (responsedata.menuDtos) {
                totalItems = responsedata.menuDtos.length;
                if (totalItems > 0) {
                    var menuIndex = 0;
                    var actualMenus = responsedata.menuDtos[0].subMenus;
                    totalItems = actualMenus.length;
                    for (; menuIndex < totalItems; menuIndex++) {
                        var menuItem = self.buildMenuItem(actualMenus[menuIndex]);
                        menus.push(menuItem);
                    }
                }
            }
            return menus;
        };

        self.buildMenuItem = function(menuItem) {

            /*if(console) {
            	console.log('menu name : ' + menuItem.name);
            }*/
            // Processing menu items
            var totalItems = menuItem.subMenus.length;
            var subMenuItems = [];
            menuItem.subMenuItems = ko.observableArray([]);
            self.setloaderMethod(menuItem);
            var submenuIndex = 0;
            for (; submenuIndex < totalItems; submenuIndex++) {
                var subMenuItem = menuItem.subMenus[submenuIndex];
                subMenuItems.push(self.buildMenuItem(subMenuItem));
            }
            menuItem.subMenuItems(subMenuItems);
            /*menuItem.subMenuItems.sort(function(left, right) {
            	return left.name == right.name ? 0 : (left.name < right.name ? -1 : 1)
            });*/
            return menuItem;
        };

        self.setloaderMethod = function(contentItem) {
            if (contentItem.pageUrl.indexOf('project') > 0 || contentItem.pageUrl.indexOf('view') > 0) {
                contentItem.loadPageData = self.loadPageData;
            } else {
                contentItem.loadPageData = self.loadDashboardData;
            }
        };

        $(document).on('webkitfullscreenchange mozfullscreenchange fullscreenchange MSFullscreenChange', function() {

            if (
                document.fullscreenElement ||
                document.webkitFullscreenElement ||
                document.mozFullScreenElement ||
                document.msFullscreenElement
            ) {
                self.isFullScreenEnabled(true);
                $('#xoportalbody').css('overflow', 'auto');
            } else {
                self.isFullScreenEnabled(false);
                $('#xoportalbody').css('overflow', self.previousOverFlowValue);
            }
        });

        self.fullScreenView = function() {
            self.isFullScreenEnabled(true);
            self.previousOverFlowValue = $('#xoportalbody').css('overflow');
            showInFullScreen("xoportalbody");
            self.changeViewSize();
        };

        self.closeFullScreen = function() {
            exitFullScreen();
            if (activeSheet) {
                activeSheet.changeSizeAsync({
                    behavior: tableauSoftware.SheetSizeBehavior.AUTOMATIC
                });
            }
        };

        self.renderTableauReport = function(responseData) {
            if (responseData && responseData.imageUrl && responseData.imageUrl.length > 0) {
                if (viz) {
                    viz.dispose();
                }
                var placeholderDiv = document.getElementById("tableauViewPlace");
                var url = responseData.imageUrl;
                var options = {
                    width: "100%",
                    height: "100%",
                    hideTabs: true,
                    hideToolbar: true,
                    onFirstInteractive: function() {
                        workbook = viz.getWorkbook();
                        activeSheet = workbook.getActiveSheet();
                        /*activeSheet.changeSizeAsync({
                            behavior: tableauSoftware.SheetSizeBehavior.AUTOMATIC
                          });*/
                        self.changeViewSize();
                    }
                };
                viz = new tableau.Viz(placeholderDiv, url, options);
                //viz = new tableauSoftware.Viz(placeholderDiv, url, options);
            }
        };

        self.changeViewSize = function() {

            if (activeSheet) {
                // get SheetSize object
                var sizeInfo = activeSheet.getSize();
                var behavior = sizeInfo.behavior;
                var maxSize = sizeInfo.maxSize || "NA";
                var minSize = sizeInfo.minSize || "NA";

                var tempWidth = $('#tableauViewPlace').width();
                var tempHeight = $('#tableauViewPlace').height();
                if (maxSize != "NA") {
                    $('#tableauViewPlace').width(maxSize.width);
                    $('#tableauViewPlace').height(maxSize.height);
                }
                if (minSize != "NA") {
                    $('#tableauViewPlace').css("min-width", maxSize.width);
                    $('#tableauViewPlace').css("min-height", maxSize.height);
                }
                tempWidth = $('#tableauViewPlace').width();
                tempHeight = $('#tableauViewPlace').height();
                //viz.setFrameSize(tempWidth, tempHeight);
                // Create sheetSize options
                var sheetSize = {
                    behavior: tableauSoftware.SheetSizeBehavior.EXACTLY,
                    minSize: {
                        width: $('#tableauViewPlace').width(),
                        height: $('#tableauViewPlace').height()
                    },
                    maxSize: {
                        width: $('#tableauViewPlace').width(),
                        height: $('#tableauViewPlace').height()
                    }
                };
                // Resize sheet
                activeSheet.changeSizeAsync(sheetSize);
            }
        };

        self.clearAll = function() {
            self.menuData.removeAll();
            self.breadcrumbData.removeAll();
            self.dashboardData.removeAll();
            self.imageUrl('');
            self.placeHolderImageUrl('');
            self.isFullScreenAvailable(false);
            self.closeFullScreen();
            self.isFullScreenAvailable(false);
            self.rooms.removeAll();
            self.currentRoom('');
        		self.chatChannelmsgs.removeAll();
        		self.enlargeImg('');
        		self.msgCount('10');
        };

        self.loadClients = function() {
            $.ajax({
                'url': xoappcontext + '/clients',
                'type': 'GET',
                'cache': false,
                'success': function(serverResponse) {
                    self.buildClientDropDown(serverResponse);
                },
                'error': function(jqXHR, textStatus, errorThrown) {
                    setGlobalMessage({
                        message: textStatus,
                        messageType: 'alert'
                    }, "general");
                }
            });
        };

        self.buildClientDropDown = function(clientDetails) {
            var i = 0;
            var totalClients = clientDetails.length;
            self.allClients.removeAll();
            for (; i < totalClients; i++) {
                var clientobj = clientDetails[i];
                var tempObj = {
                    'clientId': clientobj.clientId,
                    'name': clientobj.clientName
                };
                self.allClients.push(tempObj);
            }
        };

        self.showReportMenus = function() {
            self.isMainDashboard(false);
        };

        return {
            clearAll: self.clearAll,
            loadDashboardData: self.loadDashboardData,
            dashboardData: self.dashboardData,
            breadcrumbData: self.breadcrumbData,
            menuData: self.menuData,
            currentPage: self.currentPage,
            visibility: self.visibility,
            imageUrl: self.imageUrl,
            placeHolderImageUrl: self.placeHolderImageUrl,
            errorText: self.errorText,
            fullScreenView: self.fullScreenView,
            isFullScreenEnabled: self.isFullScreenEnabled,
            closeFullScreen: self.closeFullScreen,
            isFullScreenAvailable: self.isFullScreenAvailable,
            allClients: self.allClients,
            selectedSupClient: self.selectedSupClient,
            loadClients: self.loadClients,
            isMainDashboard: self.isMainDashboard,
            showReportMenus: self.showReportMenus,
            submitMsg: self.submitMsg,
            currentRoom: self.currentRoom,
            msgs: self.chatChannelmsgs,
            inputText: self.inputText,
            rooms: self.rooms,
            user: self.user(),
            setChatRooms: self.setChatRooms,
            chatChannelmsgs: self.chatChannelmsgs,
            imgDataUrl: self.imgDataUrl,
            storeImage: self.storeImage,
            className: self.className(),
            showImage: self.showImage,
            enlargeImg: self.enlargeImg
        };
    }
    TableauManagerModel.prototype = new BaseModel(ko, $);
    TableauManagerModel.prototype.constructor = TableauManagerModel;
    return TableauManagerModel;
});