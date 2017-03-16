/**
 * Configuration management
 */
define([ 'knockout', 'jquery' ], function(ko, $) {

	function DiffusionManagerModel() {

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
        self.filterList = ko.observableArray([]);
        self.selectedFilters = ko.observableArray([]);
		self.selectedList = ko.observableArray([]);
		self.isAllSelected = ko.observable(true);
		self.msgs=ko.observableArray([]);
		self.inputText = ko.observable("");
		self.user=ko.observable(xoappusername);
		
        self.visibility = ko.observable(false);
        var workbook = null;
        var activeSheet = null;
        self.allClients = ko.observableArray([]);
        self.selectedSupClient = ko.observable();
        self.isTopBarVisibile = ko.observable(true);
        self.selectedReportMenuItem = ko.observable("Select Report");
        self.selectReport = {"name" : self.selectedReportMenuItem, "displayOrder":-1, "pageUrl": "", "subMenus" : []};
        self.array = ko.observableArray(["1Title","1BottomLeft","1BottomRight","1TopLeft","1TopRight","2Title","2BottomLeft","2BottomRight","2TopLeft","2TopRight","3Title","3BottomLeft","3BottomRight","3TopLeft","3TopRight","4Title","4BottomLeft","4BottomRight","4TopLeft","4TopRight","5Title","5BottomLeft","5BottomRight","5TopLeft","5TopRight","6Title","6BottomLeft","6BottomRight","6TopLeft","6TopRight"]);
        self.spendArray = ko.observableArray(["1","2","3","4","5","6"]);
        self.trendArray = ko.observableArray(["1","Diffus_Stats","TS1BottomLeft","TS1BottomRight","TS1TopLeft","TS1TopRight"]);
        
        self.isTableau = ko.observable(true);

        self.selectedSupClient.subscribe(function(latestClient) {
        	self.showDiffusionMap(latestClient);
            return true;
        });
        
        self.submitComment = function () {
        	if(self.inputText() === '') {
        		alert("Enter a comment and then click Comment button")
        	} else {
        		chatContent={ 
        				message: self.inputText(),        		
        				ts: new Date().getTime(),
        				user:self.user() 
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
        				self.msgs.push(tempObj);
        				self.inputText("")
        			},
        			'error' : function(jqXHR, textStatus, errorThrown) {
        				setGlobalMessage({message:textStatus, messageType:'alert'},"general");
        			}
        		});
        	}
        }
        
        self.openNav = function() {
        	document.getElementById("mySidenav").style.width = "400px";
        }
        
        self.closeNav = function() {
        	document.getElementById("mySidenav").style.width = "0";
        }
        
        self.getComments = function() {
        	$.ajax({
				'url': xoappcontext + '/allComments',
				'type': 'GET',
				'cache':false,
				'contentType': "application/json; charset=utf-8",
				'success' : function(responseData) {
					self.formatComments(responseData);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message:textStatus, messageType:'alert'},"general");
				}
			});
        }
        
        self.formatComments = function(allComments) {
        	var noOfComments = allComments.length;
        	for ( var i= noOfComments -1; i>=0; i--) {
        		var tempObj = {
        				message: allComments[i]['message'],        		
                        ts: allComments[i]['ts'],
                        user:allComments[i]['user']
        			};
        		self.msgs.push(tempObj);
        	}
        }
        
        self.showDiffusionMap = function (latestClient) {
        	$(".se-pre-con").show(true);
            $.ajax({
                'url': xoappcontext + '/diffusionMap',
                'type': 'POST',
                'cache': false,
                headers: {
                    'X-Super-Client': latestClient ? latestClient.clientId : -1
                },
                'success': function(responsedata) {
                    if (responsedata) {
                        self.buildDashboardData(responsedata, null);
                        self.getFilterList();
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
        
        self.getFilterList = function () {
        	 $.ajax({
                'url': xoappcontext + '/filterlist',
                'type': 'GET',
                'cache': false,
                'success': function(serverResponse) {
        			self.generateSubSgmtList(serverResponse);
                },
                'error': function(jqXHR, textStatus, errorThrown) {
                    setGlobalMessage({
                        message: textStatus,
                        messageType: 'alert'
                    }, "general");
                }
            });
        }
        
        self.generateSubSgmtList = function(subSgmtList) {
        	var subSgmt = subSgmtList.split(",");
        	var tempObj;
        	self.filterList([]);
        	for ( var i=0; i< subSgmt.length - 1; i++ ) {
        		if(i < 6) {
	        		tempObj = {
	        			name: subSgmt[i], 
	        			id: i, 
	        			isChecked: ko.observable(true),
	        			isDisabled: ko.observable(false)
	        		};
        		} else {
        			tempObj = {
	        			name: subSgmt[i], 
	        			id: i, 
	        			isChecked: ko.observable(false),
	        			isDisabled: ko.observable(true)
	        		};
        		}
        		tempObj.isChecked.subscribe(function(newVal) {
        			self.isMaxSelected();
        		});
        		if (tempObj.name !== '')
        			self.filterList.push(tempObj);
        	}
        }
        
        self.isMaxSelected = function() {
        	var maxSelection = 6;
        	var noSelected = 0;
        	for ( var i=0; i<self.filterList().length; i++ ) {
        		if (self.filterList()[i]['isChecked']()) {
        			noSelected++;
        		}
        	}
        	if (noSelected === maxSelection) {
        		for ( var i=0; i<self.filterList().length; i++ ) {
        			if (!self.filterList()[i]['isChecked']()) {
        				self.filterList()[i]['isDisabled'](true);
        			}
        		}
        	} else {
        		for ( var i=0; i<self.filterList().length; i++ ) {
        			if (!self.filterList()[i]['isChecked']()) {
        				self.filterList()[i]['isDisabled'](false);
        			}
        		}
        	}
        };
        
        self.cancelSelected = function() {
        	for(var i=0; i<self.filterList().length;i++) {
    			self.filterList()[i]['isChecked'](false);
    			self.filterList()[i]['isDisabled'](false);
        	}
        }

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
            if(isNewMenus !== null) {
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
            }

            $(document).foundation('reflow');
            $(document).foundation();
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
            self.menuData.removeAll();
            self.dashboardData.removeAll();
            self.imageUrl('');
            self.placeHolderImageUrl('');
            self.isFullScreenAvailable(false);
            self.closeFullScreen();
            self.isFullScreenAvailable(false);
        };

        self.loadClients = function(isTableau) {
            $.ajax({
                'url': xoappcontext + '/clients',
                'type': 'GET',
                'cache': false,
                'success': function(serverResponse) {
        			self.isTableau(isTableau);
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
        
        self.toggleClass = function () {
        	$(".dropdown2 dd ul").slideToggle('fast');
        }
        
        self.changeActiveSheet = function (sheetName) {
        	viz.getWorkbook().activateSheetAsync(sheetName);
        }
        
        self.getSelectedFilters = function () {
        	self.toggleClass();
        	views=viz.getWorkbook().getPublishedSheetsInfo();
        	//console.log(views);
        	self.selectedFilters([]);
        	for ( var i=0; i<self.filterList().length; i++ ) {
        		if (self.filterList()[i]['isChecked']() && !self.filterList()[i]['isDisabled']()) {
        			self.selectedFilters.push(self.filterList()[i]['name']);
        		}
        	}
        	for(var i=0; i<views.length;i++) {
        		if(views[i]['$0']['isActive']) {
        			if(views[i]['$0']['name'] === "Diffusion Map") 
        				self.applyDiffusionFilters();
        			else if(views[i]['$0']['name'] === "Spend Segment")
        				self.applySpendSegmentFilters();
        			else
        				self.applyTrendsensorFilters();
        		}
        	}
		}
		
		self.applyTrendsensorFilters = function() {
			sheet = viz.getWorkbook().getActiveSheet();
			worksheetArray = sheet.getWorksheets();
			if(self.selectedFilters().length==1)
				for(var i = 0; i < worksheetArray.length; i++) {
					worksheetArray[i].applyFilterAsync("Status", self.selectedFilters()[0], 'REPLACE');							
				}
			else
				alert("Select only 1 Option");
		}
		
		self.applySpendSegmentFilters = function() {
			sheet = viz.getWorkbook().getActiveSheet();
			worksheetArray = sheet.getWorksheets();
			j=0;
			for(var k = 0; k < self.selectedFilters().length; k++) {
				for(var i = 0; i < worksheetArray.length; i++) {
					if(worksheetArray[i].getName()==self.spendArray()[j]) {
						worksheetArray[i].applyFilterAsync("Status", self.selectedFilters()[k], 'REPLACE');	
						
					}
				}j++;
			}
				
			for(k = self.selectedFilters().length; k < 6; k++) {
				for(var i = 0; i < worksheetArray.length; i++) {
					if(worksheetArray[i].getName()==self.spendArray()[j]) {
						
						worksheetArray[i].applyFilterAsync("Status", null , 'REPLACE');	
					}
				}j++;
			}
		}
		
		self.applyDiffusionFilters = function () {
			sheet = viz.getWorkbook().getActiveSheet();
			worksheetArray = sheet.getWorksheets();
			//console.log(worksheetArray);
			for(var k = 0; k < self.selectedFilters().length; k++) {
				//alert(self.selectedFilters()[k]);
				j=k*5;
				for(var i = 0; i < worksheetArray.length; i++) {
					if(worksheetArray[i].getName()==self.array()[j] || worksheetArray[i].getName()==self.array()[j+1] || worksheetArray[i].getName()==self.array()[j+2] || worksheetArray[i].getName()==self.array()[j+3] || worksheetArray[i].getName()==self.array()[j+4])
						worksheetArray[i].applyFilterAsync("Status", self.selectedFilters()[k], 'REPLACE');	
				}
				
			}
				
			for(k = self.selectedFilters().length; k < 6; k++) {
				j=k*5;
				for(var i = 0; i < worksheetArray.length; i++) {
					if(worksheetArray[i].getName()==self.array()[j] || worksheetArray[i].getName()==self.array()[j+1] || worksheetArray[i].getName()==self.array()[j+2] || worksheetArray[i].getName()==self.array()[j+3] || worksheetArray[i].getName()==self.array()[j+4])
						worksheetArray[i].applyFilterAsync("Status", null , 'REPLACE');	
				}
				
			}
		}

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
            showDiffusionMap:self.showDiffusionMap,
            filterList:self.filterList,
            toggleClass:self.toggleClass,
            getSelectedFilters:self.getSelectedFilters,
            selectedFilters:self.selectedFilters,
            isAllSelected:self.isAllSelected,
            submitComment:self.submitComment,
            inputText:self.inputText,
            getComments:self.getComments,
            msgs:self.msgs,
            openNav:self.openNav,
            closeNav:self.closeNav,
            changeActiveSheet:self.changeActiveSheet,
            cancelSelected:self.cancelSelected
        };
    }
	
	DiffusionManagerModel.prototype = new BaseModel(ko, $);
	DiffusionManagerModel.prototype.constructor = DiffusionManagerModel;
	return DiffusionManagerModel;
});
