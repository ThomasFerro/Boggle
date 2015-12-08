package boggle.gui.gameView.northPanel;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimerPanel extends JPanel{
	
	private JLabel time;
	
	public TimerPanel() {
		this.setOpaque(false);
		time = new JLabel("30");
		this.add(new JLabel("Time : "));
		this.add(time);
	}
	
	public int getTimer() {
		return Integer.parseInt(time.getText());
	}
	
	public void incTimer() {
		time.setText("" + (getTimer() + 1));
	}
	
	public void decTimer() {
		time.setText("" + (getTimer() - 1));
	}
	
	public void setTime(int time) {
		this.time.setText("" + time);
	}
}
