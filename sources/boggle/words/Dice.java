package boggle.words;

import java.util.Random;

public class Dice {
	private char [] values;
	private int currentFaceId;	//Current face's id of values' table
	private boolean used;
	private boolean locked;
	
	public Dice() {
		values = null;
		currentFaceId = -1;
		used = false;
	}
	
	public Dice(char [] values) {
		this.values = values;
		currentFaceId = -1;
		used = false;
	}
	
	public void shake() {
		if (values != null && values.length > 0) {
			Random r = new Random();
			int minValue = 0;
			int maxValue = values.length;
			int value = minValue + r.nextInt(maxValue - minValue);
			currentFaceId = value;
		}
		else {
			//Exception ..
		}
	}
	
	public void setValues(char[] values) {
		this.values = values;
	}

	public int getCurrentFaceId() {
		return currentFaceId;
	}
	
	public char getCurrentFace() {
		return values[currentFaceId];
	}
	
	public boolean isUsed() {
		return used;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean isLocked) {
		this.locked = isLocked;
	}
	
	public void setUsed(boolean isUsed) {
		this.used = isUsed;
	}

	//Face bloquée : [], Face utilisée : {}
	public String toString() {
		return used ? "{" + getCurrentFace() + "}" : (isLocked() ? "[" + getCurrentFace() + "]" : " " + getCurrentFace() + " ");
	}
}
