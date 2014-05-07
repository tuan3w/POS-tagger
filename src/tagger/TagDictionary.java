package tagger;

import java.util.HashSet;
import java.util.Hashtable;

public class TagDictionary {
	public Hashtable<String, HashSet<String>> t;
	public TagDictionary(String path) {
		// TODO Auto-generated constructor stub
		t = new Hashtable<String, HashSet<String>>();
	}
}
