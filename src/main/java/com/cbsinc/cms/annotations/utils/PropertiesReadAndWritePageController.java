package com.cbsinc.cms.annotations.utils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.StandardLocation;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class PropertiesReadAndWritePageController {

	private static XLogger logger = XLoggerFactory.getXLogger(PropertiesReadAndWritePageController.class.getName());
	private static PropertiesReadAndWritePageController propertiesReadAndWriteCommands = new PropertiesReadAndWritePageController();
	private static final String propertiesFileName = "web_actions";
	private static OutputStream out = null;
	private static BufferedWriter bufferedWriter = null;

	private PropertiesReadAndWritePageController() {
	};

	public static PropertiesReadAndWritePageController getInstance() {
		return propertiesReadAndWriteCommands;
	}

	public void writeClass(String className, ProcessingEnvironment processingEnv) throws FileNotFoundException {

		try {
			if (out == null) {

				out = processingEnv.getFiler()
						.createResource(StandardLocation.CLASS_OUTPUT, "", "" + propertiesFileName + ".properties")
						.openOutputStream();

				bufferedWriter = new BufferedWriter(new OutputStreamWriter(out));
			}
			bufferedWriter.write(className);
			bufferedWriter.newLine();
			bufferedWriter.flush();
			System.err.println("add class " + className);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
