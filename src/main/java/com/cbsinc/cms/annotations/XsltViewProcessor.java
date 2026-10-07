package com.cbsinc.cms.annotations;

import java.io.FileNotFoundException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.cbsinc.cms.annotations.utils.PropertiesReadAndWriteXsltView;
import com.google.auto.service.AutoService;

/**
 * The goal is collect java classes with this annotation type then put in
 * framework config file for further processing
 * 
 * @author kgrabko
 *
 */

@SupportedAnnotationTypes("com.cbsinc.cms.annotations.PageXsltView")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class XsltViewProcessor extends AbstractProcessor {

	private static XLogger logger = XLoggerFactory.getXLogger(XsltViewProcessor.class.getName());

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		System.out.println("XsltViewProcessor called ");
		// PropertiesReadAndWrite.getInstance().writeClass(null, null);
		try {
			PropertiesReadAndWriteXsltView.getInstance().writeClass("# xslt view setup", processingEnv);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (TypeElement annotation : annotations) {

			Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
			for (Element el : annotatedElements) {
				// String className = el.getSimpleName().toString();
				// String fullClassName = el.toString();
				PageXsltView commandHandler = el.getAnnotation(PageXsltView.class);
				String xsltName = commandHandler.xsltName();
				String jspName = commandHandler.jspName();
				try {
					PropertiesReadAndWriteXsltView.getInstance().writeClass(jspName + "=" + xsltName, processingEnv);
				} catch (FileNotFoundException ex) {
					// TODO Auto-generated catch block
					logger.error(ex.getLocalizedMessage(), ex);
				}

			}

		}

		return true;
	}

}
