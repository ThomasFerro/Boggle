package boggle.game.controller.listener.buttonListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JOptionPane;

import boggle.game.controller.GameEngine;
import boggle.game.controller.gameConfig.GameConfig;
import boggle.game.entity.Human;
import boggle.game.entity.Player;
import boggle.gui.menu.left.LeftMenu;

/**
 * The "PlayButton" listener, manage the "onclick" event.
 * @author ferrot leleuj
 */
public class ButtonPlayListener extends Observable implements ActionListener {
	private LeftMenu leftMenu;
	
	/**
	 * Add the observer, the game engine.
	 * @param engine
	 */
	public ButtonPlayListener(LeftMenu leftMenu, GameEngine engine) {
		this.leftMenu = leftMenu;
		addObserver(engine);
	}
	
	/**
	 * Send the notification to the observer on click. Sending the entire game settings.
	 */
	public void actionPerformed(ActionEvent arg0) {
		//Création des joueurs:
		int nbHuman = (int)leftMenu.getPlayersMenu().getSpinnerHuman().getValue();
		int nbIA = (int)leftMenu.getPlayersMenu().getSpinnerIA().getValue();
		Player[] players = new Player[nbHuman];
		int j;
		String name;
		for(j = 0; j < nbHuman; j++) {
			name = JOptionPane.showInputDialog("Player "+(j+1)+":");
			if (name != null) {
				players[j] = new Human(name);
			} else {
				players[j] = new Human("Player " + (j + 1));
			}
		}
		for(int k = j; k < players.length; k++) {
			//TODO : Après implémentation des IA, avec vérification de la difficulté
			//name = JOptionPane.showInputDialog("IA "+(k-j+1)+":");
			//players[k] = new IA("PlayerIA"+k);
		}
		
		//Récupération des options de la partie:
		int limit = (int)leftMenu.getGameSettingsMenu().getSpinnerRoundPointLimit().getValue();
		int tlimit = (int)leftMenu.getGameSettingsMenu().getSpinnerTimeLimit().getValue();

		//Send the result to the engine
		//RoundGame:
		if(leftMenu.getGameSettingsMenu().getRadioButtonRoundLimit().isSelected()) {
			setChanged();
			notifyObservers(new GameConfig(players, limit, tlimit, "RoundGame"));
		}
		//PointGame:
		else {
			setChanged();
			notifyObservers(new GameConfig(players, limit, tlimit, "PointGame"));
		}
	}
	
}