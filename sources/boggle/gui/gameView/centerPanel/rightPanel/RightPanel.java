package boggle.gui.gameView.centerPanel.rightPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class RightPanel extends JPanel {
	
	private WordPanel wordPanel;
	private JPanel panelSubmit;
	
	public RightPanel() {
		wordPanel = new WordPanel();
		panelSubmit = new JPanel();
		
		panelSubmit.add(new JButton("Submit"));
		this.setLayout(new BorderLayout());
		this.add(wordPanel, BorderLayout.CENTER);
		this.add(panelSubmit, BorderLayout.SOUTH);
		this.setPreferredSize(new Dimension(230,300));
	}
}
