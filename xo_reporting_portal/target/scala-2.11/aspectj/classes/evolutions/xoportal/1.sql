# --- !Ups

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

--
-- Table structure for table `resource_types`
--

DROP TABLE IF EXISTS `resource_types`;
CREATE TABLE `resource_types`(
  `resource_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `fully_qualified_name` varchar(200) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  PRIMARY KEY (`resource_type_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `resource_types`(name, fully_qualified_name, description) VALUES 
('User', 'com.xo.web.models.system.User', 'XoQuick survey Portal User entity'),
('Role', 'com.xo.web.models.system.Role', 'XoQuick survey Portal Role entity'),
('Permission', 'com.xo.web.models.system.Permission', 'XoQuick survey Portal Permission entity'),
('UserRole', 'com.xo.web.models.system.UserRole', 'XoQuick survey Portal User\'s role entity'),
('UsersPermission', 'com.xo.web.models.system.UserPermission', 'XoQuick survey Portal User\'s Permission entity'),
('RolesPermission', 'com.xo.web.models.system.RolePermission', 'XoQuick survey Portal Role\'s Permission entity'),
('UsersPermissionsResourceInstance', 'com.xo.web.models.system.UserPermissionResourceInstance', 'XoQuick survey Portal User\'s Permission on specific resource instance entity'),
('RolesPermissionsResourceInstance', 'com.xo.web.models.system.RolePermissionResourceInstance', 'XoQuick survey Portal Role\'s Permission on specific resource instance entity'),
('TableauSite', 'com.xo.web.ext.tableau.models.TableauSite', 'Tableau site entity'),
('TableauUser', 'com.xo.web.ext.tableau.models.TableauUser', 'Tableau user entity'),
('TableauProject', 'com.xo.web.ext.tableau.models.TableauProject', 'Tableau project entity'),
('TableauWorkbook', 'com.xo.web.ext.tableau.models.TableauWorkbook', 'Tableau workbook entity'),
('TableauView', 'com.xo.web.ext.tableau.models.TableauView', 'Tableau view entity');

-- Identifying the tableau project / workbook / view resources ids.

SET @TAB_PR_RES_ID=null;
SET @TAB_WK_RES_ID=null;
SET @TAB_VW_RES_ID=null;

SELECT resource_type_id into @TAB_PR_RES_ID FROM resource_types where fully_qualified_name='com.xo.web.ext.tableau.models.TableauProject';
SELECT resource_type_id into @TAB_WK_RES_ID FROM resource_types where fully_qualified_name='com.xo.web.ext.tableau.models.TableauWorkbook';
SELECT resource_type_id into @TAB_VW_RES_ID FROM resource_types where fully_qualified_name='com.xo.web.ext.tableau.models.TableauView';

--
-- Table structure for table `resource_filters`
--

DROP TABLE IF EXISTS `resource_filters`;
CREATE TABLE `resource_filters`(
  `resource_filter_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `fully_qualified_name` varchar(200) NOT NULL,
  `resource_type_id` int(11) NOT NULL, 
  `description` varchar(100) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  PRIMARY KEY (`resource_filter_id`),
  KEY `fk_resource_type_filters_idx` (`resource_type_id`),
  CONSTRAINT `fk_resource_type_filters` FOREIGN KEY (`resource_type_id`) REFERENCES `resource_types` (`resource_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `resource_filters`(resource_type_id, name, fully_qualified_name, description) VALUES 
(@TAB_PR_RES_ID, 'Tableau Project Permission filter', 'authfill_tableau_project', 'Tableau Project Permission filter'),
(@TAB_WK_RES_ID, 'Tableau workbook Permission filter', 'authfill_tableau_workbook', 'Tableau Project Permission filter'),
(@TAB_VW_RES_ID, 'Tableau view Permission filter', 'authfill_tableau_view', 'Tableau view Permission filter');

SET @TAB_PR_RES_ID=null;
SET @TAB_WK_RES_ID=null;
SET @TAB_VW_RES_ID=null;

SELECT resource_filter_id into @TAB_PR_RES_ID FROM resource_filters where fully_qualified_name='authfill_tableau_project';
SELECT resource_filter_id into @TAB_WK_RES_ID FROM resource_filters where fully_qualified_name='authfill_tableau_workbook';
SELECT resource_filter_id into @TAB_VW_RES_ID FROM resource_filters where fully_qualified_name='authfill_tableau_view';

--
-- Table structure for table `resource_filter_parameters`
--

DROP TABLE IF EXISTS `resource_filter_parameters`;

CREATE TABLE `resource_filter_parameters` (
  `resource_filter_parameter_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `param_type` varchar(20) DEFAULT 'int',
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `resource_filter_id` int(11) not NULL,
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  PRIMARY KEY (`resource_filter_parameter_id`),
  KEY `fk_resource_filters_params_idx` (`resource_filter_id`),
  CONSTRAINT `fk_resource_filters_params` FOREIGN KEY (`resource_filter_id`) REFERENCES `resource_filters` (`resource_filter_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `resource_filter_parameters`(resource_filter_id, name, description, param_type) VALUES 
(@TAB_PR_RES_ID, 'resourceIds', 'Tableau project permission filter parameter.', 'LIST_STRING'),
(@TAB_WK_RES_ID, 'resourceIds', 'Tableau workbook permission filter parameter.', 'LIST_STRING'),
(@TAB_VW_RES_ID, 'resourceIds', 'Tableau view permission filter parameter.', 'LIST_STRING');

SET @TAB_PR_RES_ID=null;
SET @TAB_WK_RES_ID=null;
SET @TAB_VW_RES_ID=null;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;

CREATE TABLE `permissions` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `resource_type_id` int(11) not NULL,
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  PRIMARY KEY (`permission_id`),
  KEY `fk_resource_types_permissions_idx` (`resource_type_id`),
  CONSTRAINT `fk_resource_types_permissions` FOREIGN KEY (`resource_type_id`) REFERENCES `resource_types` (`resource_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `permissions`
--

PREPARE GET_RESOURCE_TYPE_ID FROM 'select resource_type_id into @RESOURCE_TYPE_ID from resource_types where fully_qualified_name=?';

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.models.system.User';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES 
('CREATE_USER','Create User', @RESOURCE_TYPE_ID),
('READ_USER','View User Details', @RESOURCE_TYPE_ID),
('UPDATE_USER','Modify User Details', @RESOURCE_TYPE_ID),
('UPDATE_PASSWORD','Modify Password', @RESOURCE_TYPE_ID),
('DEACTIVATE_USER','De-Activate Users',@RESOURCE_TYPE_ID),
('ACTIVATE_USER','Activate Users', @RESOURCE_TYPE_ID),
('DELETE_USER','Delete Users', @RESOURCE_TYPE_ID);

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.models.system.UserRole';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES
('CREATE_USER_ROLE','Create User Role', @RESOURCE_TYPE_ID),
('READ_USER_ROLE','View User Role Details', @RESOURCE_TYPE_ID),
('UPDATE_USER_ROLE','Modify User Role Details',@RESOURCE_TYPE_ID),
('DELETE_USER_ROLE','Delete User Role Details', @RESOURCE_TYPE_ID),
('ACTIVATE_USER_ROLE','Activate User Role', @RESOURCE_TYPE_ID),
('DEACTIVATE_USER_ROLE','De-Activate User Role', @RESOURCE_TYPE_ID);

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.models.system.UserPermission';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES
('CREATE_USER_PERMISSION','Create User Permission', @RESOURCE_TYPE_ID),
('READ_USER_PERMISSION','View User Permission Details', @RESOURCE_TYPE_ID),
('UPDATE_USER_PERMISSION','Modify User Permission Details', @RESOURCE_TYPE_ID),
('ACTIVATE_USER_PERMISSION','Activate User Permissions', @RESOURCE_TYPE_ID),
('DEACTIVATE_USER_PERMISSION','De-Activate User Permissions', @RESOURCE_TYPE_ID),
('DELETE_USER_PERMISSION','Delete User Permissions', @RESOURCE_TYPE_ID);

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.models.system.Role';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES
('CREATE_ROLE','Create Role', @RESOURCE_TYPE_ID),
('READ_ROLE','View Role Details', @RESOURCE_TYPE_ID),
('UPDATE_ROLE','Modify Role Details', @RESOURCE_TYPE_ID),
('DEACTIVATE_ROLE','De-Activate Roles', @RESOURCE_TYPE_ID),
('ACTIVATE_ROLE','Activate Roles', @RESOURCE_TYPE_ID),
('DELETE_ROLE','Delete Roles', @RESOURCE_TYPE_ID);

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.models.system.RolePermission';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES
('CREATE_ROLE_PERMISSION','Create Role Permission', @RESOURCE_TYPE_ID),
('READ_ROLE_PERMISSION','View Role Permission Details', @RESOURCE_TYPE_ID),
('UPDATE_ROLE_PERMISSION','Modify Role Permission Details', @RESOURCE_TYPE_ID),
('ACTIVATE_ROLE_PERMISSION','Activate Role Permissions', @RESOURCE_TYPE_ID),
('DEACTIVATE_ROLE_PERMISSION','De-Activate Role Permissions', @RESOURCE_TYPE_ID),
('READ_PERMISSION','View Permission Details', @RESOURCE_TYPE_ID),
('DEACTIVATE_PERMISSION','De-Activate Permissions', @RESOURCE_TYPE_ID),
('ACTIVATE_PERMISSION','Activate Permissions', @RESOURCE_TYPE_ID);

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.ext.tableau.models.TableauSite';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES
('SYNC_TABLEAU_DATA_VIA_REST', 'Create Site', @RESOURCE_TYPE_ID),
('CREATE_SITE', 'Create Site', @RESOURCE_TYPE_ID),
('READ_SITE', 'View Site Details', @RESOURCE_TYPE_ID),
('UPDATE_SITE', 'Modify Site Details', @RESOURCE_TYPE_ID),
('DEACTIVATE_SITE', 'De-Activate Site', @RESOURCE_TYPE_ID),
('ACTIVATE_SITE', 'Activate Site', @RESOURCE_TYPE_ID),
('DELETE_SITE', 'Delete Site', @RESOURCE_TYPE_ID);

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.ext.tableau.models.TableauUser';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES
('CREATE_TABLEAU_USER', 'Create Tableau User', @RESOURCE_TYPE_ID),
('READ_TABLEAU_USER', 'View Tableau User Details', @RESOURCE_TYPE_ID),
('UPDATE_TABLEAU_USER', 'Modify Tableau User Details', @RESOURCE_TYPE_ID),
('DEACTIVATE_TABLEAU_USER', 'De-Activate Tableau User', @RESOURCE_TYPE_ID),
('ACTIVATE_TABLEAU_USER', 'Activate Tableau User', @RESOURCE_TYPE_ID),
('DELETE_TABLEAU_USER', 'Delete Tableau User', @RESOURCE_TYPE_ID);

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.ext.tableau.models.TableauProject';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES
('CREATE_TABLEAU_PROJECT', 'Create Tableau Project', @RESOURCE_TYPE_ID),
('READ_TABLEAU_PROJECT', 'View Tableau User Details', @RESOURCE_TYPE_ID),
('UPDATE_TABLEAU_PROJECT', 'Modify Tableau Project Details', @RESOURCE_TYPE_ID),
('DEACTIVATE_TABLEAU_PROJECT', 'De-Activate Tableau Projects', @RESOURCE_TYPE_ID),
('ACTIVATE_TABLEAU_PROJECT', 'Activate Tableau Projects', @RESOURCE_TYPE_ID),
('DELETE_TABLEAU_PROJECT', 'Delete Tableau Projects', @RESOURCE_TYPE_ID);

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.ext.tableau.models.TableauWorkbook';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES
('CREATE_TABLEAU_WORKBOOK', 'Create Tableau Workbook', @RESOURCE_TYPE_ID),
('READ_TABLEAU_WORKBOOK', 'View Tableau Workbook Details', @RESOURCE_TYPE_ID),
('UPDATE_TABLEAU_WORKBOOK', 'Modify Tableau Workbook Details', @RESOURCE_TYPE_ID),
('DEACTIVATE_TABLEAU_WORKBOOK', 'De-Activate Tableau Workbooks', @RESOURCE_TYPE_ID),
('ACTIVATE_TABLEAU_WORKBOOK', 'Activate Tableau Workbooks', @RESOURCE_TYPE_ID),
('DELETE_TABLEAU_WORKBOOK', 'Delete Tableau Workbooks', @RESOURCE_TYPE_ID);

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.ext.tableau.models.TableauView';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES
('CREATE_TABLEAU_VIEW', 'Create Tableau View', @RESOURCE_TYPE_ID),
('READ_TABLEAU_VIEW', 'View Tableau View Details', @RESOURCE_TYPE_ID),
('UPDATE_TABLEAU_VIEW', 'Modify Tableau View Details', @RESOURCE_TYPE_ID),
('DEACTIVATE_TABLEAU_VIEW', 'De-Activate Tableau Views', @RESOURCE_TYPE_ID),
('ACTIVATE_TABLEAU_VIEW', 'Activate Tableau Views', @RESOURCE_TYPE_ID),
('DELETE_TABLEAU_VIEW', 'Delete Tableau Views', @RESOURCE_TYPE_ID);

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE=null;
DEALLOCATE PREPARE GET_RESOURCE_TYPE_ID;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `system_resource` tinyint(1) NOT NULL DEFAULT '0',
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO roles(name, system_resource, description) VALUES('Viewer', 0,'Xoportal Report Viewer');

--
-- Table structure for table `roles_permissions`
--

DROP TABLE IF EXISTS `roles_permissions`;
CREATE TABLE `roles_permissions` (
  `role_permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  PRIMARY KEY (`role_permission_id`),
  UNIQUE KEY (`permission_id`,`role_id`),
  KEY `fk_roles_has_permissions_permissions_idx` (`permission_id`),
  KEY `fk_roles_has_permissions_roles_idx` (`role_id`),
  CONSTRAINT `fk_roles_has_permissions_permissions1` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`permission_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_roles_has_permissions_roles1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Assigning permissions to Viewer role
SET @ENTITY_ID=null;
select role_id into @ENTITY_ID from roles where name='Viewer';

-- Inserting the basic permissions for the role
INSERT INTO `roles_permissions`(role_id, permission_id) 
SELECT @ENTITY_ID, permission_id from permissions where name in ('READ_USER', 'UPDATE_USER', 'UPDATE_PASSWORD', 'READ_SITE', 
'READ_DASHBOARD', 'READ_TABLEAU_USER', 'READ_TABLEAU_PROJECT', 'READ_TABLEAU_WORKBOOK', 'READ_TABLEAU_VIEW');               

--
-- Table structure for table `roles_permissions_resource_instance`
--

DROP TABLE IF EXISTS `roles_permissions_resource_instance`;
CREATE TABLE `roles_permissions_resource_instance` (
  `role_permission_id` int(11) NOT NULL,
  `resource_instance_id` varchar(50) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  PRIMARY KEY (`role_permission_id`, `resource_instance_id`),
  CONSTRAINT `fk_roles_permissions_resinst` FOREIGN KEY (`role_permission_id`) REFERENCES `roles_permissions` (`role_permission_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `second_name` varchar(50) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `system_resource` tinyint(1) NOT NULL DEFAULT '0',
  `super_user` tinyint(1) NOT NULL DEFAULT '0',
  `is_email_verified` tinyint(1) NOT NULL DEFAULT '0',
  `last_login_dt` TIMESTAMP NULL,
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO users(first_name, second_name, email, is_email_verified, system_resource, super_user, password) VALUES
('Xoanon Portal','Super User','xoadmin@xo.com',1, 1, 1, '$2a$10$L95FICvubuMv95e1MSSwy.cbmMPugImE0qog1YHtu5FzHIVlqdEji');

--
-- Table structure for table `token_actions`
--

DROP TABLE IF EXISTS `token_actions`;
CREATE TABLE `token_actions` (
  `token_action_id` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  `expires_dt` TIMESTAMP NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`token_action_id`),
  KEY `fk_token_actions_users_idx` (`user_id`),
  CONSTRAINT `fk_token_actions_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `users_permissions`
--

DROP TABLE IF EXISTS `users_permissions`;
CREATE TABLE `users_permissions` (
  `user_permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  PRIMARY KEY (`user_permission_id`),
  UNIQUE KEY (`permission_id`,`user_id`),
  KEY `fk_user_has_permission_permission_idx` (`permission_id`),
  KEY `fk_user_has_permission_user_idx` (`user_id`),
  CONSTRAINT `fk_users_has_permissions_permissions` FOREIGN KEY (`permission_id`) REFERENCES `permissions` (`permission_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_permissions_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `users_permissions_resource_instance`
--

DROP TABLE IF EXISTS `users_permissions_resource_instance`;
CREATE TABLE `users_permissions_resource_instance` (
  `user_permission_id` int(11) NOT NULL,
  `resource_instance_id` varchar(50) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  PRIMARY KEY (`user_permission_id`, `resource_instance_id`),
  KEY `fk_users_permissions_resins_idx` (`user_permission_id`),
  CONSTRAINT `fk_users_permissions_resinst_perm` FOREIGN KEY (`user_permission_id`) REFERENCES `users_permissions` (`user_permission_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `fk_users_has_roles_roles1_idx` (`role_id`),
  KEY `fk_users_has_roles_users_idx` (`user_id`),
  CONSTRAINT `fk_users_has_roles_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_roles_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- -------------------------
-- Business related tables
-- -------------------------

--
-- Table structure for table `tableau_sites`
--

DROP TABLE IF EXISTS `tableau_sites`;
CREATE TABLE `tableau_sites` (
  `tableau_site_id` varchar(40) NOT NULL,
  `name` varchar(75) DEFAULT NULL,
  `content_url` varchar(150) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  PRIMARY KEY (`tableau_site_id`),
  KEY `fk_tableau_sites_idx` (`tableau_site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `tableau_users`
--

DROP TABLE IF EXISTS `tableau_users`;
CREATE TABLE `tableau_users` (
  `tableau_user_id` varchar(40) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `role` varchar(25) DEFAULT NULL,
  `publish` tinyint(1) NOT NULL DEFAULT '0',
  `content_admin` varchar(25) DEFAULT NULL,
  `lastLogin` datetime DEFAULT NULL,
  `externalAuthUserId` varchar(50) DEFAULT NULL,
  `tableau_site_id` varchar(40) NOT NULL,
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  PRIMARY KEY (`tableau_user_id`),
  KEY `fk_tableau_users_tableau_sites_idx` (`tableau_site_id`),
  CONSTRAINT `fk_tableau_users_sites` FOREIGN KEY (`tableau_site_id`) REFERENCES `tableau_sites` (`tableau_site_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `tableau_projects`
--

DROP TABLE IF EXISTS `tableau_projects`;
CREATE TABLE `tableau_projects` (
  `tableau_project_id` varchar(40) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `tableau_user_id` varchar(40) NOT NULL,
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  PRIMARY KEY (`tableau_project_id`),
  KEY `fk_tableau_projects_tableau_users1_idx` (`tableau_user_id`),
  CONSTRAINT `fk_tableau_projects_tableau_users1` FOREIGN KEY (`tableau_user_id`) REFERENCES `tableau_users` (`tableau_user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `tableau_workbooks`
--

DROP TABLE IF EXISTS `tableau_workbooks`;
CREATE TABLE `tableau_workbooks` (
  `tableau_workbook_id` varchar(40) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1',
  `tableau_project_id` varchar(40) NOT NULL,
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  PRIMARY KEY (`tableau_workbook_id`),
  KEY `fk_tableau_workbooks_tableau_projects1_idx` (`tableau_project_id`),
  CONSTRAINT `fk_tableau_workbooks_projects` FOREIGN KEY (`tableau_project_id`) REFERENCES `tableau_projects` (`tableau_project_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `tableau_views`
--

DROP TABLE IF EXISTS `tableau_views`;
CREATE TABLE `tableau_views` (
  `tableau_view_id` varchar(40) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `tableau_workbook_id` varchar(40) NOT NULL,
  `view_group_id` int(11) DEFAULT NULL,
  `last_modified_dt` TIMESTAMP NULL,
  `created_dt` TIMESTAMP NULL,
  `dashboard` BIT NOT NULL DEFAULT 0,
  `active` BIT NOT NULL DEFAULT 1,
  `display_order` int(2) DEFAULT 1,
  PRIMARY KEY (`tableau_view_id`),
  KEY `fk_tableau_views_workbooks_idx` (`tableau_workbook_id`),
  KEY `fk_tableau_views_groups_idx` (`view_group_id`),
  CONSTRAINT `fk_tableau_views_workbooks` FOREIGN KEY (`tableau_workbook_id`) REFERENCES `tableau_workbooks` (`tableau_workbook_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_tableau_views_groups` FOREIGN KEY (`view_group_id`) REFERENCES `view_groups` (`view_group_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;

# --- !Downs

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `resource_types`;
DROP TABLE IF EXISTS `resource_filters`;
DROP TABLE IF EXISTS `resource_filter_parameters`;
DROP TABLE IF EXISTS `permissions`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `roles_permissions`;
DROP TABLE IF EXISTS `roles_permissions_resource_instance`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `token_actions`;
DROP TABLE IF EXISTS `users_permissions`;
DROP TABLE IF EXISTS `users_permissions_resource_instance`;
DROP TABLE IF EXISTS `users_roles`;

-- Business related tables goes here.
DROP TABLE IF EXISTS `tableau_views`;
DROP TABLE IF EXISTS `tableau_workbooks`;
DROP TABLE IF EXISTS `tableau_projects`;
DROP TABLE IF EXISTS `tableau_users`;
DROP TABLE IF EXISTS `tableau_sites`;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;

