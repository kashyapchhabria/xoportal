/**
 * Campaign Filter
 */
define([ 'knockout', 'jquery' ], function(ko, $) {

	function CampaignFilterModel() {

		BaseModel.call(this, ko, $);
		var self = this;
		self.selTop = ko.observableArray();
		self.selRegion = ko.observableArray();
		self.selDate = ko.observableArray();
		self.selLifetime = ko.observable();
		self.selDataArpu = ko.observable();
		self.selVasPlan = ko.observable();
		self.campaignName = ko.observable('');
		self.campaignDescription = ko.observable('');
		self.campaignCount = ko.observable(0);
		self.campaignCountNoFormat = ko.observable(0);
		self.campaignTotalCount = ko.observable(0);
		self.selSgmtKeywords = ko.observable();
		self.campaignPercent = ko.observable();
		self.rangeValue = ko.observable(50);
		self.abSplitValue = ko.observable();
		self.sgmtKeywords = ko.observable({
			A1 : "Innovative, WWW everywhere, Dynamic, Daring, Passion, Always the latest, Bold, Energetic, Unique, Trendy, Friendly, Cool, Fun, Enjoying life",
			A2 : "Efficient, Fair, Ethical, Equality, clear, Transparent, Collaborative, Honest, Planning day-to-day activities, Reliable, Quality>design, Responsibility",
			A3 : "Stylish, Confident, Status, Designer labels improve a person's image, Respect, Power",
			A4 :  "",
			A5 : "Relationships, Seeks advice, Duty, Commitment, Duty/tradition, Integrity, Techno skeptics, Tradition",
			Y1 : "Innovative, WWW everywhere, Dynamic, Daring, Passion, Always the latest, Bold, Energetic, Unique, Trendy, Friendly, Cool, Fun, Enjoying life",
			Y2 : "Efficient, Fair, Ethical, Equality, clear, Transparent, Collaborative, Honest, Planning day-to-day activities, Reliable, Quality>design, Responsibility",
			Y3 : "Stylish, Confident, Status, Designer labels improve a person's image, Respect, Power"
		});
		
		self.formatNumberData = function(number) {
			if( number > 1000 ) {
				if( number > 1000000) {
					var tempCount = number / 1000000;
					return Number((tempCount).toFixed(2)) + " Mn";
				} else {
					var tempCount = number / 1000;
					return Number((tempCount).toFixed(2)) + " K";
				}
			} else if (self.isDemo()){
				return number + " K";
			} else {
				return number;
			}
		};
		
		self.selTop.subscribe(function(newVal) {
			if ( newVal == "*" )
        		self.selTop(["A1","A2","A3","A4","A5","Y1","Y2","Y3"]);
			self.selSgmtKeywords("");
			var keywordHeader = "<h4 class=\"ui header\">Segment Keywords - ";
			var endKeywordHeader = "</h4>";
			var keywordsPara = "<p>";
			var endKeywordsPara = "</p><br />";
        	for(var i=0; i < self.selTop().length; i++) {
        		var sgmtSelHeader = keywordHeader + self.selTop()[i] + endKeywordHeader ;
        		var sgmtSelWords = self.sgmtKeywords()[self.selTop()[i]];
        		var fullHtml = sgmtSelHeader + keywordsPara + sgmtSelWords + endKeywordsPara;
        		self.selSgmtKeywords(self.selSgmtKeywords()+fullHtml);
        	}
		});
		
		self.selLifetime.subscribe(function(newVal) {
        	if ( newVal == "*" )
        		self.selLifetime(["1 - 3 years","3 - 5 years","5+ years","6 months - 1 year"]);
		});
		
		self.selRegion.subscribe(function(newVal) {
        	if ( newVal == "*" )
        		self.selRegion(["Lagos", "North_1", "North_2", "South East", "South South", "South West", "Unavailable"]);
		});
        
        self.selDataArpu.subscribe(function(newVal) {
        	if ( newVal == "*" )
        		self.selDataArpu(["HH","LH","LL","HL"]);
		});
		
		self.selVasPlan.subscribe(function(newVal) {
        	if ( newVal == "*" )
        		self.selVasPlan(["Backup", "Entertainment", "Infotainment", "Jobs", "MFS", "Undefined", "Betting", "Financial", "Football","Music", "Promo", "Religion", "Video", "Voting", "mAgric", "mHealth"]);
		});
		
		self.rangeValue.subscribe(function(newVal){
			var abSplit = (newVal * self.campaignCountNoFormat()) / 100;
			var aSplit = self.formatNumberData(abSplit);
			var bSplit = self.formatNumberData(self.campaignCountNoFormat()-abSplit);
			self.abSplitValue("A - " + aSplit +"<br /> B - " + bSplit)
		});
		
		self.prepJsonData = function() {
			var data = {};
			var subSgt = [], homeL = [];
			data['name'] = self.campaignName();
			data['description'] = self.campaignDescription();
			data['filterJson'] = {};
			data['filterJson']['topSegment'] = self.selTop();
			data['filterJson']['selDate'] = self.selDate();
			data['filterJson']['homeLocation'] = self.selRegion();
			data['filterJson']['lifetime'] = self.selLifetime();
			data['filterJson']['dataArpu'] = self.selDataArpu();
			data['filterJson']['vasPlan'] = self.selVasPlan();
			data['filterJson']['noExported'] = self.campaignCountNoFormat();
			data['filterJson']['totalBase'] = self.campaignTotalCount();
			data['setAb'] = self.rangeValue();
			return data;
		};

		self.getSelectedFieldsJson = function(type) {
			if (type === "export" && self.campaignName() === '') {
				alert("Enter Campaign Name");
				return null;
			} else if (type === "export" && self.campaignDescription() === '') {
				alert("Enter Campaign Description");
				return null;
			} else if (type === "export" && (self.rangeValue() > 100 || self.rangeValue() === '' || self.rangeValue() === "0")) {
				alert("Kindly choose A/B value between 1-100% ");
				return null;
			} else {
				return self.prepJsonData();
			}
		};

		self.getMsisdnCount = function() {
			var filterData = JSON.stringify(self.getSelectedFieldsJson("count"));
			$("#preloader").show(true);
			$.ajax({
				'url' : xoappcontext + '/getMsisdnCount',
				'type' : 'POST',
				'cache' : false,
				'data' : filterData,
				'contentType' : "application/json; charset=utf-8",
				'success' : function(serverResponse) {
					var campCount = parseInt(serverResponse.message);
                	self.campaignCountNoFormat(campCount);
                	self.campaignCount(self.formatNumberData(campCount));
//                	if (campCount > 1000) {
//                		if (campCount > 1000000) {
//                			var tempCount = campCount / 1000000;
//                			self.campaignCount(Number((tempCount).toFixed(2)) + " Mn");
//                		} else {
//                			var tempCount = campCount / 1000;
//                			self.campaignCount(Number((tempCount).toFixed(2)) + " K");
//                		}
//                	} else if (self.isDemo()){
//                		self.campaignCount(campCount + " K");
//                	} else {
//                		self.campaignCount(campCount);
//                	}
                	var percent = Number((((campCount / self.campaignTotalCount()) * 100)).toFixed(2)); 
                	self.campaignPercent(percent);
                	$("#preloader").fadeOut("slow");
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					$("#preloader").fadeOut("slow");
					setGlobalMessage({
						message : textStatus,
						messageType : 'alert'
					}, "popup");
				}
			});
		};
		

		self.getTotalCount = function() {
			$("#preloader").show(true);
			$.ajax({
				'url' : xoappcontext + '/getTotalCount',
				'type' : 'GET',
				'cache' : false,
				'contentType' : "application/json; charset=utf-8",
				'success' : function(serverResponse) {
					self.campaignTotalCount(serverResponse.message);
					self.getMsisdnCount();
					$("#preloader").fadeOut("slow");
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					$("#preloader").fadeOut("slow");
					setGlobalMessage({
						message : textStatus,
						messageType : 'alert'
					}, "popup");
				}
			});
		};

		self.getMsisdns = function() {
			var filterData = JSON.stringify(self.getSelectedFieldsJson("export"));
			if (filterData !== "null") {
				$("#preloader").show(true);
				$.ajax({
					'url' : xoappcontext + '/savecampaign',
					'type' : 'POST',
					'cache' : false,
					'data' : filterData,
					'contentType' : "application/json; charset=utf-8",
					'success' : function(serverResponse) {
						if (serverResponse !== "Error") {
							self.getCsvFile(serverResponse);
						} else {
							alert("Error Generating File !");
							$("#preloader").fadeOut("slow");
						}
					},
					'error' : function(jqXHR, textStatus, errorThrown) {
						$("#preloader").fadeOut("slow");
						setGlobalMessage({
							message : textStatus,
							messageType : 'alert'
						}, "popup");
					}
				});

			}
		};

		self.getCsvFile = function(fileName) {
			$.ajax({
				'url' : xoappcontext + '/getCsvFile/' + fileName,
				'type' : 'GET',
				'cache' : false,
				'contentType' : "application/json; charset=utf-8",
				'success' : function(serverResponse) {
					window.open(xoappcontext + '/getCsvFile/' + fileName
							+ '?authToken='
							+ $('#authtokenvalue').attr('authtoken'));
					$("#preloader").fadeOut("slow");
					console.log("File download Successful");
				},
				'error' : function(jqXHR, textStatus, errorThrown) {
					$("#preloader").fadeOut("slow");
					setGlobalMessage({
						message : textStatus,
						messageType : 'alert'
					}, "popup");
				}
			});
		}

		return {
			selTop : self.selTop,
			selRegion : self.selRegion,
			selDate : self.selDate,
			selLifetime : self.selLifetime,
			selDataArpu : self.selDataArpu,
			selVasPlan : self.selVasPlan,
			campaignName : self.campaignName,
			campaignDescription : self.campaignDescription,
			campaignCount : self.campaignCount,
			getMsisdnCount:self.getMsisdnCount,
			selSgmtKeywords:self.selSgmtKeywords,
			getTotalCount:self.getTotalCount,
			getMsisdns:self.getMsisdns,
			campaignPercent:self.campaignPercent,
			rangeValue:self.rangeValue,
			abSplitValue:self.abSplitValue
		};
	}

	CampaignFilterModel.prototype = new BaseModel(ko, $);
	CampaignFilterModel.prototype.constructor = CampaignFilterModel;
	return CampaignFilterModel;
});