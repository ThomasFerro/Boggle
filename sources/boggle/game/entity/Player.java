package boggle.game.entity;

import java.util.List;

/**
 * 
 */
public interface Player extends Entity {
    public int getScore();
    public void setScore(int score);
    public void addWord(String word);
    public List<String> getWords();
}
