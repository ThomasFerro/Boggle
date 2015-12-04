package boggle.gui.window;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import boggle.gui.gameView.GamePanel;


public class Window implements Observer {
	
	public Window() {
		JFrame j = new JFrame("Boggle - Game");
		
		GamePanel gamePanel = new GamePanel();
		j.setSize(gamePanel.getWidth(), gamePanel.getHeight());
		j.setLocationRelativeTo(null);
		j.setContentPane(gamePanel);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
	}

	@Override
	public void update(Observable observable, Object object) {
		// TODO Auto-generated method stub
	}
	
	public static void main(String[] args) {
		new Window();
	}
}
