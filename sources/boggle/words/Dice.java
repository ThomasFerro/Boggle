package boggle.words;

import java.util.Random;

public class Dice {
	private char [] values;
	private int currentFaceId;	//Current face's id of values' table
	private boolean used;
	private boolean locked;
	private int x;
	private int y;
	
	public Dice() {
		values = null;
		currentFaceId = -1;
		used = false;
		this.x = -1;
		this.y = -1;
	}
	
	public Dice(int x, int y) {
		this();
		this.x = x;
		this.y = y;
	}
	
	public Dice(char [] values) {
		this();
		this.values = values;
	}
	
	public Dice(int x, int y, char [] values) {
		this(x, y);
		this.values = values;
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setCoord(int x, int y) {
		setX(x);
		setY(y);
	}

	//Face bloquée : [], Face utilisée : {}
	public String toString() {
		return used ? "{" + getCurrentFace() + "}" : (isLocked() ? "[" + getCurrentFace() + "]" : " " + getCurrentFace() + " ");
	}
}
