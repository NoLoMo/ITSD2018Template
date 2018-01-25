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
	private int topCardIndex;
	private boolean noCards;
	
	public cmdGameModelPlayer (int id) {
		this.id = id;
		this.CARDNUM = 40;
		this.handCardsNum = 0;
		this.topCardIndex = 0;
		this.noCards = false;
		handCards = new int[this.CARDNUM];
		
		if (id == 0)
			this.isAI = false;
		else
			this.isAI = true;
	}
	
	public boolean hasNoCards() {
		for (int i :handCards) {
			if (i != -1)
				noCards = false;
			else
				noCards = true;
		}
		return noCards;
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
	
	public int topHandCards() {
		return this.handCards[topCardIndex];
	}

	public void takeCard(int card) {
		if (handCardsNum < CARDNUM )
			handCards[handCardsNum] = card;
		handCardsNum ++;
		//System.out.println("player:" + id + "take " + card);
	}
	
	public void cardDrop () {
		handCardsNum --;
		handCards[topCardIndex] = -1;
		if (topCardIndex < CARDNUM)
			topCardIndex++;
		else 
			topCardIndex = 0;
	}
	
	public int winCards(int[] commonPile, int commonPileIndex) {
		for (int i = (commonPileIndex - 1); i >= 0; i--) {
			this.takeCard(commonPile[i]);
			
			handCardsNum ++;
			System.out.println("winner takes card "+commonPile[i]);
		}
		return 0;
	}
	
}
