package boggle.gui.gameView.centerPanel.leftPanel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import boggle.game.entity.Player;

public class ScorePanel extends JPanel {
	
	private List<Player> players;
	
	public ScorePanel() {
		players = new ArrayList<Player>();
		Border border = BorderFactory.createTitledBorder("Scores");
		this.setBorder(border);
		this.setLayout(new BoxLayout(this, 1));
	}
	
	public void init(Player[] players) {
		this.players = Arrays.asList(players);
		update();
	}
	
	public void update() {
		//Tri et réaffichage
		removeAll();
		JLabel scoreLabelTmp;
		Collections.sort(players, Collections.reverseOrder());
		
		for(Player p : players) {
			scoreLabelTmp = new JLabel(p.getName() + "\t : " + p.getScore() + "pts");
			scoreLabelTmp.setFont(new Font(scoreLabelTmp.getFont().getFontName(), Font.BOLD, 15));
			this.add(scoreLabelTmp);
		}
	}
}
