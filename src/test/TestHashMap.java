package test;

import java.util.HashMap;
/**
 * 
 * @author lol
 * <strong> Tuấn đẹp trai quá </strong>
 */
public class TestHashMap {
	public static void main(String[] args) {
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		m.put("t", 2);
		System.out.println(m.get("t"));
		m.put("t", 3);
		System.out.println(m.get("t"));

	}
}
