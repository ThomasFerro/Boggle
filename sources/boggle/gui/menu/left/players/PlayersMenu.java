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
	private JSpinner spinnerHuman;
	private JSpinner spinnerIA;
	private JRadioButton radioButtonBasic;
	private JRadioButton radioButtonAdvanced;
	
	public PlayersMenu() {
		Border border = BorderFactory.createTitledBorder("Players");
		this.setBorder(border);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//Panel "Human"
		JPanel panelHuman = new JPanel();
		panelHuman.setLayout(new FlowLayout());
		panelHuman.add(new JLabel("Human :"));
		spinnerHuman = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
		panelHuman.add(spinnerHuman);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		this.add(panelHuman, gbc);
		
		//Panel "IA"
		JPanel panelIA = new JPanel();
		panelIA.setLayout(new FlowLayout());
		panelIA.add(new JLabel("IA :")); 
		spinnerIA = new JSpinner(new SpinnerNumberModel(0, 0, 5, 1));
		panelIA.add(spinnerIA);
		gbc.gridx++;
		this.add(panelIA, gbc);
		
		//RadioButtons
		JPanel paneRadio = new JPanel();
		paneRadio.setLayout(new BoxLayout(paneRadio,3));
		radioButtonBasic = new JRadioButton("Basic", true);
		radioButtonAdvanced = new JRadioButton("Advanced");

		ButtonGroup group = new ButtonGroup();
		gbc.gridx++;
		gbc.gridheight = 1;
		paneRadio.add(radioButtonBasic);
		gbc.gridy++;
		paneRadio.add(radioButtonAdvanced);
		group.add(radioButtonBasic);
		group.add(radioButtonAdvanced);
		
		this.add(paneRadio, gbc);
	}

	public JSpinner getSpinnerHuman() {
		return spinnerHuman;
	}

	public JSpinner getSpinnerIA() {
		return spinnerIA;
	}

	public JRadioButton getRadioButtonBasic() {
		return radioButtonBasic;
	}

	public JRadioButton getRadioButtonAdvanced() {
		return radioButtonAdvanced;
	}
}
