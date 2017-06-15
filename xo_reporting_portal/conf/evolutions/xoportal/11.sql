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

-- Identifying the viewgroup resources ids.

SET @RESOURCE_FILTER_ID=null;
SET @RESOURCE_TYPE_ID=null;
SET @RESOURCE_FILTER_FULL_NAME='authfill_view_group';
SET @RESOURCE_TYPE='com.xo.web.ext.tableau.models.ViewGroup';

SELECT resource_type_id into @RESOURCE_TYPE_ID FROM resource_types where fully_qualified_name=@RESOURCE_TYPE;

INSERT INTO `resource_filters`(resource_type_id, name, fully_qualified_name, description) VALUES 
(@RESOURCE_TYPE_ID, 'View group filter', @RESOURCE_FILTER_FULL_NAME, 'View group Permission filter');

SELECT resource_filter_id into @RESOURCE_FILTER_ID FROM resource_filters where fully_qualified_name=@RESOURCE_FILTER_FULL_NAME;
INSERT INTO `resource_filter_parameters`(resource_filter_id, name, description, param_type) VALUES 
(@RESOURCE_FILTER_ID, 'resourceIds', 'View group permission filter parameter.', 'LIST_STRING');

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;


# --- !Downs

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;


SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS ;

