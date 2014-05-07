package utils;

import java.util.Arrays;

public class ArrayUtils {
	public static final String DEFAULT_DELIMITER = ",";
	private ArrayUtils() {}
	public static String join(Object[] list, String delimiter) {
		StringBuffer buf = new StringBuffer();
		buf.append(list[0].toString());
		for (int i = 1; i < list.length; i++) {
			buf.append(delimiter);
			buf.append(list[i]);
		}
		return buf.toString();
	}
	public static String arrayToString(Object[] list) {
		return Arrays.toString(list);
	}
	public static void main(String[] args) {
		Integer[] a = new Integer[]{1, 2, 4, 5};
		String[] s = new String[] {"hello", "world"};
		System.out.println(ArrayUtils.arrayToString(a));
		System.out.println(Arrays.toString(s));
		
	}
}
