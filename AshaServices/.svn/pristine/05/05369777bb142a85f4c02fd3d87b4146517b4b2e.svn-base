package com.icg.jkt.utils;

import java.net.URL;
import java.util.Properties;

public class ICGUtils {
	
	public static String getValuesFromPropertiesFile(String propertiesFileName,
			String key) {
		Properties properties = new Properties();
		String value = "";
		URL resourcePath = Thread.currentThread().getContextClassLoader()
				.getResource(propertiesFileName);
		try {
			properties.load(resourcePath.openStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			value = properties.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();

		}

		// -value- "+value);
		return value;

	}

}
