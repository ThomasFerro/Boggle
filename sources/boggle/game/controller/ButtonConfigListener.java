package boggle.game.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import boggle.gui.menu.left.LeftMenu;

public class ButtonConfigListener implements ActionListener{
	private LeftMenu leftMenu;
	private JFileChooser fileChooser;
	
	public ButtonConfigListener(LeftMenu leftMenu) {
		this.leftMenu = leftMenu;
		fileChooser = new JFileChooser();
	}

	public void actionPerformed(ActionEvent arg0) {
		fileChooser.setCurrentDirectory(new File("config"));
		//Check if there is a file selected:
		int result = fileChooser.showOpenDialog(leftMenu);
		//Check if the file is valid (.config):
		if ((result == JFileChooser.APPROVE_OPTION) && (fileChooser.getSelectedFile().toString().substring(fileChooser.getSelectedFile().toString().lastIndexOf(".") + 1).equals("config"))) {
			//TODO : envoyer fileChooser.getSelectedFile() au gameEngine.
			System.out.println(fileChooser.getSelectedFile());
		}
		else {
			//TODO : Exception pour remplacer le sysout
			System.out.println("Bad file extension, please use .config file.");
		}
	}

}
