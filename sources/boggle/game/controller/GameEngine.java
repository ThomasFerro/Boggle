package boggle.game.controller;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import boggle.game.controller.buttonListener.ButtonConfigListener;
import boggle.game.controller.buttonListener.ButtonPlayListener;
import boggle.game.controller.gameConfig.GameConfig;
import boggle.game.model.Game;
import boggle.gui.window.Window;

public class GameEngine implements Observer{
	private Window window;
	private Game game;
	private File configFile;
	private GameConfig gameConfig;
	
	public GameEngine() {
		window = new Window();
		configFile = new File("config/regles-4x4.config");
	}
	
	public void run() {
		//Lance le jeu
	}
	
	public void loadMenu() {
		//Load the Menu page and add the actionListeners
		window.loadMenu();
		window.getMenu().getLeftMenu().getButtonPlay().addActionListener(new ButtonPlayListener(window.getMenu().getLeftMenu(), this));
		window.getMenu().getLeftMenu().getButtonConfig().addActionListener(new ButtonConfigListener(window.getMenu().getLeftMenu(), this));
	}
	
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof ButtonConfigListener) {
			//Change the configFile
			configFile = new File(arg1.toString());
			System.out.println("test");
		}
		else {
			if(arg0 instanceof ButtonPlayListener) {
				//Get the player list and the limits
				gameConfig = (GameConfig)arg1;
				if(gameConfig.getGameType().equals("RoundGame")) {
					//LoadGamePanel + new RoundGame(infos)
				}
				else {
					if(gameConfig.getGameType().equals("PointGame")) {
						//LoadGamePanel + new PointGame(infos)
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		GameEngine engine = new GameEngine();
		//Lancement du jeu, chargement du menu puis de la partie
		engine.loadMenu();
	}
}
