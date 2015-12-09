package boggle.game.controller.traitement;

import boggle.game.model.GameV2;

public class TraitementGame extends Traitement{

	
	public MessageRetour execute(String key, Object o, Object parent) {
		GameV2 game = (GameV2)parent;
		switch((Message)o) {
		case DEMARRER_PARTIE : 
			game.demarrer();
			return MessageRetour.OK;
		case ARRETER_PARTIE :
			game.arreter();
			return MessageRetour.OK;
		case INCREMENTER_TOUR:
			game.incrementer_tour();
			return MessageRetour.OK;
		default : return MessageRetour.KO;
		}
	}
}
