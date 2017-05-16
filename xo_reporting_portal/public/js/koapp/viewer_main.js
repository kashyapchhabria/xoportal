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
			},
	'FileSaver':{
			exports : 'FileSaver'
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
    Responsive:  xoappcontext + '/vassets/js/datatable/dataTables.responsive',
    FileSaver: xoappcontext + '/vassets/js/koapp/FileSaver'
  }
});

define([ 'knockout', 'main/router', 'knockout.validation', 'main/koselectize', 'foundation', 
         'datatables', 'DataTable', 'Responsive','FileSaver', 'main/usermanagement', 'main/tableaumanagement'],
         function(ko, Router, kv, koSelector, foundation, datatables, DataTable, Responsive, FileSaver, UserManagerModel, TableauManagerModel) {

    var initializePages = function(){

    	var currentPage = "home";

      // More specific matches should come first.
      var urlMapping = {        
        home:   { match: /^$/,                    page: tabdashboardPage},
        changepassword: { match: /^changepassword/,        page: changepasswordPage},
        tabdashboard: { match: /^tabdashboard$/, page: tabdashboardPage}
      };

      var usermgmt = new UserManagerModel();
      var tableaumgmt = new TableauManagerModel();

	  function cleanUp() {
    	  if(tableaumgmt) {
    		  tableaumgmt.clearAll();
    	  }
      }

      function changepasswordPage(){
          return showPageLoader(function() {
        	  return new Router.Page('Telia Carrier Reporting Portal', 'password-change', {usermanagement:usermgmt, selector:koSelector});
          });
      }

      function tabdashboardPage() {
        return showPageLoader(function() {
          	cleanUp();
        	tableaumgmt.loadDashboardData();
        	return new Router.Page('Telia Carrier Reporting Portal','tab_dashboard', {tableaumgmt: tableaumgmt});
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