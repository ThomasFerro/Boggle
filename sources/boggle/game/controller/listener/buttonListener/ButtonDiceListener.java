package boggle.game.controller.listener.buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import boggle.game.controller.GameEngine;
import boggle.gui.gameView.centerPanel.centerPanel.DiceButton;
import boggle.gui.gameView.northPanel.NorthPanel;

/**
 * The "DiceButton" listener, manage the "onclick" event on any dice.
 * @author ferrot leleuj
 */
public class ButtonDiceListener extends Observable implements ActionListener {
	
	/**
	 * Add the observer, the game engine.
	 * @param engine
	 */
	public ButtonDiceListener(GameEngine engine) {
		addObserver(engine);
	}

	/**
	 * Send the notification to the observer on click.
	 */
	public void actionPerformed(ActionEvent e) {
		setChanged();
		notifyObservers(((DiceButton)e.getSource()).getDice());
	}

}
