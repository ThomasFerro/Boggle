package boggle.gui.gameView.centerPanel.rightPanel;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class WordPanel extends JPanel {

	public WordPanel() {
		Border border = BorderFactory.createTitledBorder("Words");
		this.setBorder(border);
	}
}
