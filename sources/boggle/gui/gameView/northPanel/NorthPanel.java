package boggle.gui.gameView.northPanel;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class NorthPanel extends JPanel {
	
	private JButton backToMenu;
	private TimerPanel timer;
	
	public NorthPanel() {
		this.setLayout(new GridLayout(1,3));
		
		backToMenu = new JButton("back to menu");
		timer = new TimerPanel();

		this.add(new JLabel(""));
		this.add(timer);
		this.add(backToMenu);
	}

}
