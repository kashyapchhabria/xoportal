/**
 * Configuration management
 */
define([ 'knockout', 'jquery' ], function(ko, $) {

	function ConfigurationManagerModel() {

		BaseModel.call(this, ko, $);
		var self = this;
		self.allConfigParams = ko.observableArray([]);
		self.availableConfigTemplates = ko.observableArray([]);
		self.availableConfigInstances = ko.observableArray([]);
		self.configTemplateShortName = ko.observable('');
		self.shortName = ko.observable('');
		self.configTemplateId = ko.observable(0);
		self.configInstanceId = ko.observable(0);
		self.active = ko.observable(true);
		self.isNewConfigTemplate = ko.observable(false);
		self.isNewConfigInstance = ko.observable(false);
		var configtable = null;

		function createConfigParamObj(displayname, configvalue) {
			return {
				'configname' : ko.observable(displayname),
				'configvalue' : ko.observable(configvalue),
				'isEdit' : ko.observable(false),
				'update' : self.saveConfigRow,
				'deleteConfig' : self.deleteConfigParam,
				'editable' : self.makeEditable
			};
		}

		self.cleanup = function() {
			self.shortName('');
			self.availableConfigTemplates.removeAll();
			self.availableConfigInstances.removeAll();
			self.allConfigParams.removeAll();
			if(self.currentPage() == 'configtemplate') {
				self.configTemplateId(0);
				self.configInstanceId(0);
				self.configTemplateShortName('');
			} else if(self.currentPage() == 'configinstance') {
				self.configInstanceId(0);
			} else if(self.currentPage() == 'configuration') {
			}
		};

		self.loadConfigurations = function() {
			if(self.configTemplateId() > 0 || self.configInstanceId() > 0) {
				var url = xoappcontext;
				if(self.configInstanceId() > 0) {
					url = url + '/configinstances/' + self.configInstanceId();
				} else {
					url = url + '/configtemplates/' + self.configTemplateId();
				}
				$.ajax({
					'url' : url,
					'type' : 'GET',
					'cache' : false,
					'success' : function(serverResponse) {
						configtable = self.buildDataTableWithData('configurations', self.buildConfigParamRecord, serverResponse, null, configtable);
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

		self.loadConfigTemplates = function() {
			$.ajax({
				'url' : xoappcontext + '/configtemplates',
				'type' : 'GET',
				'cache' : false,
				'success' : function(serverResponse) {
					configtable = self.buildDataTableWithData('allAvailableConfigTemplates', self.buildConfigTempData, serverResponse, null, configtable);
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({
						message : textStatus,
						messageType : 'alert'
					}, "general");
				}
			});
		};

		self.loadConfigInstances = function() {
			if(self.configTemplateId() > 0) {
				$.ajax({
					'url' : xoappcontext + '/configinstances/all/' + self.configTemplateId(),
					'type' : 'GET',
					'cache' : false,
					'success' : function(serverResponse) {
						self.buildDataTableWithData('allAvailableConfigInstances', self.buildConfigInstanceData, serverResponse);
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

		self.showConfigInstances = function(data, event) {
			if(data && event) {
				self.configTemplateId(data.configId());
				self.configTemplateShortName(data.shortName());
				self.shortName(data.shortName());
				setLocationHash('configinstance');
			}
		};

		self.showConfigParams = function(data, event) {
			if(data && event) {
				self.shortName(data.shortName());
				if(self.configTemplateId() > 0) {
					self.configInstanceId(data.configId());
				} else {
					self.configTemplateShortName(data.shortName());
					self.configTemplateId(data.configId());
				}
				setLocationHash('configuration');
			}
		};

		function createConfigRecord(configObj) {
			var configObjRecord = {};
			if(configObj) {
				if(self.configTemplateId() > 0) {
					configObjRecord.configId = ko.observable(configObj.configInstanceId);
					configObjRecord.configInstanceId = ko.observable(configObj.configInstanceId);
				} else {
					configObjRecord.configId = ko.observable(configObj.configTemplateId);
					configObjRecord.showConfigInstances = self.showConfigInstances;
					configObjRecord.newConfigInstance = self.newConfigInstance;
				}
				configObjRecord.deleteConfig = self.deleteConfig;
				configObjRecord.editConfigurations = self.showConfigParams;
				configObjRecord.active = ko.observable(configObj.active);
				configObjRecord.toggleStatus = self.toggleActiveStatus;
				configObjRecord.shortName = ko.observable(configObj.shortName);
			}
			return configObjRecord;
		}

		self.buildConfigTempData = function(configTemps) {
			if(configTemps) {
				var configTempIndex = 0;
				var totalConfigTemps = configTemps.length;
				for(;configTempIndex < totalConfigTemps; configTempIndex++) {
					self.availableConfigTemplates.push(createConfigRecord(configTemps[configTempIndex]));
				}
			}
		};

		self.buildConfigInstanceData = function(configInstances) {
			if(configInstances) {
				var configInstanceIndex = 0;
				self.shortName(self.configTemplateShortName());
				var totalConfigInstances = configInstances.length;
				for(;configInstanceIndex < totalConfigInstances; configInstanceIndex++) {
					self.availableConfigInstances.push(createConfigRecord(configInstances[configInstanceIndex]));
				}
			}
		};

		self.buildConfigParamRecord = function(configurationdetails) {
			self.configTemplateId(configurationdetails.configTemplateId);
			self.configInstanceId(configurationdetails.configInstanceId);
			self.shortName(configurationdetails.shortName);
			self.active(configurationdetails.active);
			self.mydata = JSON.parse(configurationdetails.configJson);
			var tempconfigkey, tempconfigvalue;
			for (tempconfigkey in self.mydata) {
				self.allConfigParams.push(createConfigParamObj(tempconfigkey,
						self.mydata[tempconfigkey]));
			}
		};

		self.deleteConfigParam = function(data, event) {
			if (data && event) {
				var aPos = $(event.currentTarget).parents('tr');
				self.allConfigParams.remove(data);
				self.saveConfig(function(){
					$(aPos).hide("medium", function() {
    					configtable.row($(aPos)).remove().draw();
    				});
					setLocationHash('configuration');
				});
			}
		};

		self.deleteConfig = function(data, event) {
			if (data && event) {
				var aPos = $(event.currentTarget).parents('tr');
				url = xoappcontext;	
				if(self.configTemplateId() > 0) {
					url = url + '/configinstances/';
				} else {
					url = url + '/configtemplates/';
				}
				url = url + data.configId();
				$.ajax({
	                'url': url,
	                'type':'DELETE',
	                'cache':false,
	                'success' : function(responsedata) {
	                	$(aPos).hide("medium", function() {
	    					configtable.row($(aPos)).remove().draw();
	    				});
	                    setGlobalMessage(responsedata,"general");
	                 },
	                 'error' : function(jqXHR, textStatus, errorThrown) {
	                    setGlobalMessage({message:textStatus, messageType:'alert'},"general");
	                  }
	            });
			}
		};

		self.saveConfigRow = function(data, event) {
			if (data && event) {
				data.isEdit(false);
				self.saveConfig(function(){
					setLocationHash('configuration');
				});
			}
		};

		self.newConfigInstance = function(data, event) {
			if(data && event) {
				self.isNewConfigInstance(true);
				self.isNewConfigTemplate(false);

				if(data.configId && data.configId() > 0) {
					self.configTemplateId(data.configId());
				}
				self.editShortname(data,event);
			}
		};

		self.newConfigTemplate = function(data, event) {
			if(data && event) {
				self.isNewConfigTemplate(true);
				self.isNewConfigInstance(false);
				self.editShortname(data,event);
			}
		};

		self.isConfigExistAlready = function(newConfigName) {
			if(self.isNewConfigInstance() || self.isNewConfigTemplate()) {
				if(newConfigName && newConfigName.trim().length == 0) {
					alert("Please provide configuration name.");
					return;
				} else {
					var url = xoappcontext;
					if(self.isNewConfigInstance()) {
						url = url + '/configinstances/search/' + self.configTemplateId() + "/";
					} else if(self.isNewConfigTemplate()) {
						url = url + '/configtemplates/search/';
					}
					url = url + newConfigName;
					$.ajax({
						'url' : url ,
						'type' : 'GET',
						'cache' : false,
						'contentType' : "application/json; charset=utf-8",
						'success' : function(responseData) {
							if(!responseData.resultobject) {	// The configuration with the name is not exist so save it.
								self.setNewConfigDetails(newConfigName);
							} else {
								if(self.isNewConfigInstance()) {
									self.isNewConfigInstance(false);
								} else if(self.isNewConfigTemplate()) {
									self.isNewConfigTemplate(false);
								}
							}
							setGlobalMessage(responseData, "general");
						},
						'error' : function(jqXHR, textStatus, errorThrown) {
							setGlobalMessage({
								message : textStatus,
								messageType : 'alert'
							},
							"general"
							);
						}
					});
				}
			} else {
				self.setNewConfigDetails(newConfigName);
			}
		};

		self.setNewConfigDetails = function(newConfigName) {
			self.shortName(newConfigName);
			self.saveConfig(function(){
				setLocationHash('configuration');
			});
		};
		
		self.saveConfig = function(aftercaller) {
			var i = 0;
			var j = 0;
			var configjsonstr = '{';
			var commaflag = false;
			var configjsonclose = '}';
			var tempKey = null;
			if(self.shortName() && self.shortName().trim().length == 0) {
				alert("Please provide configuration name.");
				return;
			}
			for (; i < self.allConfigParams().length; i++) {
				var tempConfigRowObj = self.allConfigParams()[i];
				tempConfigRowObj.isEdit(false);
				if (commaflag) {
					configjsonstr = configjsonstr + ",";
				}
				if (tempConfigRowObj['configname'] != null) {
					configjsonstr = configjsonstr + "\""
							+ tempConfigRowObj['configname']() + "\"" + ":"
							+ "\"" + tempConfigRowObj['configvalue']() + "\"";
					commaflag = true;
				} else {
					alert("Key should not be empty.");
					return;
				}
			}
			configjsonstr = configjsonstr + configjsonclose;
			var url = xoappcontext;
			var httpMethod = 'POST';
			if(self.configTemplateId() > 0 || self.configInstanceId() > 0 || self.isNewConfigInstance() || self.isNewConfigTemplate()) {
				if(self.isNewConfigInstance() && self.configTemplateId() > 0) {	// New config instance.
					url = url + '/configinstances';
				} else if(self.isNewConfigTemplate()) {	// New config template.
					url = url + '/configtemplates';
				} else if(self.configTemplateId() > 0 && self.configInstanceId() > 0) {
					url = url + '/configinstances/' + self.configInstanceId();
					httpMethod = 'PUT';
				} else if(self.configTemplateId() > 0) {
					url = url + '/configtemplates/' + self.configTemplateId();
					httpMethod = 'PUT';
				}
			}
			var configDto = {
					configTemplateId : self.configTemplateId(),
					configInstanceId : self.configInstanceId(),
					configJson : configjsonstr,
					shortName : self.shortName(),
					active : self.active()
			};
			$.ajax({
				'url' : url,
				'type' : httpMethod,
				'cache' : false,
				'data' : JSON.stringify(configDto),
				'contentType' : "application/json; charset=utf-8",
				'success' : function(responseData) {
					if(self.isNewConfigInstance() && responseData.resultobject) {
						self.configInstanceId(responseData.resultobject.configInstanceId);
						self.isNewConfigInstance(false);
					}
					if(self.isNewConfigTemplate() && responseData.resultobject) {
						self.configTemplateId(responseData.resultobject.configTemplateId);
						self.configTemplateShortName(responseData.resultobject.shortName);
						self.isNewConfigTemplate(false);
					}
					setGlobalMessage(responseData, "general");
					if(aftercaller) {
						aftercaller();
					}
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					setGlobalMessage({
							message : textStatus,
							messageType : 'alert'
						},
						"general"
					);
				}
			});
		};

		self.backPage = function(){
			window.history.back();
		};
		
		self.makeEditable = function(data, event) {
			if (data && event) {
				data.isEdit(true);
			}
		};

		self.toggleActiveStatus = function(data, event) {

			if(data && event) {
				var url = xoappcontext;
				if(self.configTemplateId() > 0) {
					url = url + '/configinstances/toggleactive/' + data.configId();
				} else {
					url = url + '/configtemplates/toggleactive/' + data.configId();
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

		self.editShortname = function(data,event){
        	var promptvalue = window.prompt("please enter the configuration name");
        	if(promptvalue && promptvalue.trim().length > 0) {
        		self.isConfigExistAlready(promptvalue);
        	}
        };

        self.addParamRow = function(data, event){
            if(data && event)
            {
            	var tempConfigParamObj = createConfigParamObj('', '');
            	tempConfigParamObj.isEdit(true);
            	configtable.destroy();
                self.allConfigParams.push(tempConfigParamObj);
                if(configtable) {
                	configtable = $("#configurations").DataTable({
                		responsive : true
                	});
                }
            }
        };

		return {
			visibility : self.visibility,
			loadConfigurations : self.loadConfigurations,
			currentPage : self.currentPage,
			shortName : self.shortName,
			configtemplateId : self.configTemplateId,
			allConfigParams : self.allConfigParams,
			loadConfigTemplates : self.loadConfigTemplates,
			loadConfigInstances:self.loadConfigInstances,
			availableConfigTemplates:self.availableConfigTemplates,
			availableConfigInstances:self.availableConfigInstances,
			newConfigTemplate:self.newConfigTemplate,
			newConfigInstance:self.newConfigInstance,
			cleanup:self.cleanup,
			editShortname:self.editShortname,
			backPage:self.backPage,
			addParamRow:self.addParamRow
		};
	}
	ConfigurationManagerModel.prototype = new BaseModel(ko, $);
	ConfigurationManagerModel.prototype.constructor = ConfigurationManagerModel;
	return ConfigurationManagerModel;
});
