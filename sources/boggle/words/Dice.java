package boggle.words;

import java.util.Random;

public class Dice {
	private char [] values;
	private int currentFaceId;	//Current face's id of values' table
	private boolean isUsed;
	
	public Dice() {
		values = null;
		currentFaceId = -1;
		isUsed = false;
	}
	
	public Dice(char [] values) {
		this.values = values;
		currentFaceId = -1;
		isUsed = false;
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
		return isUsed;
	}
}
