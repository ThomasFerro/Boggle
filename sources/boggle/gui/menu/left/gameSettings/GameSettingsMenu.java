package boggle.gui.menu.left.gameSettings;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class GameSettingsMenu extends JPanel{
	public GameSettingsMenu() {
		Border border = BorderFactory.createTitledBorder("Game settings");
		this.setBorder(border);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		//RadioButtons:
		gbc.gridx = 0;
		gbc.gridy = 0;
		JPanel paneRadio = new JPanel();
		paneRadio.setLayout(new BoxLayout(paneRadio,3));
		JRadioButton radioButtonRoundLimit = new JRadioButton("Round limit", true);
		JRadioButton radioButtonPointLimit = new JRadioButton("Point limit");

		ButtonGroup group = new ButtonGroup();
		paneRadio.add(radioButtonRoundLimit);
		gbc.gridy = 1;
		paneRadio.add(radioButtonPointLimit);
		group.add(radioButtonRoundLimit);
		group.add(radioButtonPointLimit);
		this.add(paneRadio);
		
		//Round/Point limit:
		gbc.gridx = 1;
		gbc.gridy = 0;
		JPanel panelRoundPointLimit = new JPanel();
		panelRoundPointLimit.add(new JLabel("Round/Point limit :"));
		JSpinner spinnerRoundPointLimit = new JSpinner(new SpinnerNumberModel(5, 1, 1000, 1));
		panelRoundPointLimit.add(spinnerRoundPointLimit);
		this.add(panelRoundPointLimit);
		
		//Time limit:
		gbc.gridx = 2;
		JPanel panelTimeLimit = new JPanel();
		panelTimeLimit.add(new JLabel("Time limit :"));
		JSpinner spinnerTimeLimit = new JSpinner(new SpinnerNumberModel(60, 10, 600, 1));
		panelTimeLimit.add(spinnerTimeLimit);
		this.add(panelTimeLimit);		
	}
}
