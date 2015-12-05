package boggle.gui.menu.left;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import boggle.gui.menu.left.gameSettings.GameSettingsMenu;
import boggle.gui.menu.left.players.PlayersMenu;

public class LeftMenu extends JPanel{
	public LeftMenu() {
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
			
		
		//Label "Boggle":
		JPanel panelLabel = new JPanel();
		JLabel labelBoggle = new JLabel("Boggle");
		labelBoggle.setFont(new Font("Serif", Font.PLAIN, 25));
		panelLabel.add(labelBoggle);
		this.add(panelLabel);
		
		
		gbc.gridy++;
		JPanel panelPlayersGameSettings = new JPanel();
		panelPlayersGameSettings.setLayout(new GridLayout(2,1));
		//PlayerMenu:
		panelPlayersGameSettings.add(new PlayersMenu());
		
		//GameSettingsMenu:
		panelPlayersGameSettings.add(new GameSettingsMenu());
		
		this.add(panelPlayersGameSettings, gbc);
		
		
		//Panel Button:
		gbc.gridy++;
		JPanel panelButtons = new JPanel();
		JButton buttonPlay = new JButton("PLAY");
		JButton buttonHighscore = new JButton("Highscore");
		panelButtons.add(buttonPlay);
		panelButtons.add(buttonHighscore);
		this.add(panelButtons, gbc);		
	}
}
