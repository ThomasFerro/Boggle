package boggle.game.controller.listener.buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;

import javax.swing.JFileChooser;

import boggle.game.controller.GameEngine;
import boggle.gui.menu.left.LeftMenu;

/**
 * The "ConfigButton" listener, manage the "onclick" event.
 * @author ferrot leleuj
 */
public class ButtonConfigListener extends Observable implements ActionListener{
	private LeftMenu leftMenu;
	private JFileChooser fileChooser;
	
	/**
	 * Add the observer, the game engine.
	 * @param engine
	 */
	public ButtonConfigListener(LeftMenu leftMenu, GameEngine engine) {
		this.leftMenu = leftMenu;
		addObserver(engine);
		fileChooser = new JFileChooser();
	}

	/**
	 * Send the notification to the observer on click.
	 */
	public void actionPerformed(ActionEvent arg0) {
		fileChooser.setCurrentDirectory(new File("config"));
		//Check if there is a file selected:
		int result = fileChooser.showOpenDialog(leftMenu);
		//Check if the file is valid (.config):
		if ((result == JFileChooser.APPROVE_OPTION) && (fileChooser.getSelectedFile().toString().substring(fileChooser.getSelectedFile().toString().lastIndexOf(".") + 1).equals("config"))) {
			//Send the result to the gameEngine
			setChanged();
			notifyObservers(fileChooser.getSelectedFile());
		}
		else {
			//TODO : Exception pour remplacer le sysout
			System.out.println("Bad file extension, please use .config file.");
		}
	}

}
