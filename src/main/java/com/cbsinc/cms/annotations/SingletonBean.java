package com.cbsinc.cms.annotations;

import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;

/**
 * MVC OOP Pattern support for controller
 * 
 * @author Konstantin Grabko
 *
 *         JSP page is view for classic Java J2EE Pattern jspName - jsp file
 *         name responseType - validate response type xmlToJson - convert xml to
 *         json response format
 * 
 *         The annotation writes in web_actions.properties pair vales Class
 *         controller and jsp file name
 *         RegPage.jsp=com.cbsinc.cms.controllers.RegistrationAction then
 *         servlet filter process it to invoke a controller class
 */

@Retention(CLASS)
public @interface SingletonBean {
}
