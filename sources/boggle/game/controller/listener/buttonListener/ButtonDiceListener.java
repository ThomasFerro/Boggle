package boggle.game.controller.listener.buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import boggle.game.controller.GameEngine;
import boggle.gui.gameView.centerPanel.centerPanel.DiceButton;
import boggle.gui.gameView.northPanel.NorthPanel;

public class ButtonDiceListener extends Observable implements ActionListener {
	private GameEngine engine;
	
	public ButtonDiceListener(GameEngine engine) {
		//addObserver(engine);
		this.engine = engine;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		setChanged();
		notifyObservers(((DiceButton)e.getSource()).getDice());
	}

}
