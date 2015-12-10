package boggle.gui.gameView.centerPanel.centerPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import boggle.words.Dice;

public class DiceButton extends JButton {

	private final int FONT_SIZE = 25;
	private Dice dice;
	
	public DiceButton(Dice d) {
		super(""+d.getCurrentFace());
		this.dice = d;
		this.setSize(new Dimension(50,50));
		this.setPreferredSize(new Dimension(50,50));
		this.setBorder(null);
	}

	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font(g.getFont().getFontName(), Font.BOLD, FONT_SIZE));
        Image img = null;
        if (dice.isLocked()) {
        	img = new ImageIcon("img/DisableDice.png").getImage();
        } else {
	        if (! dice.isUsed()) {
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
        int width = fm.stringWidth(""+dice.getCurrentFace());
        int height = fm.getHeight();
        g.drawString(""+dice.getCurrentFace(),  getWidth() / 2 - width / 2, getHeight()/2 + height / 4);
    }
	
	public void updateName() {
		
	}

	public String getName() {
		return ""+dice.getCurrentFace();
	}

	public boolean isUsed() {
		return dice.isUsed();
	}

	public Dice getDice() {
		return this.dice;
	}
}
