package boggle.game.controller.gameConfig;

import boggle.game.entity.Player;

/**
 * Class used between the menu panel and the GameEngine. 
 * @author ferrot leleuj
 */
public class GameConfig {
	private Player[] players;
	private int limit;
	private int tlimit;
	private String gameType;
	
	/**
	 * Initialize the GameConfig object.
	 * @param p The player array.
	 * @param l The round or point limit.
	 * @param t The time limit.
	 * @param g The game type (round or point).
	 */
	public GameConfig(Player[] p, int l, int t, String g) {
		players = p;
		limit = l;
		tlimit = t;
		gameType = g;
	}

	/**
	 * @return The players array.
	 */
	public Player[] getPlayers() {
		return players;
	}

	/**
	 * @return The round or point limit.
	 */
	public int getLimit() {
		return limit;
	}

	/**
	 * @return The time limit.
	 */
	public int getTlimit() {
		return tlimit;
	}
	
	/**
	 * @return The game type.
	 */
	public String getGameType() {
		return gameType;
	}
}
