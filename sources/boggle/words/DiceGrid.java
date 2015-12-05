package boggle.words;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DiceGrid {
	private Dice[][] grid;
	private int size;
	
	public DiceGrid(int size) {
		this.size = size;
		grid = new Dice[size][size];
	}
	
	public DiceGrid(int size, String path) {
		this.size = size;
		grid = new Dice[size][size];
		init(path);
	}
	
	public boolean init(String path) {
		readCSV(path);
		shake();
		return true;
	}
	
	public void readCSV(String path) {
		try {
			BufferedReader file = new BufferedReader(new FileReader(path));
			String line;
			int i = 0;
			while ((line = file.readLine()) != null) {
				String chaine = line.replaceAll(";", "");
				int x = i / size;
				int y = i % size;
				grid[x][y] = new Dice(chaine.toCharArray());
				i++;
			}
			file.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void shake() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j].shake();
			}
		}
	}
	
	public String toString() {
		String str = "";
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				str += grid[i][j].getCurrentFace();
			}
			str += "\n";
		}
		return str;
	}

	public Dice[][] getGrid() {
		return grid;
	}
	/*
		public static void main(String[] args) {
			DiceGrid d = new DiceGrid(4,"config/des-4x4.csv");
			System.out.println(d);
		}
	*/
}