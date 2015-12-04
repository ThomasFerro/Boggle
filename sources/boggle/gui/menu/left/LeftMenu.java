package boggle.gui.menu.left;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import boggle.gui.menu.left.gameSettings.GameSettingsMenu;
import boggle.gui.menu.left.players.PlayersMenu;

public class LeftMenu extends JPanel{
	public LeftMenu() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Label "Boggle":
		gbc.gridx = 0;
		gbc.gridy = 0;
		JPanel panelLabel = new JPanel();
		JLabel labelBoggle = new JLabel("Boggle");
		labelBoggle.setFont(new Font("Serif", Font.PLAIN, 25));
		panelLabel.add(labelBoggle);
		this.add(panelLabel);
		
		//PlayerMenu:
		gbc.gridy = 1;
		this.add(new PlayersMenu());
		
		//GameSettingsMenu:
		gbc.gridy = 2;
		this.add(new GameSettingsMenu());
		
		//Button Play:
		gbc.gridy = 3;
		JButton buttonPlay = new JButton("PLAY");
		this.add(buttonPlay);		
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 1000);
		frame.add(new LeftMenu());
		frame.setVisible(true);
	}
}
