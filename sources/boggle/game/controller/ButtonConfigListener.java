package boggle.game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Observable;

import javax.swing.JFileChooser;

import boggle.gui.menu.left.LeftMenu;

public class ButtonConfigListener extends Observable implements ActionListener{
	private LeftMenu leftMenu;
	private JFileChooser fileChooser;
	
	public ButtonConfigListener(LeftMenu leftMenu, GameEngine engine) {
		this.leftMenu = leftMenu;
		addObserver(engine);
		fileChooser = new JFileChooser();
	}

	public void actionPerformed(ActionEvent arg0) {
		fileChooser.setCurrentDirectory(new File("config"));
		//Check if there is a file selected:
		int result = fileChooser.showOpenDialog(leftMenu);
		//Check if the file is valid (.config):
		if ((result == JFileChooser.APPROVE_OPTION) && (fileChooser.getSelectedFile().toString().substring(fileChooser.getSelectedFile().toString().lastIndexOf(".") + 1).equals("config"))) {
			//TODO : envoyer fileChooser.getSelectedFile() au gameEngine.
			setChanged();
			notifyObservers();
			
			System.out.println(fileChooser.getSelectedFile());
		}
		else {
			//TODO : Exception pour remplacer le sysout
			System.out.println("Bad file extension, please use .config file.");
		}
	}

}
