package boggle.game.model;

import java.util.Observable;

/**
 * The runnable that represent the timer.
 * @author ferrot leleuj
 */
public class Sablier extends Observable implements Runnable {
	private int timeLeft;
	private boolean stop;

	/**
	 * Initialize the "stop" attribute.
	 */
	public Sablier() {
		stop = false;
	}

	/**
	 * Set the stop attribute to "false".
	 */
	public void stop() {
		this.stop = true;
	}
	
	/**
	 * Set the time left to 0.
	 */
	public void setFinished() {
		timeLeft = 0;
	}

	/**
	 * Set the time left.
	 * @param time
	 */
	public void setTimeLeft(int time) {
		timeLeft = time;
	}

	/**
	 * Check if the timer is finished.
	 */
	public boolean isFinished() {
		return timeLeft <= 0;
	}

	/**
	 * Run the timer until the time's up.
	 */
	public void run() {
		while(!stop) {
			System.out.print("");
			while(timeLeft > 0 && !stop) {
				timeLeft--;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				setChanged();
				notifyObservers(timeLeft);
			}
		}
	}

}