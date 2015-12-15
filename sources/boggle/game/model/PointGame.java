package boggle.game.model;

import java.io.File;

import boggle.game.entity.Player;

/**
 * Game class with a point limit.
 * @author leleuj ferrot
 */
public class PointGame extends Game {
	
	private final int MAX_POINTS;

	/**
	 * Initialize the parameters.
	 * @param players The players array.
	 * @param config The configurations.
	 * @param timeLimit The time limit.
	 * @param maxPoints The point limit.
	 */
	public PointGame(Player[] players, File config, int timeLimit, int maxPoints) {
		super(players, config, timeLimit);
		MAX_POINTS = maxPoints;
	}

	protected boolean isFinished() {
		for(Player player : getPlayers()) {
			if(player.getScore() >= MAX_POINTS) 
				return true; 
		}
		return false;
	}
}
