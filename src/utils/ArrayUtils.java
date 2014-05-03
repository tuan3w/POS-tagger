package utils;

import java.util.Arrays;

public class ArrayUtils {
	public static final String DEFAULT_DELIMITER = ",";
	private ArrayUtils() {}
	public static String arrayToString(Object[] list, String delimiter) {
		StringBuffer buf = new StringBuffer();
		buf.append("[");
		buf.append(list[0].toString());
		for (int i = 1; i < list.length; i++) {
			buf.append(delimiter + " ");
			buf.append(list[i]);
		}
		buf.append("]");
		return buf.toString();
	}
	public static String arrayToString(Object[] list) {
		return ArrayUtils.arrayToString(list, DEFAULT_DELIMITER);
	}
	public static void main(String[] args) {
		Integer[] a = new Integer[]{1, 2, 4, 5};
		String[] s = new String[] {"hello", "world"};
		System.out.println(ArrayUtils.arrayToString(a));
		System.out.println(Arrays.toString(s));
		
	}
}
