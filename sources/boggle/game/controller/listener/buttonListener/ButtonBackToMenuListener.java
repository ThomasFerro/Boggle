package boggle.game.controller.listener.buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import boggle.game.controller.GameEngine;
import boggle.gui.gameView.northPanel.NorthPanel;
import boggle.gui.menu.left.LeftMenu;

/**
 * The "BackToMenu" listener, manage the "onclick" event.
 * @author ferrot leleuj
 */
public class ButtonBackToMenuListener extends Observable implements ActionListener {

	private NorthPanel northPanel;
	
	/**
	 * Add the observer, the game engine.
	 * @param engine
	 */
	public ButtonBackToMenuListener(NorthPanel northPanel, GameEngine engine) {
		this.northPanel = northPanel;
		addObserver(engine);
	}
	
	/**
	 * Send the notification to the observer on click.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == northPanel.getBackToMenu()) {
			setChanged();
			notifyObservers();
		}
	}

}
