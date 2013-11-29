package main;

import gui.PodcastClockWindow;

public class PodcastClock {

	static PodcastClockWindow window;
	static int lastH = -1;
	static int lastM = -1;
	static int lastS = -1;
	static boolean lastGreyFlag = true;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		window = new PodcastClockWindow("PodcastClock");
		window.setVisible(true);

		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						showClock();
						Thread.sleep(50);
					} catch (Exception e) {
						// ignore
					}
				}
			}
		}.start();

	}

	public static void showClock() {
		int[] diff = window.timer.timeFieldsHMS();
		boolean greyFlag = !window.timer.isRunning();
		if (diff[0] != lastH || diff[1] != lastM || diff[2] != lastS || greyFlag != lastGreyFlag) {
			window.showTime(diff[0], diff[1], diff[2], greyFlag);
			lastH = diff[0];
			lastM = diff[1];
			lastS = diff[2];
			lastGreyFlag = greyFlag;
		}
	}

}
