package com.cbsinc.cms.annotations.utils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.StandardLocation;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

public class PropertiesReadAndWriteXsltView {

	private static XLogger logger = XLoggerFactory.getXLogger(PropertiesReadAndWriteXsltView.class.getName());
	private static PropertiesReadAndWriteXsltView propertiesReadAndWriteXsltView = new PropertiesReadAndWriteXsltView();
	private static final String propertiesFileName = "web_xslt";
	private static OutputStream out = null;
	private static BufferedWriter bufferedWriter = null;

	private PropertiesReadAndWriteXsltView() {
	};

	public static PropertiesReadAndWriteXsltView getInstance() {
		return propertiesReadAndWriteXsltView;
	}

	/*
	 * public Properties getProps(String propertiesFileName) {
	 * logger.entry(propertiesFileName); Properties properties = new Properties();
	 * try { properties = loadProps(); } catch (IOException ex) {
	 * logger.error(ex.getLocalizedMessage(), ex); }
	 * logger.exit(properties.toString()); return properties; }
	 */

	public Properties loadProps() throws IOException {
		logger.entry(propertiesFileName);
		Properties properties = new Properties();
		properties.load(PropertiesReadAndWrite.class
				.getResourceAsStream("/WEB-INF/classes/" + propertiesFileName + ".properties"));
		logger.exit(properties.toString());
		return properties;
	}

	public void writeClass(String className, ProcessingEnvironment processingEnv) throws FileNotFoundException {

		try {
			if (out == null) {
				// out = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT,
				// "",
				// "META-INF/services/" + propertiesFileName +
				// ".properties").openOutputStream();
				// out = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT,
				// "",
				// "WEB-INF/classes/" + propertiesFileName + ".properties").openOutputStream();
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
