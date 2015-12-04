package boggle.gui.menu;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import boggle.gui.menu.left.LeftMenu;
import boggle.gui.menu.right.RightMenu;

public class Menu extends JPanel{
	GridBagLayout layout;
	
	public Menu() {	
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		//LeftMenu :
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(new LeftMenu());
		
		//RightMenu :
		gbc.gridx = 1;
		this.add(new RightMenu(new File("config/Highscore")));
	}

	
	public static void main(String[] args) {
		//Titre pour les menus : Border border = BorderFactory.createTitledBorder("Titre"); panel.setBorder(border);
		JFrame frame = new JFrame("Tests");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.add(new Menu());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
