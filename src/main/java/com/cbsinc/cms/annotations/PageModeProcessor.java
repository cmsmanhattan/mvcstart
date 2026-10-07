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

import com.cbsinc.cms.annotations.utils.PropertiesReadAndWritePageModel;
import com.google.auto.service.AutoService;

/**
 * The goal is collect java classes with this annotation type then put in
 * framework config file for further processing
 * 
 * @author kgrabko
 *
 */

@SupportedAnnotationTypes("com.cbsinc.cms.annotations.PageModel")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class PageModeProcessor extends AbstractProcessor {

	private static XLogger logger = XLoggerFactory.getXLogger(PageModeProcessor.class.getName());

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		System.out.println("PageModeProcessor called ");
		// PropertiesReadAndWrite.getInstance().writeClass(null, null);
		try {
			PropertiesReadAndWritePageModel.getInstance().writeClass("## Session beans setup", Scope.SESSION,
					processingEnv);
			PropertiesReadAndWritePageModel.getInstance().writeClass("## Appication beans setup", Scope.APPLICATION,
					processingEnv);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (TypeElement annotation : annotations) {

			Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
			for (Element el : annotatedElements) {
				// String className = el.getSimpleName().toString();
				String fullClassName = el.toString();
				PageModel commandHandler = el.getAnnotation(PageModel.class);
				String id = commandHandler.Id();
				Scope scope = commandHandler.scope();
				try {
					PropertiesReadAndWritePageModel.getInstance().writeClass(id + "=" + fullClassName, scope,
							processingEnv);
				} catch (FileNotFoundException ex) {
					// TODO Auto-generated catch block
					logger.error(ex.getLocalizedMessage(), ex);
				}

			}

		}

		return true;
	}

}
