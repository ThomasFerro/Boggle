package boggle.game.controller.traitement;

import boggle.game.model.GameV2;

public class TraitementGame extends Traitement{

	
	public MessageRetour execute(String key, Object o, Object parent) {
		GameV2 game = (GameV2)parent;
		switch((Message)o) {
		case DEMARRER_PARTIE : 
			return game.demarrer();
		case ARRETER_PARTIE :
			return game.arreter();
		case INCREMENTER_TOUR:
			return game.incrementer_tour();
		case CALCULER_SCORE : 
			return game.updateScore();
		default : return MessageRetour.KO;
		}
	}
}
