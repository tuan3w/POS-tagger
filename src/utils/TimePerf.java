package utils;

/**
 * 
 * @author lol
 * Timer utils
 */
public class TimePerf {

	public static Long t;
	private TimePerf() {}
	
	/**
	 *  set timer
	 */
	public static void tic() {
		t = System.currentTimeMillis();
	}
	
	/**
	 * Get elapsed time
	 * @return elapsed time
	 */
	public static long toc() {
		long t2 = t;
		t = System.currentTimeMillis();
		return t - t2;
		
	}
}
