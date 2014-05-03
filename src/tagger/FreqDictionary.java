package tagger;

import java.util.Hashtable;

public class FreqDictionary extends Hashtable<String, Integer> {
	private double eps = Math.exp(-200);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int N;

	public FreqDictionary() {
		N = 0;
	}

	public void inc(String key, int value) {
		int v = this._get(key);
		this.put(key, v + value);

	}

	public void inc(String key) {
		this.inc(key, 1);
	}

	@Override
	public synchronized Integer put(String key, Integer value) {
		N = N - this._get(key) + value;
		return super.put(key, value);
	}

	public double freq(String key) {
		if (N == 0)
			return eps;
		else
			return this._get(key) * 1.0 / N;
	}

	private int _get(String key) {
		Integer v = this.get(key);
		if (v == null)
			return 0;
		else
			return v;
	}

	@Override
	public synchronized void clear() {
		// TODO Auto-generated method stub
		N = 0;
		super.clear();
	}

	@Override
	public synchronized Integer remove(Object key) {
		// TODO Auto-generated method stub
		N -= this._get((String) key);
		return super.remove(key);
	}

	public int getCount(String s) {
		return this._get(s);
	}

	public int N() {
		return N;
	}

	public static void main(String[] args) {
		FreqDictionary dic = new FreqDictionary();
		dic.inc("hello");
		dic.inc("hehe", 3);
		for (String s : dic.keySet()) {
			System.out.println("f(" + s + ")=" + dic.freq(s));
		}
		dic.clear();
		System.out.println("__________");
		dic.put("hello", 2);
		for (String s : dic.keySet()) {
			System.out.println("f(" + s + ")=" + dic.freq(s));
		}
	}
}
