package boggle.gui.gameView.centerPanel.leftPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author leleuj
 *
 */
public class LeftPanel extends JPanel {
	
	private JLabel currentPlayer;
	private JPanel panelCurrentPlayer;
	private ScorePanel panelScore;
	
	public LeftPanel() {
		currentPlayer = new JLabel("Captain DUCK");
		panelScore = new ScorePanel();
		panelCurrentPlayer = new JPanel();
		panelCurrentPlayer.add(new JLabel("Player : "));
		panelCurrentPlayer.add(currentPlayer);
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);
		this.add(panelCurrentPlayer, BorderLayout.NORTH);
		this.add(panelScore, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(230,300));
	}

	public JLabel getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer.setText(currentPlayer);
	}
}
