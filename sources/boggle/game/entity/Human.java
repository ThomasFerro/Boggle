package boggle.game.entity;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Human implements Player{
    private String name;
    private int score;
    private List<String> words;
    
    public Human(String name) {
		this.name = name;
		this.score = 0;
		this.words = new ArrayList<String>();
    }

    //Player + Entity's methods
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public int getScore() { return this.score; }
    public void setScore(int score) { this.score = score; }
    public void addWord(String word) { words.add(word); }
    public List<String> getWords() { return words; }

	public int compareTo(Player o) {
		return this.getScore() - o.getScore();
	}
}
