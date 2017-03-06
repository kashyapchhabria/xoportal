# --- !Ups

-- ----------------------------------------------------------------
-- Display name column for business oriented entities
-- ----------------------------------------------------------------

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

-- Adding display name field for tableau view
ALTER TABLE tableau_projects ADD COLUMN display_name varchar(150) default NULL AFTER name;
ALTER TABLE tableau_workbooks ADD COLUMN display_name varchar(150) default NULL AFTER name;
ALTER TABLE tableau_views ADD COLUMN display_name varchar(150) default NULL AFTER name;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;


# --- !Downs

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

ALTER TABLE tableau_projects DROP COLUMN display_name;
ALTER TABLE tableau_workbooks DROP COLUMN display_name;
ALTER TABLE tableau_views DROP COLUMN display_name;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;
