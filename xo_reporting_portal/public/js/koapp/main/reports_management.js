/**
 * Tableau management
 */
define([ 'knockout', 'jquery' ], function(ko, $) {

	function ReportManagementModel() {

		BaseModel.call(this, ko, $);
		var self = this;
		self.availableReports = ko.observableArray([]);
		self.availableGroups = ko.observableArray([]);
		isEdit = ko.observable(false);
		self.displayOrder = ko.observable(0);
		self.reportName = ko.observable('');
		self.dashboard = ko.observable(0);
		self.displayName = ko.observable('');
		self.viewGroupId = ko.observable(0);
		self.errorText = ko.observable('');
		var grptbl = null;

		self.cleanup = function() {
			self.availableReports.removeAll();
		};

		self.loadReports = function() {
			$.ajax({
				'url' : xoappcontext + '/tableauview/all',
				'type' : 'GET',
				'cache' : false,
				'success' : function(serverData) {
					grptbl = self.buildDataTableWithData('allAvailableReports', self.buildReportData, serverData, null, grptbl);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({
						message : textStatus,
						messageType : 'alert'
					}, "general");
				}
			});
		};

		self.buildReportData = function(reportdetails) {
			var i = 0;
			var totalReports = reportdetails.length;
			self.availableReports.removeAll();
			for (; i < totalReports; i++) {
				var reportobj = reportdetails[i];
				var tempObj = {
					viewGroupId : ko.observable(reportobj.viewGroupId),
					reportName : reportobj.name,
					displayOrder : reportobj.displayOrder,
					dashboard : ko.observable(reportobj.dashboard),
					displayName : reportobj.displayName,
					tableauViewId : reportobj.tableauViewId,
					groupName : reportobj.groupName,
					active : ko.observable(reportobj.active),
					toggleStatus : self.toggleReportActiveStatus,
					editable : self.makeEditable,
					isEdit : ko.observable(false),
					saveReports : self.saveReports,
					availableGroups : self.availableGroups,
					selectedGroup : ko.observable({}),
					checkDashboard : reportobj.dashboard ? self.toggleReportDashboardStatus : self.checkDashboard
				};
				//tempObj.dashboard.subscribe(reportobj.dashboard ? self.toggleReportDashboardStatus : self.checkDashboard)
				self.availableReports.push(tempObj);
			}
		}

		self.loadGroups = function() {
			$.ajax({
				'url' : xoappcontext + '/viewgroup/all',
				'type' : 'GET',
				'cache' : false,
				'success' : function(serverData) {
					self.buildDropDown(self.buildGroupData , serverData);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({
						message : textStatus,
						messageType : 'alert'
					}, "general");
				}
			});
		};

		self.buildGroupData = function(groupdetails) {
			var i = 0;
			var totalGroup = groupdetails.length;
			self.availableGroups.removeAll();
			for (; i < totalGroup; i++) {
				var groupobj = groupdetails[i];
				var tempObj = {
					viewGroupId : groupobj.viewGroupId,
					groupName : groupobj.groupName

				};
				self.availableGroups.push(tempObj);
			}
		};

		self.makeEditable = function(data, event) {
			if (data && event) {
				data.isEdit(!data.isEdit());
				if(data.viewGroupId() > 0){
					data.selectedGroup(self.availableGroups.find("viewGroupId", {
					viewGroupId : data.viewGroupId(),
					groupName : data.groupName
					}));
				}
			}
		};

		self.toggleReportActiveStatus = function(data, event) {
			if (data && event) {
				$.ajax({
					'url' : xoappcontext + '/tableauview/toggleactive/'
							+ data.tableauViewId,
					'type' : 'PUT',
					'cache' : false,
					'success' : function(responsedata) {
						data.active(!data.active());
						setGlobalMessage(responsedata, "general");
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({
							message : textStatus,
							messageType : 'alert'
						}, "general");
					}
				});
			}

		};

		self.saveReports = function(data, event) {
			if(typeof data.selectedGroup()==="undefined"){		
				alert(group_select);		
				return;		
			}
			
			// Create a formdata object and add the files
			if(data.displayOrder > 0){
			var reportDto = {
            				tableauViewId : data.tableauViewId,
            				displayName : data.displayName,
            				viewGroupId : data.selectedGroup() ? data.selectedGroup().viewGroupId : data.viewGroupId(),
            				displayOrder : data.displayOrder,
            				dashboard : data.dashboard()
            			};
            			data.groupName = data.selectedGroup() ? data.selectedGroup().groupName : '';
            			if (reportDto.tableauViewId == false) {
            				reportDto.tableauViewId = null;
            			}
            			var reportData = JSON.stringify(reportDto);
            			var reqUrl = xoappcontext + '/tableauview/update';
            			var reqMethod = reportDto.viewGroupId ? 'PUT' : 'POST';
            			$.ajax({
            				'url' : reqUrl,
            				'type' : reqMethod,
            				'cache' : false,
            				'data' : reportData,
            				'contentType' : "application/json; charset=utf-8",
            				'success' : function(responseData) {
            					data.isEdit(false);
            					setGlobalMessage(responseData, "general");
            					if (responseData.messageType == 'success') {
            					setTimeout(function() {
									data.viewGroupId(data.selectedGroup().viewGroupId);
            						self.loadDashboardData();
            						}, 3000);


            						             					}
            				},
            				'error' : function(jqXHR, textStatus, errorThrown) {
            					data.isEdit(false);
            					setGlobalMessage({
            						message : textStatus,
            						messageType : 'alert'
            					}, "general");
            				}
            			});
            }
            else
            {
            window.alert(display_order_err);
            }

		};



		self.loadDashboardData = function() {
        			$.ajax({
        				'url':  xoappcontext + '/dashboard',
        				'type':'POST',
        				'cache':false,
        				'success' : function(responsedata) {
        					if(responsedata) {
        						self.checkDashboardData(responsedata);
        					}

        				},
        				'error' : function(jqXHR, textStatus, errorThrown) {
        					setGlobalMessage({message:textStatus, messageType:'alert'},"general");
        				}
        			});
        		};

        		self.checkDashboardData = function(responsedata) {
                			self.errorText(responsedata.errorText);
                			if(self.errorText().length>0){
                			self.errorText(config_reprt_err);
                			setGlobalMessage({
                                                        			message : self.errorText(),
                                                        			messageType : 'alert'
                                                        			}, "general");
                			}
                		};




		self.checkDashboard = function(data, event) {
        		if (data && event) {
        			if(data.viewGroupId()){
        			$.ajax({
                           'url' : xoappcontext + '/tableauview/checkdashboard/'
                            	  + data.viewGroupId(),
                           'type' : 'GET',
                           'cache' : false,
                           'success' : function(responsedata) {
                           if(responsedata && responsedata.message == 'true'){
                           		data.dashboard(false);
                           		$("#"+event.target.id).prop('checked', false);
                           		window.alert(already_assign_err + " " + data.groupName );
                           }
                           else{
                           self.toggleReportDashboardStatus(data, event);
                           }
                           },
                           'error' : function(jqXHR, textStatus, errorThrown) {
                            		setGlobalMessage({
                            			message : textStatus,
                            			messageType : 'alert'
                            			}, "general");
                            		}
                           });
        			}
        		}
                return true;
        };


        self.toggleReportDashboardStatus = function(data, event) {
            			if (data && event) {
            				$.ajax({
            					'url' : xoappcontext + '/tableauview/toggledashboard/'
            							+ data.tableauViewId,
            					'type' : 'PUT',
            					'cache' : false,
            					'success' : function(responsedata) {
            						data.dashboard(!data.dashboard());
            						data.checkDashboard = data.dashboard() ? self.toggleReportDashboardStatus : self.checkDashboard;
            						setGlobalMessage(responsedata, "general");
            					},
            					'error' : function(jqXHR, textStatus, errorThrown) {
            						setGlobalMessage({
            							message : textStatus,
            							messageType : 'alert'
            						}, "general");
            					}
            				});
            			}

            		};


		return {
			availableReports : self.availableReports,
			loadReports : self.loadReports,
			currentPage : self.currentPage,
			viewGroupId : self.viewGroupId,
			groupName : self.groupName,
			displayOrder : self.displayOrder,
			visibility : self.visibility,
			errorText : self.errorText,
			saveGroups : self.saveGroups,
			availableGroups : self.availableGroups,
			loadGroups : self.loadGroups,
			cleanup : self.cleanup
		};
	}
	ReportManagementModel.prototype = new BaseModel(ko, $);
	ReportManagementModel.prototype.constructor = ReportManagementModel;
	return ReportManagementModel;

});
