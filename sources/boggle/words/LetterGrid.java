package boggle.words;

public class LetterGrid {
	private Letter[][] grid;
	private int size;
	
	LetterGrid(int size) {
		this.size = size;
		grid = new Letter[size][size];
		init();
	}
	
	public boolean init() {
		Dice dice = Dice.readCSV("config/des-4x4.csv");
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
	
	//-------------------------------------------
	public static void main(String[] args) {
		LetterGrid l = new LetterGrid(4);
		Letter[][] grid = l.getGrid();
		for (int i = 0; i < grid.length; i ++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j].getCharacter());
			}
			System.out.println();
		}
	}
}
