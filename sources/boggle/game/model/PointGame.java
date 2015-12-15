package boggle.game.model;

import java.io.File;

import boggle.game.entity.Player;

/**
 * 
 * @author leleuj ferrot
 *
 */
public class PointGame extends Game {
	
	private final int MAX_POINTS;

	public PointGame(Player[] players, File config, int timeLimit, int maxPoints) {
		super(players, config, timeLimit);
		MAX_POINTS = maxPoints;
	}

	@Override
	protected boolean isFinished() {
		for(Player player : getPlayers()) {
			if(player.getScore() >= MAX_POINTS) 
				return true; 
		}
		return false;
	}
}
