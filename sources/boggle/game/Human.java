package boggle.game;
/**
 */
public class Human implements Player {
    private String name;
    private int score;
    
    public Human(String name) {
	this.name = name;
	this.score = 0;
    }

    //Player + Entity's methods
    public String getName() { return this.name; }
    public void setName(String name) { this.name = name; }
    public int getScore() { return this.score; }
    public void setScore(int score) { this.score = score; }
}
