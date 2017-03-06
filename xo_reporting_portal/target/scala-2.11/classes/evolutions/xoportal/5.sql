# --- !Ups

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

-- Moving last_msg field from xo_jobs to client_jobs_configurations table
ALTER TABLE xo_jobs DROP COLUMN last_msg;
ALTER TABLE client_jobs_configurations ADD COLUMN last_msg MEDIUMTEXT default NULL AFTER active;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;


# --- !Downs
