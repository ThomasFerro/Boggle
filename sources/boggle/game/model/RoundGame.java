package boggle.game.model;

import java.io.File;

import boggle.game.entity.Player;

/**
 * 
 * @author leleuj ferrot
 *
 */
public class RoundGame extends Game {

	private final int MAX_ROUNDS;
	
	public RoundGame(Player[] players, File config, int timeLimit, int maxRounds) {
		super(players, config, timeLimit);
		MAX_ROUNDS = maxRounds;
	}

	@Override
	protected boolean isFinished() {
		return !(this.getRound() < MAX_ROUNDS);
	}

}
