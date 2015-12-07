package boggle.game.controller.listener.buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import boggle.game.controller.GameEngine;
import boggle.gui.gameView.centerPanel.rightPanel.RightPanel;

public class ButtonSubmitListener extends Observable implements ActionListener{
	private RightPanel rightPanel; 
	
	public ButtonSubmitListener(RightPanel panel ,GameEngine engine) {
		this.rightPanel = panel;
		addObserver(engine);
	}

	public void actionPerformed(ActionEvent e) {
		this.setChanged();
		this.notifyObservers();
	}

}
