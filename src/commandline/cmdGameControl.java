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
	/**
	 * when tests is available, this constructor is used.
	 */
	public cmdGameControl (cmdLog logs, cmdDatabase db) {
		this.isLogs = true;
		this.logs = logs;
		this.db = db;
		gView = new cmdGameView(logs, db);
		db.createCards();
		db.createPlayers();
	}
	
	/**
	 * when tests is unavailable, this constructor is used.
	 */
	public cmdGameControl (cmdDatabase db) {
		this.db = db;
		gView = new cmdGameView(logs, db);
		db.createCards();
		db.createPlayers();
	}
	
	public void start () {
		
	}
	
}
