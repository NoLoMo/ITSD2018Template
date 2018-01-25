package commandline;

import java.util.Scanner;

/**
 * This class 是MVC结构中的Control。负责处理玩游戏（菜单选1）的各种操作。
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
	 * 开启-t测试模式，使用这个构造器
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
	 * 不开启-t测试模式，使用这个构造器
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
	 * start a game, 洗牌，发牌，选定玩家， 然后开启循环一轮一轮走，直到游戏结束。
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
	 * 随机洗牌，玩家按照顺序依次摸牌。
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
		//-------------------------------------print common pile（打印洗完的牌）-----------------------------
//		for (int k = 0; k < CARDSNUM; k++) {
//			System.out.println(k + "-" + pile[k]);
//		}
	}
	
	/**
	 * 随机洗牌。随机选一张牌放在牌堆顶，然后重复。（如果随机选到重复牌，自动选择编号下一张）
	 * @return 洗好的牌组
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
	 * 开局随机选择一位玩家
	 * @return 玩家编号
	 */
	private int shufflePlayer() {
		int res = (int) ((PLAYERNUM - 1) * Math.random());
		return res;
	}
	
	/**
	 * 一回合游戏
	 * @param player 本回合能玩游戏的玩家
	 * @param in 输入检测scanner
	 */
	private void gameRound(int player, Scanner userIn) {
		
		// 展示玩家手牌
		this.gView.showCards(players, cards);
		
		// 获取玩家或电脑选择的category
		if (players[player].isAI()) {
			this.chooseBestCategory(player);
		} else {
			choosingCategory = gView.chooseCategory(userIn);
		}
		System.out.println(">>>" + choosingCategory);
		
		// 比较category，找到获胜玩家
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
	 * 比较卡牌某个类别的值
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
				return -1;  //代表没有赢家，平局
			}
		}
		return winner;
	}
	
	private void gameRoundEnd() {
		//把所有本局卡牌存入公共牌堆
		for (int i = 0; i < PLAYERNUM; i++) {
			this.commonPile[commonPileIndex] = players[i].topHandCards();
			players[i].cardDrop();
			commonPileIndex ++;
		}
		
		// 把公共牌堆卡牌交给玩家，或者平局时跳过。同时选择下一局玩家
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
	 * 检查每一位玩家手牌数，决定游戏是否结束
	 * @return
	 */
	private boolean gameIsNotFinish() {
		int playerNoCardNum = 0;
		
		// 检查每一位玩家的手牌数，没牌则加一
		for (int i = 0; i < PLAYERNUM; i++) {
			if (players[i].hasNoCards())
				playerNoCardNum ++;
		}
		
		// 如果只剩一个人有牌，游戏结束。否则，继续
		if (playerNoCardNum >= (PLAYERNUM - 1))
			return false;
		else
			return true;
	}
	
}
