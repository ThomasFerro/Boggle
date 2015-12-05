package boggle.gui.menu;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

import boggle.gui.menu.left.LeftMenu;
import boggle.gui.menu.right.RightMenu;

public class Menu extends JPanel{
	private GridBagLayout layout;
	private JFrame frame;
	
	public Menu(JFrame frame) {	
		this.frame = frame;
		layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		//LeftMenu :
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(new LeftMenu(frame), gbc);
		
		//RightMenu :
		gbc.gridx = 1;
		gbc.insets = new Insets(0,10,0,0);
		this.add(new RightMenu(new File("config/Highscore")), gbc);
	}

	
	public static void main(String[] args) {
		//Titre pour les menus : Border border = BorderFactory.createTitledBorder("Titre"); panel.setBorder(border);
		JFrame frame = new JFrame("Tests");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(778, 280);
		frame.setResizable(false);
		frame.add(new Menu(frame));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
