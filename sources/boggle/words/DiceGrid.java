package boggle.words;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The DiceGrid represent the game's grid model
 * @author leleuj
 *
 */
public class DiceGrid {
	private Dice[][] grid;		//Array of dices
	private int size;			//Array's size, knowing that dice's grid is an (n^2) array

	/**
	 * Create an empty dice grid
	 * @param size the dice's grid size
	 * @param path the csv path
	 */
	public DiceGrid(int size, String path) {
		this.size = size;
		grid = new Dice[size][size];
		init(path);
	}
	
	/**
	 * Read a CSV to create dices and shake the grid
	 * @param path the CSV path
	 */
	public void init(String path) {
		readCSV(path);
		shake();
	}
	
	/**
	 * Initialize the grid from a CSV
	 * @param path the CSV path
	 */
	public void readCSV(String path) {
		try {
			BufferedReader file = new BufferedReader(new FileReader(path));
			String line;
			int i = 0;
			while ((line = file.readLine()) != null) {
				String chaine = line.replaceAll(";", "");
				int x = i / size;
				int y = i % size;
				grid[x][y] = new Dice(x, y, chaine.toCharArray());
				i++;
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Shake the dice and switch dice each other
	 */
	public void shake() {
		List<Dice> dices = new ArrayList<Dice>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				dices.add(grid[i][j]);
			}
		}
		Random r = new Random();
		int minValue = 0;
		int maxValue;
		int value;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				maxValue = dices.size();
				value = minValue + r.nextInt(maxValue - minValue);
				grid[i][j] = dices.remove(value);
				grid[i][j].shake();
				grid[i][j].setCoord(i, j);
			}
		}
	}
	
	/**
	 * Lock all dices except those around the dice in parameter
	 * @param x x position dice
	 * @param y y position dice
	 * @return boolean <code>true</code> if succeed, else <code>false</code>
	 */
	public boolean lock(int x, int y) {
		getDice(x,y).setUsed(true);
		if (x < grid[0].length && y < grid.length) {
			for (int i = 0; i < grid.length; i ++) { // x
				for (int j = 0; j < grid[i].length; j++) { // y 
					if (!getDice(i,j).isUsed() && !((i ==  x + 1 || i == x - 1 || x == i) && (j ==  y + 1 || j == y - 1 || j == y))) {
						getDice(i,j).setLocked(true);
					}
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Unlock all dices
	 */
	public void unlock() {
		for (int i = 0; i < grid.length; i ++) { // x
			for (int j = 0; j < grid[i].length; j++) { // y 
				getDice(i,j).setLocked(false);
			}
		}
	}
	
	public Dice[][] getGrid() {
		return grid;
	}
	
	public Dice getDice(int x, int y) {
		if (x >= 0 && x < grid[0].length && y >= 0 && y < grid.length) {
			return grid[x][y];
		}
		return null;
	}
	 
	public String toString() {
		String str = "";
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				str += grid[i][j].toString();
			}
			str += "\n";
		}
		return str;
	}
}