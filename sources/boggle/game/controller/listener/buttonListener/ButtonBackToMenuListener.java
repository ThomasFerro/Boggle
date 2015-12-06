package boggle.game.controller.listener.buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import boggle.game.controller.GameEngine;
import boggle.gui.gameView.northPanel.NorthPanel;
import boggle.gui.menu.left.LeftMenu;

public class ButtonBackToMenuListener extends Observable implements ActionListener {

	private NorthPanel northPanel;
	public ButtonBackToMenuListener(NorthPanel northPanel, GameEngine engine) {
		this.northPanel = northPanel;
		addObserver(engine);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == northPanel.getBackToMenu()) {
			setChanged();
			notifyObservers();
		}
	}

}
