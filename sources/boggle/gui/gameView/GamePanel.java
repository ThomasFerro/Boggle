package boggle.gui.gameView;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import boggle.gui.gameView.centerPanel.MiddlePanel;
import boggle.gui.gameView.northPanel.NorthPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {

	private NorthPanel northPanel;
	private MiddlePanel centerPanel;

	public GamePanel() {
		northPanel = new NorthPanel();
		centerPanel = new MiddlePanel();

		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.setSize(900, 500);
	}

	public NorthPanel getNorthPanel() {
		return northPanel;
	}

	public void setNorthPanel(NorthPanel northPanel) {
		this.northPanel = northPanel;
	}

	public MiddlePanel getCenterPanel() {
		return centerPanel;
	}

	public void setCenterPanel(MiddlePanel centerPanel) {
		this.centerPanel = centerPanel;
	}
}