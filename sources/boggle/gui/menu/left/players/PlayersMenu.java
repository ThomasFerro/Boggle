package boggle.gui.menu.left.players;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class PlayersMenu extends JPanel{
	public PlayersMenu() {
		Border border = BorderFactory.createTitledBorder("Players");
		this.setBorder(border);
		this.setSize(new Dimension(100,100));
		
		GridLayout layout = new GridLayout(1, 3);
		layout.setHgap(0);
		this.setLayout(layout);
		this.add(new JLabel("Human :"));
		
		JSpinner spinnerHuman = new JSpinner(new SpinnerNumberModel(1, 0, 5, 1));
		this.add(spinnerHuman);
		
		this.add(new JLabel("IA :")); 
		
		JSpinner spinnerIA = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
		this.add(spinnerIA);
		
		JPanel paneRadio = new JPanel();
		paneRadio.setLayout(new BoxLayout(paneRadio,3));
		JRadioButton radioButtonBasic = new JRadioButton("Basic", true);
		JRadioButton radioButtonAdvanced = new JRadioButton("Advanced");
		
		ButtonGroup group = new ButtonGroup();
		paneRadio.add(radioButtonBasic);
		paneRadio.add(radioButtonAdvanced);
		group.add(radioButtonBasic);
		group.add(radioButtonAdvanced);
		
		this.add(paneRadio);
	}
}
