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
    /*selectize : 'selectize.js/js/standalone/selectize.min',*/
    main :  xoappcontext + '/vassets/js/koapp/main',
    foundation: 'foundation/js/foundation.min',
    datatables:  xoappcontext + '/vassets/js/datatable/jquery.dataTables',
    DataTable:  xoappcontext + '/vassets/js/datatable/foundation.datatable',
    Responsive:  xoappcontext + '/vassets/js/datatable/dataTables.responsive'
  }
});


define([ 'knockout', 'main/router', 'knockout.validation', 'foundation',
        'datatables', 'DataTable','Responsive', 'main/usermanagement', 'main/rolemanagement',
        'main/permissionmgmt', 'main/user_role_management','main/userpermissionmgmt', 'main/role_permission_mgmt', 
        'main/tableaumanagement', 'main/configuration_mgmt', 'main/job_mgmt', 'main/rlp_mgmt', 'main/reports_management', 'main/report_group_management', 'main/diffusion_map'],
   function(ko, Router, kv, foundation, datatables, DataTable, Responsive, UserManagerModel, 
         RoleManagerModel, PermissionMgmtModel, UserRoleManagerModel, UserPermissionManagerModel, RolePermissionManagerModel, 
         TableauManagerModel, ConfigurationManagerModel, JobManagerModel, RowLevelPermissionModel, ReportManagementModel, ReportGroupManagementModel,DiffusionManagerModel) {

    var initializePages = function(){

    	var currentPage = "home";

      // More specific matches should come first.
      var urlMapping = {        
        home:   { match: /^$/,                    page: adminPage},
        diffusionmap: { match: /^diffusionmap$/, page: diffusionmapPage},
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
      var configmgmt = new ConfigurationManagerModel();
      var clientjobmgmt = new JobManagerModel();
      var rowlevelmgmt = RowLevelPermissionModel();
      var reportmgmt = new ReportManagementModel();
      var reportgrpmgmt = new ReportGroupManagementModel();
	  var diffMap = new DiffusionManagerModel();
	  
      function cleanUp() {
    	  if(tableaumgmt) {
    		  tableaumgmt.clearAll();
    	  }
      }

      function changepasswordPage(){
          return showPageLoader(function() {
        	  cleanUp();
        	  return new Router.Page('Xoanon Analytics Reporting Portal', 'password-change', {usermanagement:usermgmt});
          });
      }

      function adminPage() {
    	  return showPageLoader(function() {
    		  cleanUp();
    		  return new Router.Page('Xoanon Analytics Reporting Portal', 'admin-template', {});
    	  });
      }

      function createsurveyorPage() {
    	  return showPageLoader(function() {
    		  cleanUp();
    		  return new Router.Page('Xoanon Analytics Reporting Portal','create-surveyor',{usermanagement:usermgmt});
    	  });
      }

      function editsurveyorPage() {
    	  return showPageLoader(function() {
    		  cleanUp();
    		  return new Router.Page('Xoanon Analytics Reporting Portal','create-surveyor',{usermanagement:usermgmt});
    	  });
      }

      function createrolePage() {
        return showPageLoader(function() {
        	cleanUp();
        	return new Router.Page('Xoanon Analytics Reporting Portal','create-role',{rolemanagement:rolemgmt});
        });
      }

      function editrolePage() {
        return showPageLoader(function() {
        	cleanUp();
        	return new Router.Page('Xoanon Analytics Reporting Portal','create-role',{rolemanagement:rolemgmt});
        });
      }

      function uploadUsersPage() {
    	  return showPageLoader(function() {
    		  cleanUp();
    		  return new Router.Page('Xoanon Analytics Reporting Portal','upload-users',{usermanagement:usermgmt});
    	  });
      }

      function usermanagementPage() {
    	  return showPageLoader(function() {
    		  cleanUp();
    		  usermgmt.loadUsers();
    		  return new Router.Page('Xoanon Analytics Reporting Portal','user_mgmt',{usermanagement:usermgmt});
    	  });
      }

      function rolemanagementPage() {
        return showPageLoader(function() {
        	cleanUp();
        	rolemgmt.loadRoles();
        	return new Router.Page('Xoanon Analytics Reporting Portal','role_mgmt',{rolemanagement:rolemgmt});
        });
      }
      
      function permissionmanagementPage() {
        return showPageLoader(function() {
        	cleanUp();
        	permissionmgmt.loadPermissions();
        	return new Router.Page('Xoanon Analytics Reporting Portal','permission_mgmt',{permissionmanagement:permissionmgmt});
        });
      }

      function rolePermissionmanagementPage() {
        return showPageLoader(function() {
        	cleanUp();
        	var roleprmgmt = new RolePermissionManagerModel(rolemgmt.currentRole());
        	roleprmgmt.loadRolesPermission();
        	roleprmgmt.loadPermissions();
        	return new Router.Page('Xoanon Analytics Reporting Portal','role_perm_mgmt',{rolespermissionmgmt:roleprmgmt});
        });
      }

      function userrolemanagementPage() {
    	  return showPageLoader(function() {
    		  cleanUp();
    		  var userrolemgnt = new UserRoleManagerModel(usermgmt.currentUser());
    		  userrolemgnt.loadUserRoles();
    		  userrolemgnt.loadUnassignedRoles();
    		  return new Router.Page('Xoanon Analytics Reporting Portal','user_role_mgmt', {userrolemgnt: userrolemgnt});
    	  });
      }

      function userpermissionmanagementPage() {
        return showPageLoader(function() {
        	cleanUp();
        	var userpermmgnt = new UserPermissionManagerModel(usermgmt.currentUser());
        	userpermmgnt.loadUserPermissions();
        	userpermmgnt.loadUnassignedPermissions();
        	return new Router.Page('Xoanon Analytics Reporting Portal','user_perm_mgmt', {userpermmgnt: userpermmgnt});
        });
      }

      function tabdashboardPage() {
        return showPageLoader(function() {
        	tableaumgmt.clearAll();
        	tableaumgmt.loadClients();
        	tableaumgmt.loadDashboardData();
        	return new Router.Page('Xoanon Analytics Reporting Portal','tab_dashboard', {tableaumgmt: tableaumgmt});
        });
      }
      
      function diffusionmapPage() {
        return showPageLoader(function() {
        	diffMap.clearAll();
        	diffMap.showDiffusionMap();
        	diffMap.getComments();
        	diffMap.isTitleVisible(true);
        	return new Router.Page('Xoanon Analytics Reporting Portal','diffusion_map',{diffMap:diffMap});
        });
      }

      function systemSettingsPage() {
			return showPageLoader(function() {
				return new Router.Page('Xoanon Analytics Reporting Portal', 'system_settings', {});
			});
	}
      
      function configPage() {
			return showPageLoader(function() {
				configmgmt.currentPage('configuration');
				configmgmt.cleanup();
				configmgmt.loadConfigurations();
				return new Router.Page('Xoanon Analytics Reporting Portal', 'config', {configmgmt : configmgmt});
			});
		}

      function configtemplatePage() {
			return showPageLoader(function() {
				configmgmt.currentPage('configtemplate');
				configmgmt.cleanup();
				configmgmt.loadConfigTemplates();
				return new Router.Page('Xoanon Analytics Reporting Portal',	'conftemp_mgmt', {configmgmt : configmgmt});
			});
		}

      function configinstancePage() {
			return showPageLoader(function() {
				configmgmt.currentPage('configinstance');
				configmgmt.cleanup();
				configmgmt.loadConfigInstances();
				return new Router.Page('Xoanon Analytics Reporting Portal',	'confinstance_mgmt', {configmgmt : configmgmt});
			});
      }

      function clientjobconfigPage() {
    	  return showPageLoader(function() {
    		  clientjobmgmt.currentPage('clientjobconfig');
    		  clientjobmgmt.cleanup();
    		  clientjobmgmt.loadallClientJobsConfigs();
				return new Router.Page('Xoanon Analytics Reporting Portal',	'clientjobconfig_mgmt', {clientjobmgmt : clientjobmgmt});
			});
      }

      function reportmgmtPage() {
          	  return showPageLoader(function() {
          	  reportmgmt.currentPage('reportmgmt');
          	  reportmgmt.cleanup();
          	  reportmgmt.loadReports();
          	  reportmgmt.loadGroups();
      				return new Router.Page('Xoanon Analytics Reporting Portal',	'report_mgmt', { reportmgmt:reportmgmt });
      			});
            }

      function reportgrpmgmtPage() {
              return showPageLoader(function() {
              reportgrpmgmt.currentPage('reportgrpmgmt');
              reportgrpmgmt.loadGroups();
            	  return new Router.Page('Xoanon Analytics Reporting Portal',	'report_grp_mgmt', { reportgrpmgmt:reportgrpmgmt });
                });
            }

      function rowlevelpermissionPage() {
    	  return showPageLoader(function() {
    		  	rowlevelmgmt.currentPage('rowlevelpermission');
    		  	rowlevelmgmt.cleanup();
				return new Router.Page('Xoanon Analytics Reporting Portal',	'rowlevelpermission_mgmt', {});
			});
      }

      function userrowlevelpermissionPage() {
    	  return showPageLoader(function() {
    		  	rowlevelmgmt.currentPage('userrowlevelpermission');
    		  	rowlevelmgmt.cleanup();
    		  	rowlevelmgmt.selectedGroup('User');
				return new Router.Page('Xoanon Analytics Reporting Portal',	'userrowlevelpermission_mgmt', {'rowlevelmgmt' : rowlevelmgmt});
			});
      }

      function rolerowlevelpermissionPage() {
    	  return showPageLoader(function() {
    		  	rowlevelmgmt.currentPage('rowlevelpermission');
    		  	rowlevelmgmt.cleanup();
    		  	rowlevelmgmt.selectedGroup('Role');
				return new Router.Page('Xoanon Analytics Reporting Portal',	'rolerowlevelpermission_mgmt', {'rowlevelmgmt' : rowlevelmgmt});
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
    };

    initializePages();
    $(".se-pre-con").fadeOut('slow');
});