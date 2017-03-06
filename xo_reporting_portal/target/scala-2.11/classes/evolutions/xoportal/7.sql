# --- !Ups

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

SET @SUPPORT_TEAM_CONFIG_EMAIL='Xoapp Support Team';

-- Dumping data for config instances from config templates
insert into xo_config_templates(short_name, config_json) values
(@SUPPORT_TEAM_CONFIG_EMAIL, '{\"contact_emails\": [\"maria.sekar@xoanonanalytics.com\"], \"enabled\": true}');

-- Dumping data for config instances from config templates
insert into xo_config_instances(config_template_id, short_name, config_json) SELECT config_template_id, short_name, config_json FROM xo_config_templates where short_name=@SUPPORT_TEAM_CONFIG_EMAIL;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

# --- !Downs

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;



SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;