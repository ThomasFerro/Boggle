package boggle.words;

public class LettersGrid {
	private Letter[][] grid;
	private int size;
	
	LettersGrid(int size) {
		this.size = size;
		init();
		//TODO : 
	}
	
	public boolean init() {
		return false;
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

}
