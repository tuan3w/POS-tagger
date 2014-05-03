package tagger;
import java.util.HashMap;
import java.util.HashSet;


public class TagCount {
	HashMap<String, Integer> tags;
	public TagCount() {
		tags = new HashMap<String, Integer>();
	}
	public void incTag(String t) {
		Integer n = tags.get(t);
		if (n == null)
			tags.put(t, 1);
		else
			tags.put(t, n + 1);
	}
//	public void addTag(String t) {
//		Integer n = tags.get(t);
//		if (n == null)
//			tags.put(t, 0);
//	}
	public void removeTag(String t) {
		Integer n = tags.get(t);
		if (n == null)
			return;
		if (n > 1)
			tags.put(t, n - 1);
		else
			tags.remove(t);
	}
	public void setTagCount(HashMap<String, Integer> tmap) {
		tags = tmap;
	}
	public String[] getTagset() {
		return (String[]) tags.keySet().toArray(new String[tags.keySet().size()]);
	}
	public  int getCountOfTag(String t) {
		if (tags.containsKey(t))
			return tags.get(t);
		else
			return 0;
	}
	public String toString() {
		StringBuffer b = new StringBuffer();
		for (String s : tags.keySet())
			b.append(s +'-' +  tags.get(s)).append("|");
		return b.toString();
	}
}
