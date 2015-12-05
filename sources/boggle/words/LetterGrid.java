package boggle.words;

public class LetterGrid {
	private Letter[][] grid;
	private int size;
	private Dice dice;
	
	public LetterGrid(int size, String path) {
		this.size = size;
		grid = new Letter[size][size];
		init(path);
	}
	
	public boolean init(String path) {
		dice = Dice.readCSV(path);
		shake();
		return true;
	}
	
	public boolean shake() {
		if(dice == null)
			return false;
		
		for (int i = 0; i < grid.length; i ++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Letter(dice.getRandomValue(i, j));
			}
		}
		return true;
	}
	
	//-------------------------------------------
	//Getters and Setters
	
	public int getSize() {
		return this.size;
	}
	
	public Letter[][] getGrid() {
		return this.grid;
	}
	
	public Letter getLetter(int x, int y) {
		return getGrid()[x][y];
	}
}
