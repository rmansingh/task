package com.framework.config;

/**
 * @author Rupak This will help if framework has multiple external files
 *         like properties, excel etc.
 */
public class FileReader {

	private static FileReader fileReaderManager = new FileReader();
	private static ConfigFileReader configFileReader;

	private FileReader() {
	}

	public static FileReader getInstance() {

		return fileReaderManager;
	}

	// initialize ConfigFileReader object, if already present then return the
	// current instance.
	public ConfigFileReader getConfigReader() {

		return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
	}

}
