/**
 * Tableau management
 */
define([ 'knockout', 'jquery' ], function(ko, $) {

	function ReportGroupManagementModel() {

		BaseModel.call(this, ko, $);
		var self = this;
		self.availableGroups = ko.observableArray([]);
		isEdit = ko.observable(false);
		self.displayOrder = ko.observable(0);
		self.groupName = ko.observable('');
		var grptbl = null;

		self.loadGroups = function() {
			$.ajax({
				'url' : xoappcontext + '/viewgroup/all',
				'type' : 'GET',
				'cache' : false,
				'success' : function(serverData) {
					grptbl = self.buildDataTableWithData('allAvailableGroups', self.buildGroupData, serverData, null, grptbl);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message : textStatus, messageType : 'alert'}, "general");
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
					groupName : groupobj.groupName,
					displayOrder : groupobj.displayOrder,
					active : ko.observable(groupobj.active),
					toggleStatus : self.toggleGroupActiveStatus,
					editable : self.makeEditable,
					isEdit : ko.observable(false),
					saveGroups : self.saveGroups,
					deleteGroupCheck : self.deleteGroupCheck
				};
				self.availableGroups.push(tempObj);
			}
		};

		self.makeEditable = function(data, event) {
			if (data && event) {
				data.isEdit(!data.isEdit());
			}
		};

		self.toggleGroupActiveStatus = function(data, event) {

			if (data && event) {
				$.ajax({
					'url' : xoappcontext + '/viewgroup/toggleactive/'
							+ data.viewGroupId,
					'type' : 'PUT',
					'cache' : false,
					'success' : function(responsedata) {
						data.active(!data.active());
						setGlobalMessage(responsedata, "general");
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({message : textStatus, messageType : 'alert'}, "general");
					}
				});
			}
		};

		self.saveGroups = function(data, event) {
			var reqUrl = xoappcontext;
			var groupDto = {};
			// Create a formdata object and add the files
			if (data.viewGroupId) {
				groupDto = {
					viewGroupId : data.viewGroupId,
					groupName : data.groupName,
					displayOrder : data.displayOrder
				};
				reqUrl = reqUrl + '/viewgroup/update';
			} else {
				groupDto = {
					groupName : self.groupName(),
					displayOrder : 1
				};
				reqUrl = reqUrl + '/viewgroup';
			}

			var groupData = JSON.stringify(groupDto);

			var reqMethod = groupDto.viewGroupId ? 'PUT' : 'POST';
			$.ajax({
				'url' : reqUrl,
				'type' : reqMethod,
				'cache' : false,
				'data' : groupData,
				'contentType' : "application/json; charset=utf-8",
				'success' : function(responseData) {
					if (data.viewGroupId) {
						data.isEdit(false);
					}

					setGlobalMessage(responseData, "general");
					if (responseData.messageType == 'success') {
						setTimeout(function() {
							self.loadGroups();
						}, 3000);
					}
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					if (data.viewGroupId) {
						data.isEdit(false);
					}
					setGlobalMessage({message : textStatus, messageType : 'alert'}, "general");
				}
			});
		};

		self.deleteGroupCheck = function(data, event) {

			if (data && event) {
				var rowPosition = $(event.currentTarget).parents('tr');
				var viewGroupId = data.viewGroupId;

				$.ajax({
					'url' : xoappcontext + '/viewgroup/groupcount/'
							+ data.viewGroupId,
					'type' : 'GET',
					'cache' : false,
					'success' : function(resultData) {
						self.showDeleteConfirmation(resultData, viewGroupId, rowPosition);
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({message : textStatus, messageType : 'alert'}, "general");
					}
				});
			}
		};

		self.showDeleteConfirmation = function(resultData, viewGroupId, rowPosition) {

			if (resultData != 0) {
				if (confirm("This group is assigned to reports(s) !! Are You Sure u want to delete ?") == true) {
					self.deleteGroup(viewGroupId, rowPosition);
				}
			} else {
				self.deleteGroup(viewGroupId, rowPosition);
			}
		};

		self.deleteGroup = function(viewGroupId, rowPosition) {

			$.ajax({
				'url' : xoappcontext + '/viewgroup/' + viewGroupId,
				'type' : 'DELETE',
				'cache' : false,
				'success' : function(responsedata) {
					$(rowPosition).hide("medium", function() {
						grptbl.row($(rowPosition)).remove();
						grptbl.draw();
					});
					setGlobalMessage(responsedata, "general");
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message : textStatus, messageType : 'alert'}, "general");
				}
			});
		};

		self.loadPopup = function() {
			loadPopup("myModal");
		};

		return {
			availableGroups : self.availableGroups,
			loadGroups : self.loadGroups,
			currentPage : self.currentPage,
			viewGroupId : self.viewGroupId,
			groupName : self.groupName,
			displayOrder : self.displayOrder,
			visibility : self.visibility,
			saveGroups : self.saveGroups,
			loadPopup : self.loadPopup,
			saveGroups : self.saveGroups,
			saveNewGroup : self.saveNewGroup
		};
	}
	ReportGroupManagementModel.prototype = new BaseModel(ko, $);
	ReportGroupManagementModel.prototype.constructor = ReportGroupManagementModel;
	return ReportGroupManagementModel;
});
