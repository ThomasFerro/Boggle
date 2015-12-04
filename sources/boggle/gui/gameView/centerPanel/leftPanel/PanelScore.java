package boggle.gui.gameView.centerPanel.leftPanel;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PanelScore extends JPanel {
	
	public PanelScore() {
		Border border = BorderFactory.createTitledBorder("Scores");
		this.setBorder(border);
	}
}
