package boggle.game.controller;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import boggle.game.controller.gameConfig.GameConfig;
import boggle.game.controller.listener.buttonListener.ButtonBackToMenuListener;
import boggle.game.controller.listener.buttonListener.ButtonConfigListener;
import boggle.game.controller.listener.buttonListener.ButtonPlayListener;
import boggle.game.controller.listener.buttonListener.ButtonSubmitListener;
import boggle.game.model.Game;
import boggle.game.model.PointGame;
import boggle.game.model.RoundGame;
import boggle.gui.window.Window;

public class GameEngine implements Observer{
	private Window window;
	private Game game;
	private File configFile;
	private GameConfig gameConfig;
	private Thread gameThread;
	
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
		window.getGamePanel().getCenterPanel().getRightPanel().getButtonSubmit().addActionListener(new ButtonSubmitListener(window.getGamePanel().getCenterPanel().getRightPanel(), this));
	}
	
	public void update(Observable obs, Object obj) {
		if(obs instanceof ButtonConfigListener) {
			//Change the configFile
			configFile = new File(obj.toString());
		}
		else if(obs instanceof ButtonPlayListener) {
			//Get the player list and the limits
			updateButtonPlay((GameConfig)obj);			
		}
		else if (obs instanceof ButtonBackToMenuListener) {
			this.loadMenu();
		}
		else if (obs instanceof ButtonSubmitListener) {
			this.updateButtonSubmit();
		}
	}
	
	private void updateButtonPlay(GameConfig gameConfig) {
		if(gameConfig.getGameType().equals("RoundGame")) {
			//LoadGamePanel + new RoundGame(infos)
			this.loadGame();
			game = new RoundGame(gameConfig.getPlayers(),configFile , gameConfig.getLimit());
			gameThread = new Thread(game);
			gameThread.start();
		}
		else {
			if(gameConfig.getGameType().equals("PointGame")) {
				//LoadGamePanel + new PointGame(infos)
				this.loadGame();
				game = new PointGame(gameConfig.getPlayers(),configFile , gameConfig.getLimit());
				gameThread = new Thread(game);
				gameThread.start();
			}
		}
	}
	
	private void updateButtonSubmit() {
		game.setSubmited();
	}

	public static void main(String[] args) {
		GameEngine engine = new GameEngine();
		//Lancement du jeu, chargement du menu puis de la partie
		engine.loadMenu();
	}
}
