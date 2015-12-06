package boggle.gui.gameView.centerPanel.centerPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DiceButton extends JButton {

	private final int FONT_SIZE = 25;
	private String name;
	private boolean used;
	private boolean locked;
	
	public DiceButton(String name) {
		super(name);
		this.setSize(new Dimension(50,50));
		this.setPreferredSize(new Dimension(50,50));
		this.name = name;
		this.used = false;
		this.locked = false;
	}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, FONT_SIZE));
        Image img = null;
        if (locked) {
        	img = new ImageIcon("img/DisableDice.png").getImage();
        } else {
	        if (! used) {
	        	img = new ImageIcon("img/EnableDice.png").getImage();
	        }
	        else {
	        	img = new ImageIcon("img/BusyDice.png").getImage();
	        }
        }
        if (img == null) 
        	return;
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        FontMetrics fm = getFontMetrics(g.getFont());
        int width = fm.stringWidth(name);
        int height = fm.getHeight();
        g.drawString(name,  getWidth() / 2 - width / 2, getHeight()/2 + height / 4);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLock(boolean locked) {
		this.locked = locked;
	}
}
