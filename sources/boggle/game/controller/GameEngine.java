package boggle.game.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import boggle.game.controller.gameConfig.GameConfig;
import boggle.game.controller.listener.buttonListener.ButtonAddWordListener;
import boggle.game.controller.listener.buttonListener.ButtonBackToMenuListener;
import boggle.game.controller.listener.buttonListener.ButtonClearListener;
import boggle.game.controller.listener.buttonListener.ButtonConfigListener;
import boggle.game.controller.listener.buttonListener.ButtonDiceListener;
import boggle.game.controller.listener.buttonListener.ButtonPlayListener;
import boggle.game.controller.listener.buttonListener.ButtonSubmitListener;
import boggle.game.entity.Player;
import boggle.game.model.Game;
import boggle.game.model.PointGame;
import boggle.game.model.RoundGame;
import boggle.game.model.Sablier;
import boggle.gui.gameView.centerPanel.centerPanel.DiceButton;
import boggle.gui.window.Window;
import boggle.words.Dice;

/**
 * Main class of the game, the controller that take care of the interface and the game instance.
 * @author ferrot leleuj
 */
public class GameEngine implements Observer {
	private Window window;
	private Game game;
	private File configFile;
	private Thread gameThread;
	private String motCourant;
	private int timer;

	/**
	 * 
	 */
	public GameEngine() {
		motCourant = "";
		timer = 0;
		window = new Window();
		configFile = new File("config/regles-4x4.config");
	}

	/**
	 * Load and show the menu.
	 */
	public void loadMenu() {
		//Load the Menu page and add the actionListeners
		window.loadMenu();
		window.getMenu().getLeftMenu().getButtonPlay().addActionListener(new ButtonPlayListener(window.getMenu().getLeftMenu(), this));
		window.getMenu().getLeftMenu().getButtonConfig().addActionListener(new ButtonConfigListener(window.getMenu().getLeftMenu(), this));
	}

	/**
	 * Load the game, start a thread and show the game panel.
	 * @param game
	 */
	public void loadGame(Game game) {
		if (gameThread != null)
			gameThread.interrupt();
		window.loadGame();
		window.getGamePanel().getCenterPanel().getCenterPanel().getButtonClear().addActionListener(new ButtonClearListener(this));
		window.getGamePanel().getCenterPanel().getCenterPanel().getButtonAddWord().addActionListener(new ButtonAddWordListener(this));
		window.getGamePanel().getNorthPanel().getBackToMenu().addActionListener(new ButtonBackToMenuListener(window.getGamePanel().getNorthPanel(), this));
		window.getGamePanel().getCenterPanel().getRightPanel().getButtonSubmit().addActionListener(new ButtonSubmitListener(window.getGamePanel().getCenterPanel().getRightPanel(), this));
		window.getGamePanel().getCenterPanel().getCenterPanel().getWordTextField().setEditable(false);
		
		this.game = game;
		this.game.deleteObservers();
		this.game.addObserver(this);
		this.game.getSablier().addObserver(this);
		gameThread = new Thread(this.game);
		gameThread.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().init(this.game.getGrid());
		this.window.getGamePanel().getCenterPanel().getLeftPanel().setCurrentPlayer(this.game.getCurrentPlayer().getName());
		this.window.getGamePanel().getCenterPanel().getLeftPanel().getPanelScore().init(this.game.getPlayers());
		

		//Add the listeners to the DiceButton
		DiceButton[][] buttons = (DiceButton[][])this.window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().getButtons();
		for(DiceButton[] ligne : buttons) {
			for(DiceButton button : ligne) {
				button.addActionListener(new ButtonDiceListener(this));
			}
		}
	}

	/**
	 * Recast the Observer and redirect the notification to the right method.
	 * @param Obs : The observer that send the notification.
	 * @param Obj (optional): The object of this notification. 
	 */
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
			this.motCourant = "";
			this.game.getSablier().stop();
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
		else if (obs instanceof Sablier) {
			updateTimer((int)obj);
			if((int)obj == 0)
				this.updateButtonSubmit();
		}
		else if (obs instanceof Game) {
			endGame((ArrayList<Player>)obj);
		}
	}
	
	private void endGame(ArrayList<Player> players) {
		String text = "GAGNANT(S): ";
		for(Player p : players) {
			text += p.getName() + ": " + p.getScore() + "; ";
		}
		System.out.println(text);
		JOptionPane dialogWinner = new JOptionPane();
		dialogWinner.showMessageDialog(null, text, "GAGNANT(S)", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void updateTimer(int t) {
		this.window.getGamePanel().getNorthPanel().getTimer().setTime(t);
		window.getGamePanel().repaint();
		window.getGamePanel().revalidate();
	}
	
	private void updateButtonAddWord() {
		if(!game.getCurrentPlayer().getWords().contains(motCourant)) {
			game.getCurrentPlayer().addWord(motCourant);
			this.window.getGamePanel().getCenterPanel().getRightPanel().getWordPanel().add(motCourant);
			this.window.getGamePanel().getCenterPanel().getRightPanel().getWordPanel().update();
			this.window.getGamePanel().getCenterPanel().getCenterPanel().getWordTextField().setText(motCourant);
		}
		this.window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().resetGrid();
		this.window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().repaint();
		this.window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().revalidate();
		motCourant = "";
	}
	
	private void updateButtonClear() {
		motCourant = "";
		this.window.getGamePanel().getCenterPanel().getCenterPanel().getWordTextField().setText(motCourant);
		this.window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().resetGrid();
		this.window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().repaint();
		this.window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().revalidate();
	}

	private void updateButtonDice(Dice d) {
		motCourant += d.getCurrentFace();
		this.window.getGamePanel().getCenterPanel().getCenterPanel().getWordTextField().setText(motCourant);
		game.getGrid().unlock();
		game.getGrid().lock(d.getX(), d.getY());
		game.getGrid().getDice(d.getX(), d.getY()).setUsed(true);
		this.window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().update();
		window.getGamePanel().repaint();
		window.getGamePanel().revalidate();
	}
	
	private void updateButtonPlay(GameConfig gameConfig) {
		if(gameConfig.getGameType().equals("RoundGame")) {
			//LoadGamePanel + new RoundGame(infos)
			this.loadGame(new RoundGame(gameConfig.getPlayers(),configFile , gameConfig.getTlimit(), gameConfig.getLimit()));
		}
		else {
			if(gameConfig.getGameType().equals("PointGame")) {
				//LoadGamePanel + new PointGame(infos)
				this.loadGame(new PointGame(gameConfig.getPlayers(),configFile , gameConfig.getTlimit(), gameConfig.getLimit()));
			}
		}
	}

	private void updateButtonSubmit() {
		game.setSubmited();
		game.getSablier().setFinished();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().update();
		this.window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().init(this.game.getGrid());
		//Add the listeners to the DiceButton
		DiceButton[][] buttons = (DiceButton[][])this.window.getGamePanel().getCenterPanel().getCenterPanel().getGridView().getButtons();
		addDiceButtonListener(buttons);
		this.window.getGamePanel().getCenterPanel().getLeftPanel().setCurrentPlayer(this.game.getCurrentPlayer().getName());
		this.window.getGamePanel().getCenterPanel().getLeftPanel().getPanelScore().update();
		this.window.getGamePanel().revalidate();
		this.window.getGamePanel().repaint();
		this.motCourant = "";
		this.window.getGamePanel().getCenterPanel().getCenterPanel().getWordTextField().setText(motCourant);
		this.window.getGamePanel().getCenterPanel().getRightPanel().getWordPanel().clear();
	}
	
	private void addDiceButtonListener(DiceButton[][] buttons) {
		for(DiceButton[] ligne : buttons) {
			for(DiceButton button : ligne) {
				button.addActionListener(new ButtonDiceListener(this));
			}
		}
	}
	
	/**
	 * Main method of the game, load the engine and the menu.
	 * @param args
	 */
	public static void main(String[] args) {
		GameEngine engine = new GameEngine();
		//Lancement du jeu, chargement du menu puis de la partie
		engine.loadMenu();
	}
}
