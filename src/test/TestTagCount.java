package test;

import tagger.TagCount;

public class TestTagCount {
	public static void main(String[] args) {
		TagCount t = new TagCount();
		t.incTag("N");
		int k= 2;
		assert  k == 1 +2;
		t.incTag("NP");
		t.incTag("NP");
		t.incTag("V");
		
		System.out.println(t);
		t.removeTag("n");
		t.removeTag("n");
		String[] tlist = t.getTagset();
		for (int i = 0; i < tlist.length; i++)
			System.out.println(tlist[i]);
	}
}
