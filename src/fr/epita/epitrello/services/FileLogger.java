/**
 * 
 */
package fr.epita.epitrello.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kuwar
 *
 */
public class FileLogger {

	/**
	 * Print the message in the file All of the system output are logged in the file
	 * The filename is make unique on daily basis Which means only one file is
	 * generated on daily basis.
	 * 
	 * @param message
	 */
	public static void write(String message) {
		SimpleDateFormat formater = new SimpleDateFormat(Configuration.getValue("log.filename"));
		String date = formater.format(new Date());
		File file = new File(date + ".log");
		// print in the file
		PrintWriter writer;
		try {
			FileOutputStream out = new FileOutputStream(file, true);
			writer = new PrintWriter(out);
			writer.println(message);
			writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
