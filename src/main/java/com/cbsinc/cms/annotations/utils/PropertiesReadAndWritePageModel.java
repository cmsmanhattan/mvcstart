package com.cbsinc.cms.annotations.utils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.StandardLocation;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import com.cbsinc.cms.annotations.Scope;

public class PropertiesReadAndWritePageModel {

	private static XLogger logger = XLoggerFactory.getXLogger(PropertiesReadAndWritePageController.class.getName());
	private static PropertiesReadAndWritePageModel propertiesReadAndWritePageModel = new PropertiesReadAndWritePageModel();
	private static OutputStream outApplication = null;
	private static OutputStream outSession = null;
	private static BufferedWriter bufferedWriterApplication = null;
	private static BufferedWriter bufferedWriterSession = null;

	private PropertiesReadAndWritePageModel() {
	};

	public static PropertiesReadAndWritePageModel getInstance() {
		return propertiesReadAndWritePageModel;
	}

	public void writeClass(String className, Scope scope, ProcessingEnvironment processingEnv)
			throws FileNotFoundException {

		try {

			switch (scope) {
			case APPLICATION:

				if (outApplication == null) {
					outApplication = processingEnv.getFiler()
							.createResource(StandardLocation.CLASS_OUTPUT, "", "application_scope.properties")
							.openOutputStream();
					bufferedWriterApplication = new BufferedWriter(new OutputStreamWriter(outApplication));
				}
				bufferedWriterApplication.write(className);
				bufferedWriterApplication.newLine();
				bufferedWriterApplication.flush();
				System.err.println("add class " + className);

				break;

			case SESSION:

				if (outSession == null) {
					outSession = processingEnv.getFiler()
							.createResource(StandardLocation.CLASS_OUTPUT, "", "session_scope.properties")
							.openOutputStream();
					bufferedWriterSession = new BufferedWriter(new OutputStreamWriter(outSession));
				}
				bufferedWriterSession.write(className);
				bufferedWriterSession.newLine();
				bufferedWriterSession.flush();
				System.err.println("add class " + className);

				break;

			default:
				System.err.println("The class did not add - " + className);
				break;
			}

		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}
