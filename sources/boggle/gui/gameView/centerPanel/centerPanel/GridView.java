package boggle.gui.gameView.centerPanel.centerPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GridView extends JPanel {
	
	List<JButton> buttons;
	
	public GridView() {
		GridLayout gridLayout = new GridLayout(4, 4);
		gridLayout.setHgap(15);
		gridLayout.setVgap(15);
		this.setLayout(gridLayout);
		
		buttons = new ArrayList<JButton>();
		for (int i = 0; i < 16; i++) {
			this.add(new DiceButton("" + (char)(65+i)));
		}
	}
}
