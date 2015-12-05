package boggle.gui.menu.left;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import boggle.game.entity.Human;
import boggle.game.entity.Player;
import boggle.game.modele.PointGame;
import boggle.game.modele.RoundGame;
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
		
		
		//Panel Button:
		gbc.gridy++;
		JPanel panelButtons = new JPanel();
		JButton buttonPlay = new JButton("PLAY");
		buttonPlay.addActionListener(new ButtonPlayListener());
		panelButtons.add(buttonPlay);
		this.add(panelButtons, gbc);		
	}
	
	public PlayersMenu getPlayersMenu() {
		return playersMenu;
	}

	public GameSettingsMenu getGameSettingsMenu() {
		return gameSettingsMenu;
	}

	public class ButtonPlayListener implements ActionListener {
		//TODO : A déplacer pour respecter le MVC
		public void actionPerformed(ActionEvent arg0) {
			//Création des joueurs:
			int nbHuman = (int)getPlayersMenu().getSpinnerHuman().getValue();
			int nbIA = (int)getPlayersMenu().getSpinnerIA().getValue();
			Player[] players = new Player[nbHuman+nbIA];
			int j;
			String name;
			for(j = 0; j < nbHuman; j++) {
				name = JOptionPane.showInputDialog("Player "+(j+1)+":");
				players[j] = new Human(name); 
			}
			for(int k = j; k < players.length; k++) {
				//TODO : Après implémentation des IA, avec vérification de la difficulté
				//name = JOptionPane.showInputDialog("IA "+(k-j+1)+":");
				//players[k] = new IA("PlayerIA"+k);
			}
			
			//Récupération des options de la partie:
			int limit = (int)getGameSettingsMenu().getSpinnerRoundPointLimit().getValue();
			int tlimite = (int)getGameSettingsMenu().getSpinnerTimeLimit().getValue();

			//RoundGame:
			if(getGameSettingsMenu().getRadioButtonRoundLimit().isSelected()) {
				//TODO: Envoyer info de la nouvelle partie au gameEngine
				//Test, à supprimer
				new RoundGame(players, new File("config/regles-4x4.config"), limit);
			}
			//PointGame:
			else {
				//TODO: Envoyer info de la nouvelle partie au gameEngine
				//Test, à supprimer
				new PointGame(players, new File("config/regles-4x4.config"), limit);
			}
		}
		
	}
}
