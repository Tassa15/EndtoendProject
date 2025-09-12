package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties properties = new Properties();
	static {
		try {
			FileInputStream input = new FileInputStream("src/test/resources/config.properties");
			properties.load(input);
		}catch(Exception e) {
			throw new RuntimeException("Erreur lors de chargmenet du fichier config.properties");

		}
	}
	public static String getProperty (String key) {
		return properties.getProperty(key);

	}
}
