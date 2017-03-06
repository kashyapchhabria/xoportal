# --- !Ups

-- --------------------------------
-- Job's class names are updated
-- --------------------------------

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

-- Dumping Available system Job details
update xo_jobs set class_name='com.xo.web.work.scheduler.XoSessionKiller' where name='XoSession Killer';
update xo_jobs set class_name='com.xo.web.work.scheduler.XoTableauReportSynchronizer' where name='Tableau Sync';

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;


# --- !Downs

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

update xo_jobs set class_name='com.xo.web.plugins.scheduler.XoSessionKillerPlugin' where name='XoSession Killer';
update xo_jobs set class_name='com.xo.web.plugins.scheduler.XoTableauSyncPlugin' where name='Tableau Sync';

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;
