package commandline;

import java.util.Scanner;

import commandline.function.CMDDatabase;
import commandline.function.CMDLog;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {
	private static final String LOAD_TEXT = "------------------------\n"
										+ "1  Play a new game.\n"
										+ "2  Game record.\n"
										+ "3  Exit the game.\n"
										+ "------------------------\n";
	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// create a cmdlog object to record logs. at the end, if needed, call it to save logs to a file.
		CMDLog testLog = new CMDLog();
		
		// create database
		CMDDatabase db;
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// create a scanner that would be used to capture user's input.
		Scanner in = new Scanner(System.in);
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			
			// print manu.
			System.out.println(LOAD_TEXT);
			db = new CMDDatabase();
			
			// get user's choice.
			int userIn = in.nextInt();
			
			// deal with user's choice.
			switch(userIn) {
				case 1:	{
					testLog.record("-------A new game------\r\n");
					new CMDGameView(testLog, db);
					break;
				}
				case 2:	break;
				case 3: {
					in.close();
					
					if (writeGameLogsToFile) {
						testLog.saveClose();
					} else
						testLog.close();
					
					userWantsToQuit=true; // use this when the user wants to exit the game
					System.out.println("CYA");
					System.exit(0);
					break;
				}
				default : ;
			}
		}


	}

}
