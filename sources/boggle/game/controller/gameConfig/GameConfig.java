package boggle.game.controller.gameConfig;

import boggle.game.entity.Player;

public class GameConfig {
	private Player[] players;
	private int limit;
	private int tlimit;
	private String gameType;
	
	public GameConfig(Player[] p, int l, int t, String g) {
		players = p;
		limit = l;
		tlimit = t;
		gameType = g;
	}

	public Player[] getPlayers() {
		return players;
	}

	public int getLimit() {
		return limit;
	}

	public int getTlimit() {
		return tlimit;
	}
	
	public String getGameType() {
		return gameType;
	}
}
