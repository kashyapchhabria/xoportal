# --- !Ups 
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `diffusionmap_filters`;
CREATE TABLE `diffusionmap_filters`(
`export_id` int(11) NOT NULL AUTO_INCREMENT,
`created_date` timestamp NOT NULL,
`device_date` varchar(30),
`subsegment_label` varchar(50),
`region` varchar(100),
`status` varchar(50),
`location_type` varchar(100),
`user_id` int(11) NOT NULL,
`x_tile` varchar(11),
`y_tile` varchar(11),
`lifetime_bucket` varchar(100),
`spend_bucket` varchar(100),
PRIMARY KEY (`export_id`),
CONSTRAINT `fk_export_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;