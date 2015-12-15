package boggle.game.controller.listener.buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import boggle.game.controller.GameEngine;
import boggle.gui.gameView.centerPanel.centerPanel.DiceButton;

/**
 * The "ClearButton" listener, manage the "onclick" event.
 * @author ferrot leleuj
 */
public class ButtonClearListener extends Observable implements ActionListener {
	
	/**
	 * Add the observer, the game engine.
	 * @param engine
	 */
	public ButtonClearListener(GameEngine engine) {
		addObserver(engine);
	}
	
	/**
	 * Send the notification to the observer on click.
	 */
	public void actionPerformed(ActionEvent e) {
		setChanged();
		notifyObservers();
	}

}