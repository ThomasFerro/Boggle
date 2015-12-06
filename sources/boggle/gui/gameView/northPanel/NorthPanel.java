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

	public JPanel getBackToMenuPanel() {
		return backToMenuPanel;
	}

	public void setBackToMenuPanel(JPanel backToMenuPanel) {
		this.backToMenuPanel = backToMenuPanel;
	}

	public JButton getBackToMenu() {
		return backToMenu;
	}

	public void setBackToMenu(JButton backToMenu) {
		this.backToMenu = backToMenu;
	}

	public TimerPanel getTimer() {
		return timer;
	}

	public void setTimer(TimerPanel timer) {
		this.timer = timer;
	}
}
