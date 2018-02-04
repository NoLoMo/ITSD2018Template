package commandline;

import java.awt.Color;
import java.util.ArrayList;

import commandline.vo.CMDGameModelCard;
import commandline.function.CMDDatabase;
import commandline.function.CMDLog;
import ult.Utility;

/**
 * This class is the view of MVC framework.
 * @author yifeng sun
 */
public class CMDGameView {
	private CMDGameControl control;
	
	
	public CMDGameView(CMDLog testLog, CMDDatabase db) {
		this.control = new CMDGameControl(testLog, db);
		System.out.println("//////////// Game is Starting //////////////////");
		this.control.newRound();
		System.out.println("////////////////// Game Over ////////////////// ");
		
//		ArrayList<CMDGameModelCard> arr = new ArrayList<CMDGameModelCard>();
//		
//				arr.add(new CMDGameModelCard(1, "350", 3, 2, 3, 4,5));
//				arr.add(new CMDGameModelCard(2, "351", 32, 2, 3, 4,5));
//				arr.add(new CMDGameModelCard(3, "352", 12, 2, 3, 4,5)); 
//				arr.add(new CMDGameModelCard(4, "353", 12, 2, 3, 4,5)); 
//				arr.add(new CMDGameModelCard(5, "354", 12, 2, 3, 4,5));
//		
//		Utility.sort(arr, 1);
//		for(int i = 0; i < arr.size(); i++) {
//			System.out.println(arr.get(i).getId()+ "---" + arr.get(i).getSize());
//		}
		
//		System.out.println(CMDGameControl.isDraw(arr, 1));
		
	}
	
	
}
