/**
 * Configuration management
 */
define([ 'knockout', 'jquery' ], function(ko, $) {

	function JobManagerModel() {

		BaseModel.call(this, ko, $);
		var self = this;

		self.currentPage = ko.observable('jobs');
		self.visibility = ko.observable(false);
		self.selectedClient = ko.observable({});
		self.selectedJob = ko.observable({});
		self.selectedInstance = ko.observable({});
		self.allJobs = ko.observableArray([]);
		self.availableConfigs = ko.observableArray([]);
		self.addavailableJobs = ko.observableArray([]);
		self.addavailableInstances = ko.observableArray([]);
		self.availableClients = ko.observableArray([]);
		var jobConfigtable = null;

		if ($('#authtokenvalue')) {
			$.ajaxSetup({
				headers : {
					'X-Xoanon-Auth' : $('#authtokenvalue').attr('authtoken')
				}
			});
		}

		self.cleanup = function() {
			self.allJobs.removeAll();
			self.availableClients.removeAll();
			self.addavailableJobs.removeAll();
			self.addavailableInstances.removeAll();
		};

//Pop Up data Functions>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		self.loadPopup = function() {
			self.loadClients();
			self.loadallJobsForAdding();
			self.loadallConfigInstances();
			loadPopup("myModal");
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
						clientId:clientobj.clientId,
						name:clientobj.clientName
				};
				self.availableClients.push(tempObj);
			}
		};

		self.loadallJobsForAdding = function() {
			$.ajax({
				'url' : xoappcontext + '/jobs',
				'type' : 'GET',
				'cache' : false,
				'success' : function(serverResponse) {
				self.buildJobDropDown(serverResponse);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({
						message : textStatus,
						messageType : 'alert'
					}, "general");
				}
			});
		};

        self.buildJobDropDown = function(jobdropdowndetails){
			var i = 0;
			var totalJobs = jobdropdowndetails.length;
			self.addavailableJobs.removeAll();
			for(;i < totalJobs; i++){
				var addjobobj = jobdropdowndetails[i];
				var tempObj = {
						jobId:addjobobj.jobId,
						jobName:addjobobj.jobName
				};
				self.addavailableJobs.push(tempObj);
			}
		};

		self.loadallConfigInstances = function() {
			$.ajax({
				'url' : xoappcontext + '/configinstances',
				'type' : 'GET',
				'cache' : false,
				'success' : function(serverResponse) {
					self.buildInstancesDropDown(serverResponse);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({
						message : textStatus,
						messageType : 'alert'
					}, "general");
				}
			});
		};

        self.buildInstancesDropDown = function(instanceDetails){
			var i = 0;
			var totalInstances = instanceDetails.length;
			self.addavailableInstances.removeAll();
			for(;i < totalInstances; i++){
				var addinstancesobj = instanceDetails[i];
				var tempObj = {
						configInstanceId:addinstancesobj.configInstanceId,
						shortName:addinstancesobj.shortName
				};
				self.addavailableInstances.push(tempObj);
			}
		};


		self.saveClientJobConfig = function() {
			if (self.selectedClient() && self.selectedJob() && self.selectedInstance()){
				var ClientJobDto = {clientId:self.selectedClient().clientId, jobId:self.selectedJob().jobId, configInstanceId:self.selectedInstance().configInstanceId};
				var data = JSON.stringify(ClientJobDto);
				$.ajax({
					'url': xoappcontext + '/clientjobconfigs',
					'type': 'POST',
					'cache':false,
					'data':data,
					'contentType': "application/json; charset=utf-8",
					'success' : function(responseData) {
						setGlobalMessage(responseData,"popup");
						if(responseData.messageType == 'success') {
							setTimeout(function(){
								self.loadallClientJobsConfigs();
							}, 3000);
						}
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({message:textStatus, messageType:'alert'},"popup");
					}
				});
			} else{
				alert(input_err);
			}
		};



//ALL JOBS PAGE FUNCTIONS>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        self.loadallClientJobsConfigs = function() {
			$.ajax({
				'url' : xoappcontext + '/clientjobconfigs',
				'type' : 'GET',
				'cache' : false,
				'success' : function(serverResponse) {
					jobConfigtable = self.buildDataTableWithData('allClientJobsConfigs', self.buildClientJobConfigTable, serverResponse, null, jobConfigtable);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {

					setGlobalMessage({
						message : textStatus,
						messageType : 'alert'
					}, "general");
				}
			});
		};

        self.buildClientJobConfigTable = function(jobdetails) {
			var i = 0;
			var totalJobs = jobdetails.length;
			self.allJobs.removeAll();
			for(;i < totalJobs; i++){
				var jobobj = jobdetails[i];
				var tempObj = {
					jobId:jobobj.jobId,
					clientId:jobobj.clientId,
					configId:jobobj.configInstanceId,
					client_name:jobobj.clientName,
					configName:jobobj.configName,
					jobName:jobobj.jobName,
					isRunning:ko.observable(false),
					lastMessage:ko.observable(jobobj.lastMessage),
					statusType:ko.observable(jobobj.lastMessage == 'Completed' ? 'button label success' : 'button label alert'),
					deleteConfig:self.deleteConfig,
					active:ko.observable(jobobj.active),
					toggleActiveStatus:self.toggleActiveStatus,
					runJob:self.runJob
				};
				self.allJobs.push(tempObj);
			}
		};

		self.deleteConfig = function(data, event) {
			if(data && event) {
				var aPos = $(event.currentTarget).parents('tr')
				$(aPos).hide("medium", function () {
					jobConfigtable.row($(aPos)).remove();
					jobConfigtable.draw();
				} );
			}
			var deleteConfigJobDto = {clientId:data.clientId, jobId:data.jobId, configInstanceId:data.configId};
			var configJobdata = JSON.stringify(deleteConfigJobDto);
			if(data && event) {
				$.ajax({
					'url':  xoappcontext + '/clientjobconfigs',
					'type':'DELETE',
					'cache':false,
					'data':configJobdata,
					'contentType': "application/json; charset=utf-8",
					'success' : function(responsedata) {
						setGlobalMessage(responsedata,"general");
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						setGlobalMessage({message:textStatus, messageType:'alert'},"general");
					}
				});
			}
		};

		self.toggleActiveStatus = function(data, event) {

			if(data && event) {
				$.ajax({
					'url':  xoappcontext + '/clientjobconfigs/toggleactive/' + data.clientId + '/' + data.jobId + '/' + data.configId,
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

		self.runJob = function(data, event) {
			if(data && event) {
				data.isRunning(true);
				$.ajax({
					'url':  xoappcontext + '/clientjobconfigs/runJob/' + data.clientId + '/' + data.jobId + '/' + data.configId,
					'type':'PUT',
					'cache':false,
					'success' : function(responsedata) {
						data.isRunning(false);
						setGlobalMessage(responsedata,"general");
						if(responsedata && responsedata.resultobject) {
							data.statusType(responsedata.resultobject.message == 'Completed' ? 'button label success' : 'button label alert')
							data.lastMessage(responsedata.resultobject.message);
						}
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						data.isRunning(false);
						setGlobalMessage({message:textStatus, messageType:'alert'},"general");
					}
				});
			}
		};

		return {

			visibility : self.visibility,
			currentPage : self.currentPage,
			loadJobs:self.loadJobs,
			allJobs : self.allJobs,
			availableConfigs : self.availableConfigs,
			availableClients : self.availableClients,
			addavailableInstances:self.addavailableInstances,
			addavailableJobs:self.addavailableJobs,
			loadClients:self.loadClients,
			cleanup:self.cleanup,
			selectedClient:self.selectedClient,
			selectedJob:self.selectedJob,
			selectedInstance:self.selectedInstance,
			loadallClientJobsConfigs:self.loadallClientJobsConfigs,
			loadallConfigInstances:self.loadallConfigInstances,
			loadPopup:self.loadPopup,
			saveClientJobConfig:self.saveClientJobConfig,
			deleteConfig:self.deleteConfig,
			runJob:self.runJob

		};
	}
	JobManagerModel.prototype = new BaseModel(ko, $);
	JobManagerModel.prototype.constructor = JobManagerModel;
	return JobManagerModel;
});