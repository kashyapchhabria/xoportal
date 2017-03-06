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
		self.segment = ko.observable('');
		self.subSegment = ko.observableArray([]);
		self.measureList = ko.observableArray([]);
		
        self.visibility = ko.observable(false);
        var workbook = null;
        var activeSheet = null;
        self.allClients = ko.observableArray([]);
        self.selectedSupClient = ko.observable();
        self.isTopBarVisibile = ko.observable(true);
        self.selectedReportMenuItem = ko.observable("Select Report");
        self.selectReport = {"name" : self.selectedReportMenuItem, "displayOrder":-1, "pageUrl": "", "subMenus" : []};

        self.selectedSupClient.subscribe(function(latestClient) {
            self.loadDashboardData(latestClient);
            self.fullScreenView();
            $("#getFilters").css('visibility', 'visible');
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
                        viz.addEventListener(tableau.TableauEventName.MARKS_SELECTION, onMarksSelection);
            			viz.addEventListener(tableau.TableauEventName.FILTER_CHANGE, onFilterChange);
            			viz.addEventListener(tableau.TableauEventName.PARAMETER_VALUE_CHANGE, onParameterChange);
                    }
                };
                viz = new tableau.Viz(placeholderDiv, url, options);
                //viz = new tableauSoftware.Viz(placeholderDiv, url, options);
            }
        };

		self.onMarksSelection = function(marksEvent) {
			//alert(marksEvent.getWorksheet().getName());
			if(marksEvent.getWorksheet().getName()=="Xonitor-Map")
			return marksEvent.getMarksAsync().then(segmentName);
			if(marksEvent.getWorksheet().getName()=="Xonitor-SubSegment- Cust & Rev")
			return marksEvent.getMarksAsync().then(subSegmentName);	
			};
			
			
		self.segmentName = function(marks) {
            for (var markIndex = 0; markIndex < marks.length; markIndex++) 
                var pairs = marks[markIndex].getPairs();
            for (var pairIndex = 0; pairIndex < pairs.length; pairIndex++) {
                   	var pair = pairs[pairIndex];
            		if(pair.fieldName.localeCompare("Segment Name")==0)
            		//alert(pair.formattedValue);
            		self.segment(pair.formattedValue);
            	}	
          };
          
          self.subSegmentName = function(marks) {
          	self.subSegment([]);
            for (var markIndex = 0; markIndex < marks.length; markIndex++) {
                var pairs = marks[markIndex].getPairs();
            	for (var pairIndex = 0; pairIndex < pairs.length; pairIndex++) {
                   	var pair = pairs[pairIndex];
                   	if(pair.fieldName.localeCompare("Segment Name")==0) {
            			//var pair = pairs[3];
            			//alert(pair.formattedValue);
            			self.segment(pair.formattedValue);
                   	}
            		if(pair.fieldName.localeCompare("Subsegment Name")==0) {
	            		//var pair = pairs[4];
    	        		//alert(pair.formattedValue);
        	    		self.subSegment.push(pair.formattedValue);
            		}
            	}
           }
          };
          
          self.onFilterChange = function(Filter)
        {
        	//window.alert(Filter.getWorksheet().getName());
            return Filter.getFilterAsync().then(reportSelectedFilter);
        };
        
        self.reportSelectedFilter = function(field) {
			var fname = field.getFieldName();
			var ftype = field.getFilterType();
			//if(fname=="Region Name")
			//{
	        	self.measureList([]);
	        	values = field.getAppliedValues();
	        	for (i = 0; i < values.length; i++) 
	            	self.measureList.push(values[i].value);
	            //window.alert(measureList);				
			//}
			
		};
          
          
        self.onParameterChange = function(Param)
        {
           	//window.alert(Param.getParameterName());
        	return Param.getParameterAsync().then(reportParamChanged);
        	
        };

        self.reportParamChanged = function (params)
        {
        		var parameterValue=params.getCurrentValue();
        		window.alert(parameterValue.value);
        		self.onFilterChange;
        } ;
        
        self.showPopup = function ()
        {
        	$('#myModal').foundation('reveal', 'open');
        }
        
        self.getMsisdns = function () {
        	var msisdns = [];
        	var data = {};
        	var csvName = 'Segment- ' + self.segment() + 'SubSegment- ' + self.subSegment() + '.csv' ;
        	data['segment'] = self.segment();
        	data['subSegment'] = self.subSegment();
        	data['regions'] = self.measureList();
        	var filterData=JSON.stringify(data);
        	$.ajax({
                'url': xoappcontext + '/getMsisdns',
                'type': 'POST',
                'cache': false,
                'data':filterData,
                'contentType':"application/json; charset=utf-8",
                'success': function(serverResponse) {
                    //alert(serverResponse.message);
                   var obj = JSON.parse(serverResponse.message);
                   var csv ='Msisdn\n';
                    for(i=0;i<obj.hits.hits.length;i++)
                    {
                    	msisdns.push(obj.hits.hits[i].fields.msisdn);
                    	csv+= obj.hits.hits[i].fields.msisdn+'\n';
                    }
                    var hiddenElement = document.createElement('a');
				    hiddenElement.href = 'data:text/csv;charset=utf-8,' + encodeURI(csv);
				    hiddenElement.target = '_blank';
				    hiddenElement.download = csvName;
    				hiddenElement.click();
                    
                },
                'error': function(jqXHR, textStatus, errorThrown) {
                    setGlobalMessage({
                        message: textStatus,
                        messageType: 'alert'
                    }, "general");
                }
            });
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
            self.menuData.removeAll();
            self.dashboardData.removeAll();
            self.imageUrl('');
            self.placeHolderImageUrl('');
            self.isFullScreenAvailable(false);
            self.closeFullScreen();
            self.isFullScreenAvailable(false);
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
            showPopup:self.showPopup,
            getMsisdns:self.getMsisdns,
            segment:self.segment,
            subSegment:self.subSegment,
            measureList:self.measureList
        };
    }
    TableauManagerModel.prototype = new BaseModel(ko, $);
    TableauManagerModel.prototype.constructor = TableauManagerModel;
    return TableauManagerModel;
});