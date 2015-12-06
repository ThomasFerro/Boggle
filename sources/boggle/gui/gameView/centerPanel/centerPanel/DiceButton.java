package boggle.gui.gameView.centerPanel.centerPanel;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DiceButton extends JButton {

	private String name;
	
	public DiceButton(String name) {
		this.name = name;
		this.setBorder(null);
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = new ImageIcon("img/EnableDice.png").getImage();
        if (img == null) 
        	return;
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        FontMetrics fm = getFontMetrics(g.getFont());
        int width = fm.stringWidth(name);
        int height = fm.getHeight();
        g.drawString(name,  getWidth() / 2 - width / 2, getHeight()/2 + height / 4);
    }
}
