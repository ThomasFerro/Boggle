package boggle.gui.gameView.centerPanel.leftPanel;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ScorePanel extends JPanel {
	
	public ScorePanel() {
		Border border = BorderFactory.createTitledBorder("Scores");
		this.setBorder(border);
	}
}
