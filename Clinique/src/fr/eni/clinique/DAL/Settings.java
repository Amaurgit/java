package fr.eni.clinique.DAL;

import java.util.Properties;

public class Settings {
	private static Properties properties;

	static {
		try {
			properties = new Properties();
			properties.load(Settings.class.getResourceAsStream("settings.properties"));
			// properties.loadFromXML(Settings.class.getResourceAsStream("settings.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key, null);
	}

}


