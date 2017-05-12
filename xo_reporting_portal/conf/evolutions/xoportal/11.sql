# --- !Ups

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

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
