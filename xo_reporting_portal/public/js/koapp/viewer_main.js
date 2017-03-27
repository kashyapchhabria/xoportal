$(".se-pre-con").show(true);
requirejs.config({
  baseUrl :  xoappcontext + '/vassets/lib/',
  shim : {
    'knockout' : {exports: 'knockout'},
	'foundation': {
					exports:'foundation',
					deps:['jquery']
				},
	"knockout.validation": {"deps": ["knockout"]},
	'datatables': {
		   	 	exports: 'datatables'
			},
	'datatables-foundation': {
				exports: 'DataTable',
		   	 	deps:['datatables', 'foundation']
			},
	'datatables-responsive': {
		   	 	exports: 'Responsive',
		   	 	deps:['datatables', 'foundation']
			}
  },
  paths : {
    jquery : 'jquery/jquery.min',
    knockout : 'knockout/knockout',
    "knockout.validation": xoappcontext + '/vassets/js/koapp/knockout.validation.min',
    selectize : 'selectize.js/js/standalone/selectize.min',
    main :  xoappcontext + '/vassets/js/koapp/main',
    foundation: xoappcontext + '/vassets/lib/foundation/js/foundation.min',
    datatables:  xoappcontext + '/vassets/js/datatable/jquery.dataTables',
    DataTable:  xoappcontext + '/vassets/js/datatable/foundation.datatable',
    Responsive:  xoappcontext + '/vassets/js/datatable/dataTables.responsive'
  }
});

define([ 'knockout', 'main/router', 'knockout.validation', 'main/koselectize', 'foundation', 
         'datatables', 'DataTable', 'Responsive', 'main/usermanagement', 'main/tableaumanagement', 'main/diffusion_map'],
         function(ko, Router, kv, koSelector, foundation, datatables, DataTable, Responsive, UserManagerModel, TableauManagerModel, DiffusionManagerModel) {

    var initializePages = function(){

    	var currentPage = "home";

      // More specific matches should come first.
      var urlMapping = {        
        home:   { match: /^$/,                    page: diffusionmapPage},
        changepassword: { match: /^changepassword/,        page: changepasswordPage},
        diffusionmap: { match: /^diffusionmap$/, page: diffusionmapPage},
        tabdashboard: { match: /^tabdashboard$/, page: tabdashboardPage}
      };

      var usermgmt = new UserManagerModel();
      var tableaumgmt = new TableauManagerModel();
      var diffMap = new DiffusionManagerModel();

      function changepasswordPage(){
      	self.userGuide(true);	
          return showPageLoader(function() {
        	  return new Router.Page('Xoanon Analytics Reporting Portal', 'password-change', {usermanagement:usermgmt, selector:koSelector});
          });
      }

      function tabdashboardPage() {
      	self.userGuide(true);
        return showPageLoader(function() {
          tableaumgmt.loadDashboardData();
          return new Router.Page('Xoanon Analytics Reporting Portal','tab_dashboard', {tableaumgmt: tableaumgmt});
        });
      }
      
      function diffusionmapPage() {
      	self.userGuide(false);
        return showPageLoader(function() {
        	diffMap.showDiffusionMap();
        	diffMap.getComments();
        	return new Router.Page('Xoanon Analytics Reporting Portal','diffusion_map',{diffMap:diffMap});
        });
      }

      function randomFilter(path) {   
      }

      // This is the KO ViewModel for the whole page, which contains our router, which
      // in turn keeps track of the current page.
      var topLevelModel = { router: new Router(urlMapping, randomFilter)};
      // Make model accessible in global context, purely to aid debugging.
      window.topLevelModel = topLevelModel;

      // Need to explicitly bind to 'html' node if we want setting the page title to work.
      ko.applyBindings(topLevelModel, $('html').get(0));
    }

    initializePages();
    $(".se-pre-con").fadeOut('slow');
});