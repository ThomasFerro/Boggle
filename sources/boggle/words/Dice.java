package boggle.words;

import java.util.Random;

/**
 * The Dice class allow to store a dice with his 6 faces, his current face etc ...
 * @author leleuj
 *
 */
public class Dice {
	private char [] values;
	private int currentFaceId;	//Current face's id of values' table
	private boolean used;
	private boolean locked;
	private int x;
	private int y;
	
	/**
	 * Create a dice with his coords (x,y) and a value's array corresponding to dices' faces' values
	 */
	public Dice(int x, int y, char [] values) {
		this.x = x;
		this.y = y;
		this.values = values;
	}
	
	/**
	 * Shake the dice to change current face
	 */
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
	
	/**
	 * Return the dice's current face
	 * @return <code>char</code>, the current face value
	 */
	public char getCurrentFace() {
		return values[currentFaceId];
	}
	
	/**
	 * To know if dice is used or not
	 * @return <code>true</code> if dice is used,
	 * <code>false</false> if is not used
	 */
	public boolean isUsed() {
		return used;
	}
	
	/**
	 * To know if dice is locked or not
	 * @return <code>true</code> if dice is locked,
	 * <code>false</false> if is not locked
	 */
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
	/**
	 * Return a String representation of dice,
	 * Face blocked : [], Face used : {}
	 */
	public String toString() {
		return used ? "{" + getCurrentFace() + "}" : (isLocked() ? "[" + getCurrentFace() + "]" : " " + getCurrentFace() + " ");
	}
}