package commandline;

import java.util.Scanner;

/**
 * This class 
 * @author 
 */
public class cmdGameView {

	private boolean isLogs = false;
	private int PLAYERNUM;
	private cmdLog logs;
	private cmdDatabase db;
	private Scanner in;
	/**
	 * when tests is available, this constructor is used.
	 */
	public cmdGameView (cmdLog logs, cmdDatabase db, int PLAYERNUM) {
		isLogs = true;
		this.PLAYERNUM = PLAYERNUM;
		this.logs = logs;
		this.db = db;
		
	}
	
	/**
	 * when tests is unavailable, this constructor is used.
	 */
	public cmdGameView (cmdDatabase db, int PLAYERNUM) {
		this.PLAYERNUM = PLAYERNUM;
		this.db = db;
	}
	
	/**
	 * show human player's cards
	 * @param players are all game players
	 */
	public void showCards(cmdGameModelPlayer[] players, cmdGameModelCard[] cards) {
		System.out.println("---Top of your cards---");
		int index = players[0].topHandCards();
		System.out.println("Description Size Speed Range Firepower Cargo");
		System.out.println(  cards[index].detail());

	}
	
	public void showRoundStartInfo (int roundNum, int activePlayer) {
		System.out.println("////////////////////  Round "+roundNum+"  ///////////////////////");
		System.out.println("The player has been choosen is No." + activePlayer + " player");
	}
	
	/**
	 * 
	 * @param in
	 * @return
	 */
	public String chooseCategory (Scanner in) {
		System.out.println("---Please choose a Category---");
		String result = "";
		do {
		if(in.hasNext())
			result = in.next();
		} while(result == null);
		return result;
	}
	
	public void showRoundWinner(int content) {
		if (content == -1)
			System.out.println("---No player wins this round");
		else
			System.out.println("---Winner is No." + content + " player");
	}
	
	
}
