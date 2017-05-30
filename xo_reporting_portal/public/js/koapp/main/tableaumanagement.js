/**
 * Tableau management
 */
define(['knockout', 'jquery'], function(ko, $) {

    function TableauManagerModel() {

        BaseModel.call(this, ko, $);
        var viz = null;
        self.dashboardData = ko.observableArray([]);
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
        self.isTopBarVisibile = ko.observable(true);
        self.selectedReportMenuItem = ko.observable("Main Dashboard");
        self.selectReport = {"name" : self.selectedReportMenuItem, "displayOrder":-1, "pageUrl": "", "subMenus" : []};
        self.dashboardCommentHeading = ko.observable('Main Dashboard');
        self.commentText = ko.observable('');
        self.dashboardMsgs=ko.observableArray([]);

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

        	$('#preloader').show(true);
            if (data && event) {
            	self.selectedReportMenuItem(data.name);
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
                        $("#preloader").fadeOut("slow");
                    },
                    'error': function(jqXHR, textStatus, errorThrown) {
                        setGlobalMessage({
                            message: textStatus,
                            messageType: 'alert'
                        }, "general");
                        $("#preloader").fadeOut("slow");
                    }
                });
            }
        };

        self.buildDashboardData = function(responsedata, isNewMenus) {

            var dashboardItems = [];
            self.dashboardData([]);

            if (isNewMenus) {
                self.menuData([]);
            }

            self.imageUrl(responsedata.imageUrl);
            //self.isTopBarVisibile(responsedata.isMainDashboard);
            self.renderTableauReport(responsedata);
            self.placeHolderImageUrl(responsedata.placeHolderImageUrl);
            self.errorText(responsedata.errorText);

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
            self.dashboardData(dashboardItems);
        };

        self.buildMenus = function(responsedata) {

            var menus = [];
            var reportsMenu;
            // Processing menu items
            if (responsedata.menuDtos) {
                totalItems = responsedata.menuDtos.length;
                if (totalItems > 0) {
                    var menuIndex = 0;
                    var actualMenus = responsedata.menuDtos[0].subMenus;
                    actualMenus.unshift(self.selectReport);
                    totalItems = actualMenus.length;
                    for (; menuIndex < totalItems; menuIndex++) {
                        var menuItem = self.buildMenuItem(actualMenus[menuIndex]);
                        if(menuIndex == 0) {
                        	reportsMenu = menuItem;
                        	menus.push(menuItem);
                        } else {
                        	reportsMenu.subMenuItems.push(menuItem);
                        }
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
                    self.workSheet = undefined;
                }
                var placeholderDiv = document.getElementById("tableauViewPlace");
                var url = responseData.imageUrl;
                var options = {
                    width: "100%",
                    height: "100%",
                    hideTabs: true,
                    hideToolbar: true,
                    onFirstInteractive: function() {
                    	//self.setFilterValues();
                        workbook = viz.getWorkbook();
                        activeSheet = workbook.getActiveSheet();
                        /*activeSheet.changeSizeAsync({
                            //behavior: tableauSoftware.SheetSizeBehavior.AUTOMATIC,
                        	behavior: tableauSoftware.SheetSizeBehavior.EXACTLY,
                        	minSize: {
                                width: $('#tableauViewPlace').width(),
                                height: $('#tableauViewPlace').height()
                            },
                            maxSize: {
                                width: $('#tableauViewPlace').width(),
                                height: $('#tableauViewPlace').height()
                            }
                          });*/
                        self.changeViewSize();
                    }
                };
                viz = new tableau.Viz(placeholderDiv, url, options);
            }
        };

        self.setFilterValues = function() {
        	if(self.workSheet == undefined) {
        		var worksheetArray = viz.getWorkbook().getActiveSheet().getWorksheets();
        		for(var i = 0; i < worksheetArray.length; i++) {
        			if(worksheetArray[i].getName() == self.worksheetName) {
        				self.workSheet = worksheetArray[i]; 
        				self.workSheet.applyFilterAsync(self.filterField, client_logo_Id, 'REPLACE');
        			}
        		}
        	}
        }

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
                    //behavior: tableauSoftware.SheetSizeBehavior.AUTOMATIC,
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
        	self.workSheet = undefined;
            self.menuData.removeAll();
            self.dashboardData.removeAll();
            self.imageUrl('');
            self.placeHolderImageUrl('');
            self.isFullScreenAvailable(false);
            self.closeFullScreen();
            self.isFullScreenAvailable(false);
            self.selectedReportMenuItem("Select Report");
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
            self.isTopBarVisibile(!self.isTopBarVisibile());
            self.changeViewSize();
            $(document).foundation();
            $(document).foundation('reflow');
        };

		self.exportPdf = function() {
        	if(viz) {
        		viz.showExportPDFDialog(); 
        	}
        };

        self.openCommentNav = function() {
        	if( $("#commentDiv").width() === 0 ) {
        		document.getElementById("commentDiv").style.width = "400px";
        	} else {
        		document.getElementById("commentDiv").style.width = "0px";
        	}
        }
        
        self.closeCommentNav = function() {
        	document.getElementById("commentDiv").style.width = "0px";
        }
        
        self.submitDashboardComment = function() {
        	var dashboardName='MainDashboard';
        	if(self.commentText() === '') {
        		alert("Enter a comment and then click Comment button")
        	} else {
        		//sheet = viz.getWorkbook().getActiveSheet();
        		//alert(sheet.getName());
        		chatContent={ 
        				message: self.commentText(),        		
        				ts: new Date().getTime(),
        				user:self.user(),
        				sheetName: self.selectedReportMenuItem(),
        				dashboardName: dashboardName
        		} ;
        		data=JSON.stringify(chatContent);
        		$.ajax({
        			'url': xoappcontext + '/comment',
        			'type': 'POST',
        			'cache':false,
        			'data':data,
        			'contentType': "application/json; charset=utf-8",
        			'success' : function(responseData) {
        				var tempObj = {
        						message: responseData.resultobject['message'],        		
        						ts: responseData.resultobject['ts'],
        						user:responseData.resultobject['user']
        				};
        				self.dashboardMsgs.push(tempObj);
        				self.commentText("")
        			},
        			'error' : function(jqXHR, textStatus, errorThrown) {
        				setGlobalMessage({message:textStatus, messageType:'alert'},"general");
        			}
        		});
        	}
        }
        
        self.getDashboardComments = function() {
        	var dashboardName='/MainDashboard';
        	$.ajax({
				'url': xoappcontext + '/sheetComments/'+self.selectedReportMenuItem()+dashboardName,
				'type': 'GET',
				'cache':false,
				'contentType': "application/json; charset=utf-8",
				'success' : function(responseData) {
					self.formatDashboardComments(responseData);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message:textStatus, messageType:'alert'},"general");
				}
			});
        }
        
        self.formatDashboardComments = function(allComments) {
        	var noOfComments = allComments.length;
			allComments.sort(function(a, b) {
				return b.messageId - a.messageId;
			});
			self.dashboardMsgs.removeAll();
        	for ( var i= noOfComments -1; i>=0; i--) {
        		var tempObj = {
        				message: allComments[i]['message'],        		
                        ts: allComments[i]['ts'],
                        user:allComments[i]['user']
        			};
        		self.dashboardMsgs.push(tempObj);
        	}
        }
        
        self.selectedReportMenuItem.subscribe(function(newVal) {
        	self.getDashboardComments();
        	self.dashboardCommentHeading(newVal);
        });

        return {
            clearAll: self.clearAll,
            loadDashboardData: self.loadDashboardData,
            dashboardData: self.dashboardData,
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
            isTopBarVisibile: self.isTopBarVisibile,
            showReportMenus: self.showReportMenus,
            openCommentNav:self.openCommentNav,
            closeCommentNav:self.closeCommentNav,
            dashboardCommentHeading:self.dashboardCommentHeading,
            commentText:self.commentText,
            submitDashboardComment:self.submitDashboardComment,
            dashboardMsgs:self.dashboardMsgs,
            getDashboardComments:self.getDashboardComments,
            exportPdf : self.exportPdf,
            selectedReportMenuItem:self.selectedReportMenuItem
        };
    }
    TableauManagerModel.prototype = new BaseModel(ko, $);
    TableauManagerModel.prototype.constructor = TableauManagerModel;
    return TableauManagerModel;
});