package commandline;

/**
 * This class 
 * @author 
 */
public class cmdGameView {

	private boolean isLogs = false;
	private cmdLog logs;
	private cmdDatabase db;
	/**
	 * when tests is available, this constructor is used.
	 */
	public cmdGameView (cmdLog logs, cmdDatabase db) {
		isLogs = true;
		this.logs = logs;
		this.db = db;
		
	}
	
	/**
	 * when tests is unavailable, this constructor is used.
	 */
	public cmdGameView (cmdDatabase db) {
		this.db = db;
	}
	
}
