package boggle.words;

public class LetterGrid {
	private Letter[][] grid;
	private int size;
	
	LetterGrid(int size) {
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
