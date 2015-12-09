package boggle.game.model;

import java.io.File;

import boggle.game.controller.traitement.Command;
import boggle.game.controller.traitement.Message;
import boggle.game.entity.Player;

/**
 * 
 * @author leleuj ferrot
 *
 */
public class PointGameV2 extends GameV2 {
	
	private final int MAX_POINTS;

	public PointGameV2(Player[] players, File config, int maxPoints) {
		super(players, config);
		MAX_POINTS = maxPoints;
	}

	protected boolean isFinished() {
		for(Player player : getPlayers()) {
			if(player.getScore() >= MAX_POINTS) {
				this.notify(new Command("GAME", Message.ARRETER_PARTIE));
				return true; 
			}
		}
		return false;
	}
}
