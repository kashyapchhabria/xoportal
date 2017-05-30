/**
 * User perm management
 */
define(['knockout', 'jquery'], function(ko, $) {

	function UserPermissionManagerModel(cUser) {

		BaseModel.call(this, ko, $);
		var self = this;
		self.curUser = ko.observable(cUser);
		self.availableUserPermissions = ko.observableArray([]);
		self.visibility = ko.observable(false);
		var userpermtbl = null;
		var popup = null;
		self.availableUnassignedPermissions = ko.observableArray([]);
		self.checkedPermissions=ko.observableArray([])
				
		self.loadUserPermissions = function() {

			$.ajax({
				'url':  xoappcontext + '/userpermission/' + self.curUser().userId,
				'type':'GET',
				'cache':false,
				'success' : function(serverData){
					userpermtbl = self.buildDataTableWithData('allAvailableUserPermissions',
									self.buildUserPermissionData,
									serverData,
									{'responsive':false},
									userpermtbl);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message:textStatus, messageType:'alert'},"general");
				}
			});
		};

		self.buildUserPermissionData = function(userpermdetails) {
			var i = 0;
			var totalUserPermissions = userpermdetails.length;
			self.availableUserPermissions([]);
			for(;i < totalUserPermissions; i++){
				var userpermobj = userpermdetails[i];
				var tempObj = {
						userPermissionId:userpermobj.userPermissionId,
						userId:userpermobj.userId,
						permId:userpermobj.permissionId,
						name:userpermobj.name,
						active:ko.observable(userpermobj.active),
						toggleUserPermissionStatus:self.toggleUserPermissionStatus,
						deleteUserPermission:self.deleteUserPermission
				};
				self.availableUserPermissions.push(tempObj);
			}
		};

		self.toggleUserPermissionStatus = function(data, event) {

			if(data && event) {
				$.ajax({
					'url':  xoappcontext + '/userpermission/toggleactive/' + data.userPermissionId ,
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


		self.deleteUserPermission = function(data, event) {
			if(data && event) {
			var aPos = $(event.currentTarget).parents('tr')
            $(aPos).hide("medium", function () {
            userpermtbl.row($(aPos)).remove();
            userpermtbl.draw();
            } );
				$.ajax({
					'url':  xoappcontext + '/userpermission/' + data.userPermissionId ,
					'type':'DELETE',
					'cache':false,
					'success' : function(responsedata) {

						setGlobalMessage(responsedata,"general");
						$(".se-pre-con").fadeOut("slow");
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({message:textStatus, messageType:'alert'},"general");
						$(".se-pre-con").fadeOut("slow");
					}
				});
			}
		};

		self.loadPopup = function(data, event) {
			self.loadUnassignedPermissions();
			popup = loadPopup('myModal');
		};


		var poptbl = null;

		self.loadUnassignedPermissions = function() {

			$.ajax({
				'url':  xoappcontext + '/permissions/unassigned_user_permissions/' + self.curUser().userId,
				'type':'GET',
				'cache':false,
				'success' : function(serverData) {
					poptbl = self.buildDataTableWithData('UnassignedUserPermissions',
								self.buildUnassignedPermissionData,
								serverData,
								{responsive: false, "bLengthChange": false, "iDisplayLength": 5 },
								poptbl);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message:textStatus, messageType:'alert'},"popup");
				}
			});
		};

		self.buildUnassignedPermissionData = function(permdetails) {
			var i = 0;
			var totalPermissions = permdetails.length;
			self.availableUnassignedPermissions([]);
			for(;i < totalPermissions; i++){
				var permobj = permdetails[i];
				var tempObj = {
						permId:permobj.permissionId,
						name:permobj.name,
						active:ko.observable(permobj.active),
						updateUserPermission:self.updateUserPermission

				};
				self.availableUnassignedPermissions.push(tempObj);
			}
		};

		self.updateUserPermission= function(data, event) {
			if(data && event) {

            var aPos = $(event.currentTarget).parents('tr')
            $(aPos).hide("medium", function () {
            poptbl.row($(aPos)).remove();
            poptbl.draw();
            } );
				$.ajax({
					'url':  xoappcontext + '/userpermission/' + self.curUser().userId+ '/' + data.permId ,
					'type':'POST',
					'cache':false,
					'success' : function(responsedata) {
						self.loadUserPermissions();
						setGlobalMessage(responsedata,"popup");
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({message:textStatus, messageType:'alert'},"popup");
					}
				});
			}

		}


		return {
			availableUserPermissions:self.availableUserPermissions,
			checkedPermissions:self.checkedPermissions,
			availableUnassignedPermissions:self.availableUnassignedPermissions,
			loadPopup:self.loadPopup,
			loadUserPermissions:self.loadUserPermissions,
			loadUnassignedPermissions:self.loadUnassignedPermissions,
			currentPage: self.currentPage,
			visibility:self.visibility,
			curUser:self.curUser
		};
	}
	UserPermissionManagerModel.prototype = new BaseModel(ko, $);
	UserPermissionManagerModel.prototype.constructor = UserPermissionManagerModel;
	return UserPermissionManagerModel;
});
