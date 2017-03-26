package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

public class PropertyUtil {
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();
	private static final Properties properties = new Properties();

	static {

		InputStream is = null;

		try {
			org.springframework.core.io.Resource res = resourceLoader.getResource("Global.properties");// 不知为啥，只能放在classes下
			is = res.getInputStream();
			properties.load(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static String getValue(String key) {
		String systemProperty = System.getProperty(key);
		if (systemProperty != null) {
			return systemProperty;
		}
		return properties.getProperty(key);
	}

	public static String getProperty(String key) {
		String value = getValue(key);
		if (value == null) {
			throw new NoSuchElementException();
		}
		return value;
	}

}
