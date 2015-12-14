package boggle.gui.gameView.centerPanel.rightPanel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class WordPanel extends JPanel {
	private List<JLabel> words;

	public WordPanel() {
		words = new ArrayList<JLabel>();
		Border border = BorderFactory.createTitledBorder("Words");
		this.setBorder(border);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	
	public void update() {
		removeAll();
		for (JLabel w : words) {
			add(w);
		}
		this.revalidate();
		this.repaint();
	}
	
	public void add(String word) {
		words.add(new JLabel(word));
	}
	
	public List<JLabel> getWords() {
		return words;
	}

	public void clear() {
		removeAll();
		words.clear();
	}
}