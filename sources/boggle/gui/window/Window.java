package boggle.gui.window;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import boggle.gui.gameView.GamePanel;
import boggle.gui.menu.Menu;


public class Window implements Observer {
	private GamePanel gamePanel;
	private Menu menu;
	private JFrame gameFrame;
	
	public Window() {
		gameFrame = new JFrame("Boggle - Game");
	}
	
	public void loadMenu() {
		menu = new Menu(gameFrame);
		gameFrame.getContentPane().removeAll();
		gameFrame.setSize(menu.getWidth(), menu.getHeight());
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setContentPane(menu);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}
	
	public void loadGame() {
		gamePanel = new GamePanel();
		gameFrame.getContentPane().removeAll();
		gameFrame.setSize(gamePanel.getWidth(), gamePanel.getHeight());
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setContentPane(gamePanel);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}

	@Override
	public void update(Observable observable, Object object) {
		// TODO Auto-generated method stub
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public Menu getMenu() {
		return menu;
	}

	public JFrame getGameFrame() {
		return gameFrame;
	}
}