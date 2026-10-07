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

public class PropertiesReadAndWrite {

	private static XLogger logger = XLoggerFactory.getXLogger(PropertiesReadAndWrite.class.getName());
	private static PropertiesReadAndWrite propertiesReadAndWrite = new PropertiesReadAndWrite();
	private static final String propertiesFileName = "annotations";
	private static OutputStream out = null;
	private static BufferedWriter bufferedWriter = null;

	private PropertiesReadAndWrite() {
	};

	public static PropertiesReadAndWrite getInstance() {
		return propertiesReadAndWrite;
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
				.getResourceAsStream("/META-INF/services/" + propertiesFileName + ".properties"));
		logger.exit(properties.toString());
		return properties;
	}

	public void writeClass(String className, ProcessingEnvironment processingEnv) throws FileNotFoundException {

		try {
			if (out == null) {
				out = processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "",
						"META-INF/services/" + propertiesFileName + ".properties").openOutputStream();
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
