package boggle.game.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import boggle.game.controller.traitement.Command;
import boggle.game.controller.traitement.MessageRetour;
import boggle.game.controller.traitement.Traitement;
import boggle.game.controller.traitement.TraitementGame;
import boggle.game.controller.traitement.TraitementGrid;
import boggle.game.controller.traitement.TraitementPlayer;
import boggle.game.entity.Player;
import boggle.game.model.GameV2;
import boggle.words.DiceGrid;

public class GameEngineV2 implements Observer{
	//Execute des traitements (process)
	private GameV2 game;
	private DiceGrid grid;
	private Player[] players;
	private Map<String, Traitement> map; 
	
	public GameEngineV2(GameV2 g) {
		this.game = g;
		this.grid = game.getGrid();
		this.players = game.getPlayers();
		//Initialisation de la map:
		map = new HashMap<String, Traitement>();
		map.put("GAME", new TraitementGame());
		map.put("GRID", new TraitementGrid());
		map.put("PLAYER", new TraitementPlayer());
	}
	
	public void update(Observable arg0, Object arg1) {
		//Redirection du message au bon traitement.
		Command c = (Command)arg1;
		//TODO : Remplacer par une exception
		if(execute(c.getTraitementName(), c.getMessage(),arg0) == MessageRetour.KO)
			System.out.println("ERREUR!!");
	}	
	
	private MessageRetour execute(String key, Object o, Object parent) {
		return map.get(key).execute(key, o, parent);
	}
}
