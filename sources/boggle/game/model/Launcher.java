package boggle.game.model;

import java.io.File;

import boggle.game.controller.traitement.Command;
import boggle.game.controller.traitement.Message;
import boggle.game.entity.Human;
import boggle.game.entity.Player;

public class Launcher {
	private GameV2 game;
	
	public Launcher(GameV2 game) {
		this.game = game;
	}
	
	public Launcher() { 
		
	}
	
	public GameV2 getGame() {
		return this.game;
	}
	
	public void setGame(GameV2 g) {
		this.game = g;
	}
	
	public static void main(String[] args) {
		Player[] players = new Player[2];
		players[0] = new Human("J1");
		players[1] = new Human("J2");
		Launcher launcher = new Launcher(new RoundGameV2(players, new File("config/regles-4x4.config"), 1));
		
		launcher.getGame().notify(new Command("GAME", Message.DEMARRER_PARTIE));
	}
}
