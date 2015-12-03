package boggle.gui.menu;


import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import boggle.gui.menu.right.RightMenu;
import boggle.gui.menu.right.highscore.HighscoreMenu;
import boggle.gui.menu.right.highscore.HighscoreModel;

public class Menu extends JPanel{
	GridBagLayout layout;
	
	public Menu() {	
		layout = new GridBagLayout();
		this.setLayout(layout);
	}

	
	public static void main(String[] args) {
		//Titre pour les menus : Border border = BorderFactory.createTitledBorder("Titre"); panel.setBorder(border);
		JFrame frame = new JFrame("Tests");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 200);
		frame.add(new RightMenu(new File("config/Highscore")));
		frame.setVisible(true);
	}
}
