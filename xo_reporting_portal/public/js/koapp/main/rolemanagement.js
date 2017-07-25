/**
 * Role management
 */
define(['knockout', 'jquery'], function(ko, $) {

	function RoleManagerModel() {

		BaseModel.call(this, ko, $);
		var self = this;
		self.availableRoles = ko.observableArray([]);
		self.currentRole = ko.observable({});
		var roletbl = null;
		self.loadRoles = function() {
			$.ajax({
				'url':  xoappcontext + '/roles',
				'type':'GET',
				'cache':false,
				'success' : function(serverData) {
					roletbl = self.buildDataTableWithData('allAvailableRoles', self.buildRoleData, serverData, null, roletbl);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message:textStatus, messageType:'alert'},"general");
				}
			});
		};

		self.buildRoleData = function(roledetails) {
			var i = 0;
			var totalRoles = roledetails.length;
			self.availableRoles.removeAll();
			for(;i < totalRoles; i++){
				var roleobj = roledetails[i];
				var tempObj = {
						roleId:roleobj.roleId,
						name:roleobj.name,
						description:roleobj.description,
						active:ko.observable(roleobj.active),
						toggleStatus:self.toggleRoleActiveStatus,
						loadRole:self.loadRole,
						deleteRole:self.deleteRole,
						deleteRoleCheck:self.deleteRoleCheck,
						showRolesPermission:self.showRolesPermission					
				};
				self.availableRoles.push(tempObj);
			}
		};
		self.roleId = ko.observable(false);
		self.name = ko.observable().extend({ required: rolename_err }),
		self.description = ko.observable();

		self.newRole = function(data, event) {
			self.roleId(false);
			self.name(''),
			self.description(''),			
			setLocationHash('createrole');
		};

		self.saveRole = function() {
			$("#preloader").show(true);
			// Create a formdata object and add the files
			var roleDto = {roleId:self.roleId(), name:self.name(), description:self.description()};
			if(roleDto.roleId == false) {
				roleDto.roleId = null;
			}
			var data = JSON.stringify(roleDto);
			var reqUrl = xoappcontext + (roleDto.roleId ? '/roles/' + roleDto.roleId : '/roles');
			var reqMethod = roleDto.roleId ? 'PUT' : 'POST';
			$.ajax({
				'url': reqUrl,
				'type': reqMethod,
				'cache':false,
				'data':data,
				'contentType': "application/json; charset=utf-8",
				'success' : function(responseData) {
					setGlobalMessage(responseData,"general");
					if(responseData.messageType == 'success') {						
						setTimeout(function(){
							setLocationHash('rolemanagement');
						}, 3000);
					}
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message:textStatus, messageType:'alert'},"general");
				}
			});
		};

		self.toggleRoleActiveStatus = function(data, event) {

			if(data && event) {
				$.ajax({
					'url':  xoappcontext + '/roles/toggleactive/' + data.roleId,
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

         self.deleteRole = function(roleId, rowPosition){

            $.ajax({
                'url':  xoappcontext + '/roles/' + roleId,
                'type':'DELETE',
                'cache':false,
                'success' : function(responsedata) {
                    $(rowPosition).hide("medium", function () {
                        roletbl.row($(rowPosition)).remove();
                        roletbl.draw();
                    });
                    setGlobalMessage(responsedata,"general");
                 },
                 'error' : function(jqXHR, textStatus, errorThrown) {
                    setGlobalMessage({message:textStatus, messageType:'alert'},"general");
                  }
            });
        };


		self.deleteRoleCheck = function(data, event) {

			if(data && event) {
			    var rowPosition = $(event.currentTarget).parents('tr');
			    var roleId = data.roleId;

                 $.ajax({
                     'url':  xoappcontext + '/userroles/roles_count/' + data.roleId,
                     'type':'GET',
                     'cache':false,
                     'success' : function(resultData) {
                        self.showDeleteConfirmation(resultData, roleId, rowPosition);
                     },
                     'error' : function(jqXHR, textStatus, errorThrown) {
                        setGlobalMessage({message:textStatus, messageType:'alert'},"general");
                     }
                 });
		    }
		};

        self.showDeleteConfirmation = function(resultData, roleId, rowPosition) {

            if(resultData != 0){
                if(confirm(role_delete_cnfrm) == true){
                    self.deleteRole(roleId, rowPosition);
                }
            } else {
                self.deleteRole(roleId, rowPosition);
            }
        };

		self.loadRole = function(data, event) {

			if(data && event) {
				$.ajax({
					'url':  xoappcontext + '/roles/' + data.roleId,
					'type':'GET',
					'cache':false,
					'success' : function(responsedata) {
						if(responsedata.roleId) {
							self.roleId(responsedata.roleId);
							self.name(responsedata.name);
							self.description(responsedata.description);
							setLocationHash('createrole');
						} else {						
							setGlobalMessage(responsedata,"general");
						}
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({message:textStatus, messageType:'alert'},"general");
					}
				});
			}
		};

		self.showRolesPermission = function(data, event) {
			if(data && event) {
				var roleObj = {"roleId":data.roleId, "name":data.name};
				self.currentRole(roleObj);
				setLocationHash('rolePermissionmanagement');
			}
		};
		
		return {
			availableRoles:self.availableRoles,
			loadRoles:self.loadRoles,
			currentPage: self.currentPage,
			roleId:self.roleId,
			permissionId:self.permissionId,
			name:self.name,
			description:self.description,
			newRole:self.newRole,
			saveRole:self.saveRole,
			currentRole :self.currentRole,
			visibility:self.visibility
		};
	}
	RoleManagerModel.prototype = new BaseModel(ko, $);
	RoleManagerModel.prototype.constructor = RoleManagerModel;
	return RoleManagerModel;
});
