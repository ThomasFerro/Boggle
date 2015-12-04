package boggle.gui.gameView;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import boggle.gui.gameView.centerPanel.CenterPanel;
import boggle.gui.gameView.northPanel.NorthPanel;
import boggle.gui.gameView.southPanel.SouthPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{
	
	private NorthPanel northPanel;
	private CenterPanel centerPanel;
	private SouthPanel southPanel;
	
	public GamePanel() {
		northPanel = new NorthPanel();
		centerPanel = new CenterPanel();
		southPanel = new SouthPanel();
		
		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		JFrame j = new JFrame("Boggle - Game");
		j.setSize(700, 500);
		j.setLocationRelativeTo(null);
		j.setContentPane(new GamePanel());
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}
}
