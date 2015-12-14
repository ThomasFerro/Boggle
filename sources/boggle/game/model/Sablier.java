package boggle.game.model;

import java.util.Observable;

public class Sablier extends Observable implements Runnable {
	private int timeLeft;
	private boolean stop;

	public Sablier() {
		stop = false;
	}

	public void stop() {
		this.stop = true;
	}

	public void setFinished() {
		timeLeft = 0;
	}

	public void setTimeLeft(int time) {
		timeLeft = time;
	}

	public boolean isFinished() {
		return timeLeft <= 0;
	}

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