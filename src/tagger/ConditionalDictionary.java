package tagger;

import java.util.Hashtable;
import java.util.Set;

public class ConditionalDictionary {
	public static final double eps = Math.exp(-200);

	public static final String SEPARATOR = " / ";
	private FreqDictionary prior, p;
	private Hashtable<String, Double> prob;

	private boolean computed;

	public ConditionalDictionary() {
		p = new FreqDictionary();
		prior = new FreqDictionary();
		prob = new Hashtable<>();
		computed = false;
	}

	public void computeFreq() {
		if (computed) {
			System.out.println("Already computed ");
			return;
		}
		for (String k : p.keySet()) {
			String c = k.split(" / ")[1];
			prob.put(k, p.getCount(k) * 1.0 / prior.getCount(c));
		}
		computed = true;
	}

	public void inc(String condition, String key, int count) {
		if (computed) {
			System.out.println("<WARN> need recompute probability...");
			// free memory
			prob.clear();
			computed = false;
		}
		prior.inc(condition, count);
		p.inc(key + " / " + condition, count);
	}

	public void inc(String condition, String key) {
		this.inc(condition, key, 1);
	}

	public void clear() {
		prior.clear();
		p.clear();
		prob.clear();
		computed = false;
	}

	public void set(String condition, String key, int count) {
		int delta = count - prior.getCount(key + SEPARATOR + condition);
		p.put(key + SEPARATOR + condition, count);
		prior.inc(condition, delta);
	}

	public int getCombinationCount(String condition, String key) {
		return p.getCount(key + SEPARATOR + condition);
	}

	public int getConditionalCount(String condition) {
		return prior.getCount(condition);
	}

	public Set<String> getPosibleCombination() {
		return p.keySet();
	}

	public Set<String> getAllConditions() {
		return prior.keySet();
	}

	public double freq(String condition, String key) {
		if (!computed) {
			int c1 = p.getCount(key + SEPARATOR + condition);
			int c2 = prior.getCount(condition);
			if (c2 == 0)
				return eps;
			else
				return c1 * 1.0 / c2;
		} else {
			Double pr = prob.get(key + SEPARATOR + condition);
			if (pr == null)
				return eps;
			else
				return pr;
		}
	}

	public static void main(String[] args) {
		ConditionalDictionary dic = new ConditionalDictionary();
		dic.set("hello", "world", 100);
		dic.set("hello", "tuan", 30);
		dic.set("hello", "dog", 3);
		dic.set("hi", "world", 100);
		dic.set("hi", "tuan", 30);
		dic.set("hoho", "dog", 3);
		dic.set("haha", "dog", 3);

		System.out.println("f(tuan|hello) = " + dic.freq("hello", "tuan"));
		dic.inc("hello", "tuan", 200);
		dic.computeFreq();
		dic.inc("hello", "tuan", 200);
		dic.computeFreq();

		System.out.println("f(tuan|hello) = " + dic.freq("hello", "tuan"));

	}
}
