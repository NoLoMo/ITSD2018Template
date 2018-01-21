package commandline;

/** 
 * this class 
 * @author 
 */
public class cmdGameHistory {
	
	private cmdDatabase db;
	
	public cmdGameHistory (cmdDatabase db) {
		this.db = db;
	}
	
	public void getHistory () {
		String history = db.getDbHistory();
		System.out.println(history);
	}
}
