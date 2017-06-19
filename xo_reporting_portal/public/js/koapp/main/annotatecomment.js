/**
 * Tableau management
 */
define(['knockout', 'jquery','FileSaver'], function(ko, $,fileSaver) {
	
    function AnotateCommentModel() {
    	var viz;
    	self.initViz = function() {
            var containerDiv = document.getElementById("vizContainer"),
                url = "http://192.175.112.82/t/Xo_Prod/views/V3_Multi-SIM/V2_Multi-SIM?:embed=y&:showShareOptions=true&:display_count=no&:showVizHome=no",
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
        	return marksEvent.getMarksAsync().then(reportSelectedMarks);
        }
        
        function reportSelectedMarks(marks) {
        	if(i==1) {
        	var person = prompt("Please enter your name:", "");
        	alert(person);
        	i++;
        	}
        	
        }
        
    	BaseModel.call(this, ko, $);
    	return{
    		initViz:self.initViz
    	};
    }
    
    AnotateCommentModel.prototype = new BaseModel(ko, $);
    AnotateCommentModel.prototype.constructor = AnotateCommentModel;
    return AnotateCommentModel;
});