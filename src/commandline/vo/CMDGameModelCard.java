package commandline.vo;

/**
 * This class 
 * @author feiguang cao, yifeng sun
 */
public class CMDGameModelCard{
	private String description;
	private int id, size, speed, range, firepower, cargo;

	public CMDGameModelCard (int id, String des, int size, int speed, int range, int firepower, int cargo) {
		this.id = id;
		this.description = des;
		this.size = size;
		this.speed = speed;
		this.range = range;
		this.firepower = firepower;
		this.cargo = cargo;
	}
	
	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public int getSize() {
		return size;
	}

	public int getSpeed() {
		return speed;
	}

	public int getRange() {
		return range;
	}

	public int getFirepower() {
		return firepower;
	}

	public int getCargo() {
		return cargo;
	}

	public int getAttribute(int key) {
		int ret = -1;
			switch(key) {
				case 0: ret = this.size; break;
				case 1: ret = this.speed; break;
				case 2: ret = this.range ; break;
				case 3: ret = this.firepower; break;
				case 4: ret = this.cargo; break;
			}
			
		return ret;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) {
			return false;
		}
		
		if(obj == this) {
			return true;
		}
		
		if(!(obj instanceof CMDGameModelCard)) {
			return false;
		}
		
		CMDGameModelCard card = (CMDGameModelCard)obj;
		return card.id == this.id;
	}
}
