/**
 * 
 */
package fr.epita.epitrello.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import fr.epita.epitrello.services.DBConfiguration;

/**
 * @author kuwar
 *
 */
public class PropertiesTest {

	public static void main(String[] args) throws IOException {
		FileReader reader = new FileReader(new File("db.properties"));
		
		Properties p = new Properties();
		p.load(reader);
		
		System.out.println(p.getProperty("db.user"));
		
		System.out.println(DBConfiguration.getValue("db.user"));
	}
}
