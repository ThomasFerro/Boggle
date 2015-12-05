package boggle.gui.menu;


import java.awt.Dimension;
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
		layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		//LeftMenu :
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(new LeftMenu(), gbc);
		
		//RightMenu :
		gbc.gridx = 1;
		gbc.ipadx = 100;
		this.add(new RightMenu(new File("config/Highscore")), gbc);
	}

	
	public static void main(String[] args) {
		//Titre pour les menus : Border border = BorderFactory.createTitledBorder("Titre"); panel.setBorder(border);
		JFrame frame = new JFrame("Tests");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(778, 280);
		frame.setResizable(false);
		frame.add(new Menu());
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
