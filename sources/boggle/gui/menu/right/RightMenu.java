package boggle.gui.menu.right;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;

import boggle.gui.menu.right.highscore.HighscoreMenu;
import boggle.gui.menu.right.highscore.HighscoreModel;

public class RightMenu extends JPanel{
	public RightMenu(File f) {
		super(new BorderLayout());
		
		JPanel panelLabel = new JPanel();
		JLabel labelHighscore = new JLabel("Highscore");
		panelLabel.add(labelHighscore);
		
		this.add(panelLabel, BorderLayout.NORTH);
		this.add(new HighscoreMenu(new HighscoreModel(f)), BorderLayout.CENTER);
	}
}
