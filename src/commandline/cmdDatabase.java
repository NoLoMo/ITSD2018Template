package commandline;

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
	private int cardsNum;
	
	/**
	 * constructor will create connection, read file, and load data into database.
	 */
	public cmdDatabase () {

	}

	/**
	 * this method will create a card array.
	 */
	public cmdGameModelCard[] createCards () {
		return cards;
	}

	/**
	 * this method will create a player array.
	 */
	public cmdGameModelPlayer[] createPlayers () {
		return players;
	}
	
	/**
	 * this method will get game history from database.
	 */
	public String getDbHistory() {
		String results = "";
		return results;
	}
	
	/**
	 * this method will get game information, which is part of game history , and save to database.
	 */
	public void recordGameInfo (String info) {
		
	}
}
