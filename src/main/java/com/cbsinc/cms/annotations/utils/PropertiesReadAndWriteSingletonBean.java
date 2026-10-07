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

public class PropertiesReadAndWriteSingletonBean {

	private static XLogger logger = XLoggerFactory.getXLogger(PropertiesReadAndWritePageController.class.getName());
	private static PropertiesReadAndWriteSingletonBean propertiesReadAndWriteSingletonBean = new PropertiesReadAndWriteSingletonBean();
	private static final String propertiesFileName = "beans";
	private static OutputStream out = null;
	private static BufferedWriter bufferedWriter = null;

	private PropertiesReadAndWriteSingletonBean() {
	};

	public static PropertiesReadAndWriteSingletonBean getInstance() {
		return propertiesReadAndWriteSingletonBean;
	}

	public static Properties loadProps() throws IOException {
		logger.entry(propertiesFileName);
		Properties properties = new Properties();
		properties.load(PropertiesReadAndWriteSingletonBean.class
				.getResourceAsStream("/WEB-INF/classes/" + propertiesFileName + ".properties"));
		logger.exit(properties.toString());
		return properties;
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
