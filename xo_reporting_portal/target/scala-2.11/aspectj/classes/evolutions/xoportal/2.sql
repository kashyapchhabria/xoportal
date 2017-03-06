# --- !Ups

-- -------------------------
-- Configuration feature and row level permission features related sqls
-- -------------------------

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE=null;

alter table roles_permissions_resource_instance add column display_text varchar(200) after `resource_instance_id`;
alter table users_permissions_resource_instance add column display_text varchar(200) after `resource_instance_id`;

-- Initiating system specific settings

-- Adding new entities into resource types
INSERT INTO `resource_types`(name, fully_qualified_name, description) VALUES 
('ResourceType', 'com.xo.web.models.system.ResourceType', 'XoApp ResourceType entity'),
('XoConfigTemplate', 'com.xo.web.models.system.XoConfigTemplate', 'XoApp XoConfigTemplate entity'),
('XoConfigInstance', 'com.xo.web.models.system.XoConfigInstance', 'XoApp  XoConfigInstance entity'),
('XoClient', 'com.xo.web.models.system.XoClient', 'XoApp  XoClient entity'),
('XoJob', 'com.xo.web.models.system.XoJob', 'XoApp  XoJob entity'),
('XoClientJobConfig', 'com.xo.web.models.system.XoClientJobConfig', 'XoApp  client\'s job configuration entity'),
('view_groups', 'com.xo.web.ext.tableau.models.ViewGroup', 'XoApp Tableau Report Grouping entity');

--
-- Dumping data for table `permissions`
--

PREPARE GET_RESOURCE_TYPE_ID FROM 'select resource_type_id into @RESOURCE_TYPE_ID from resource_types where fully_qualified_name=?';

-- ResourceType related permissions

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.models.system.ResourceType';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES 
('READ_RESOURCE_TYPE','Read ResourceType details', @RESOURCE_TYPE_ID);

-- UserPermissionResourceInstance related permissions

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.models.system.UserPermissionResourceInstance';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES 
('CREATE_USER_PERMISSION_RESOURCE_INSTANCE','Create UserPermissionResourceInstance', @RESOURCE_TYPE_ID),
('READ_USER_PERMISSION_RESOURCE_INSTANCE','View UserPermissionResourceInstance Details', @RESOURCE_TYPE_ID),
('UPDATE_USER_PERMISSION_RESOURCE_INSTANCE','Modify UserPermissionResourceInstance Details', @RESOURCE_TYPE_ID),
('DELETE_USER_PERMISSION_RESOURCE_INSTANCE','Delete UserPermissionResourceInstance', @RESOURCE_TYPE_ID),
('DEACTIVATE_USER_PERMISSION_RESOURCE_INSTANCE','De-Activate UserPermissionResourceInstance',@RESOURCE_TYPE_ID),
('ACTIVATE_USER_PERMISSION_RESOURCE_INSTANCE','Activate UserPermissionResourceInstance', @RESOURCE_TYPE_ID);

-- RolePermissionResourceInstance related permissions

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.models.system.RolePermissionResourceInstance';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES 
('CREATE_ROLE_PERMISSION_RESOURCE_INSTANCE','Create RolePermissionResourceInstance', @RESOURCE_TYPE_ID),
('READ_ROLE_PERMISSION_RESOURCE_INSTANCE','View RolePermissionResourceInstance', @RESOURCE_TYPE_ID),
('UPDATE_ROLE_PERMISSION_RESOURCE_INSTANCE','Modify RolePermissionResourceInstance Details', @RESOURCE_TYPE_ID),
('DELETE_ROLE_PERMISSION_RESOURCE_INSTANCE','Delete RolePermissionResourceInstance', @RESOURCE_TYPE_ID),
('DEACTIVATE_ROLE_PERMISSION_RESOURCE_INSTANCE','De-Activate RolePermissionResourceInstance',@RESOURCE_TYPE_ID),
('ACTIVATE_ROLE_PERMISSION_RESOURCE_INSTANCE','Activate RolePermissionResourceInstance', @RESOURCE_TYPE_ID);


-- Xoconfig Template related permissions

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.models.system.XoConfigTemplate';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES 
('CREATE_CONFIGURATION_TEMPLATE','Create config template', @RESOURCE_TYPE_ID),
('READ_CONFIGURATION_TEMPLATE','View config template Details', @RESOURCE_TYPE_ID),
('UPDATE_CONFIGURATION_TEMPLATE','Modify config template Details', @RESOURCE_TYPE_ID),
('DELETE_CONFIGURATION_TEMPLATE','Delete config template', @RESOURCE_TYPE_ID),
('DEACTIVATE_CONFIGURATION_TEMPLATE','De-Activate config template',@RESOURCE_TYPE_ID),
('ACTIVATE_CONFIGURATION_TEMPLATE','Activate config template', @RESOURCE_TYPE_ID);

-- XoConfigInstance related permissions

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.models.system.XoConfigInstance';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES 
('CREATE_CONFIGURATION_INSTANCE','Create config instance', @RESOURCE_TYPE_ID),
('READ_CONFIGURATION_INSTANCE','View config instance Details', @RESOURCE_TYPE_ID),
('UPDATE_CONFIGURATION_INSTANCE','Modify config instance Details', @RESOURCE_TYPE_ID),
('DELETE_CONFIGURATION_INSTANCE','Delete config instance', @RESOURCE_TYPE_ID),
('DEACTIVATE_CONFIGURATION_INSTANCE','De-Activate config instance',@RESOURCE_TYPE_ID),
('ACTIVATE_CONFIGURATION_INSTANCE','Activate config instance', @RESOURCE_TYPE_ID);

-- XoClient related permissions

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.models.system.XoClient';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES 
('CREATE_CLIENT','Create XoClient', @RESOURCE_TYPE_ID),
('READ_CLIENT','View XoClient Details', @RESOURCE_TYPE_ID),
('UPDATE_CLIENT','Modify XoClient Details', @RESOURCE_TYPE_ID),
('DELETE_CLIENT','Delete XoClient', @RESOURCE_TYPE_ID),
('DEACTIVATE_CLIENT','De-Activate XoClient',@RESOURCE_TYPE_ID),
('ACTIVATE_CLIENT','Activate XoClient', @RESOURCE_TYPE_ID);

-- XoJob related permissions

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.models.system.XoJob';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES 
('CREATE_JOB','Create XoJob', @RESOURCE_TYPE_ID),
('READ_JOB','View XoJob Details', @RESOURCE_TYPE_ID),
('UPDATE_JOB','Modify XoJob Details', @RESOURCE_TYPE_ID),
('DELETE_JOB','Delete XoJob', @RESOURCE_TYPE_ID),
('DEACTIVATE_JOB','De-Activate XoJob',@RESOURCE_TYPE_ID),
('ACTIVATE_JOB','Activate XoJob', @RESOURCE_TYPE_ID);

-- XoClientJobConfig related permissions

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.models.system.XoClientJobConfig';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES 
('CREATE_CLIENT_JOB_CONFIG','Create XoClientJobConfig', @RESOURCE_TYPE_ID),
('READ_CLIENT_JOB_CONFIG','View XoClientJobConfig Details', @RESOURCE_TYPE_ID),
('UPDATE_CLIENT_JOB_CONFIG','Modify XoClientJobConfig Details', @RESOURCE_TYPE_ID),
('DELETE_CLIENT_JOB_CONFIG','Delete XoClientJobConfig', @RESOURCE_TYPE_ID),
('DEACTIVATE_CLIENT_JOB_CONFIG','De-Activate XoClientJobConfig',@RESOURCE_TYPE_ID),
('ACTIVATE_CLIENT_JOB_CONFIG','Activate XoClientJobConfig', @RESOURCE_TYPE_ID);


-- View Groups Related permissions

SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE='com.xo.web.ext.tableau.models.ViewGroup';
EXECUTE GET_RESOURCE_TYPE_ID USING @RESOURCE_TYPE;
INSERT INTO permissions(name, description, resource_type_id) VALUES
('CREATE_VIEW_GROUP','Create View Group', @RESOURCE_TYPE_ID),
('READ_VIEW_GROUP','View View Group ', @RESOURCE_TYPE_ID),
('UPDATE_VIEW_GROUP','Modify View Group ', @RESOURCE_TYPE_ID),
('DELETE_VIEW_GROUP','Delete View Group', @RESOURCE_TYPE_ID),
('DEACTIVATE_VIEW_GROUP','De-Activate View Group',@RESOURCE_TYPE_ID),
('ACTIVATE_VIEW_GROUP','Activate View Group', @RESOURCE_TYPE_ID);

-- Clearing the session variables
SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_TYPE=null;
DEALLOCATE PREPARE GET_RESOURCE_TYPE_ID;

-- Successfully completed system specific settings.

--
-- Creating the Configuration tables
--

DROP TABLE IF EXISTS `xo_config_templates`;
CREATE TABLE `xo_config_templates` (
  `config_template_id` int(8) PRIMARY KEY AUTO_INCREMENT,
  `config_json` MEDIUMTEXT NOT NULL,
  `short_name` VARCHAR(150)  NOT NULL,
  `active` BIT NOT NULL DEFAULT 1,
  `created_dt` TIMESTAMP NULL,
  `last_modified_dt` TIMESTAMP NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into xo_config_templates(short_name, config_json) values
('XoSession Killer', '{\"initialdelay\": 0, \"frequency\":1, \"enabled\": true}'),
('Tableau Sync', '{\"initialdelay\": 0, \"frequency\":23, \"enabled\": true, \"publichost\":\"https://tableau.xoanonanalytics.net\", \"internalhost\":\"http://10.11.50.58\", \"username\":\"Etisalat\", \"password\":\"X0anon2o15\", \"sitename\":\"Etisalat_Production\", \"contenturl\":\"Xo_etisalat\", \"clientip\":\"10.11.50.68\"}');


DROP TABLE IF EXISTS `xo_config_instances`;
CREATE TABLE `xo_config_instances` (
  `config_instance_id` int(8) PRIMARY KEY AUTO_INCREMENT,
  `config_template_id` int(8),
  `short_name` VARCHAR(150)  NOT NULL,
  `config_json` MEDIUMTEXT NOT NULL,
  `active` BIT NOT NULL DEFAULT 1,
  `created_dt` TIMESTAMP NULL,
  `last_modified_dt` TIMESTAMP NULL,
   FOREIGN KEY (`config_template_id`) REFERENCES xo_config_templates(config_template_id) on DELETE CASCADE on UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for config instances from config templates
insert into xo_config_instances(config_template_id, short_name, config_json) SELECT config_template_id, short_name, config_json FROM xo_config_templates;

DROP TABLE IF EXISTS `xo_jobs`;
CREATE TABLE `xo_jobs` (
  `job_id` int(8) PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(150)  NOT NULL,
  `class_name` VARCHAR(150)  NOT NULL,
  `active` BIT NOT NULL DEFAULT 1,
  `deleted` BIT NOT NULL DEFAULT 0,
  `last_state` VARCHAR(150),
  `last_msg` MEDIUMTEXT,
  `created_dt` TIMESTAMP NULL,
  `last_modified_dt` TIMESTAMP NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping Available system Job details
INSERT INTO xo_jobs(name, class_name, created_dt) values
('XoSession Killer', 'com.xo.web.plugins.scheduler.XoSessionKillerPlugin', NOW()),
('Tableau Sync', 'com.xo.web.plugins.scheduler.XoTableauSyncPlugin', NOW());

DROP TABLE IF EXISTS `xo_clients`;
CREATE TABLE `xo_clients` (
  `client_id` int(8) PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(150),
  `preferred_time_zone` VARCHAR(100),
  `active` BIT NOT NULL DEFAULT 1,
  `deleted` BIT NOT NULL DEFAULT 0,
  `created_dt` TIMESTAMP NULL,
  `last_modified_dt` TIMESTAMP NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping client details
INSERT INTO xo_clients(name, created_dt) values('Demo Client', NOW());

-- Linking client and users
ALTER TABLE users ADD COLUMN `client_id` int(8), ADD COLUMN `preferred_time_zone` VARCHAR(100);
ALTER TABLE users ADD CONSTRAINT fk_client_users FOREIGN KEY(`client_id`) REFERENCES xo_clients(`client_id`) on DELETE CASCADE on UPDATE CASCADE;

DROP TABLE IF EXISTS `client_jobs_configurations`;
CREATE TABLE `client_jobs_configurations` (
  `client_id` int(8),
  `job_id` int(8),
  `config_instance_id` int(8),
  `active` BIT NOT NULL DEFAULT 1,
  `deleted` BIT NOT NULL DEFAULT 0,
  `created_dt` TIMESTAMP NULL,
  `last_modified_dt` TIMESTAMP NULL,
   PRIMARY KEY(`client_id`, `job_id`, `config_instance_id`),
   FOREIGN KEY (`client_id`) REFERENCES xo_clients(client_id) on DELETE CASCADE on UPDATE CASCADE,
   FOREIGN KEY (`job_id`) REFERENCES xo_jobs(job_id) on DELETE CASCADE on UPDATE CASCADE,
   FOREIGN KEY (`config_instance_id`) REFERENCES xo_config_instances(config_instance_id) on DELETE CASCADE on UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `view_groups`;
CREATE TABLE `view_groups` (
  `view_group_id` int(8) PRIMARY KEY AUTO_INCREMENT,
  `group_name` VARCHAR(150),
  `active` BIT NOT NULL DEFAULT 1,
  `display_order` int(2) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into view_groups(group_name) values
('Main Dashboard'),
('Segment Monitoring'),
('Multi Sim'),
('Value Monitor'),
('Social Media');



SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;


# --- !Downs

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `view_groups`;
DROP TABLE IF EXISTS `xo_config_instances`;
DROP TABLE IF EXISTS `xo_config_templates`;
DROP TABLE IF EXISTS `xo_clients`;
DROP TABLE IF EXISTS `xo_jobs`;
DROP TABLE IF EXISTS `client_jobs_configurations`;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;
