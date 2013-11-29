package logic;


public class Timer {

	private enum Status {
		ON_ZERO, RUNNING, STOPPED
	}

	private long timeStarted = 0;
	private long timeStopped = 0;
	private Status status = Status.ON_ZERO;

	public void start() {
		if (status == Status.STOPPED) {
			reset();
		}
		if (status == Status.ON_ZERO) {
			timeStarted = System.currentTimeMillis();
			status = Status.RUNNING;
		}
	}

	public void stop() {
		if (status == Status.RUNNING) {
			timeStopped = System.currentTimeMillis();
			status = Status.STOPPED;
		}
	}

	public void reset() {
		status = Status.ON_ZERO;
		timeStarted = 0;
		timeStopped = 0;
	}

	public boolean isRunning() {
		return status == Status.RUNNING;
	}

	public long time() {
		switch (status) {
		case ON_ZERO:
			return 0;
		case RUNNING:
			return System.currentTimeMillis() - timeStarted;
		case STOPPED:
			return timeStopped - timeStarted;
		default:
			// should never happen
			return 0;
		}
	}

	public int[] timeFieldsHMS() {
		long diff = time();
		int diffSeconds = (int) (diff / 1000 % 60);
		int diffMinutes = (int) (diff / (60 * 1000) % 60);
		int diffHours = (int) (diff / (60 * 60 * 1000) % 24);
		return new int[] { diffHours, diffMinutes, diffSeconds };
	}

	public String timeFormatted() {
		int[] diff = timeFieldsHMS();
		return "" + (diff[0] < 10 ? "0" + diff[0] : diff[0]) + ":" + (diff[1] < 10 ? "0" + diff[1] : diff[1]) + ":"
				+ (diff[2] < 10 ? "0" + diff[2] : diff[2]);
	}

}
