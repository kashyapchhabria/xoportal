$('#preloader').show(true);
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
		}
	},
	paths : {
		jquery : 'jquery/jquery.min',
		knockout : 'knockout/knockout',
		"knockout.validation": xoappcontext + '/vassets/js/koapp/knockout.validation.min',
		selectize : 'selectize.js/js/standalone/selectize.min',
		main :  xoappcontext + '/vassets/js/koapp/main',
		semantic: xoappcontext + '/assets/semantic/out/semantic.min',
		datatables:  xoappcontext + '/vassets/DataTables/media/js/jquery.dataTables.min',
		DataTable:  xoappcontext + '/vassets/DataTables/media/js/dataTables.semanticui.min'
	}
});

define([ 'knockout', 'main/router', 'knockout.validation', 'main/koselectize', 'semantic', 
         'datatables', 'DataTable', 'main/usermanagement', 'main/tableaumanagement', 'main/diffusion_map', 'main/campaign_filter'],
         function(ko, Router, kv, koSelector, semantic, datatables, DataTable, UserManagerModel, TableauManagerModel, DiffusionManagerModel, CampaignFilterModel) {

    var initializePages = function(){

    	var currentPage = "home";

      // More specific matches should come first.
      var urlMapping = {        
        home:   { match: /^$/,                    page: diffusionmapPage},
        changepassword: { match: /^changepassword/,        page: changepasswordPage},
        diffusionmap: { match: /^diffusionmap$/, page: diffusionmapPage},
        tabdashboard: { match: /^tabdashboard$/, page: tabdashboardPage},
        campgaignfilter: {match: /^newcampaign/, page: campgaignfilterPage}
      };

      var usermgmt = new UserManagerModel();
      var tableaumgmt = new TableauManagerModel();
      var diffMap = new DiffusionManagerModel();

      function changepasswordPage(){
      	self.userGuide(true);	
          return showPageLoader(function() {
        	  return new Router.Page('Change Password', 'password-change', {usermanagement:usermgmt, selector:koSelector});
          });
      }

      function tabdashboardPage() {
      	self.userGuide(true);
        return showPageLoader(function() {
          tableaumgmt.loadDashboardData();
          return new Router.Page('Dashboard','tab_dashboard', {tableaumgmt: tableaumgmt});
        });
      }
      
      function diffusionmapPage() {
      	self.userGuide(false);
        return showPageLoader(function() {
        	diffMap.clearAll();
        	diffMap.showDiffusionMap();
        	diffMap.getComments();
        	return new Router.Page('Diffusion Map','diffusion_map',{diffMap:diffMap});
        });
      }
      
      function campgaignfilterPage() {
    	  return showPageLoader(function() {
    		  cleanUp();
    		  campFilter.getTotalCount();
    		  campFilter.selTop(diffMap.selTop());
    		  campFilter.selDate(diffMap.selDate());
    		  campFilter.selRegion(diffMap.selRegion());
    		  campFilter.selLifetime(diffMap.selLifetime());
    		  campFilter.selDataArpu(diffMap.selDataArpu());
    		  campFilter.selVasPlan(diffMap.selVasPlan());
    		  return new Router.Page('Campaign Filter', 'campaign-filter', {campaign:campFilter});
    	  });
      }

      function randomFilter(path) {   
      }

      // This is the KO ViewModel for the whole page, which contains our router, which
      // in turn keeps track of the current page.
      var topLevelModel = { router: new Router(urlMapping, randomFilter)};
      
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