package boggle.game.controller;

import java.util.Observable;
import java.util.Observer;

import boggle.game.model.Game;
import boggle.gui.window.Window2;

public class GameEngine implements Observer{
	private Window2 window;
	private Game game;
	
	public GameEngine() {
		window = new Window2();
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
		System.out.println("Observable : " + arg0);
	}

	public static void main(String[] args) {
		GameEngine engine = new GameEngine();
		//Lancement du jeu, chargement du menu puis de la partie
		engine.loadMenu();
	}
}
