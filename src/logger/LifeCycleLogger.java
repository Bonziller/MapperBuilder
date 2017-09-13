package logger;

public class LifeCycleLogger {

	private int currentValue = 0;
	private int maxValue = 0;
	private String step;

	public LifeCycleLogger() {

	}

	public LifeCycleLogger(int currentValue, int maxValue, String step) {
		super();
		this.currentValue = currentValue;
		this.maxValue = maxValue;
		this.step = step;
	}

	public int getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(int currentValue) {
		this.currentValue = currentValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	public void addCurrentVal() {
		if (this.currentValue + 1 <= this.maxValue)
			this.currentValue++;
	}

	public boolean isMaximumReached() {
		return currentValue == maxValue;
	}

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public void logCurrent() {
		System.out.printf("Current Step: %s, CurrentValue: %s, MaxValue: %s%n", this.step, this.currentValue,
				this.maxValue);
	}

}
