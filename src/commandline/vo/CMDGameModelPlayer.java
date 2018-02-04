package commandline.vo;

import java.util.ArrayList;

/**
 * This class 
 * @author feiguang cao, yifeng sun
 */
public class CMDGameModelPlayer {
	public static final int INIT = 8;
	public static final int CARD_NUM = 40;
	
	private int id;
	private boolean isAI;
	private ArrayList<CMDGameModelCard> list;
	
	public CMDGameModelPlayer (int id) {
		this.id = id;
		this.list = new ArrayList<CMDGameModelCard>();
		this.isAI = id == 0 ? false : true;
	}
	
	public void addCard(CMDGameModelCard newCard) {
		if(this.list.size() <= CARD_NUM) {
			this.list.add(0, newCard);
		}
	}
	
	public void addCard(ArrayList<CMDGameModelCard> newCard, int index) {
		if(this.list.size() + newCard.size() <= CARD_NUM) {
			this.list.addAll(index, newCard);
		}
	}
	
	/**
	 * get the top card of player's handcards.
	 * @return
	 */
	public CMDGameModelCard getCard() {
		return this.list.get(this.list.size() - 1);
	}
	
	/**
	 * get a specific card of player
	 * @param index
	 * @return
	 */
	public CMDGameModelCard getCard(int index) {
		return this.list.get(index);
	}
	
	public CMDGameModelCard removeCard() {
		if(this.list.size() > 0) {
			CMDGameModelCard ret = list.get(list.size() - 1);
			this.list.remove(list.size() - 1);
			return ret;
		}
		
		return null;
	}
	
	public int getID() {
		return this.id;
	}
	
	public boolean isAI() {
		return this.isAI;
	}
	
	public boolean hasCard() {
		return !this.list.isEmpty();
	}
	
	public int size() {
		return this.list.size();
	}
}
