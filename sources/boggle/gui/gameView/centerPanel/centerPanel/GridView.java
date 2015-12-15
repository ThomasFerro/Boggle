package boggle.gui.gameView.centerPanel.centerPanel;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import boggle.words.DiceGrid;

public class GridView extends JPanel{
	
	private DiceButton [][] buttons;
	
	public void init(DiceGrid diceGrid) {
		removeAll();
		int lengthX = diceGrid.getGrid().length;
		int lengthY = diceGrid.getGrid()[0].length;
		
		buttons = new DiceButton[lengthX][lengthY];
		GridLayout gridLayout = new GridLayout(lengthX, lengthY);
		gridLayout.setHgap(15);
		gridLayout.setVgap(15);
		this.setLayout(gridLayout);
		
		for (int i = 0; i < lengthX; i++) {
			for (int j = 0; j < lengthY; j++) {
				buttons[i][j] = new DiceButton(diceGrid.getGrid()[i][j]);
				buttons[i][j].getDice().setLocked(false);
				buttons[i][j].getDice().setUsed(false);
				buttons[i][j].setEnabled(true);
				add(buttons[i][j]);
			}
		}
	}
	
	public void resetGrid() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				buttons[i][j].getDice().setLocked(false);
				buttons[i][j].getDice().setUsed(false);
				buttons[i][j].setEnabled(true);
			}
		}
	}
	
	public void update() {
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[0].length; j++) {
				if(buttons[i][j].getDice().isLocked() || buttons[i][j].getDice().isUsed())
					buttons[i][j].setEnabled(false);
				else
					buttons[i][j].setEnabled(true);
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
