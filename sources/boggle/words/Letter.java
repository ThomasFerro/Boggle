package boggle.words;

public class Letter {
	private char character;
	private boolean used;
	
	Letter(char c) {
		character = c;
		used = false;
	}
	
	//-------------------------------------------
	//Getters and Setters:
	
	public char getCharacter() {
		return this.character;
	}
	
	public boolean isUsed() {
		return used;
	}
	
	public void getUsed(boolean b) {
		this.used = b;
	}
	
	//-------------------------------------------
}
