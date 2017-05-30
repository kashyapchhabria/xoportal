/**
 * Permission management
 */
define(['knockout', 'jquery'], function(ko, $) {

	function PermissionManagerModel() {

		BaseModel.call(this, ko, $);
		var self = this;
		self.availablePermissions = ko.observableArray([]);
		var configtable = null;

		self.loadPermissions = function() {
			$.ajax({
				'url':  xoappcontext + '/permissions',
				'type':'GET',
				'cache':false,
				'success' : function(serverData) {
					configtable = self.buildDataTableWithData('allAvailablePermissions', self.buildPermissionData, serverData, null, configtable);
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
						toggleStatus:self.togglePermissionActiveStatus
				
				};
				self.availablePermissions.push(tempObj);
			}
		}


        self.togglePermissionActiveStatus = function(data, event) {

            if(data && event) {
                $.ajax({
                    'url':  xoappcontext + '/permissions/toggleactive/' + data.permissionId,
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

		return {
			availablePermissions:self.availablePermissions,
			loadPermissions:self.loadPermissions,
			currentPage: self.currentPage,			
			permissionId:self.permissionId,
			name:self.name,
            description:self.description,
			visibility:self.visibility
		};
	}
	PermissionManagerModel.prototype = new BaseModel(ko, $);
	PermissionManagerModel.prototype.constructor = PermissionManagerModel;
	return PermissionManagerModel;
});
