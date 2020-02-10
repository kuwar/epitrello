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
	
	public static void write(String message) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy_MM_dd");
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
