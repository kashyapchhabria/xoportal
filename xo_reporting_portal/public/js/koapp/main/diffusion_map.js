/**
 * Configuration management
 */
define([ 'knockout', 'jquery' ], function(ko, $) {

	function DiffusionManagerModel() {

        BaseModel.call(this, ko, $);
        var viz = null;
        self.clientLogoImageUrl = ko.observable(xoappcontext + '/assets/images/' + xoappclient + '_logo.png');
        self.imageUrl = ko.observable('');
        self.errorText = ko.observable('');
        self.isFullScreenEnabled = ko.observable(false);
        self.isFullScreenAvailable = ko.observable(false);
        self.previousOverFlowValue = null;
        self.filterList = ko.observableArray([]);
        self.selectedFilters = ko.observableArray([]);
        self.selectedDiffFilters = ko.observableArray([]);
        self.selectedSpendFilters = ko.observableArray([]);
        self.selectedTrendFilters = ko.observableArray([]);
		self.selectedList = ko.observableArray([]);
		self.isAllSelected = ko.observable(true);
		self.msgs=ko.observableArray([]);
		self.inputText = ko.observable("");
		self.user=ko.observable(xoappusername);
		self.activeSheet = ko.observable(diff_map);
		self.maxSel = ko.observable(sel_vas);
		self.showSelect= ko.observable(true);
		self.dashboardCommentHeading = ko.observable(diff_map);
		self.prevSelected = ko.observable(diff_map);
		
		self.selTop = ko.observableArray(["*"]);
        self.selLifetime = ko.observableArray(["*"]);
        self.selDataArpu = ko.observableArray(["*"]);
        self.selVasPlan = ko.observableArray(["*"]);
        self.selDate = ko.observable("2017-03");
        self.selRegion = ko.observableArray(["*"]);
        
        self.visibility = ko.observable(false);
        var workbook = null;
        var activeSheet = null;
        self.isTopBarVisibile = ko.observable(true);
        self.userGuide = ko.observable(false);
        self.array = ko.observableArray(["1Title","1BottomLeft","1BottomRight","1TopLeft","1TopRight","2Title","2BottomLeft","2BottomRight","2TopLeft","2TopRight","3Title","3BottomLeft","3BottomRight","3TopLeft","3TopRight","4Title","4BottomLeft","4BottomRight","4TopLeft","4TopRight","5Title","5BottomLeft","5BottomRight","5TopLeft","5TopRight","6Title","6BottomLeft","6BottomRight","6TopLeft","6TopRight"]);
        self.spendArray = ko.observableArray(["1","2","3","4","5","6"]);
        self.trendArray = ko.observableArray(["1","Diffus_Stats","TS1BottomLeft","TS1BottomRight","TS1TopLeft","TS1TopRight"]);
        
        self.isTitleVisible = ko.observable(false);

        self.submitComment = function () {
        	var dashboardName='diffusionMap';
        	if(self.inputText() === '') {
        		alert(cmnt_warn);
        	} else {
        		//sheet = viz.getWorkbook().getActiveSheet();
        		//alert(sheet.getName());
        		chatContent={ 
        				message: self.inputText(),        		
        				ts: new Date().getTime(),
        				user:self.user(),
        				sheetName: self.activeSheet(),
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
        				self.msgs.push(tempObj);
        				self.inputText("");
        			},
        			'error' : function(jqXHR, textStatus, errorThrown) {
        				setGlobalMessage({message:textStatus, messageType:'alert'},"general");
        			}
        		});
        	}
        }
        
        self.openNav = function() {
        	if( $("#mySidenav").width() === 0 ) {
        		document.getElementById("mySidenav").style.width = "400px";
        	} else {
        		document.getElementById("mySidenav").style.width = "0px";
        	}
        }
        
        self.closeNav = function() {
        	document.getElementById("mySidenav").style.width = "0";
        }
        
        self.getComments = function() {
        	var dashboardName='/diffusionMap';
        	$.ajax({
				'url': xoappcontext + '/sheetComments/'+self.activeSheet()+dashboardName,
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
			allComments.sort(function(a, b) {
				return b.messageId - a.messageId;
			});
			self.msgs.removeAll();
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
        	self.isFullScreenAvailable(true);
        	$("#preloader").show(true);
            $.ajax({
                'url': xoappcontext + '/diffusionMap',
                'type': 'POST',
                'cache': false,
                headers: {
                    'X-Super-Client': latestClient ? latestClient.clientId : -1
                },
                'success': function(responsedata) {
                    if (responsedata) {
                        self.buildDiffusionDashboardData(responsedata);
                        self.getFilterList();
                    }
                	$('#preloader').fadeOut("slow");

                },
                'error': function(jqXHR, textStatus, errorThrown) {
                    setGlobalMessage({
                        message: textStatus,
                        messageType: 'alert'
                    }, "general");
                	$('#preloader').fadeOut("slow");
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
        	self.selectedDiffFilters([]);
        	self.selectedSpendFilters([]);
        	self.selectedTrendFilters([]);
        	self.selectedTrendFilters.push(subSgmt[0]);
        	for ( var i=0; i< subSgmt.length - 1; i++ ) {
        		if(i < 6) {
	        		tempObj = {
	        			name: subSgmt[i], 
	        			id: i, 
	        			isChecked: ko.observable(true),
	        			isDisabled: ko.observable(false)
	        		};
	        		self.selectedDiffFilters.push(subSgmt[i]);
	        		self.selectedSpendFilters.push(subSgmt[i]);
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
        	var maxSelection = self.activeSheet() === "Trendsensor" ? 1 : 6 ;
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

        self.buildDiffusionDashboardData = function(responsedata) {

            var dashboardItems = [];

            self.renderDiffusionMap(responsedata);
            self.errorText(responsedata.errorText);
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

        self.diffusionFullScreenView = function() {
            self.isFullScreenEnabled(true);
            self.previousOverFlowValue = $('#xoportalbody').css('overflow');
            showInFullScreen("xoportalbody");
            self.changeDiffusionViewSize();
        };

        self.diffusionCloseFullScreen = function() {
            exitFullScreen();
            if (activeSheet) {
                activeSheet.changeSizeAsync({
                    behavior: tableauSoftware.SheetSizeBehavior.AUTOMATIC
                });
            }
        };

        self.renderDiffusionMap = function(responseData) {
            if (responseData && responseData.imageUrl && responseData.imageUrl.length > 0) {
                if (viz) {
                    viz.dispose();
                }
                var placeholderDiv = document.getElementById("diffusionViewPlace");
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
                                width: $('#diffusionViewPlace').width(),
                                height: $('#diffusionViewPlace').height()
                            },
                            maxSize: {
                                width: $('#diffusionViewPlace').width(),
                                height: $('#diffusionViewPlace').height()
                            }
                          });*/
                          self.isTitleVisible(true);
                          self.changeDiffusionViewSize();
                        viz.addEventListener(tableau.TableauEventName.MARKS_SELECTION,onMarksSelection);
                        viz.addEventListener(tableau.TableauEventName.FILTER_CHANGE, onFilterChange);
                    }
                };
                viz = new tableau.Viz(placeholderDiv, url, options);
            }
        };
        
        var i=0;
        function onFilterChange(Filter)
        {
//        	var list;
        	i=0;
          window.alert(Filter.getFieldName());
        	return Filter.getFilterAsync().then(function(field) {
            	{
            		window.alert(field.getFieldName());
            		if(i==0) {
            			alert(field.getFieldName());
	            		if(field.getFieldName()=="Region")
	            			self.selRegion([]);
	            		if(field.getFieldName()=="Devicedate")
	            			self.selDate("");
	            		if(field.getFieldName()=="Lifetime")
	            			self.selLifetime([]);
	            		//alert(field.getAppliedValues());
	            		values = field.getAppliedValues();
//	            		list="";
//        				for (j = 0; j < values.length; j++) {
//            				list += values[j].value + ",";
//						}
	            		if(field.getFieldName()=="Region") {
	            			for (j = 0; j < values.length; j++) {
	            				self.selRegion.push(values[j].value);
	            			}
	            		}
	            		if(field.getFieldName()=="Devicedate"){
	            			for (j = 0; j < values.length; j++) {
	            				self.selDate(values[j].value);
	            			}
	            		}
	            		if(field.getFieldName()=="Lifetime"){
	            			for (j = 0; j < values.length; j++) {
	            				self.selLifetime.push(values[j].value);
	            			}
	            		}
	            		alert(self.selLifetime());
            			i++;
            		}
            	}
        	});
        }
        
        
        self.onMarksSelection = function(marksEvent) {
//        	alert(marksEvent.getWorksheet().getName());
			if(marksEvent.getWorksheet().getName()=="Segment" || marksEvent.getWorksheet().getName()== "Vas Plan")
				return marksEvent.getMarksAsync().then(reportSelectedMarks);
		}
		
		
		self.reportSelectedMarks = function(marks) {
			if(marks.length > 0) {
				self.selTop([]);
				self.selVasPlan([]);
				self.selLifetime([]);
				self.selDataArpu([]);
				self.selRegion([]);
			}
			for (var markIndex = 0; markIndex < marks.length; markIndex++) {
                var pairs = marks[markIndex].getPairs();
                for (var pairIndex = 0; pairIndex < pairs.length; pairIndex++) {
                   var pair = pairs[pairIndex];
                   if (pair.fieldName=="ATTR(Segment)")
                	   self.selTop.push(pair.formattedValue);
                   if (pair.fieldName=="ATTR(Region)")
                	   self.selRegion.push(pair.formattedValue);
                   if (pair.fieldName=="ATTR(Date Week)")
                	   self.selDate(pair.formattedValue);
                   if (pair.fieldName=="Vas Plan")
                	   self.selVasPlan.push(pair.formattedValue);
                   if (pair.fieldName=="ATTR(Lifetime)") {
                	   self.selLifetime.push(pair.formattedValue);
                   }
                   if (pair.fieldName=="ATTR(Arpu Data)") {
                	   self.selDataArpu.push(pair.formattedValue);
                   }
//                  alert(pair.fieldName);
//                  alert(pair.formattedValue);
                }
             }
		}
        
        
        
        
        
        self.loadFilterPopup = function() {
			loadPopup("list_filters");
		 };
		 
        self.exportFilters = function() {
        	$('.ui.modals').modal('show');
        	setLocationHash("newcampaign");
        };

        self.changeDiffusionViewSize = function() {

            if (activeSheet) {
                // get SheetSize object
                var sizeInfo = activeSheet.getSize();
                var behavior = sizeInfo.behavior;
                var maxSize = sizeInfo.maxSize || "NA";
                var minSize = sizeInfo.minSize || "NA";

                var tempWidth = $('#diffusionViewPlace').width();
                var tempHeight = $('#diffusionViewPlace').height();
                if (maxSize != "NA") {
                    $('#diffusionViewPlace').width(maxSize.width);
                    $('#diffusionViewPlace').height(maxSize.height);
                }
                if (minSize != "NA") {
                    $('#diffusionViewPlace').css("min-width", maxSize.width);
                    $('#diffusionViewPlace').css("min-height", maxSize.height);
                }
                tempWidth = $('#diffusionViewPlace').width();
                tempHeight = $('#diffusionViewPlace').height();
                //viz.setFrameSize(tempWidth, tempHeight);
                // Create sheetSize options
                var sheetSize = {
                    behavior: tableauSoftware.SheetSizeBehavior.EXACTLY,
                    //behavior: tableauSoftware.SheetSizeBehavior.AUTOMATIC,
                    minSize: {
                        width: $('#diffusionViewPlace').width(),
                        height: $('#diffusionViewPlace').height()
                    },
                    maxSize: {
                        width: $('#diffusionViewPlace').width(),
                        height: $('#diffusionViewPlace').height()
                    }
                };
                // Resize sheet
                activeSheet.changeSizeAsync(sheetSize);
            }
        };

        self.clearAll = function() {
            self.imageUrl('');
            self.isFullScreenAvailable(false);
            self.diffusionCloseFullScreen();
            self.errorText('');
            self.filterList.removeAll();
            self.selectedFilters.removeAll();
            self.selectedDiffFilters.removeAll();
            self.selectedSpendFilters.removeAll();
            self.selectedTrendFilters.removeAll();
    		self.selectedList.removeAll();
    		self.isAllSelected(true);
    		self.msgs.removeAll();
    		self.inputText("");
			
            self.visibility(false);
            self.selectedSupClient();
            
            self.dashboardCommentHeading(diff_map);
            self.activeSheet(diff_map);
			self.maxSel(sel_vas);
			self.showSelect(true);
        };

        self.toggleClass = function () {
        	$(".dropdown2 dd ul").slideToggle('fast');
        }
        
        self.changeActiveSheet = function (sheetName) {
        	var defaultColor = "#E7E7E7";
        	var activeColor = "#0ABAB5";
        	var actTextColor = "white";
        	var defTextColor = "black";
        	self.cancelSelected();
        	self.activeSheet(sheetName);
        	if(sheetName === "Diffusion Map") {
        		self.showSelect(true);
        		self.dashboardCommentHeading(diff_map);
        		self.maxSel(sel_vas);
        		self.retrieveFilters(self.selectedDiffFilters,6);
        		$("#diffMap").css("background-color", activeColor);
        		$("#diffMap").css("color", actTextColor);
			}
			else if(sheetName === "Spend Segment") {
				self.showSelect(true);
				self.dashboardCommentHeading('Spend Segment');
				self.maxSel(sel_vas);
				self.retrieveFilters(self.selectedSpendFilters,6);
				$("#spndSeg").css("background-color", activeColor);
				$("#spndSeg").css("color", actTextColor);
			}
			else if(sheetName === "Reports") {
				self.showSelect(false);
				self.dashboardCommentHeading('Reports');
				self.retrieveFilters(self.selectedSpendFilters,6);
				$("#reports").css("background-color", activeColor);
				$("#reports").css("color", actTextColor);
			}
			else {
				self.showSelect(true);
				self.dashboardCommentHeading('Trendsensor');
				self.maxSel(sel_vas);
				self.retrieveFilters(self.selectedTrendFilters,1);
				$("#trndSen").css("background-color", activeColor);
				$("#trndSen").css("color", actTextColor);
			}
			if (self.prevSelected() === diff_map) {
				if (sheetName !== diff_map) {
					$("#diffMap").css("background-color", defaultColor);
					$("#diffMap").css("color", defTextColor);
				}
			} else if (self.prevSelected() === 'Spend Segment') {
				if (sheetName !== 'Spend Segment') {
					$("#spndSeg").css("background-color", defaultColor);
					$("#spndSeg").css("color", defTextColor);
				}
			} else if (self.prevSelected() === 'Reports') {
				if (sheetName !== 'Reports') {
					$("#reports").css("background-color", defaultColor);
					$("#reports").css("color", defTextColor);
				}
			} else {
				if (sheetName !== 'Trendsensor') {
					$("#trndSen").css("background-color", defaultColor);
					$("#trndSen").css("color", defTextColor);
				}
			}
			self.prevSelected([]);
			self.prevSelected(sheetName);
        	viz.getWorkbook().activateSheetAsync(sheetName);
        	self.getComments();
        }
        
        self.retrieveFilters = function(Filters, maxSelections) {
        	var flag = false;
        	var selFilters = Filters;
        	if(selFilters().length === maxSelections)
        		flag = true;
        	for ( var i=0; i< self.filterList().length - 1; i++ ) {
        		if(selFilters().indexOf(self.filterList()[i]['name']) !== -1) {
        			self.filterList()[i]['isChecked'](true);
        			self.filterList()[i]['isDisabled'](false);
        		} else {
        			self.filterList()[i]['isChecked'](false);
        			if (flag)
        				self.filterList()[i]['isDisabled'](true);
        			else
        				self.filterList()[i]['isDisabled'](false);
        		}
        	}
        }
        
        self.getSelectedFilters = function () {
        	self.toggleClass();
        	views=viz.getWorkbook().getPublishedSheetsInfo();
        	self.selectedFilters([]);
        	for ( var i=0; i<self.filterList().length; i++ ) {
        		if (self.filterList()[i]['isChecked']() && !self.filterList()[i]['isDisabled']()) {
        			self.selectedFilters.push(self.filterList()[i]['name']);
        		}
        	}
        	for(var i=0; i<views.length;i++) {
        		if(views[i]['$0']['isActive']) {
        			if(views[i]['$0']['name'] === "Diffusion Map") {
        				self.selectedDiffFilters(self.selectedFilters.slice(0));
        				self.applyDiffusionFilters();
        			}
        			else if(views[i]['$0']['name'] === "Spend Segment") {
        				self.selectedSpendFilters(self.selectedFilters.slice(0));
        				self.applySpendSegmentFilters();
        			}
        			else {
        				self.selectedTrendFilters(self.selectedFilters.slice(0));
        				self.applyTrendsensorFilters();
        			}
        		}
        	}
		}
		
		self.applyTrendsensorFilters = function() {
			sheet = viz.getWorkbook().getActiveSheet();
			worksheetArray = sheet.getWorksheets();
			if(self.selectedTrendFilters().length==1)
				for(var i = 0; i < worksheetArray.length; i++) {
					worksheetArray[i].applyFilterAsync("Status", self.selectedTrendFilters()[0], 'REPLACE');							
				}
			else
				alert(sel_opt);
		}
		
		self.applySpendSegmentFilters = function() {
			sheet = viz.getWorkbook().getActiveSheet();
			worksheetArray = sheet.getWorksheets();
			j=0;
			for(var k = 0; k < self.selectedSpendFilters().length; k++) {
				for(var i = 0; i < worksheetArray.length; i++) {
					if(worksheetArray[i].getName()==self.spendArray()[j]) {
						worksheetArray[i].applyFilterAsync("Status", self.selectedSpendFilters()[k], 'REPLACE');	
						
					}
				}j++;
			}
				
			for(k = self.selectedSpendFilters().length; k < 6; k++) {
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
			for(var k = 0; k < self.selectedDiffFilters().length; k++) {
				j=k*5;
				for(var i = 0; i < worksheetArray.length; i++) {
					if(worksheetArray[i].getName()==self.array()[j] || worksheetArray[i].getName()==self.array()[j+1] || worksheetArray[i].getName()==self.array()[j+2] || worksheetArray[i].getName()==self.array()[j+3] || worksheetArray[i].getName()==self.array()[j+4])
						worksheetArray[i].applyFilterAsync("Status", self.selectedDiffFilters()[k], 'REPLACE');	
				}
				
			}
				
			for(k = self.selectedDiffFilters().length; k < 6; k++) {
				j=k*5;
				for(var i = 0; i < worksheetArray.length; i++) {
					if(worksheetArray[i].getName()==self.array()[j] || worksheetArray[i].getName()==self.array()[j+1] || worksheetArray[i].getName()==self.array()[j+2] || worksheetArray[i].getName()==self.array()[j+3] || worksheetArray[i].getName()==self.array()[j+4])
						worksheetArray[i].applyFilterAsync("Status", null , 'REPLACE');	
				}
				
			}
		}

        return {
            clearAll: self.clearAll,
            currentPage: self.currentPage,
            visibility: self.visibility,
            imageUrl: self.imageUrl,
            errorText: self.errorText,
            fullScreenView: self.diffusionFullScreenView,
            isFullScreenEnabled: self.isFullScreenEnabled,
            closeFullScreen: self.diffusionCloseFullScreen,
            isFullScreenAvailable: self.isFullScreenAvailable,
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
            cancelSelected:self.cancelSelected,
            isTitleVisible:self.isTitleVisible,
            maxSel:self.maxSel,
            showSelect:self.showSelect,
            dashboardCommentHeading:self.dashboardCommentHeading,
            clientLogoImageUrl:self.clientLogoImageUrl,
            exportFilters:self.exportFilters,
            loadFilterPopup:self.loadFilterPopup,
            selTop:self.selTop,
            selRegion:self.selRegion,
        	selSubSgmt:self.selSubSgmt,
        	selLifetime:self.selLifetime,
        	selDataArpu:self.selDataArpu,
        	selVasPlan:self.selVasPlan,
        	selDate:self.selDate,
        	selLocation:self.selLocation
        };
    }
	
	DiffusionManagerModel.prototype = new BaseModel(ko, $);
	DiffusionManagerModel.prototype.constructor = DiffusionManagerModel;
	return DiffusionManagerModel;
});
