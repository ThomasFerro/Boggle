package boggle.game;

import java.io.File;

/**
 * 
 * @author leleuj ferrot
 *
 */
public class RoundGame extends Game {

	private final int MAX_ROUNDS;
	
	RoundGame(Player[] players, File config, int maxRounds) {
		super(players, config);
		MAX_ROUNDS = maxRounds;
		super.run();
	}

	@Override
	protected boolean isFinished() {
		return !(this.getRound() < MAX_ROUNDS);
	}

}
