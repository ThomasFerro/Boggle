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
		currentPlayer = new JLabel();
		panelScore = new ScorePanel();
		panelCurrentPlayer = new JPanel();
		panelCurrentPlayer.add(new JLabel("Player : "));
		currentPlayer.setForeground(Color.RED);
		panelCurrentPlayer.add(currentPlayer);


		this.setLayout(new BorderLayout());
		this.setBackground(Color.GRAY);
		this.add(panelCurrentPlayer, BorderLayout.NORTH);
		this.add(panelScore, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(250,300));
	}

	public JLabel getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(String currentPlayer) {
		this.currentPlayer.setText(currentPlayer);
	}

	public JPanel getPanelCurrentPlayer() {
		return panelCurrentPlayer;
	}

	public void setPanelCurrentPlayer(JPanel panelCurrentPlayer) {
		this.panelCurrentPlayer = panelCurrentPlayer;
	}

	public ScorePanel getPanelScore() {
		return panelScore;
	}

	public void setPanelScore(ScorePanel panelScore) {
		this.panelScore = panelScore;
	}

	public void setCurrentPlayer(JLabel currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
}
