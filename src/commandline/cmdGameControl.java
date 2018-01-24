package commandline;

/**
 * This class 
 * @author 
 */
public class cmdGameControl {
	
	private boolean isLogs = false;
	private cmdLog logs;
	private cmdDatabase db;
	private cmdGameView gView;
	
	private static int PLAYERNUM = 5;
	private static int CARDSNUM = 40;
	private cmdGameModelCard[] cards;
	private cmdGameModelPlayer[] players;
	
	/**
	 * when tests is available, this constructor is used.
	 */
	public cmdGameControl (cmdLog logs, cmdDatabase db) {
		this.isLogs = true;
		this.logs = logs;
		this.db = db;
		gView = new cmdGameView(logs, db);
		cards = db.createCards();
		players = db.createPlayers(PLAYERNUM);
	}
	
	/**
	 * when tests is unavailable, this constructor is used.
	 */
	public cmdGameControl (cmdDatabase db) {
		this.db = db;
		gView = new cmdGameView(logs, db);
		cards = db.createCards();
		players = db.createPlayers(PLAYERNUM);
	}
	
	/**
	 * initialize game
	 */
	public void start () {

		this.licensing();

		for (int i = 0; i < PLAYERNUM; i++) {
			for (int j = 0; j < players[i].handCardsNum(); j++) {
				System.out.println("Player-" + i);
				System.out.println(j + " - " + players[i].handCards(j));
			}
		}
	}
	
	private void licensing () {
		int[] pile = this.shuffleCards();

		int i = 0;
		while (i < CARDSNUM) {
			for (int j = 0; j < PLAYERNUM; j++) {
				this.players[j].takeCard(pile[i]);
				i++;
			}
			
		}
		//-------------------------------------print common pile-----------------------------
		for (int k = 0; k < CARDSNUM; k++) {
			System.out.println(k + "-" + pile[k]);
		}
	}
	
	private int[] shuffleCards () {
		int[] pile = new int[CARDSNUM];
		int temp;
		for (int i = 0; i < CARDSNUM; i++) {

			// pick a card
			temp = (int) (Math.random() * CARDSNUM);

			// if card has been in pile
			for (int j = 0; j < i; j++) {
				
				// the card repeat
				if (temp == pile[j]) {
								
					// reset check process
					j = -1;
					
					// choose next card
					if (temp == (CARDSNUM - 1))
						temp = 0;
					else
						temp ++;
				} 
			}
			
			pile[i] = temp;
			
		}
		return pile;
	}
	
}
