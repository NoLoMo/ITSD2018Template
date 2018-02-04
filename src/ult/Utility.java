package ult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import commandline.CMDGameControl;
import commandline.vo.CMDGameModelCard;

public class Utility {
	
	/**
	 * swap two items' order
	 * @param arr
	 * @param index1
	 * @param index2
	 */
	public static void swap(CMDGameModelCard[] arr, int index1, int index2) {
		CMDGameModelCard temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
	
	/**
	 * 
	 * @param arr
	 * @param key
	 * @return is the card index in this round's list, which is also the index of this roud's player index.
	 */
	public static int max(ArrayList<CMDGameModelCard> arr, int key) {
		int max = 0;
		for(int i = 0; i < arr.size(); i++) {
			if(arr.get(i).getAttribute(key) >= arr.get(max).getAttribute(key)) {
					max = i;
			}
		}
		
		return max;
	}
	
	
}
