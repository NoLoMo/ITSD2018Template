package commandline;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/** 
 * This class writes game logs to file
 * @author feiguang cao
 */
public class cmdLog {
	private static String BASEDIR;
	private static String fileName;
	private FileWriter writer;
	private String log = "" ;
	private Date time ;
	
	public cmdLog () {
		BASEDIR = "../../";
		fileName = BASEDIR + "cmdGameLogs.txt";
		System.out.println(fileName);
		try {
			this.writer = new FileWriter(fileName);
		} catch (IOException e) {
			System.err.println("log recording system initial failed.");
		}
		
	}
	
	/**
	 * record a log into file.
	 * @param logContents log content.
	 */
	public void record (String logContents) {
		try {
			time = Calendar.getInstance().getTime() ;
			log = "[" + time + "]: " + logContents + "\n";
			this.writer.write(log);
		} catch (IOException e) {
			System.err.println("log writing is failed.");
		}
		
	}
	
	/**
	 * close log recording system, and save logs to a file
	 * named 'cmdGameLogs.txt'.
	 */
	public void close() {
		try {
			this.writer.close();
		} catch (IOException e) {
			System.err.println("logfile saving is failed.");
		}
		
	}
}
