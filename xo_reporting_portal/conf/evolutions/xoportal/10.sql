# --- !Ups

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

UPDATE xo_config_instances SET config_json='{\"initialdelay\": 0, \"frequency\":1, \"enabled\": true,\"isScheduled\": 1}' 
WHERE short_name='XoSession Killer';

UPDATE xo_config_instances SET config_json='{\"initialdelay\": 0, \"frequency\":23, \"enabled\": true, 
\"publichost\":\"https://tableau.xoanonanalytics.net\", \"internalhost\":\"http://10.11.50.58\", 
\"username\":\"Etisalat\", \"password\":\"X0anon2o15\", \"sitename\":\"Etisalat_Production\", 
\"contenturl\":\"Xo_etisalat\", \"clientip\":\"10.11.50.68\",\"isScheduled\": 1}' 
WHERE short_name='Tableau Sync';

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;
