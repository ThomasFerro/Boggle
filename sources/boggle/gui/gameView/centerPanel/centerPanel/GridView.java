package boggle.gui.gameView.centerPanel.centerPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GridView extends JPanel {
	
	private DiceButton [][] buttons;
	
	public GridView() {
		buttons = new DiceButton[4][4];
		GridLayout gridLayout = new GridLayout(4, 4);
		gridLayout.setHgap(15);
		gridLayout.setVgap(15);
		this.setLayout(gridLayout);
		
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				buttons[i][j] = new DiceButton("" + (char)(65+i+j));
				add(buttons[i][j]);
			}
		}
	}

	public JButton[][] getButtons() {
		return buttons;
	}

	public void setButtons(DiceButton[][] buttons) {
		this.buttons = buttons;
	}
}
