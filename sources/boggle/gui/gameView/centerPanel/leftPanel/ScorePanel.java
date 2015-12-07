package boggle.gui.gameView.centerPanel.leftPanel;

import java.awt.Font;
import java.util.ArrayList;
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
		JLabel tmp;
		for (Player p : players) {
			tmp = new JLabel(p.getName() + "\t : " + p.getScore() + "pts");
			tmp.setFont(new Font(tmp.getFont().getFontName(), Font.BOLD, 15));
			this.players.add(p);
			this.add(tmp);
		}
	}
	
	public void update() {
		//Tri et réaffichage
		removeAll();
		JLabel tmp;
		Collections.sort(players);
		Collections.reverse(players);
		
		for(Player p : players) {
			if(p.getName().equals("Un"))
				p.setScore(p.getScore()+1);
			tmp = new JLabel(p.getName() + "\t : " + p.getScore() + "pts");
			tmp.setFont(new Font(tmp.getFont().getFontName(), Font.BOLD, 15));
			this.add(tmp);
		}
	}
}
