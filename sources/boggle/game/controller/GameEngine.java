package boggle.game.controller;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import boggle.game.controller.gameConfig.GameConfig;
import boggle.game.controller.listener.buttonListener.ButtonAddWordListener;
import boggle.game.controller.listener.buttonListener.ButtonBackToMenuListener;
import boggle.game.controller.listener.buttonListener.ButtonClearListener;
import boggle.game.controller.listener.buttonListener.ButtonConfigListener;
import boggle.game.controller.listener.buttonListener.ButtonDiceListener;
import boggle.game.controller.listener.buttonListener.ButtonPlayListener;
import boggle.game.controller.listener.buttonListener.ButtonSubmitListener;
import boggle.game.model.Game;
import boggle.game.model.PointGame;
import boggle.game.model.RoundGame;
import boggle.gui.gameView.GamePanel;
import boggle.gui.gameView.centerPanel.centerPanel.DiceButton;
import boggle.gui.window.Window;
import boggle.words.Dice;

public class GameEngine implements Observer {
	private Window window;
	private Game game;
	private File configFile;
	private Thread gameThread;
	private String motCourant;

	public GameEngine() {
		motCourant = "";
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

	public void loadGame(Game game) {
		if (gameThread != null)
			gameThread.interrupt();
		window.loadGame();
		window.getGamePanel().getCenterPanel().getCenterPanel().getButtonClear().addActionListener(new ButtonClearListener(this));
		window.getGamePanel().getCenterPanel().getCenterPanel().getButtonAddWord().addActionListener(new ButtonAddWordListener(this));
		window.getGamePanel().getNorthPanel().getBackToMenu().addActionListener(new ButtonBackToMenuListener(window.getGamePanel().getNorthPanel(), this));
		window.getGamePanel().getCenterPanel().getRightPanel().getButtonSubmit().addActionListener(new ButtonSubmitListener(window.getGamePanel().getCenterPanel().getRightPanel(), this));

		this.game = game;
		gameThread = new Thread(this.game);
		gameThread.start();
		window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().init(this.game.getGrid());
		window.getGamePanel().getCenterPanel().getLeftPanel().setCurrentPlayer(this.game.getCurrentPlayer().getName());
		window.getGamePanel().getCenterPanel().getLeftPanel().getPanelScore().init(this.game.getPlayers());

		//Add the listeners to the DiceButton
		DiceButton[][] buttons = (DiceButton[][])this.window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().getButtons();
		addDiceButtonListener(buttons);
	}

	public void update(Observable obs, Object obj) {
		if(obs instanceof ButtonConfigListener) {			//Change the configFile
			configFile = new File(obj.toString());
		}
		else if(obs instanceof ButtonPlayListener) {		//Get the player list and the limits
			updateButtonPlay((GameConfig)obj);			
		}
		else if (obs instanceof ButtonBackToMenuListener) {
			this.loadMenu();
		}
		else if (obs instanceof ButtonSubmitListener) {
			this.updateButtonSubmit();
		}
		else if (obs instanceof ButtonDiceListener) {
			updateButtonDice((Dice)obj);
		}
		else if (obs instanceof ButtonAddWordListener) {
			updateButtonAddWord();
		}
		else if (obs instanceof ButtonClearListener) {
			updateButtonClear();
		}
	}
	
	private void updateButtonAddWord() {
		game.getCurrentPlayer().addWord(motCourant);
		window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().resetGrid();
		motCourant = "";
		window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().repaint();
		window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().revalidate();
	}
	
	public void updateButtonClear() {
		window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().resetGrid();
		motCourant = "";
		window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().repaint();
		window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().revalidate();
	}

	private void updateButtonDice(Dice d) {
		motCourant += d.getCurrentFace();
		System.out.println("Mot : " + motCourant);
		game.getGrid().unlock();
		game.getGrid().lock(d.getX(), d.getY());
		game.getGrid().getDice(d.getX(), d.getY()).setUsed(true);
		window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().update();
		window.getGamePanel().repaint();
		window.getGamePanel().revalidate();
	}
	
	private void updateButtonPlay(GameConfig gameConfig) {
		if(gameConfig.getGameType().equals("RoundGame")) {
			//LoadGamePanel + new RoundGame(infos)
			this.loadGame(new RoundGame(gameConfig.getPlayers(),configFile , gameConfig.getLimit()));
		}
		else {
			if(gameConfig.getGameType().equals("PointGame")) {
				//LoadGamePanel + new PointGame(infos)
				this.loadGame(new PointGame(gameConfig.getPlayers(),configFile , gameConfig.getLimit()));
			}
		}
	}

	private void updateButtonSubmit() {
		game.setSubmited();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().update();
		window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().init(this.game.getGrid());
		//Add the listeners to the DiceButton
		DiceButton[][] buttons = (DiceButton[][])this.window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().getButtons();
		addDiceButtonListener(buttons);
		
		window.getGamePanel().getCenterPanel().getLeftPanel().setCurrentPlayer(this.game.getCurrentPlayer().getName());
		window.getGamePanel().getCenterPanel().getLeftPanel().getPanelScore().update();
		window.getGamePanel().revalidate();
		window.getGamePanel().repaint();
		motCourant = "";
	}

	public void addDiceButtonListener(DiceButton[][] buttons) {
		for(DiceButton[] ligne : buttons) {
			for(DiceButton button : ligne) {
				button.addActionListener(new ButtonDiceListener(this));
			}
		}
	}
	
	public static void main(String[] args) {
		GameEngine engine = new GameEngine();
		engine.loadMenu();						//Lancement du jeu, chargement du menu puis de la partie
	}
}
