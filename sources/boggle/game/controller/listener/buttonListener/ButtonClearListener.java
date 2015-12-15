package boggle.game.controller.listener.buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import boggle.game.controller.GameEngine;
import boggle.gui.gameView.centerPanel.centerPanel.DiceButton;

public class ButtonClearListener extends Observable implements ActionListener {
	private GameEngine engine;
	
	public ButtonClearListener(GameEngine engine) {
		addObserver(engine);
		this.engine = engine;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		setChanged();
		notifyObservers();
	}

}