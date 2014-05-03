package tagger;

import java.util.HashMap;


public class GenericTable<RType, CType, T> {
	private HashMap<RType, HashMap<CType, T>> table;
	private Integer num;
	public GenericTable() {
		table =  new HashMap<RType, HashMap<CType, T>>();
		num = 0;
	}
	public void set(RType r, CType c, T v) {
		HashMap<CType, T> t;
		t = table.get(r);
		if (t != null) {
			
			if (t.get(c) == null)
				num ++;
			
			t.put(c, v);
		}else {
			t = new HashMap<CType, T>();
			t.put(c, v);
			table.put(r, t);
		}
	}
	public T get(RType r, CType c) {
		HashMap<CType, T> t;
		t = table.get(r);
		if (t != null)
			return t.get(c);
		else {
			return null;
		}
	}
	
	public int size() {
		return num;
	}
	
}

