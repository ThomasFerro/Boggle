package boggle.game;

import java.io.File;

/**
 * 
 * @author leleuj ferrot
 *
 */
public abstract class Game {
	//TODO : Après le package word
	private int round;
	private Player[] players;
	private File config;

	Game(Player[] players, File config) {
		this.players = players;
		this.config = config;
	}
	
	public boolean launch() {
		//TODO
		return false;
	}
	
	public void endTurn() {
		//TODO
	}

	abstract boolean isFinished();

	//-------------------------------------------
	//Getters and Setters :
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
	
	public Player[] getPlayers() {
		return players;
	}

	public File getConfig() {
		return config;
	}

	//-------------------------------------------


}
