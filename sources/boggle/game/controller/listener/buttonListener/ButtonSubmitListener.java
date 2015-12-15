package boggle.game.controller.listener.buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import boggle.game.controller.GameEngine;
import boggle.gui.gameView.centerPanel.rightPanel.RightPanel;

/**
 * The "SubmitButton" listener, manage the "onclick" event.
 * @author ferrot leleuj
 */
public class ButtonSubmitListener extends Observable implements ActionListener{
	private RightPanel rightPanel; 
	
	/**
	 * Add the observer, the game engine.
	 * @param engine
	 */
	public ButtonSubmitListener(RightPanel panel ,GameEngine engine) {
		this.rightPanel = panel;
		addObserver(engine);
	}

	/**
	 * Send the notification to the observer on click.
	 */
	public void actionPerformed(ActionEvent e) {
		this.setChanged();
		this.notifyObservers();
	}

}
