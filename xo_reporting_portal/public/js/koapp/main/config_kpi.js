define([ 'knockout', 'jquery' ], function(ko, $) {

	function ConfigKPITargetModel() {

		BaseModel.call(this, ko, $);
		var self = this;
		/* self.kpiList = ko.observableArray([
						{name:'Kpi 1',isSelected: ko.observable(true)},
						{name:'Kpi 2',isSelected: ko.observable(true)},
						{name:'Kpi 3',isSelected: ko.observable(true)},
						{name:'Kpi 4',isSelected: ko.observable(true)},
						{name:'Kpi 5',isSelected: ko.observable(true)}]);
		self.selectedKpiList = ko.observableArray([]);
		self.kpiDetails = ko.observable();
		self.prevSelectedHomeTab = ko.observable('tab_select');
		self.prevSelectedHomeContent = ko.observable('select');
		
		*/ 
		
		self.cleanup = function() {

		};
		
		
		self.handleHomeTabs=function(data) {
				$('#' + self.prevSelectedHomeTab()).removeClass('active');
				$('#' + self.prevSelectedHomeContent()).removeClass('active');
				$('#'+data).addClass('active');
				var prevtabid = data;
				self.prevSelectedHomeTab(prevtabid);
				var contentdivid = prevtabid.substring(4);
				self.prevSelectedHomeContent(contentdivid);
				$('#' + contentdivid).addClass('active');
			};
		/*
		self.showSelected = function() {
			for(var i=0;i<self.kpiList().length;i++) {
				if(self.kpiList()[i]['isSelected']) {
					self.selectedKpiList.push(self.kpiList()[i]['name']);
				}
			}
		}
		
		*/
		
		return {
			showSelected:self.showSelected,
			//kpiList:self.kpiList,
			//kpiDetails:self.kpiDetails,
			//selectedKpiList:self.selectedKpiList,
			handleHomeTabs:self.handleHomeTabs,
			prevSelectedHomeTab:self.prevSelectedHomeTab,
			prevSelectedHomeContent:self.prevSelectedHomeContent
		};
	}
	
	ConfigKPITargetModel.prototype = new BaseModel(ko, $);
	ConfigKPITargetModel.prototype.constructor = ConfigKPITargetModel;
	return ConfigKPITargetModel;

});
