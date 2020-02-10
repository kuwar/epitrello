/**
 * 
 */
package fr.epita.epitrello.test;

import fr.epita.epitrello.services.FileLogger;

/**
 * @author kuwar
 *
 */
public class FileLoggerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		FileLogger.write("Voilla 4");
		FileLogger.write("Voilla ");
		FileLogger.write("Voilla 6");
		FileLogger.write("Voilla 7");
		FileLogger.write("Voilla 8");

	}

}
