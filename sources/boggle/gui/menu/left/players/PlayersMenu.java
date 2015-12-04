package boggle.gui.menu.left.players;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Panel "Human"
		JPanel panelHuman = new JPanel();
		panelHuman.setLayout(new FlowLayout());
		panelHuman.add(new JLabel("Human :"));
		JSpinner spinnerHuman = new JSpinner(new SpinnerNumberModel(1, 0, 5, 1));
		panelHuman.add(spinnerHuman);
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(panelHuman);
		
		//Panel "IA"
		JPanel panelIA = new JPanel();
		panelIA.setLayout(new FlowLayout());
		panelIA.add(new JLabel("IA :")); 
		JSpinner spinnerIA = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
		panelIA.add(spinnerIA);
		gbc.gridx = 1;
		this.add(panelIA);
		
		//RadioButtons
		JPanel paneRadio = new JPanel();
		paneRadio.setLayout(new BoxLayout(paneRadio,3));
		JRadioButton radioButtonBasic = new JRadioButton("Basic", true);
		JRadioButton radioButtonAdvanced = new JRadioButton("Advanced");

		ButtonGroup group = new ButtonGroup();
		gbc.gridx = 2;
		paneRadio.add(radioButtonBasic);
		gbc.gridy = 1;
		paneRadio.add(radioButtonAdvanced);
		group.add(radioButtonBasic);
		group.add(radioButtonAdvanced);
		
		this.add(paneRadio);
	}
}
