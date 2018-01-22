package commandline;

import java.util.Scanner;

/**
 * Top Trumps command line application
 * @author feiguang cao
 * @author yifeng sun
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {
		
		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// input detection variables
		Scanner in = new Scanner(System.in);
		int userChoice = 0;
		
		//
		System.out.println("Commend Line Version Game is loading ......\n");
		if (writeGameLogsToFile) 
			System.out.println("Log recording function is avaliable ......\n");
		
		// create a cmdLog instance to print, record and write logs.
		cmdLog logs = new cmdLog();
		
		// create database class.
		cmdDatabase db = new cmdDatabase();
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			
			userChoice = manu(in);
			switch (userChoice) {
				case 0 : {
					// no valid input is detected.
					break;
				}
				case 1 : {
					// user wants to play a new game. Create a game and start it.
					if (writeGameLogsToFile) {
						cmdGameControl cmdGame = new cmdGameControl(logs, db);
						logs.record("");
						cmdGame.start();
					} else {
						cmdGameControl cmdGame = new cmdGameControl(db);
						cmdGame.start();
					}
					break;
				}
				case 2 : {
					// user wants to print game history.
					if (writeGameLogsToFile) {
						cmdGameHistory history = new cmdGameHistory(db);
						history.getHistory();
						logs.record(" user checked game history.");
					} else {
						cmdGameHistory history = new cmdGameHistory(db);
						history.getHistory();
					}
					
					break;
				}
				case 3 : {
					in.close();
					logs.close();
					userWantsToQuit=true; // use this when the user wants to exit the game
					System.out.println(" You have logged out ...");
					if (writeGameLogsToFile) 
						System.out.println("Log has been recorded in project root dirctoy cmdGameLogs.txt ......\n");
				}
			}
			
			
		}


	}
	
	static int manu (Scanner in) {
		int op = 0;
		System.out.println("------------------------");
		System.out.println("1  Play a new game.\n2  Game record.\n3  Exit the game.");
		System.out.println("------------------------");
//		try {
			if(in.hasNext()) {
				op = in.nextInt();
			}
//		} catch (IOException e) {
//			System.err.println(" manu input has problem.");
//		}
		return op;
	}

}
