package boggle.game.entity;

import java.util.List;

import boggle.game.controller.traitement.MessageRetour;

/**
 * 
 */
public interface Player extends Entity, Comparable<Player> {
	public int compareTo(Player p);
    public int getScore();
    public void setScore(int score);
    public void addWord(String word);
    public List<String> getWords();
    public void notify(Object o);
    public boolean isPlaying();
    public MessageRetour plays();
    public MessageRetour updateScore(int points);
    public MessageRetour stopPlaying();
}
