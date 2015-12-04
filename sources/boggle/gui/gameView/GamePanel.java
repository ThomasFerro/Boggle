package boggle.gui.gameView;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import boggle.gui.gameView.centerPanel.MiddlePanel;
import boggle.gui.gameView.northPanel.NorthPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	private NorthPanel northPanel;
	private MiddlePanel centerPanel;
	
	public GamePanel() {
		northPanel = new NorthPanel();
		centerPanel = new MiddlePanel();
		
		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame("Boggle - Game");
		j.setSize(900, 500);
		j.setLocationRelativeTo(null);
		j.setContentPane(new GamePanel());
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}
}
