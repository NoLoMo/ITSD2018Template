package commandline;

/**
 * This class 
 * @author 
 */
public class cmdGameModelPlayer {
	private int id;
	private static int CARDNUM;
	private boolean isAI;
	private int[] handCards;
	private int handCardsNum;
	
	public cmdGameModelPlayer (int id) {
		this.id = id;
		this.CARDNUM = 40;
		this.handCardsNum = 0;
		handCards = new int[this.CARDNUM];
		
		if (id == 0)
			this.isAI = false;
		else
			this.isAI = true;
	}
	
	public int id() {
		return this.id;
	}
	
	public boolean isAI() {
		return this.isAI;
	}
	
	public int handCardsNum() {
		return this.handCardsNum;
	}
	
	public int handCards(int i) {
		return this.handCards[i];
	}

	public void takeCard(int card) {
		if (handCardsNum < CARDNUM )
			handCards[handCardsNum] = card;
		handCardsNum ++;
		//System.out.println("player:" + id + "take " + card);
	}
	
}
