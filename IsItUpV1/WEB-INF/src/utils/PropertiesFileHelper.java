package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesFileHelper {

	public static String getProperty(String property) {
		Properties properties = new Properties();
		try {
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("utils/cfg.properties"));
		} catch (IOException e) {
		}

		return properties.getProperty(property);

	}
}
