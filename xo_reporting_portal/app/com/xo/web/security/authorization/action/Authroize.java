package com.xo.web.security.authorization.action;

import com.xo.web.models.system.PermissionEnum;
import play.mvc.With;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Documented;

@With(XOAuthroizationAction.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE})
@Inherited
@Documented
public @interface Authroize {

	PermissionEnum[] permissions() default {};

	String handlerKey() default "defaultHandler";

	String value() default "";

	String meta() default "";

	String content() default "Authentication Failiure";

	int[] resourceParamIndexes() default {};

	Class<?>[] resourceTypes() default {}; 
}
