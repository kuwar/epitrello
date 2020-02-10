/**
 * 
 */
package fr.epita.epitrello.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author kuwar
 *
 */
public class DBConfiguration {

	private static Properties properties = new Properties();
	private static boolean isInit = false;

	public static void init() throws FileNotFoundException, IOException {
		properties.load(new FileInputStream(new File("db.properties")));
		isInit = true;
	}

	public static String getValue(String key) {
		if (!isInit) {
			try {
				init();
			} catch (Exception e) {
				// TODO do a custom exception
			}
		}
		return properties.getProperty(key);
	}

}
