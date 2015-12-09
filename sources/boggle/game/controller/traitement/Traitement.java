package boggle.game.controller.traitement;

public abstract class Traitement {
	
	public abstract MessageRetour execute(String key, Object o, Object parent);
}
