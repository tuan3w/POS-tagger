package tagger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class ConditionalDictionary {
	public static final String SEPARATOR = " / ";
	public static final int MAX_CACHE_SIZE = 100;
	private FreqDictionary prior, p;
	// private Hashtable<String, HashSet<String>> cache;
	private Hashtable<String, Double> cache;
	private String key_cache[];
	private int cache_idx;
	private double eps = Math.exp(-200);

	public ConditionalDictionary(int cacheSize) {
		p = new FreqDictionary();
		prior = new FreqDictionary();
		cache = new Hashtable<String, Double>();
		cache_idx = 0;
		key_cache = new String[cacheSize];
	}

	public ConditionalDictionary() {
		this(MAX_CACHE_SIZE);
	}

	public void inc(String condition, String key, int count) {
		prior.inc(condition, count);
		p.inc(key + " / " + condition, count);
	}

	public void inc(String condition, String key) {
		this.inc(condition, key, 1);
	}
	public void clearCache(){
		cache.clear();
		cache_idx = 0;
	}
	public void clear() {
		prior.clear();
		p.clear();
		cache.clear();
		cache_idx = 0;
	}

	public void set(String condition, String key, int count) {
		int delta = count - prior.getCount(key + SEPARATOR + condition);
		p.put(key + SEPARATOR + condition, count);
		prior.inc(condition, delta);
	}

	public HashSet<String> getSamples(String condition) {
		return null;
		// HashSet<String> c = cache.get(condition);
		//
		// if (c == null) {
		// c = new HashSet<>();
		// for (String key : p.keySet()) {
		// String[] sample = key.split(SEPARATOR);
		// if (sample[1].equals(condition))
		// c.add(sample[0]);
		// }
		// } else
		// System.out.println("Get \'" + condition + "\' from cache");
		// // add to cache
		// if (cache.size() < key_cache.length) {
		// cache.put(condition, c);
		// key_cache[cache_idx] = condition;
		// cache_idx = (cache_idx + 1) % key_cache.length;
		// } else {
		// cache.remove(key_cache[cache_idx]);
		// key_cache[cache_idx] = condition;
		// cache.put(condition, c);
		// cache_idx = (cache_idx + 1) % key_cache.length;
		// }
		//
		// return c;
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
	
		int c1 = p.getCount(key + SEPARATOR + condition);
		int c2 = prior.getCount(condition);
		if (c2 == 0)
			return eps;
		else
			return c1 * 1.0 / c2;
	}

	public static void main(String[] args) {
		ConditionalDictionary dic = new ConditionalDictionary(2);
		dic.set("hello", "world", 100);
		dic.set("hello", "tuan", 30);
		dic.set("hello", "dog", 3);
		dic.set("hi", "world", 100);
		dic.set("hi", "tuan", 30);
		dic.set("hoho", "dog", 3);
		dic.set("haha", "dog", 3);

		System.out.println("f(tuan|hello) = " + dic.freq("hello", "tuan"));
		dic.inc("hello", "tuan", 200);
		System.out.println("f(tuan|hello) = " + dic.freq("hello", "tuan"));
		HashSet<String> samples;
		samples = dic.getSamples("hello");
		System.out.println(Arrays.toString(samples.toArray()));
		samples = dic.getSamples("hi");
		samples = dic.getSamples("hi");
		samples = dic.getSamples("haha");
		samples = dic.getSamples("haha");
		samples = dic.getSamples("hello");
		samples = dic.getSamples("hoho");
		samples = dic.getSamples("hi");

	}
}
