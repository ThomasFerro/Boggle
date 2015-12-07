package boggle.gui.gameView.centerPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import boggle.gui.gameView.centerPanel.centerPanel.CenterPanel;
import boggle.gui.gameView.centerPanel.leftPanel.LeftPanel;
import boggle.gui.gameView.centerPanel.rightPanel.RightPanel;

public class MiddlePanel extends JPanel {
	
	private LeftPanel leftPanel;
	private CenterPanel centerPanel;
	private RightPanel rightPanel;
	
	public MiddlePanel() {
		leftPanel = new LeftPanel();
		centerPanel = new CenterPanel();
		rightPanel = new RightPanel();
		
		this.setBackground(Color.GREEN);
		this.setLayout(new BorderLayout());
		this.add(leftPanel, BorderLayout.WEST);
		this.add(rightPanel, BorderLayout.EAST);
		this.add(centerPanel, BorderLayout.CENTER);
	}

	public LeftPanel getLeftPanel() {
		return leftPanel;
	}

	public CenterPanel getCenterPanel() {
		return centerPanel;
	}

	public RightPanel getRightPanel() {
		return rightPanel;
	}
}
