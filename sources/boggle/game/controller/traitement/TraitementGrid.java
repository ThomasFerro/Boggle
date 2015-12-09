package boggle.game.controller.traitement;

import boggle.words.DiceGrid;

public class TraitementGrid extends Traitement{
	
	public MessageRetour execute(String key, Object o, Object parent) {
		DiceGrid grid = (DiceGrid)parent;
		switch((Message)o) {
		case MELANGER_GRILLE : 
			return MessageRetour.OK;
		case BLOQUER_GRILLE : 
			return MessageRetour.OK;
		default : return MessageRetour.KO;
		}
	}

}
