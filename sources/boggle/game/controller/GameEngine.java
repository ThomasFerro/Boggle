package boggle.game.controller;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import boggle.game.controller.gameConfig.GameConfig;
import boggle.game.controller.listener.buttonListener.ButtonBackToMenuListener;
import boggle.game.controller.listener.buttonListener.ButtonConfigListener;
import boggle.game.controller.listener.buttonListener.ButtonPlayListener;
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
	
	public void loadGame() {
		window.loadGame();
		window.getGamePanel().getNorthPanel().getBackToMenu().addActionListener(new ButtonBackToMenuListener(window.getGamePanel().getNorthPanel(), this));
	}
	
	public void update(Observable obs, Object obj) {
		if(obs instanceof ButtonConfigListener) {
			//Change the configFile
			configFile = new File(obj.toString());
			System.out.println("test");
		}
		else if(obs instanceof ButtonPlayListener) {
			//Get the player list and the limits
			gameConfig = (GameConfig)obj;
			if(gameConfig.getGameType().equals("RoundGame")) {
				//LoadGamePanel + new RoundGame(infos)
				this.loadGame();
			}
			else {
				if(gameConfig.getGameType().equals("PointGame")) {
					//LoadGamePanel + new PointGame(infos)
					this.loadGame();
				}
			}
		}
		else if (obs instanceof ButtonBackToMenuListener) {
			this.loadMenu();
		}
	}

	public static void main(String[] args) {
		GameEngine engine = new GameEngine();
		//Lancement du jeu, chargement du menu puis de la partie
		engine.loadMenu();
	}
}
