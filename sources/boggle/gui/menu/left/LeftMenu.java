package boggle.gui.menu.left;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import boggle.game.controller.listener.buttonListener.ButtonConfigListener;
import boggle.game.controller.listener.buttonListener.ButtonPlayListener;
import boggle.gui.menu.left.gameSettings.GameSettingsMenu;
import boggle.gui.menu.left.players.PlayersMenu;

public class LeftMenu extends JPanel{
	private PlayersMenu playersMenu;
	private GameSettingsMenu gameSettingsMenu;
	private JFrame frame;
	private JButton buttonPlay;
	private JButton buttonConfig;
	
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
		
		
		//Panel Button:
		gbc.gridy++;
		JPanel panelButtons = new JPanel();
		buttonPlay = new JButton("PLAY");
		buttonConfig = new JButton("CONFIG");
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

	public JButton getButtonPlay() {
		return buttonPlay;
	}

	public JButton getButtonConfig() {
		return buttonConfig;
	}
}
