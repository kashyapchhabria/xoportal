/**
 * User role management
 */
define(['knockout', 'jquery'], function(ko, $) {

	function RolePermissionManagerModel(cRole) {

		BaseModel.call(this, ko, $);
		var self = this;
		self.curRole = ko.observable(cRole);
		self.availableRolesPermission= ko.observableArray([]);
		var rolepermtbl = null;
		var permtbl = null;
		self.availablePermissions = ko.observableArray([]);

	// Handle Roles permission details
		self.loadRolesPermission = function() {

			$.ajax({
				'url':  xoappcontext + '/rolepermission/' + self.curRole().roleId,
				'type':'GET',
				'cache':false,
				'success' : function(serverData) {
					rolepermtbl = self.buildDataTableWithData('allAvailablePermissions', self.buildRolesPermissionData, serverData, null, rolepermtbl);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message:textStatus, messageType:'alert'},"general");
				}
			});
		};



		self.buildRolesPermissionData = function(rolespermissiondetails) {
			var i = 0;
			var totalDetails = rolespermissiondetails.length;
			self.availableRolesPermission.removeAll();
			for(;i < totalDetails; i++){
				var rolePermissionobj = rolespermissiondetails[i];
				var tempObj = {
					    rolePermissionId:rolePermissionobj.rolePermissionId,
						roleId:rolePermissionobj.roleId,
						permissionId:rolePermissionobj.permissionId,
						r_name:rolePermissionobj.r_name,
						p_name:rolePermissionobj.p_name,
						description:rolePermissionobj.description,
						active:ko.observable(rolePermissionobj.active),
						toggleRolePermissionStatus:self.toggleRolePermissionStatus,
						deleteRolePermission:self.deleteRolePermission
				};
				self.availableRolesPermission.push(tempObj);
			}
		};


		self.toggleRolePermissionStatus = function(data, event) {

			if(data && event) {
				$.ajax({
					'url':  xoappcontext + '/rolepermission/toggleactive/' + data.rolePermissionId,
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

		self.deleteRolePermission = function(data, event) {
			if(data && event) {
				var aPos = $(event.currentTarget).parents('tr')
                $(aPos).hide("medium", function () {
                rolepermtbl.row($(aPos)).remove();
                rolepermtbl.draw();
                } );
                    $.ajax({
					'url':  xoappcontext + '/rolepermission/' + data.rolePermissionId ,
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




// permission pop up functions Below

             self.loadPopup = function(data, event) {
                self.loadPermissions();
                $('#permissionAssignModel').foundation('reveal', 'open');
             };

            self.loadPermissions = function() {
                    $.ajax({
                        'url':  xoappcontext + '/permissions/unassigned/' + self.curRole().roleId,
                        'type':'GET',
                        'cache':false,
                        'success' : function(serverData) {
                            permtbl = self.buildDataTableWithData('allAvailablePermissions_pop', 
                            		self.buildPermissionData, 
                            		serverData, 
                            		{'responsive':true,"bLengthChange":false,"iDisplayLength":6}, 
                            		permtbl);
                        },
                        'error' : function(jqXHR, textStatus, errorThrown) {
                            setGlobalMessage({message:textStatus, messageType:'alert'},"general");
                        }
                    });
                };



            self.buildPermissionData = function(permissiondetails) {
                    var i = 0;
                    var totalPermissions = permissiondetails.length;
                    self.availablePermissions.removeAll();
                    for(;i < totalPermissions; i++){
                        var permissionobj = permissiondetails[i];
                        var tempObj = {
                                permissionId:permissionobj.permissionId,
                                name:permissionobj.name,
                                description:permissionobj.description,
                                active:ko.observable(permissionobj.active),
                                togglePermissionStatus:self.togglePermissionStatus,
                                updateRolePermission:self.updateRolePermission

                        };
                        self.availablePermissions.push(tempObj);
                    }
                }


            self.updateRolePermission = function(data, event) {
                if(data && event) {
                var aPos = $(event.currentTarget).parents('tr')
                                                $(aPos).hide("medium", function () {
                                                permtbl.row($(aPos)).remove();
                                                permtbl.draw();
                                            } );

                    $.ajax({
                        'url':  xoappcontext + '/rolepermission/' + self.curRole().roleId + '/' + data.permissionId ,
                        'type':'POST',
                        'cache':false,
                        'success' : function(responsedata) {
                           self.loadRolesPermission();
                           setGlobalMessage(responsedata,"popup");
                        },
                        'error' : function(jqXHR, textStatus, errorThrown) {
                            setGlobalMessage({message:textStatus, messageType:'alert'},"popup");
                        }
                    });
                }
            };

		return {
			availableRolesPermission:self.availableRolesPermission,
			loadRolesPermission:self.loadRolesPermission,
			currentPage: self.currentPage,
			visibility:self.visibility,
			curRole:self.curRole,
			loadPopup:self.loadPopup,
			availablePermissions:self.availablePermissions,
            loadPermissions:self.loadPermissions,
            permissionId:self.permissionId,
            name:self.name,
            description:self.description,
            visibility:self.visibility
		};
	}
	RolePermissionManagerModel.prototype = new BaseModel(ko, $);
	RolePermissionManagerModel.prototype.constructor = RolePermissionManagerModel;
	return RolePermissionManagerModel;
});
