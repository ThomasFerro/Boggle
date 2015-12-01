package boggle.game;

import java.io.File;

/**
 * 
 * @author leleuj ferrot
 *
 */
public class PointGame extends Game {
	
	private final int MAX_POINTS;

	PointGame(Player[] players, File config, int maxPoints) {
		super(players, config);
		MAX_POINTS = maxPoints;
		super.run();
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
