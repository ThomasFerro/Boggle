package boggle.gui.gameView.northPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class NorthPanel extends JPanel {
	
	private JPanel backToMenuPanel;
	private JButton backToMenu;
	private TimerPanel timer;
	
	public NorthPanel() {
		this.setLayout(new GridLayout(1,3));
		backToMenuPanel = new JPanel(new BorderLayout());
		backToMenu = new JButton("back to menu");
		timer = new TimerPanel();
		
		backToMenuPanel.add(backToMenu, BorderLayout.EAST);

		this.add(new JLabel(""));
		this.add(timer);
		this.add(backToMenuPanel);
	}

}
