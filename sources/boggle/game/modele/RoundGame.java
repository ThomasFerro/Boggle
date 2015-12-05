package boggle.game.modele;

import java.io.File;

import boggle.game.entity.Player;

/**
 * 
 * @author leleuj ferrot
 *
 */
public class RoundGame extends Game {

	private final int MAX_ROUNDS;
	
	public RoundGame(Player[] players, File config, int maxRounds) {
		super(players, config);
		MAX_ROUNDS = maxRounds;
		super.run();
	}

	@Override
	protected boolean isFinished() {
		return !(this.getRound() < MAX_ROUNDS);
	}

}
