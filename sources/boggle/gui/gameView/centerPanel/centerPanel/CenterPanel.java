package boggle.gui.gameView.centerPanel.centerPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CenterPanel extends JPanel {
	
	private GridView gridView;
	private JPanel panelSouth;
	
	public CenterPanel() {
		gridView = new GridView();
		panelSouth = new JPanel();
		
		BorderLayout borderLayoutSouth = new BorderLayout();
		borderLayoutSouth.setVgap(10);
		borderLayoutSouth.setHgap(10);
		
		panelSouth.setLayout(borderLayoutSouth);
		panelSouth.add(new JTextField(), BorderLayout.NORTH);
		panelSouth.add(new JButton("Clear"), BorderLayout.WEST);
		panelSouth.add(new JButton("Add word"), BorderLayout.EAST);
		
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(10);
		borderLayout.setHgap(10);
		
		this.setLayout(borderLayout);
		this.add(gridView, BorderLayout.CENTER);
		this.add(panelSouth, BorderLayout.SOUTH);
	}
}
