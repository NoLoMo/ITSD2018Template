package commandline;

import java.util.Scanner;

/**
 * This class ��MVC�ṹ�е�Control������������Ϸ���˵�ѡ1���ĸ��ֲ�����
 * @author 
 */
public class cmdGameControl {
	
	private boolean isLogs = false, isNotFinish = true;
	private cmdLog logs;
	private cmdDatabase db;
	private cmdGameView gView;
	
	private static int PLAYERNUM = 5;
	private static int CARDSNUM = 40;
	private int winner, commonPileIndex = 0, activePlayer;
	private int[]  commonPile;
	private cmdGameModelCard[] cards;
	private cmdGameModelPlayer[] players;
	private Scanner userIn;
	private String choosingCategory;
	
	/**
	 * when tests is available, this constructor is used.
	 * ����-t����ģʽ��ʹ�����������
	 */
	public cmdGameControl (cmdLog logs, cmdDatabase db) {
		this.isLogs = true;
		this.logs = logs;
		this.db = db;
		gView = new cmdGameView(logs, db, PLAYERNUM);
		cards = db.createCards();
		commonPile = new int[CARDSNUM];
		for (int i = 0; i < CARDSNUM; i++) {
			commonPile[i] = -1;
		}
		players = db.createPlayers(PLAYERNUM);
	}
	
	/**
	 * when tests is unavailable, this constructor is used.
	 * ������-t����ģʽ��ʹ�����������
	 */
	public cmdGameControl (cmdDatabase db) {
		this.db = db;
		gView = new cmdGameView(logs, db, PLAYERNUM);
		cards = db.createCards();
		players = db.createPlayers(PLAYERNUM);
		commonPile = new int[CARDSNUM];
		for (int i = 0; i < CARDSNUM; i++) {
			commonPile[i] = -1;
		}
	}
	
	/**
	 * start a game, ϴ�ƣ����ƣ�ѡ����ң� Ȼ����ѭ��һ��һ���ߣ�ֱ����Ϸ������
	 */
	public void start (Scanner in) {
		userIn = in;
		this.licensing();
		// this.gView.showCards(players);  ---old test code, not work now
		activePlayer = this.shufflePlayer();
		
		int roundNum = 0;
		do {
			this.gView.showRoundStartInfo( roundNum,  activePlayer);
			
			this.gameRound(activePlayer, userIn);
			this.gameRoundEnd();
			this.isNotFinish = this.gameIsNotFinish();
			roundNum ++;
		} while(this.isNotFinish);

	}
	
	/**
	 * ���ϴ�ƣ���Ұ���˳���������ơ�
	 */
	private void licensing () {
		int[] pile = this.shuffleCards();

		int i = 0;
		while (i < CARDSNUM) {
			for (int j = 0; j < PLAYERNUM; j++) {
				this.players[j].takeCard(pile[i]);
				i++;
			}
			
		}
		//-------------------------------------print common pile����ӡϴ����ƣ�-----------------------------
//		for (int k = 0; k < CARDSNUM; k++) {
//			System.out.println(k + "-" + pile[k]);
//		}
	}
	
	/**
	 * ���ϴ�ơ����ѡһ���Ʒ����ƶѶ���Ȼ���ظ�����������ѡ���ظ��ƣ��Զ�ѡ������һ�ţ�
	 * @return ϴ�õ�����
	 */
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
	
	/**
	 * �������ѡ��һλ���
	 * @return ��ұ��
	 */
	private int shufflePlayer() {
		int res = (int) ((PLAYERNUM - 1) * Math.random());
		return res;
	}
	
	/**
	 * һ�غ���Ϸ
	 * @param player ���غ�������Ϸ�����
	 * @param in ������scanner
	 */
	private void gameRound(int player, Scanner userIn) {
		
		// չʾ�������
		this.gView.showCards(players, cards);
		
		// ��ȡ��һ����ѡ���category
		if (players[player].isAI()) {
			this.chooseBestCategory(player);
		} else {
			choosingCategory = gView.chooseCategory(userIn);
		}
		System.out.println(">>>" + choosingCategory);
		
		// �Ƚ�category���ҵ���ʤ���
		winner = this.compare(choosingCategory);
		this.gView.showRoundWinner(winner);
		
		
	}
	
	/**
	 * AI player choose its best category
	 * @param player
	 */
	private void chooseBestCategory(int player) {
		// TODO Auto-generated method stub
		int index = this.players[player].topHandCards();
		choosingCategory = this.cards[index].best();
	}

	/**
	 * �ȽϿ���ĳ������ֵ
	 * @param choosingCategory
	 * @return 
	 */
	private int compare(String choosingCategory) {
		int temp = 0, max, cardIndex;
		max = temp;
		int winner = -1;
		for(int i = 0; i < PLAYERNUM; i++) {
			cardIndex = players[i].topHandCards();
			temp = cards[cardIndex].valueofCategory(choosingCategory);
			if (temp > max) {
				max = temp;
				winner = i;
			} else if(temp == max){
				return -1;  //����û��Ӯ�ң�ƽ��
			}
		}
		return winner;
	}
	
	private void gameRoundEnd() {
		//�����б��ֿ��ƴ��빫���ƶ�
		for (int i = 0; i < PLAYERNUM; i++) {
			this.commonPile[commonPileIndex] = players[i].topHandCards();
			players[i].cardDrop();
			commonPileIndex ++;
		}
		
		// �ѹ����ƶѿ��ƽ�����ң�����ƽ��ʱ������ͬʱѡ����һ�����
		if (winner == -1) {
			if (this.activePlayer < (PLAYERNUM- 1))
				this.activePlayer ++;
			else
				this.activePlayer = 0;
		} else {
			commonPileIndex = players[winner].winCards(commonPile, commonPileIndex);
			this.activePlayer = winner;
		}
		
		System.out.println("---You have " + this.players[0].handCardsNum() + " cards");
//		for(int i: players[winner].topHandCards()) {
//			System.out.println(i);
//		}
	}
	
	/**
	 * ���ÿһλ�����������������Ϸ�Ƿ����
	 * @return
	 */
	private boolean gameIsNotFinish() {
		int playerNoCardNum = 0;
		
		// ���ÿһλ��ҵ���������û�����һ
		for (int i = 0; i < PLAYERNUM; i++) {
			if (players[i].hasNoCards())
				playerNoCardNum ++;
		}
		
		// ���ֻʣһ�������ƣ���Ϸ���������򣬼���
		if (playerNoCardNum >= (PLAYERNUM - 1))
			return false;
		else
			return true;
	}
	
}
