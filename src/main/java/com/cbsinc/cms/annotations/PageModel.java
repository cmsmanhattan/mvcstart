package com.cbsinc.cms.annotations;

import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;

/**
 * MVC OOP Pattern support for model with J2EE scope implementation
 * 
 * @author Konstantin Grabko
 *
 *         Id is key string in servlet container concept (J2EE) in order to
 *         retrieve a instance of class as Model fpr MVC Pattern implementation
 *         The same like JSP with Bean concept
 * 
 *         <jsp:useBean id="productlistBeanId" scope="request" class=
 *         "com.cbsinc.cms.ProductlistBean" />
 *         <jsp:useBean id="authorizationPageBeanId" scope="session" class=
 *         "com.cbsinc.cms.AuthorizationPageBean" />
 *         <jsp:useBean id="authorizationPageFaced" scope="application" class=
 *         "com.cbsinc.cms.faceds.AuthorizationPageFaced" />
 *
 */

@Retention(CLASS)
public @interface PageModel {
	public String Id() default "";

	public Scope scope() default Scope.REQUESR;
}
