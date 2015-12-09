package boggle.game.model;

import java.io.File;

import boggle.game.entity.Player;

/**
 * 
 * @author leleuj ferrot
 *
 */
public class RoundGameV2 extends GameV2 {

	private final int MAX_ROUNDS;
	
	public RoundGameV2(Player[] players, File config, int maxRounds) {
		super(players, config);
		MAX_ROUNDS = maxRounds;
	}

	protected boolean isFinished() {
		return !(this.getRound() < MAX_ROUNDS);
	}

}
