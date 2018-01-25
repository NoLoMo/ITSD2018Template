package commandline;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class will
 * 1, create connection to database
 * 2, read StarCitizenDeck.txt and put these cards into tables in database
 * 3, return game history
 * 4, record game information when a game is finished
 * @author 
 */
public class cmdDatabase {

	// variables
	private cmdGameModelCard[] cards;
	private cmdGameModelPlayer[] players;
	private static int CARDSNUM = 40;
	private FileReader reader;
	private Scanner in;
	private String classFile;
	private boolean isLogs;
	private cmdLog logs ;
	
	/**
	 * this constructor will create connection, read file, and load data into database.
	 */
	public cmdDatabase () {
		this.loadFile();
		this.isLogs = false;
	}
	
	/**
	 * this constructor will create connection, read file, and load data into database, with log function.
	 */
	public cmdDatabase (cmdLog Logs) {
		this.isLogs = true;
		this.logs = Logs;
	}

	/**
	 * load card file from outside
	 */
	private void loadFile () {
		classFile = "StarCitizenDeck.txt";
		try {
			//System.out.println(classFile);
			this.reader = new FileReader(classFile);
			in = new Scanner(reader);
		} catch (IOException e) {
			System.err.println(" load card file is failed.");
		}
	}
	
	/**
	 * this method will create a card array.
	 */
	public cmdGameModelCard[] createCards () {
		this.loadFile();
		cards = new cmdGameModelCard[CARDSNUM];
		int i = 0;
		boolean hasCards = true;
		while (hasCards) {
			if (in.hasNextLine()) {
				cards[i] = new cmdGameModelCard(in.next(), in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt());
				i++;
			} else
				hasCards = false;
			
		}
		in.close();
		return cards;
	}

	/**
	 * this method will create a player array.
	 */
	public cmdGameModelPlayer[] createPlayers (int num) {
		this.players = new cmdGameModelPlayer[num] ;
		players[0] = new cmdGameModelPlayer(0);
		for (int i = 1; i <5; i++)
			players[i] = new cmdGameModelPlayer(i);
		if (isLogs)
			logs.record("users have joined the game.");
		return players;
	}
	
	/**
	 * this method will get game history from database.
	 */
	public String getDbHistory() {
		String results = "\no history!";
		return results;
	}
	
	/**
	 * this method will get game information, which is part of game history , and save to database.
	 */
	public void recordGameInfo (String info) {
		
	}
}
