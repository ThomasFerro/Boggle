package boggle.gui.gameView.centerPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import boggle.gui.gameView.centerPanel.leftPanel.LeftPanel;

public class CenterPanel extends JPanel {
	
	private LeftPanel leftPanel;
	
	public CenterPanel() {
		leftPanel = new LeftPanel();
		
		this.setBackground(Color.GREEN);
		this.setLayout(new BorderLayout());
		this.add(leftPanel, BorderLayout.WEST);
	}
}
