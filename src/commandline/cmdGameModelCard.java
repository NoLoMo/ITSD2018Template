package commandline;

/**
 * This class 
 * @author feiguang cao
 */
public class cmdGameModelCard {
	private String description;
	private int size, speed, range, firepower, cargo;

	public cmdGameModelCard (String des, int size, int speed, int range, int firepower, int cargo) {
		this.description = des;
		this.size = size;
		this.speed = speed;
		this.range = range;
		this.firepower = firepower;
		this.cargo = cargo;
	}
	
	public int size () {
		return this.size;
	}
	
	public int speed () {
		return this.speed;
	}
	
	public int range () {
		return this.range;
	}
	
	public int firepower () {
		return this.firepower;
	}
	
	public int cargo () {
		return this.cargo;
	}
	
	public String description () {
		return this.description;
	}
}
