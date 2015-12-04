package boggle.gui.gameView.centerPanel.rightPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class RightPanel extends JPanel {
	
	private WordPanel wordPanel;
	
	public RightPanel() {
		wordPanel = new WordPanel();
		this.setLayout(new BorderLayout());
		this.add(wordPanel, BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(230,300));
	}
}
