package commandline;

/**
 * This class 
 * @author feiguang cao
 */
public class cmdGameModelCard {
	private String description, allDetail, bestCategory;
	private int size, speed, range, firepower, cargo, bestValue;

	public cmdGameModelCard (String des, int size, int speed, int range, int firepower, int cargo) {
		this.description = des;
		this.size = size;
		this.speed = speed;
		this.range = range;
		this.firepower = firepower;
		this.cargo = cargo;
		
		this.searchBest();	

	}
	
	private void searchBest() {
		bestCategory = "size";
		this.bestValue = this.valueofCategory(bestCategory);
			if (bestValue < this.valueofCategory("speed")) {
				bestCategory = "speed";
				this.bestValue = this.valueofCategory(bestCategory);
			}
			else if (bestValue < this.valueofCategory("range")) {
				bestCategory = "range";
				this.bestValue = this.valueofCategory(bestCategory);
			}
				
			else if (bestValue < this.valueofCategory("firepower")) {
				bestCategory = "firepower";
				this.bestValue = this.valueofCategory(bestCategory);
			}
				
			else if (bestValue < this.valueofCategory("cargo")) {
				bestCategory = "cargo";
				this.bestValue = this.valueofCategory(bestCategory);
			}
	}
	
	public int valueofCategory (String cate) {
		if (cate.equals("size") || cate.equals("Size"))
			return this.size;
		else if(cate.equals("speed") || cate.equals("Speed"))
			return this.speed;
		else if(cate.equals("range") || cate.equals("Range"))
			return this.range;
		else if(cate.equals("firepower") || cate.equals("Firepower"))
			return this.firepower;
		else if(cate.equals("cargo") || cate.equals("Cargo"))
			return this.cargo;
		else
			return -1;
	}
	
	public String description () {
		return this.description;
	}
	
	public String detail() {
		allDetail = this.description + "     " +this.size+ "     " +this.speed+ "     " +this.range+ "     " +this.firepower+ "     " +this.cargo ;
		return allDetail;
	}
	
	public String best() {
		return bestCategory;
	}
}
