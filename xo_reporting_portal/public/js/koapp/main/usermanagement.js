/**
 * User management
 */
define(['knockout', 'jquery', 'knockout.validation'], function(ko, $, validation) {

	function UserManagerModel() {

		BaseModel.call(this, ko, $);
		var self = this;

		self.errors = ko.validation.group(this, { deep: true, observable: false });
		self.availableUsers = ko.observableArray([]);
		self.availableClients = ko.observableArray([]);
		self.userId = ko.observable(false);
		self.selectedClient = ko.observable({}).extend({required: {'message':"Please select the client"}});
		self.firstName = ko.observable().extend({ required: {'message':"Please enter a first name"}});
		self.lastName = ko.observable();
		self.email= ko.observable().extend({ required: {'message':"Please enter a email id"}});
		self.currentUser = ko.observable({});
		var usertbl = null;

		self.loadUsers = function() {
			self.loadClients();
			$.ajax({
				'url':  xoappcontext + '/users',
				'type':'GET',
				'cache':false,
				'success' : function(serverData) {
					if(serverData && serverData.message) {
						setGlobalMessage(serverData, "general");
					}
					usertbl = self.buildDataTableWithData('allAvailableUsers', self.buildUserData, serverData, null, usertbl);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message:textStatus, messageType:'alert'},"general");
				}
			});
		};

		self.buildUserData = function(userdetails) {
			var i = 0;
			var totalUsers = userdetails.length;
			self.availableUsers([]);
			for(;i < totalUsers; i++){
				var userobj = userdetails[i];
				var tempObj = {
						userId:userobj.userId,
						name:userobj.firstName + ' ' + userobj.secondName,
						active:ko.observable(userobj.active),
						email:userobj.email,
						lastLoginDt:userobj.lastLoginDt,
						deleted:ko.observable(userobj.deleted),
						toggleStatus:self.toggleUserActiveStatus,
						deleteUser:self.deleteUser,
						loadUser:self.loadUser,
						resetpassword:self.resetpassword,
						showUserRoles:self.showUserRoles,
						showUserPermissions:self.showUserPermissions
				};
				self.availableUsers.push(tempObj);
			}
		};

		self.toggleUserActiveStatus = function(data, event) {

			if(data && event) {
				$.ajax({
					'url':  xoappcontext + '/users/toggleactive/' + data.userId,
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

		self.deleteUser = function(data, event) {

			if(data && event) {
				$.ajax({
					'url':  xoappcontext + '/users/' + data.userId,
					'type':'DELETE',
					'cache':false,
					'success' : function(responsedata) {
						data.active(!data.active());
						data.deleted(!data.deleted());
						setGlobalMessage(responsedata,"general");
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({message:textStatus, messageType:'alert'},"general");
					}
				});
			}
		};

		self.saveSurveyor = function() {
			// check if valid
            if(self.errors().length > 0) {
                self.errors.showAllMessages();
                return;
            }
			$("#preloader").show();

			// Create a formdata object and add the files
			var userDto = {
						userId:self.userId(), 
						firstName:self.firstName(), 
						secondName:self.lastName(), 
						email:self.email(), 
						clientId:self.selectedClient().clientId
					};
			if(userDto.userId == false) {
				userDto.userId = null;
			}
			var data = JSON.stringify(userDto);
			var reqUrl = xoappcontext + (userDto.userId ? '/users/' + userDto.userId : '/users');
			var reqMethod = userDto.userId ? 'PUT' : 'POST';
			$.ajax({
				'url': reqUrl,
				'type': reqMethod,
				'cache':false,
				'data':data,
				'contentType': "application/json; charset=utf-8",
				'success' : function(responseData) {
					$('#preloader').fadeOut("slow");
					setGlobalMessage(responseData,"general");
					if(responseData.messageType == 'success') {						
						setTimeout(function(){
							setLocationHash('usermanagement');
						}, 3000);
					}
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({message:textStatus, messageType:'alert'},"general");
				}
			});

		};

		self.loadUser = function(data, event) {
			if(data && event) {
				$.ajax({
					'url':  xoappcontext + '/users/' + data.userId,
					'type':'GET',
					'cache':false,
					'success' : function(responsedata) {
						if(responsedata.userId) {
							self.userId(responsedata.userId);
							self.firstName(responsedata.firstName);
							self.lastName(responsedata.secondName);
							self.email(responsedata.email);
							self.selectedClient(self.availableClients.find("clientId", 
											{'clientId' : responsedata.clientId, 'name': responsedata.clientName}));
							setLocationHash('createsurveyor');
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

		self.newUser = function(data, event) {
			self.userId(false);
			self.firstName(''),
			self.lastName('');
			self.email('');
			self.selectedClient({});
			setLocationHash('createsurveyor');
		};

		self.bulkUploadUsers = function(data, event) {
			setLocationHash('uploadusers');
		};

		self.uploadUsers = function() {
			$("#preloader").show(true);

			files = $('#user_entries').prop("files");

			if(files && files.length == 0) {
				alert("Please select at least one user entries file.");
				return;
			}
			
			// Create a formdata object and add the files
			var data = new FormData();
			$.each(files, function(key, value) {
				data.append(key, value);
			});
			$.ajax({
				'url':  xoappcontext + '/users/upload',
				'type':'POST',
				'data':data,
				'cache':false,
				'dataType':'json',
				'processData':false, // Don't process the files
				'contentType': false,
				'success' : function(data) {
					setGlobalMessage(data,"general");
					setTimeout(function() {
						$('#preloader').fadeOut("slow");
						setLocationHash('usermanagement');
					}, 5000);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					$('#preloader').fadeOut("slow");
					setGlobalMessage(data,"general");
				}
			});
		};

		self.changepassword = function() {

			// Create a formdata object
			var data = $("#passwordReset").serialize();
			$.ajax({
				'url':  xoappcontext + '/users/changepassword',
				'cache':false,
				'type':'POST',
				'data':data,
				'success' : function(responsedata) {
						if(responsedata && responsedata.messageType && responsedata.messageType == 'success') {
							setGlobalMessage(responsedata,"general");
							setTimeout(function(responsedata){
								setLocationHash();
							}, 5000);
						} else {
							setGlobalMessage(responsedata,"general");						
						}					
				},
				'error' : function(jqXHR, textStatus, responsedata) {
					setGlobalMessage(responsedata,"general");
				}
			});
		};

		self.resetpassword = function(data, event) {

			if(data && event) {
				$.ajax({
					'url':  xoappcontext + '/users/resetpassword/' + data.userId,
					'cache':false,
					'type':'PUT',
					'success' : function(responsedata) {
						setGlobalMessage(responsedata,"general");
					},
					'error' : function(jqXHR, textStatus, responsedata) {
						setGlobalMessage(responsedata,"general");
					}
				});
			}
		};

		self.showUserRoles = function(data, event) {
			if(data && event) {
				var userObj = {"userId":data.userId, "name":data.name};
				self.currentUser(userObj);
				setLocationHash('userrolemanagement');
			}
		};

		self.showUserPermissions = function(data, event) {
			if(data && event) {
				var userObj = {"userId":data.userId, "name":data.name};
				self.currentUser(userObj);
				setLocationHash('userpermissionmanagement');
			}
		};

		self.loadClients = function() {
			$.ajax({
				'url' : xoappcontext + '/clients',
				'type' : 'GET',
				'cache' : false,
				'success' : function(serverResponse) {
					self.buildClientDropDown(serverResponse);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({
						message : textStatus,
						messageType : 'alert'
					}, "general");
				}
			});
		};

		self.buildClientDropDown = function(clientDetails){
			var i = 0;
			var totalClients = clientDetails.length;
			self.availableClients.removeAll();
			for(;i < totalClients; i++){
				var clientobj = clientDetails[i];
				var tempObj = {
						'clientId':clientobj.clientId,
						'name':clientobj.clientName
				};
				self.availableClients.push(tempObj);
			}
		};

		return {
			availableUsers:self.availableUsers,
			loadUsers:self.loadUsers,
			currentPage: self.currentPage,
			saveSurveyor:self.saveSurveyor,
			userId:self.userId,
			firstName:self.firstName,
			lastName:self.lastName,
			email:self.email,
			newUser:self.newUser,
			visibility:self.visibility,
			changepassword:self.changepassword,
			currentUser :self.currentUser,
			bulkUploadUsers:self.bulkUploadUsers,
			uploadUsers:self.uploadUsers,
			availableClients:self.availableClients,
			selectedClient:self.selectedClient,
			loadClients:self.loadClients
		};
	}
	UserManagerModel.prototype = new BaseModel(ko, $);
	UserManagerModel.prototype.constructor = UserManagerModel;
	return UserManagerModel;
});
