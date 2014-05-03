package utils;

public class LoggerUtil {
	private LoggerUtil() {
	}

	public static void log(String msg) {
		System.out.println("<INFO>: " + msg);
	}

	public static void err(String msg) {
		System.err.println("<ERR>: " + msg);
	}

	public static void main(String[] args) {
		LoggerUtil.log("hello");
		LoggerUtil.err("hello");
	}
}
