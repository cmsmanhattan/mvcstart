package com.cbsinc.cms.annotations;

import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;

/**
 * MVC OOP Pattern support for view concept
 * 
 * @author konst
 *
 *         The annotation writes in web_xslt.properties pair vales Class jsp
 *         file name and xslt file name AccountHistory.jsp=accounthistory.xsl
 *         then servlet filter process it for data transfomation for html or
 *         selected type
 *
 */
@Retention(CLASS)
public @interface PageXsltView {
	public String xsltName() default "";

	public String jspName() default "";

	public Type responseType() default Type.HTML;
}
