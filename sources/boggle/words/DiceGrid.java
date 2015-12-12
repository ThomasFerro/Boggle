package boggle.words;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;

import boggle.game.controller.GameEngineV2;
import boggle.game.controller.traitement.MessageRetour;

public class DiceGrid extends Observable{
	private Dice[][] grid;
	private int size;
	
	public DiceGrid(int size, GameEngineV2 engine) {
		this.addObserver(engine);
		this.size = size;
		grid = new Dice[size][size];
	}
	
	public DiceGrid(int size, String path, GameEngineV2 engine) {
		this.addObserver(engine);
		this.size = size;
		grid = new Dice[size][size];
		init(path);
	}
	
	public void init(String path) {
		readCSV(path);
		shake();
	}
	
	public void notify(Object o) {
		this.setChanged();
		this.notifyObservers(o);
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
	
	public MessageRetour shake() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j].shake();
			}
		}
		return MessageRetour.OK;
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
}