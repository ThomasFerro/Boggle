package boggle.game.model;

import java.io.File;

import boggle.game.entity.Player;

/**
 * Game class with a round limit.
 * @author leleuj ferrot
 */
public class RoundGame extends Game {

	private final int MAX_ROUNDS;
	
	/**
	 * Initialize the parameters.
	 * @param players The players array.
	 * @param config The configurations.
	 * @param timeLimit The time limit.
	 * @param maxRounds The round limit.
	 */
	public RoundGame(Player[] players, File config, int timeLimit, int maxRounds) {
		super(players, config, timeLimit);
		MAX_ROUNDS = maxRounds;
	}

	protected boolean isFinished() {
		return !(this.getRound() < MAX_ROUNDS);
	}

}
