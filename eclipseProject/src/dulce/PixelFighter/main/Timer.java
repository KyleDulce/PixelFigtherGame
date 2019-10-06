package dulce.PixelFighter.main;

public class Timer {
	
	private double timeStart;
	private double timeNow;
	private double passed;
	private double unprocessedTime;
	private double framePerTime;
	private double frameCap;
	private boolean canRender;
	private int frames;
	
	public Timer() {
		timeStart = getTime();
		frameCap = 1d / 60d;
		unprocessedTime = 0;
	}
	
	public Timer(boolean runtime) {
		if(runtime = true) {
			timeStart = getTime();
		}
		else {
			timeStart = getTime();
			frameCap = 1d / 60d;
			unprocessedTime = 0;
		}
	}
	
	public double getTime() {
		return (double)System.nanoTime() / (double)1000000000L;
	}
	
	public void update() {
		canRender = false;
		
		timeNow = getTime();
		passed = timeNow - timeStart;
		unprocessedTime += passed;
		
		framePerTime += passed;
		
		timeStart = timeNow;
	}
	
	public int[] runGet() {
		timeNow = getTime();
		double runTime = timeNow - timeStart;
		int[] timeFormat = new int[3];
		timeFormat[0] = (int) (runTime / 360d);
		runTime -= (double) timeFormat[0];
		timeFormat[1] = (int) (runTime / 60d);
		runTime -= (double) timeFormat[1];
		timeFormat[2] = (int) runTime;
		return timeFormat;
	}
	
	public boolean shouldProcess() {
		return unprocessedTime >= frameCap;
	}
	
	public void process() {
		unprocessedTime -= frameCap;
		canRender = true;

		if(framePerTime >= 1.0) {
			framePerTime = 0;
			System.out.println("FPS " + frames);
			frames = 0;
		}
	}
	
	public boolean getCanRender() {return canRender;}
	public void addFrame() {frames++;}
	
}
