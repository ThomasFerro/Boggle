package boggle.game.controller.traitement;

import boggle.game.entity.Player;

public class TraitementPlayer extends Traitement{

	public MessageRetour execute(String key, Object o, Object parent) {
		Player player = (Player) parent;
		switch((Message)o) {
		case DONNER_MAIN :
			return player.plays();
		case PRENDRE_MAIN :
			return player.stopPlaying();
		default: 
			return MessageRetour.KO;
		}
	}
}
