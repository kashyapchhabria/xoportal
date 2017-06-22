/**
 * Tableau management
 */
define(['knockout', 'jquery','FileSaver'], function(ko, $,fileSaver) {
	
    function AnotateCommentModel() {
    	BaseModel.call(this, ko, $);
    	self.menuData = ko.observableArray([]);
    	self.fieldName1 = ko.observable();
    	self.fieldName2 = ko.observable("hello");
    	self.reportName = ko.observable();
    	self.status = ko.observable("1");
		self.comment = ko.observable();
		self.workbookName = ko.observable();
    	var viz;
    	
    	
    	self.initViz = function() {
            var containerDiv = document.getElementById("vizContainer"),
                url = "http://192.175.112.82/t/Xo_demo/views/CRSReports_comment/CustomerDashboard?:embed=y&:showShareOptions=true&:display_count=no&:showVizHome=no",
                options = {
                    "Academic Year": "",
                    hideTabs: true,
                    onFirstInteractive: function () {
                        viz.addEventListener(tableau.TableauEventName.MARKS_SELECTION, onMarksSelection);
                    }
                };
            
            viz = new tableau.Viz(containerDiv, url, options);
        }
        var i=0;
        function onMarksSelection(marksEvent) {
        	i=1;
        	self.workbookName(viz.getWorkbook().getName());
        	if(marksEvent.getWorksheet().getName()=="Total Customers v1") {
        		self.reportName("Total Customers v1");
        		return marksEvent.getMarksAsync().then(reportSelectedMarks);
        	}	
        }
        
        function reportSelectedMarks(marks) {
        	if(i==1 && marks.length>0) {
        	for (var markIndex = 0; markIndex < marks.length; markIndex++) {
                var pairs = marks[markIndex].getPairs();
        		for (var pairIndex = 0; pairIndex < pairs.length; pairIndex++) {
                   var pair = pairs[pairIndex];
                   //alert(pair.fieldName);
                   if(pair.fieldName.localeCompare("ATTR(forcomment)")==0) {
                   		self.fieldName1(pair.formattedValue);
                   		//alert(self.fieldName1());
        		   }
               }
            }
        	self.load();
        	i++;
        	}
        }
        
        
        self.load = function() {
        	loadPopup("myModal");
        }
        
        self.reportComment = function() {
        	self.comment(document.getElementById("report_comment").value);
        	reportComment={ 
        				user: self.user(),
        				reportName: self.reportName(),
        				fieldName1: self.fieldName1(),
        				fieldName2: self.fieldName2(),
        				workbookName: self.workbookName(),
        				status: "1",
        				comment: self.comment()
        		} ;
        		data=JSON.stringify(reportComment);
        		//alert(data);
        		$.ajax({
        			'url': xoappcontext + '/exportdata',
        			'type': 'POST',
        			'cache':false,
        			'data':data,
        			'contentType': "application/json; charset=utf-8",
        			'success' : function(responseData) {
        							console.log(responseData);        		
        			},
        			'error' : function(jqXHR, textStatus, errorThrown) {
        				setGlobalMessage({message:textStatus, messageType:'alert'},"general");
        			}
        		});
        		$('#myModal').modal('hide');
        }
        
    	return{
    		initViz:self.initViz,
    		menuData: self.menuData,
    		reportComment: self.reportComment
    	};
    }
    
    AnotateCommentModel.prototype = new BaseModel(ko, $);
    AnotateCommentModel.prototype.constructor = AnotateCommentModel;
    return AnotateCommentModel;
});