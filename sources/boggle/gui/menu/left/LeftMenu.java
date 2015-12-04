package boggle.gui.menu.left;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import boggle.gui.menu.left.players.PlayersMenu;

public class LeftMenu extends JPanel{
	public LeftMenu() {
		this.setSize(new Dimension(1000,1000));
		this.add(new PlayersMenu());
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new LeftMenu());
		frame.setVisible(true);
	}
}
