package boggle.gui.menu.left;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import boggle.gui.menu.controller.ButtonConfigListener;
import boggle.gui.menu.controller.ButtonPlayListener;
import boggle.gui.menu.left.gameSettings.GameSettingsMenu;
import boggle.gui.menu.left.players.PlayersMenu;

public class LeftMenu extends JPanel{
	private PlayersMenu playersMenu;
	private GameSettingsMenu gameSettingsMenu;
	private JFrame frame;
	
	public LeftMenu(JFrame frame) {
		this.frame = frame;
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
		playersMenu = new PlayersMenu();
		panelPlayersGameSettings.add(playersMenu);
		
		//GameSettingsMenu:
		gameSettingsMenu = new GameSettingsMenu();
		panelPlayersGameSettings.add(gameSettingsMenu);
		this.add(panelPlayersGameSettings, gbc);
		
		//TODO : Manque le fichier de config !! 
		
		
		//Panel Button:
		gbc.gridy++;
		JPanel panelButtons = new JPanel();
		JButton buttonPlay = new JButton("PLAY");
		buttonPlay.addActionListener(new ButtonPlayListener(this));
		JButton buttonConfig = new JButton("CONFIG");
		buttonConfig.addActionListener(new ButtonConfigListener(this));
		panelButtons.add(buttonPlay);
		panelButtons.add(buttonConfig);
		this.add(panelButtons, gbc);		
	}
	
	public PlayersMenu getPlayersMenu() {
		return playersMenu;
	}

	public GameSettingsMenu getGameSettingsMenu() {
		return gameSettingsMenu;
	}
}
