# --- !Ups

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

-- Adding client secret field to xo_clients table
ALTER TABLE xo_clients DROP COLUMN secret;
ALTER TABLE xo_clients ADD COLUMN secret VARCHAR(150) NOT NULL AFTER name;

update xo_clients set secret='G3_/:3>YWMcDAPnt22pPV1kSGQKZl^zDC8l]HsQkInJIQ<`P/^Hl7sosM9BYwE>n' where name='Demo Client';

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;


# --- !Downs
-- Removing client secret field from xo_clients table
ALTER TABLE xo_clients DROP COLUMN secret;