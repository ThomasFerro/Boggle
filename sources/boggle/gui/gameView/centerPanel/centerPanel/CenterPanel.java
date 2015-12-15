package boggle.gui.gameView.centerPanel.centerPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CenterPanel extends JPanel {
	
	private GridView gridView;
	private JPanel southPanel;
	private JButton buttonAddWord;
	private JButton buttonClear;
	private JTextField wordTextField;
	
	public CenterPanel() {
		gridView = new GridView();
		southPanel = new JPanel();
		wordTextField = new JTextField();
		
		
		BorderLayout borderLayoutSouth = new BorderLayout();
		borderLayoutSouth.setVgap(10);
		borderLayoutSouth.setHgap(10);
		
		buttonAddWord = new JButton("Add word");
		buttonClear = new JButton("Clear");
		
		southPanel.setLayout(borderLayoutSouth);
		southPanel.add(wordTextField, BorderLayout.NORTH);
		southPanel.add(buttonClear, BorderLayout.WEST);
		southPanel.add(buttonAddWord, BorderLayout.EAST);
		
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(10);
		borderLayout.setHgap(10);
		
		this.setLayout(borderLayout);
		this.add(gridView, BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
	}

	public GridView getGridView() {
		return gridView;
	}

	public void setGridView(GridView gridView) {
		this.gridView = gridView;
	}

	public JPanel getSouthPanel() {
		return southPanel;
	}

	public void setSouthPanel(JPanel southPanel) {
		this.southPanel = southPanel;
	}

	public JButton getButtonAddWord() {
		return buttonAddWord;
	}

	public JButton getButtonClear() {
		return buttonClear;
	}

	public JTextField getWordTextField() {
		return wordTextField;
	}
}
