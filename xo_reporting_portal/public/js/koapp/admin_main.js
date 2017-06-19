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
		semantic: xoappcontext + '/assets/semantic/out/semantic.min',
		datatables:  xoappcontext + '/vassets/DataTables/media/js/jquery.dataTables.min',
		DataTable:  xoappcontext + '/vassets/DataTables/media/js/dataTables.semanticui.min',
		FileSaver: xoappcontext + '/vassets/js/koapp/FileSaver'
  }
});


define([ 'knockout', 'main/router', 'knockout.validation', 'semantic',
        'datatables', 'DataTable', 'FileSaver','main/usermanagement', 'main/rolemanagement',
        'main/permissionmgmt', 'main/user_role_management','main/userpermissionmgmt', 'main/role_permission_mgmt', 
        'main/tableaumanagement', 'main/configuration_mgmt', 'main/job_mgmt', 'main/rlp_mgmt', 'main/reports_management', 'main/report_group_management', 'main/annotatecomment'],
   function(ko, Router, kv, semantic, datatables, DataTable, FileSaver,UserManagerModel, 
         RoleManagerModel, PermissionMgmtModel, UserRoleManagerModel, UserPermissionManagerModel, RolePermissionManagerModel, 
         TableauManagerModel, ConfigurationManagerModel, JobManagerModel, RowLevelPermissionModel, ReportManagementModel, ReportGroupManagementModel, AnotateCommentModel) {

    var initializePages = function(){

    	var currentPage = "home";

      // More specific matches should come first.
      var urlMapping = {        
        home:   { match: /^$/,                    page: adminPage},
        changepassword: { match: /^changepassword/,        page: changepasswordPage},
        createsurveyor: { match: /^createsurveyor$/, page:createsurveyorPage},
        editsurveyor: { match: /^editsurveyor$/, page:editsurveyorPage},
        createrole: { match: /^createrole$/, page:createrolePage},
        uploadusers: { match: /^uploadusers$/, page:uploadUsersPage},
        editrole: { match: /^editsurveyor$/, page:editrolePage},
        usermanagement: { match: /^usermanagement$/, page: usermanagementPage},
        rolemanagement: { match: /^rolemanagement$/, page: rolemanagementPage},
        permissionmanagement: { match: /^permissionmanagement$/, page: permissionmanagementPage},        
        userrolemanagement: { match: /^userrolemanagement$/, page: userrolemanagementPage},
        userpermissionmanagement :{ match: /^userpermissionmanagement$/, page: userpermissionmanagementPage},
        rolePermissionmanagement: { match: /^rolePermissionmanagement$/, page: rolePermissionmanagementPage},
        tabdashboard: { match: /^tabdashboard$/, page: tabdashboardPage},
        anotatecomment: { match: /^anotatecomment$/, page: anotatecommentPage},
        systemsettings: { match: /^systemsettings$/, page: systemSettingsPage},
        configuration: {match: /^configuration$/, page: configPage},
        configtemplate: {match: /^configtemplate$/, page: configtemplatePage},
        configinstance: {match: /^configinstance$/, page: configinstancePage},
        clientjobconfig: {match: /^clientjobconfig$/, page: clientjobconfigPage},
        reportmgmt: {match: /^reportmgmt$/, page: reportmgmtPage},
        reportgrpmgmt: {match: /^reportgrpmgmt$/, page: reportgrpmgmtPage},
        rowlevelpermission: {match: /^rowlevelpermission$/, page: rowlevelpermissionPage},
        userrowlevelpermission: {match: /^userrowlevelpermission$/, page: userrowlevelpermissionPage},
        rolerowlevelpermission: {match: /^rolerowlevelpermission$/, page: rolerowlevelpermissionPage}
      };

      var usermgmt = new UserManagerModel();
      var rolemgmt = new RoleManagerModel();
      var permissionmgmt= new PermissionMgmtModel();
      var tableaumgmt = new TableauManagerModel();
      var anotate = new AnotateCommentModel();
      var configmgmt = new ConfigurationManagerModel();
      var clientjobmgmt = new JobManagerModel();
      var rowlevelmgmt = RowLevelPermissionModel();
      var reportmgmt = new ReportManagementModel();
      var reportgrpmgmt = new ReportGroupManagementModel();

      function cleanUp() {
    	  if(tableaumgmt) {
    		  tableaumgmt.clearAll();
    	  }
      }

      function changepasswordPage(){
          return showPageLoader(function() {
        	  cleanUp();
        	  return new Router.Page('Change Password', 'password-change', {usermanagement:usermgmt});
          });
      }

      function adminPage() {
    	  return showPageLoader(function() {
    		  cleanUp();
    		  return new Router.Page('Xoanon Reporting Portal', 'admin-template', {tableaumgmt: tableaumgmt});
    	  });
      }

      function createsurveyorPage() {
    	  return showPageLoader(function() {
    		  cleanUp();
    		  return new Router.Page('Create User','create-surveyor',{usermanagement:usermgmt});
    	  });
      }

      function editsurveyorPage() {
    	  return showPageLoader(function() {
    		  cleanUp();
    		  return new Router.Page('Edit User','create-surveyor',{usermanagement:usermgmt});
    	  });
      }

      function createrolePage() {
        return showPageLoader(function() {
        	cleanUp();
        	return new Router.Page('Create Role','create-role',{rolemanagement:rolemgmt});
        });
      }

      function editrolePage() {
        return showPageLoader(function() {
        	cleanUp();
        	return new Router.Page('Edit Role','create-role',{rolemanagement:rolemgmt});
        });
      }

      function uploadUsersPage() {
    	  return showPageLoader(function() {
    		  cleanUp();
    		  return new Router.Page('Upload Users','upload-users',{usermanagement:usermgmt});
    	  });
      }

      function usermanagementPage() {
    	  return showPageLoader(function() {
    		  cleanUp();
    		  usermgmt.loadUsers();
    		  return new Router.Page('Manage Users','user_mgmt',{usermanagement:usermgmt});
    	  });
      }

      function rolemanagementPage() {
        return showPageLoader(function() {
        	cleanUp();
        	rolemgmt.loadRoles();
        	return new Router.Page('Manage Roles','role_mgmt',{rolemanagement:rolemgmt});
        });
      }
      
      function permissionmanagementPage() {
        return showPageLoader(function() {
        	cleanUp();
        	permissionmgmt.loadPermissions();
        	return new Router.Page('Manage Permissions','permission_mgmt',{permissionmanagement:permissionmgmt});
        });
      }

      function rolePermissionmanagementPage() {
        return showPageLoader(function() {
        	cleanUp();
        	var roleprmgmt = new RolePermissionManagerModel(rolemgmt.currentRole());
        	roleprmgmt.loadRolesPermission();
        	roleprmgmt.loadPermissions();
        	return new Router.Page('Manage Role Permissions','role_perm_mgmt',{rolespermissionmgmt:roleprmgmt});
        });
      }

      function userrolemanagementPage() {
    	  return showPageLoader(function() {
    		  cleanUp();
    		  var userrolemgnt = new UserRoleManagerModel(usermgmt.currentUser());
    		  userrolemgnt.loadUserRoles();
    		  userrolemgnt.loadUnassignedRoles();
    		  return new Router.Page('Manage User Role','user_role_mgmt', {userrolemgnt: userrolemgnt});
    	  });
      }

      function userpermissionmanagementPage() {
        return showPageLoader(function() {
        	cleanUp();
        	var userpermmgnt = new UserPermissionManagerModel(usermgmt.currentUser());
        	userpermmgnt.loadUserPermissions();
        	userpermmgnt.loadUnassignedPermissions();
        	return new Router.Page('Manage User Permissions','user_perm_mgmt', {userpermmgnt: userpermmgnt});
        });
      }

      function tabdashboardPage() {
        return showPageLoader(function() {
        	tableaumgmt.clearAll();
        	tableaumgmt.loadClients();
        	tableaumgmt.loadDashboardData();
        	return new Router.Page('Dashboard','tab_dashboard', {tableaumgmt: tableaumgmt});
        });
      }
      
      function anotatecommentPage() {
        return showPageLoader(function() {
        	anotate.initViz();
        	return new Router.Page('Anotate','anotate_comment', {anotate: anotate});
        });
      }

      function systemSettingsPage() {
			return showPageLoader(function() {
				return new Router.Page('System Settings', 'system_settings', {});
			});
	}
      
      function configPage() {
			return showPageLoader(function() {
				configmgmt.currentPage('configuration');
				configmgmt.cleanup();
				configmgmt.loadConfigurations();
				return new Router.Page('Configuration', 'config', {configmgmt : configmgmt});
			});
		}

      function configtemplatePage() {
			return showPageLoader(function() {
				configmgmt.currentPage('configtemplate');
				configmgmt.cleanup();
				configmgmt.loadConfigTemplates();
				return new Router.Page('Configuration Template',	'conftemp_mgmt', {configmgmt : configmgmt});
			});
		}

      function configinstancePage() {
			return showPageLoader(function() {
				configmgmt.currentPage('configinstance');
				configmgmt.cleanup();
				configmgmt.loadConfigInstances();
				return new Router.Page('Config Instances',	'confinstance_mgmt', {configmgmt : configmgmt});
			});
      }

      function clientjobconfigPage() {
    	  return showPageLoader(function() {
    		  clientjobmgmt.currentPage('clientjobconfig');
    		  clientjobmgmt.cleanup();
    		  clientjobmgmt.loadallClientJobsConfigs();
				return new Router.Page('Config Client Job',	'clientjobconfig_mgmt', {clientjobmgmt : clientjobmgmt});
			});
      }

      function reportmgmtPage() {
          	  return showPageLoader(function() {
          	  reportmgmt.currentPage('reportmgmt');
          	  reportmgmt.cleanup();
          	  reportmgmt.loadReports();
          	  reportmgmt.loadGroups();
      				return new Router.Page('Manage Reports',	'report_mgmt', { reportmgmt:reportmgmt });
      			});
            }

      function reportgrpmgmtPage() {
              return showPageLoader(function() {
              reportgrpmgmt.currentPage('reportgrpmgmt');
              reportgrpmgmt.loadGroups();
            	  return new Router.Page('Manage Reports Group',	'report_grp_mgmt', { reportgrpmgmt:reportgrpmgmt });
                });
            }

      function rowlevelpermissionPage() {
    	  return showPageLoader(function() {
    		  	rowlevelmgmt.currentPage('rowlevelpermission');
    		  	rowlevelmgmt.cleanup();
				return new Router.Page('Row Level Permission',	'rowlevelpermission_mgmt', {});
			});
      }

      function userrowlevelpermissionPage() {
    	  return showPageLoader(function() {
    		  	rowlevelmgmt.currentPage('userrowlevelpermission');
    		  	rowlevelmgmt.cleanup();
    		  	rowlevelmgmt.selectedGroup('User');
				return new Router.Page('User Based Permissions',	'userrowlevelpermission_mgmt', {'rowlevelmgmt' : rowlevelmgmt});
			});
      }

      function rolerowlevelpermissionPage() {
    	  return showPageLoader(function() {
    		  	rowlevelmgmt.currentPage('rowlevelpermission');
    		  	rowlevelmgmt.cleanup();
    		  	rowlevelmgmt.selectedGroup('Role');
				return new Router.Page('Role Based Permissions',	'rolerowlevelpermission_mgmt', {'rowlevelmgmt' : rowlevelmgmt});
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
    };

    initializePages();
    $("#preloader").fadeOut('slow');
});