package boggle.game.entity;

import java.util.List;

/**
 * 
 */
public interface Player extends Entity, Comparable<Player>{
    public int getScore();
    public void setScore(int score);
    public void addWord(String word);
    public List<String> getWords();
}
