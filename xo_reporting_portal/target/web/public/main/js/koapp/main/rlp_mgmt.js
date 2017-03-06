/**
 * Configuration management
 */
define([ 'knockout', 'jquery' ], function(ko, $) {

	function RowLevelPermissionModel() {

		BaseModel.call(this, ko, $);
		var self = this;
		self.selectedResource = ko.observable({});
		self.selectedGroup = ko.observable({});
		self.selectedUser = ko.observable({});
		self.selectedRole = ko.observable({});
		self.rowlevelpermfor = ko.observableArray(['User', 'Role']);
		self.availableusers = ko.observableArray([]);
		self.availableroles = ko.observableArray([]);
		self.availableresources = ko.observableArray([]);
		self.availableEntityInstances = ko.observableArray([]);
		self.unAssignedInstances =  ko.observableArray([]);

		var instanceTbl = null;
		var unAssinstanceTbl = null;

		self.cleanup = function() {
			self.availableEntityInstances.removeAll();
			self.availableroles.removeAll();
			self.availableusers.removeAll();
			if(instanceTbl) {
				instanceTbl.clear();
				instanceTbl.destroy();
			}
		};

		self.loadData = function(data) {
			if(data) {
				var url = xoappcontext;
				var dataHolder = '';
				if(self.selectedGroup() == 'User') {
					url = url + '/users/keyvalue'
					dataHolder = self.availableusers;
				} else if(self.selectedGroup() == 'Role') {
					url = url + '/roles/keyvalue'
					dataHolder = self.availableroles;
				}
				$.ajax({
					'url' :  url,
					'type' : 'GET',
					'cache' : false,
					'success' : function(serverData) {
						self.buildDropDown(self.dropDownBuilder, {'dataHolder': dataHolder, 'data':serverData});
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({message : textStatus, messageType : 'alert'}, "general");
					}
				});
			}
			return true;
		};

		self.loadResources = function() {
			var url = xoappcontext + '/resourcetypes/keyvalue';
			var dataHolder = self.availableresources;
			$.ajax({
				'url' :  url,
				'type' : 'GET',
				'cache' : false,
				'success' : function(serverData) {
					self.buildDropDown(self.dropDownBuilder, {'dataHolder': dataHolder, 'data':serverData});
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message : textStatus, messageType : 'alert'}, "general");
				}
			});
		};

		self.selectedGroup.subscribe(function(newValue) {
			self.loadData(newValue);
			self.loadResources();
			self.reloadTbl();
		});

		self.loadRoleRLPData = function() {
			$.ajax({
				'url' : xoappcontext + '/rolerlp',
				'type' : 'GET',
				'cache' : false,
				'success' : function(serverResponse) {
					instanceTbl = self.buildDataTableWithData('allRLPEntities', self.buildRLPInstanceRecord, serverResponse, null, instanceTbl);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message : textStatus, messageType : 'alert'}, "general");
				}
			});
		};

		self.loadUserRLPData = function() {
			$.ajax({
				'url' : xoappcontext + '/userrlp',
				'type' : 'GET',
				'cache' : false,
				'success' : function(serverResponse) {
					instanceTbl = self.buildDataTableWithData('allRLPEntities', self.buildRLPInstanceRecord, serverResponse, null, instanceTbl);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message : textStatus, messageType : 'alert'}, "general");
				}
			});
		};

		self.reloadTbl = function() {
			if(self.selectedGroup() == 'User') {
				self.self.loadUserRLPData();
			} else if(self.selectedGroup() == 'Role') {
				self.loadRoleRLPData();
			}
		}

		self.buildRLPInstanceRecord = function(serverResponse) {
			if(serverResponse) {
				self.availableEntityInstances.removeAll();
				var instanceIndex = 0;
				var totalInstances = serverResponse.length;
				for(;instanceIndex < totalInstances; instanceIndex++) {
					var entityInstance = serverResponse[instanceIndex];
					entityInstance.active = ko.observable(serverResponse[instanceIndex].active);
					entityInstance.deleteRecord = self.deleteRecord;
					entityInstance.toggleActiveStatus = self.toggleActiveStatus; 
					self.availableEntityInstances.push(entityInstance);
				}
			}
		};

		self.showPopup = function() {
			var tempStatus = false;
			if(self.selectedGroup() == 'User') {
				tempStatus = self.selectedUser() && self.selectedUser().displayText;
			} else if(self.selectedGroup() == 'Role') {
				tempStatus = self.selectedRole() && self.selectedRole().displayText; 
			}
			tempStatus = tempStatus && self.selectedResource() && self.selectedResource().displayText;
			if(tempStatus) {
				self.loadUnAssignedInstances();
				$('#addrlpModel').foundation('reveal', 'open');
			} else {
				alert('Please select the entity type and ' + self.selectedGroup() + ' in filters.');				
			}
		 };

		self.loadUnAssignedInstances = function() {
			var url = xoappcontext;
			if(self.selectedGroup() == 'User') {
				url = url + '/userrlp/ressourceinstanceids/' + self.selectedUser().entityId;
			} else if(self.selectedGroup() == 'Role') {
				url = url + '/rolerlp/ressourceinstanceids/'  + self.selectedRole().entityId;
			}
			url = url + '/' + self.selectedResource().entityId;
			$.ajax({
				'url' : url,
				'type' : 'GET',
				'cache' : false,
				'success' : function(serverResponse) {
					unAssinstanceTbl = self.buildDataTableWithData('allUnAssignedRlpInstances', self.buildRLPUnAssInstanceRecord, serverResponse, null, unAssinstanceTbl);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message : textStatus, messageType : 'alert' }, "general");
				}
			});
		};

		self.buildRLPUnAssInstanceRecord = function(serverResponse) {
			if(serverResponse) {
				self.unAssignedInstances.removeAll();
				var instanceIndex = 0;
				var totalInstances = serverResponse.length;
				for(;instanceIndex < totalInstances; instanceIndex++) {
					var entityInstance = {
							'displayText' : serverResponse[instanceIndex].value,
							'entityId' : serverResponse[instanceIndex].key
						};
					entityInstance.addRecord = self.addRecord;
					self.unAssignedInstances.push(entityInstance);
				}
			}
		};

		self.addRecord = function(data, event) {
			if(data, event) {

				var aPos = $(event.currentTarget).parents('tr')
                	$(aPos).hide("medium", function () {
                		unAssinstanceTbl.row($(aPos)).remove();
                		unAssinstanceTbl.draw();
                } );

				var url = xoappcontext;
				if(self.selectedGroup() == 'User') {
					url = url + '/userrlp';
				} else if(self.selectedGroup() == 'Role') {
					url = url + '/rolerlp';
				}
				var rowLevelPerDto = {
						'roleId' : (self.selectedRole() ? self.selectedRole().entityId : -9999), 
						'userId': (self.selectedUser() ? self.selectedUser().entityId : -9999),
						'resourceTypeId':self.selectedResource().entityId,
						'entityId' : data.entityId,
						'displayText':data.displayText};
				$.ajax({
					'url' : url,
					'type' : 'POST',
					'cache' : false,
					'data'	: JSON.stringify(rowLevelPerDto),
					'contentType' : "application/json; charset=utf-8",
					'success' : function(serverResponse) {
						setGlobalMessage(serverResponse, "popup");
						self.reloadTbl();
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({message : textStatus, messageType : 'alert'}, "popup");
					}
				});
			}
		};

		self.toggleActiveStatus = function(data, event) {

			if(data, event) {
				var url = xoappcontext;
				if(self.selectedGroup() == 'User') {
					url = url + '/userrlp/toggleactive/' + data.userPermissionId + '/' + data.entityId;
				} else if(self.selectedGroup() == 'Role') {
					url = url + '/rolerlp/toggleactive/' + data.rolePermissionId + '/' + data.entityId;
				}
				$.ajax({
					'url':  url,
					'type':'PUT',
					'cache':false,
					'success' : function(responsedata) {
						data.active(!data.active());
						setGlobalMessage(responsedata,"general");
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({message:textStatus, messageType:'alert'},"general");
					}
				});
			}
		};

		self.deleteRecord = function(data, event) {

			if(data, event) {
				var url = xoappcontext;
				if(self.selectedGroup() == 'User') {
					url = url + '/userrlp/' + data.userPermissionId + '/' + data.entityId;
				} else if(self.selectedGroup() == 'Role') {
					url = url + '/rolerlp/' + data.rolePermissionId + '/' + data.entityId;
				}
				$.ajax({
					'url':  url,
					'type':'DELETE',
					'cache':false,
					'success' : function(responsedata) {
						setGlobalMessage(responsedata,"general");
						self.reloadTbl();
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({message:textStatus, messageType:'alert'},"general");
					}
				});
			}
		};

		return {
			selectedResource:self.selectedResource,
			currentPage : self.currentPage,
			visibility : self.visibility,
			rowlevelpermfor : self.rowlevelpermfor,
			selectedGroup : self.selectedGroup,
			selectedUser : self.selectedUser,
			selectedRole : self.selectedRole,
			availableusers : self.availableusers,
			availableroles : self.availableroles,
			availableEntityInstances:self.availableEntityInstances,
			availableresources:self.availableresources,
			cleanup:self.cleanup,
			toggleActiveStatus:self.toggleActiveStatus,
			unAssignedInstances:self.unAssignedInstances,
			showPopup:self.showPopup,
		};
	}
	RowLevelPermissionModel.prototype = new BaseModel(ko, $);
	RowLevelPermissionModel.prototype.constructor = RowLevelPermissionModel;
	return RowLevelPermissionModel;
});