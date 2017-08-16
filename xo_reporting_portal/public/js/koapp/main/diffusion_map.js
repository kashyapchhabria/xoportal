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
        self.selDate = ko.observable("*");
        self.selRegion = ko.observableArray(["*"]);
        self.count = ko.observable();
        
        self.selLifetimeFlag = ko.observable(1);
        self.selRegionFlag = ko.observable(1);
        self.isNoValuesSelected = ko.observable(1);
        self.selTopFlag = ko.observable(1);
        self.selDataArpuFlag = ko.observable(1);
        self.selVasPlanFlag = ko.observable(1);
        self.selDateFlag = ko.observable(1);
        
        
        self.visibility = ko.observable(false);
        var workbook = null;
        var activeSheet = null;
        self.isTopBarVisibile = ko.observable(true);
        self.userGuide = ko.observable(false);
        
        self.isTitleVisible = ko.observable(false);
        self.loopcount = ko.observable(0);
        self.campaignName = ko.observable('');
		self.campaignDescription = ko.observable('');
		self.campaignCount = ko.observable(0);
		self.campaignCountNoFormat = ko.observable(0);
		self.campaignTotalCount = ko.observable(0);
		self.selSgmtKeywords = ko.observable();
		self.campaignPercent = ko.observable();
		self.rangeValue = ko.observable(50);
		self.abSplitValue = ko.observable();
		self.isDemo = ko.observable();
		self.sgmtKeywords = ko.observable({
			A1 : "Innovative, WWW everywhere, Dynamic, Daring, Passion, Always the latest, Bold, Energetic, Unique, Trendy, Friendly, Cool, Fun, Enjoying life",
			A2 : "Efficient, Fair, Ethical, Equality, clear, Transparent, Collaborative, Honest, Planning day-to-day activities, Reliable, Quality>design, Responsibility",
			A3 : "Stylish, Confident, Status, Designer labels improve a person's image, Respect, Power",
			A4 : "Relationships, Seeks advice, Duty, Commitment, Duty/tradition, Integrity, Techno skeptics, Tradition",
			A5 : "Relationships, Seeks advice, Duty, Commitment, Duty/tradition, Integrity, Techno skeptics, Tradition",
			Y1 : "Innovative, WWW everywhere, Dynamic, Daring, Passion, Always the latest, Bold, Energetic, Unique, Trendy, Friendly, Cool, Fun, Enjoying life",
			Y2 : "Efficient, Fair, Ethical, Equality, clear, Transparent, Collaborative, Honest, Planning day-to-day activities, Reliable, Quality>design, Responsibility",
			Y3 : "Stylish, Confident, Status, Designer labels improve a person's image, Respect, Power"
		});
        
		self.formatNumberData = function(number) {
			if( number > 1000 ) {
				if( number > 1000000) {
					var tempCount = number / 1000000;
					return Number((tempCount).toFixed(0)) + " Mn";
				} else {
					var tempCount = number / 1000;
					return Number((tempCount).toFixed(0)) + " K";
				}
			} else if (self.isDemo()){
				return number.toFixed(0) + " K";
			} else {
				return number.toFixed(0);
			}
		};
		
		self.clearHtml = function() {
			self.campaignName('');
			self.campaignDescription('');
			self.rangeValue(50);
			self.abSplitValue('');
		}
		
		self.selTop.subscribe(function(newVal) {
			if ( newVal == "*" )
        		self.selTop(["A1","A2","A3","A4","A5","Y1","Y2","Y3"]);
			self.selSgmtKeywords("");
			var keywordHeader = "<h4 class=\"ui header\">Segment Keywords - ";
			var endKeywordHeader = "</h4>";
			var keywordsPara = "<p>";
			var endKeywordsPara = "</p>";
        	for(var i=0; i < self.selTop().length; i++) {
        		var sgmtSelHeader = keywordHeader + self.selTop()[i] + endKeywordHeader ;
        		var sgmtSelWords = self.sgmtKeywords()[self.selTop()[i]];
        		var fullHtml = sgmtSelHeader + keywordsPara + sgmtSelWords + endKeywordsPara;
        		self.selSgmtKeywords(self.selSgmtKeywords()+fullHtml);
        	}
		});
		
		
		self.rangeValue.subscribe(function(newVal){
			var abSplit = (newVal * self.campaignCountNoFormat()) / 100;
			var aSplit = self.formatNumberData(abSplit);
			var bSplit = self.formatNumberData(self.campaignCountNoFormat()-abSplit);
			self.abSplitValue("A - " + aSplit +"<br /> B - " + bSplit)
		});
		
		self.prepJsonData = function() {
			var data = {};
			var subSgt = [], homeL = [];
			if(self.selRegion() == '*')
				self.selRegion(["Lagos", "North_1", "North_2", "South East", "South South", "South West", "Unavailable"]);
			if(self.selVasPlan() == '*' || self.selVasPlan().length == 0)
				self.selVasPlan(["Backup", "Entertainment", "Infotainment", "Jobs", "MFS", "Undefined", "Betting", "Financial", "Football","Music", "Promo", "Religion", "Video", "Voting", "mAgric", "mHealth"]);
			if(self.selDataArpu() == '*')
				self.selDataArpu(["HH","LH","LL","HL"]);
			if(self.selLifetime() == '*')
				self.selLifetime(["1 - 3 years","3 - 5 years","5+ years","6 months - 1 year"]);
			data['name'] = self.campaignName();
			data['description'] = self.campaignDescription();
			data['filterJson'] = {};
			data['filterJson']['topSegment'] = self.selTop();
			data['filterJson']['dateWeek'] = self.selDate();
			data['filterJson']['regions'] = self.selRegion();
			data['filterJson']['lifetime'] = self.selLifetime();
			data['filterJson']['dataArpu'] = self.selDataArpu();
			data['filterJson']['vasPlan'] = self.selVasPlan();
			data['filterJson']['noExported'] = self.campaignCountNoFormat();
			data['filterJson']['totalBase'] = self.campaignTotalCount();
			data['setAb'] = self.rangeValue();
			return data;
		};

		self.getSelectedFieldsJson = function(type) {
			if (type === "export" && self.campaignName() === '') {
				alert("Enter Campaign Name");
				return null;
			} else if (type === "export" && self.campaignDescription() === '') {
				alert("Enter Campaign Description");
				return null;
			} else if (type === "export" && (self.rangeValue() > 100 || self.rangeValue() === '' || self.rangeValue() === "0")) {
				alert("Kindly choose A/B value between 1-100% ");
				return null;
			} else {
				return self.prepJsonData();
			}
		};

		self.getMsisdnCount = function() {
			var filterData = JSON.stringify(self.getSelectedFieldsJson("count"));
			$("#preloader").show(true);
			$.ajax({
				'url' : xoappcontext + '/getMsisdnCount',
				'type' : 'POST',
				'cache' : false,
				'data' : filterData,
				'contentType' : "application/json; charset=utf-8",
				'success' : function(serverResponse) {
					var campCount = parseInt(serverResponse.message);
                	self.campaignCountNoFormat(campCount);
                	self.campaignCount(self.formatNumberData(campCount));
//                	if (campCount > 1000) {
//                		if (campCount > 1000000) {
//                			var tempCount = campCount / 1000000;
//                			self.campaignCount(Number((tempCount).toFixed(2)) + " Mn");
//                		} else {
//                			var tempCount = campCount / 1000;
//                			self.campaignCount(Number((tempCount).toFixed(2)) + " K");
//                		}
//                	} else if (self.isDemo()){
//                		self.campaignCount(campCount + " K");
//                	} else {
//                		self.campaignCount(campCount);
//                	}
                	var percent = Number((((campCount / self.campaignTotalCount()) * 100)).toFixed(2)); 
                	self.campaignPercent(percent);
                	$("#preloader").fadeOut("slow");
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					$("#preloader").fadeOut("slow");
					setGlobalMessage({
						message : textStatus,
						messageType : 'alert'
					}, "popup");
				}
			});
		};
		

		self.getTotalCount = function() {
			$("#preloader").show(true);
			$.ajax({
				'url' : xoappcontext + '/getTotalCount',
				'type' : 'GET',
				'cache' : false,
				'contentType' : "application/json; charset=utf-8",
				'success' : function(serverResponse) {
					self.campaignTotalCount(serverResponse.message);
					self.getMsisdnCount();
					$("#preloader").fadeOut("slow");
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					$("#preloader").fadeOut("slow");
					setGlobalMessage({
						message : textStatus,
						messageType : 'alert'
					}, "popup");
				}
			});
		};

		self.getMsisdns = function() {
			var filterData = JSON.stringify(self.getSelectedFieldsJson("export"));
			if (filterData !== "null") {
				$("#preloader").show(true);
				$.ajax({
					'url' : xoappcontext + '/savecampaign',
					'type' : 'POST',
					'cache' : false,
					'data' : filterData,
					'contentType' : "application/json; charset=utf-8",
					'success' : function(serverResponse) {
						if (serverResponse !== "Error") {
							self.getCsvFile(serverResponse);
						} else {
							alert("Error Generating File !");
							$("#preloader").fadeOut("slow");
						}
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						$("#preloader").fadeOut("slow");
						setGlobalMessage({
							message : textStatus,
							messageType : 'alert'
						}, "popup");
					}
				});

			}
		};

		self.getCsvFile = function(fileName) {
			$.ajax({
				'url' : xoappcontext + '/getCsvFile/' + fileName,
				'type' : 'GET',
				'cache' : false,
				'contentType' : "application/json; charset=utf-8",
				'success' : function(serverResponse) {
					window.open(xoappcontext + '/getCsvFile/' + fileName
							+ '?authToken='
							+ $('#authtokenvalue').attr('authtoken'));
					$("#preloader").fadeOut("slow");
					console.log("File download Successful");
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					$("#preloader").fadeOut("slow");
					setGlobalMessage({
						message : textStatus,
						messageType : 'alert'
					}, "popup");
				}
			});
		}
        
		
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
//          window.alert(Filter.getFieldName());
        	return Filter.getFilterAsync().then(function(field) {
            	{
//            		window.alert(field.getFieldName());
            		if(i==0) {
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
            			i++;
            		}
            	}
            	self.checkAllSelected();
        	});
        }
        
        self.checkAllSelected = function() {
        	if(self.selTopFlag() == 1 && self.selDataArpuFlag()==1 && self.selVasPlanFlag()==1 &&  self.selDateFlag()==1)
        		self.isNoValuesSelected(1);
        	else
        		self.isNoValuesSelected(0);
        }
        
        
        self.selLifetime.subscribe(function(newVal) {
        	if(newVal == '*' || newVal.length == 5){
//        		self.selLifetime(["1 - 3 years","3 - 5 years","5+ years","6 months - 1 year"]);
        		self.selLifetimeFlag(1);
        	}
        	else
        		self.selLifetimeFlag(0);
        });
        
        self.selRegion.subscribe(function(newVal) {
        	if(newVal == '*' || newVal.length == 7){
//        		self.selRegion(["Lagos", "North_1", "North_2", "South East", "South South", "South West", "Unavailable"]);
        		self.selRegionFlag(1);
        	}
        	else
        		self.selRegionFlag(0);
        });
        
        self.selTop.subscribe(function(newVal) {
        	if(newVal == '*'){
        		self.selTopFlag(1);
        	}
        	else
        		self.selTopFlag(0);
        });
        
        self.selDataArpu.subscribe(function(newVal) {
        	if(newVal == '*'){
//        		self.selDataArpu(["HH","LH","LL","HL"]);
        		self.selDataArpuFlag(1);
        	}
        	else
        		self.selDataArpuFlag(0);
        });
        
        self.selVasPlan.subscribe(function(newVal) {
        	if(newVal == '*' || self.selVasPlan() == "" || newVal.length == 16){
//        		self.selVasPlan(["Backup", "Entertainment", "Infotainment", "Jobs", "MFS", "Undefined", "Betting", "Financial", "Football","Music", "Promo", "Religion", "Video", "Voting", "mAgric", "mHealth"]);
        		self.selVasPlanFlag(1);
        	}
        	else
        		self.selVasPlanFlag(0);
        	alert(self.selVasPlan());
        });
        
        self.selDate.subscribe(function(newVal) {
        	if(newVal == '*')
        		self.selDateFlag(1);
        	else
        		self.selDateFlag(0);
        });
        
        
        self.onMarksSelection = function(marksEvent) {
//        	alert(marksEvent.getWorksheet().getName());
			if(marksEvent.getWorksheet().getName()=="Segment" || marksEvent.getWorksheet().getName()== "Vas Plan") {
				self.count(0);
				self.loopcount(0);
				return marksEvent.getMarksAsync().then(reportSelectedMarks);
			}
		}
		
        
		self.reportSelectedMarks = function(marks) {
			if (marks.length==0)
				self.count(self.count()+1);
			if (self.count() == 2){
				self.selTop(["*"]);
		        self.selDataArpu(["*"]);
		        self.selVasPlan(["*"]);
		        self.selDate("*");
			}
			if(self.loopcount() ==  0) {
				self.loopcount(self.loopcount() + 1);
				self.selTop([]);
				self.selDataArpu([]);
				self.selVasPlan([]);
			}
			for (var markIndex = 0; markIndex < marks.length; markIndex++) {
                var pairs = marks[markIndex].getPairs();
                for (var pairIndex = 0; pairIndex < pairs.length; pairIndex++) {
                   var pair = pairs[pairIndex];
                   if (pair.fieldName=="ATTR(Segment)") {
                	   if (self.selTop.indexOf(pair.formattedValue) < 0) { 
                		   self.selTop.push(pair.formattedValue); 
                	   }
                   }
//                   if (pair.fieldName=="ATTR(Region)")
//                	   self.selRegion.push(pair.formattedValue);
                   if (pair.fieldName=="ATTR(Date Week)")
                	   self.selDate(pair.formattedValue);
                   if (pair.fieldName=="Vas Plan")
                	   self.selVasPlan.push(pair.formattedValue);
//                   if (pair.fieldName=="ATTR(Lifetime)") {
//                	   self.selLifetime.push(pair.formattedValue);
//                   }
                   if (pair.fieldName=="ATTR(Arpu Data)") {
                	   if (self.selDataArpu.indexOf(pair.formattedValue) < 0) { 
                		   self.selDataArpu.push(pair.formattedValue); 
                	   }
                   }
//                  alert(pair.fieldName);
//                  alert(pair.formattedValue);
                }
             }
			self.checkAllSelected();
		}
        
        
        self.loadFilterPopup = function() {
        	if(self.isNoValuesSelected()==1) {
        		loadPopup("no_filters");
        	} else {
        		self.getTotalCount();
        		loadPopup("list_filters");
        	}
		 };
		 
        self.exportFilters = function() {
        	$('.ui.modals').modal('hide');
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
			
			
			self.selTop(["*"]);
	        self.selLifetime(["*"]);
	        self.selDataArpu(["*"]);
	        self.selVasPlan(["*"]);
	        self.selDate("*");
	        self.selRegion(["*"]);
	        
	        self.selLifetimeFlag(1);
	        self.selRegionFlag(1);
	        self.isNoValuesSelected(1);
	        self.selTopFlag(1);
	        self.selDataArpuFlag(1);
	        self.selVasPlanFlag(1);
	        self.selDateFlag(1);
        };

        

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
        	selLifetime:self.selLifetime,
        	selDataArpu:self.selDataArpu,
        	selVasPlan:self.selVasPlan,
        	selDate:self.selDate,
        	selTopFlag:self.selTopFlag,
            selRegionFlag:self.selRegionFlag,
        	selLifetimeFlag:self.selLifetimeFlag,
        	selDataArpuFlag:self.selDataArpuFlag,
        	selVasPlanFlag:self.selVasPlanFlag,
        	selDateFlag:self.selDateFlag,
        	isNoValuesSelected:self.isNoValuesSelected,
        	
        	
        	campaignName : self.campaignName,
			campaignDescription : self.campaignDescription,
			campaignCount : self.campaignCount,
			getMsisdnCount:self.getMsisdnCount,
			selSgmtKeywords:self.selSgmtKeywords,
			getTotalCount:self.getTotalCount,
			getMsisdns:self.getMsisdns,
			campaignPercent:self.campaignPercent,
			rangeValue:self.rangeValue,
			abSplitValue:self.abSplitValue,
			clearHtml:self.clearHtml
        };
    }
	
	DiffusionManagerModel.prototype = new BaseModel(ko, $);
	DiffusionManagerModel.prototype.constructor = DiffusionManagerModel;
	return DiffusionManagerModel;
});
