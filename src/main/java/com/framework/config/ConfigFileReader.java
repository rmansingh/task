package com.framework.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


/**
 * @author Rupak Mansingh This class helps to get properties file data and supply to
 *         UI scripts
 */
public class ConfigFileReader {

	private Properties properties;
	private final String propertyFilePath = "config/Configuration.properties";

	// constructor to initialize properties
	public ConfigFileReader() {

		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("configuration.properties not found at " + propertyFilePath);
		}
	}

	// method to get implicit wait from properties file
	public long getImplicitlyWait() {

		String implicitlyWait = properties.getProperty("implicitlyWait");
		if (implicitlyWait != null)
			return Long.parseLong(implicitlyWait);
		else
			throw new RuntimeException("implicitlyWait not specified in the configuration.properties file.");
	}

	// method to get baseurl from properties file
	public String getApplicationUrl() {

		String url = properties.getProperty("baseurl");
		if (url != null)
			return url;
		else
			throw new RuntimeException("url not specified in the configuration.properties file.");
	}


	// method to get browser window size to maximize from properties file
	public Boolean getBrowserWindowSize() {

		String windowSize = properties.getProperty("windowMaximize");
		if (windowSize != null)
			return Boolean.valueOf(windowSize);
		return true;
	}

}
