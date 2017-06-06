/**
 * User role management
 */
define(['knockout', 'jquery'], function(ko, $) {

	function UserRoleManagerModel(cUser) {

		BaseModel.call(this, ko, $);
		var self = this;
		self.curUser = ko.observable(cUser);
		self.availableUserRoles = ko.observableArray([]);
		var userroletbl = null;
		var popup = null;
		self.availableUnassignedRoles = ko.observableArray([]);
		self.checkedRoles=ko.observableArray([])
		self.editUserRoles = function() {
		}
		
		self.loadUserRoles = function() {

			$.ajax({
				'url':  xoappcontext + '/userroles/' + self.curUser().userId,
				'type':'GET',
				'cache':false,
				'success' : function(serverData) {
					userroletbl = self.buildDataTableWithData('allAvailableUserRoles', self.buildUserRoleData, serverData, null, userroletbl);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message:textStatus, messageType:'alert'},"general");
				}
			});
		};

		self.buildUserRoleData = function(userroledetails) {
			var i = 0;
			var totalUserRoles = userroledetails.length;
			self.availableUserRoles([]);
			for(;i < totalUserRoles; i++){
				var userroleobj = userroledetails[i];
				var tempObj = {
						userId:userroleobj.userId,
						roleId:userroleobj.roleId,
						name:userroleobj.name,
						active:ko.observable(userroleobj.active),
						toggleUserRoleStatus:self.toggleUserRoleStatus,
						deleteUserRole:self.deleteUserRole
				};
				self.availableUserRoles.push(tempObj);
			}
		};

		self.toggleUserRoleStatus = function(data, event) {

			if(data && event) {
				$.ajax({
					'url':  xoappcontext + '/userroles/toggleactive/' + data.userId + '/' + data.roleId,
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


		self.deleteUserRole = function(data, event) {
			if(data && event) {
			var aPos = $(event.currentTarget).parents('tr')
            $(aPos).hide("medium", function () {
            userroletbl.row($(aPos)).remove();
            userroletbl.draw();
            } );
				$.ajax({
					'url':  xoappcontext + '/userroles/' + data.userId + '/' + data.roleId,
					'type':'DELETE',
					'cache':false,
					'success' : function(responsedata) {
						setGlobalMessage(responsedata,"general");
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({message:textStatus, messageType:'alert'},"general");
					}
				});
			}
		};

		self.loadPopup = function(data, event) {
			self.loadUnassignedRoles();
			loadPopup("myModal");
		};


		var poptbl = null;

		self.loadUnassignedRoles = function() {

			$.ajax({
				'url':  xoappcontext + '/roles/unassigned/' + self.curUser().userId,
				'type':'GET',
				'cache':false,
				'success' : function(serverData) {
					poptbl = self.buildDataTableWithData('UnassignedUserRoles', self.buildUnassignedRoleData, serverData, 
							{responsive:true, "bLengthChange":false,"iDisplayLength":6}, poptbl);

				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message:textStatus, messageType:'alert'},"popup");
				}
			});
		};

		self.buildUnassignedRoleData = function(roledetails) {
			var i = 0;
			var totalRoles = roledetails.length;
			self.availableUnassignedRoles([]);
			for(;i < totalRoles; i++){
				var roleobj = roledetails[i];
				var tempObj = {
						roleId:roleobj.roleId,
						name:roleobj.name,
						active:ko.observable(roleobj.active),
						updateUserRole:self.updateUserRole

				};
				self.availableUnassignedRoles.push(tempObj);
			}
		};

		self.updateUserRole= function(data, event) {
			if(data && event) {
			var aPos = $(event.currentTarget).parents('tr')
                        $(aPos).hide("medium", function () {
                        poptbl.row($(aPos)).remove();
                        poptbl.draw();
                        } );
				$.ajax({
					'url':  xoappcontext + '/userroles/' + self.curUser().userId+ '/' + data.roleId ,
					'type':'POST',
					'cache':false,
					'success' : function(responsedata) {
						self.loadUserRoles();
						setGlobalMessage(responsedata,"popup");
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({message:textStatus, messageType:'alert'},"popup");
					}
				});
			}

		}


		return {
			availableUserRoles:self.availableUserRoles,
			checkedRoles:self.checkedRoles,
			availableUnassignedRoles:self.availableUnassignedRoles,
			loadPopup:self.loadPopup,
			loadUserRoles:self.loadUserRoles,
			loadUnassignedRoles:self.loadUnassignedRoles,
			currentPage: self.currentPage,
			visibility:self.visibility,
			curUser:self.curUser
		};
	}
	UserRoleManagerModel.prototype = new BaseModel(ko, $);
	UserRoleManagerModel.prototype.constructor = UserRoleManagerModel;
	return UserRoleManagerModel;
});
