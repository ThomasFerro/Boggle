package boggle.gui.gameView.centerPanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import boggle.gui.gameView.centerPanel.gridView.GridView;
import boggle.gui.gameView.centerPanel.leftPanel.LeftPanel;
import boggle.gui.gameView.centerPanel.rightPanel.RightPanel;

public class MiddlePanel extends JPanel {
	
	private LeftPanel leftPanel;
	private GridView gridView;
	private RightPanel rightPanel;
	
	public MiddlePanel() {
		leftPanel = new LeftPanel();
		gridView = new GridView();
		rightPanel = new RightPanel();
		this.setBackground(Color.GREEN);
		this.setLayout(new BorderLayout());
		this.add(leftPanel, BorderLayout.WEST);
		this.add(rightPanel, BorderLayout.EAST);
		this.add(gridView, BorderLayout.CENTER);
	}
}
