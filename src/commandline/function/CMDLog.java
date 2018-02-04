package commandline.function;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/** 
 * This class writes game logs to file
 * @author feiguang cao
 */
public class CMDLog {
	private static String BASEDIR;
	private static String fileName;
	private FileWriter writer;
	private String log = "" ;
	private Date time ;
	
	public CMDLog () {
		BASEDIR = "./";
		fileName = BASEDIR + " toptrumps.log";
		//System.out.println(fileName);
		try {
			this.writer = new FileWriter(fileName);
		} catch (IOException e) {
			System.err.println("log recording system initial failed.\n");
		}
		
	}
	
	/**
	 * record a log .
	 * @param logContents log content.
	 */
	public void record (String logContents) {
		//time = Calendar.getInstance().getTime() ;
		try {
			log = logContents;
			this.writer.write(log);
		} catch (IOException e) {
			System.err.println("logfile saving is failed.");
		}
		
	}
	
	/**
	 * close log recording system, and save logs to a file
	 * named 'cmdGameLogs.txt'.
	 */
	public void saveClose() {
		try {
			this.writer.close();
		} catch (IOException e) {
			System.err.println("logfile saving is failed.");
		}
		
	}
	
	/**
	 * close log recording system, and doesn't save logs to a file
	 */
	public void close() {
		try {
			this.log = null;
			this.writer.close();
			File file = new File(fileName);
			file.delete();
		} catch (IOException e) {
			System.err.println("logfile saving is failed.");
		}
		
	}
}
