$("#preloader").show(true);
requirejs.config({
	waitSeconds : 0,
	baseUrl :  xoappcontext + '/vassets/lib/',
	shim : {
		'knockout' : {exports: 'knockout'},
		'semantic': {
			exports:'semantic',
			deps:['jquery']
		},
		"knockout.validation": {"deps": ["knockout"]},
		'datatables': {
			exports: 'datatables'
		},
		'datatables-semantic': {
			exports: 'DataTable',
			deps:['datatables', 'semantic']
		},
		'FileSaver':{
			exports : 'FileSaver'
		}
  },
	paths : {
		jquery : 'jquery/jquery.min',
		knockout : 'knockout/knockout',
		"knockout.validation": xoappcontext + '/vassets/js/koapp/knockout.validation.min',
		main :  xoappcontext + '/vassets/js/koapp/main',
	    selectize : 'selectize.js/js/standalone/selectize.min',
		semantic: xoappcontext + '/assets/semantic/out/semantic.min',
		datatables:  xoappcontext + '/vassets/DataTables/media/js/jquery.dataTables.min',
		DataTable:  xoappcontext + '/vassets/DataTables/media/js/dataTables.semanticui.min',
		FileSaver: xoappcontext + '/vassets/js/koapp/FileSaver'
  }
});


define([ 'knockout', 'main/router', 'knockout.validation', 'main/koselectize', 'semantic', 
         'datatables', 'DataTable', 'FileSaver', 'main/usermanagement', 'main/tableaumanagement'],
         function(ko, Router, kv, koSelector, semantic, datatables, DataTable, FileSaver, UserManagerModel, TableauManagerModel) {

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
        	  return new Router.Page('Change Password', 'password-change', {usermanagement:usermgmt, selector:koSelector});
          });
      }

      function tabdashboardPage() {
        return showPageLoader(function() {
          	cleanUp();
        	tableaumgmt.loadDashboardData();
        	return new Router.Page('Dashboard','tab_dashboard', {tableaumgmt: tableaumgmt});
        });
      }

      function randomFilter(path) {   
      }

      // This is the KO ViewModel for the whole page, which contains our router, which
      // in turn keeps track of the current page.
      var topLevelModel = { router: new Router(urlMapping, randomFilter)};
      
      topLevelModel.land = {};
      topLevelModel.land.afterRendererdHandler = function (){
    	  $('.menu.top .item').tab();
    	  $('.ui.checkbox').checkbox();
    	  $('.ui.menu.top').find('.item').click(function(){
    			localStorage.setItem('activeTab',$(this).text());
    	  });
    	  var activeTab = localStorage.getItem('activeTab');
    	  if(activeTab!==null){
    		  $('.ui.menu.top').find('.item').tab('change tab', activeTab);
    	  }
    	  hidemenu();
    	  showmenu();
    	  if((document.getElementById("show_nav_bar").style.display != "none")&&((document.getElementById("top_nav_bar").style.display != "none"))){
    		  document.getElementById("show_nav_bar").style.display = "none";
    	  }
      }
      
      topLevelModel.display_msgbox= {};
      topLevelModel.display_msgbox.afterRendererdHandler = function (){
    	  
    	  $('#popup-box-container').css('visibility', 'hidden');
    	  
    	  $('#alert-box-container').css('visibility', 'hidden');
    	  $("#close_message").click(function(){
    		  $('.display_message').css('visibility', 'hidden');
    	  });
    	  hidemenu();
    	  showmenu();
    	  if((document.getElementById("show_nav_bar").style.display != "none")&&((document.getElementById("top_nav_bar").style.display != "none"))){
    		  document.getElementById("show_nav_bar").style.display = "none";
    	  }
    	  $('#multi_select').dropdown({action: 'nothing'});
      }
      // Make model accessible in global context, purely to aid debugging.
      window.topLevelModel = topLevelModel;

      // Need to explicitly bind to 'html' node if we want setting the page title to work.
      ko.applyBindings(topLevelModel, $('html').get(0));
    }

    initializePages();
    $("#preloader").fadeOut('slow');
});