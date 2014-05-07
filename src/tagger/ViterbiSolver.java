package tagger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class ViterbiSolver {
	ReadDictionary dic;
	Hashtable<String, Double> ptable;

	public ViterbiSolver(ReadDictionary dic) {
		this.dic = dic;
		ptable = new Hashtable<String, Double>();
	}

	public String[] getSolution(String[] words) {
		ptable.clear();
		String[] allTags = dic.getAllTags();
		String tag, st;
		Double p, p1;
		String prev = "_S2_"; // dump
		st = prev;
		String bs = "";
		Hashtable<String, String> backPointer = new Hashtable<String, String>();
		String w = words[0];
		if (!dic.isKnownWord(w)) {
			System.out.println(w + " is unknown");
			w = "<UNK>";
		}
		for (int i = 0; i < allTags.length; i++) {
			tag = allTags[i];
			setCell(tag,
					0,
					dic.getTagProbability(tag)
							* dic.getProbWordGivenTag(w, tag));
			backPointer.put(tag + "/0", "_S2_");

		}
		for (int ix = 1; ix < words.length; ix++) {
			w = words[ix];
			//HashSet<String> tlist;
			if (!dic.isKnownWord(w)) {
				System.out.println("unknown " + w);
				w = "<UNK>";
			}
			//tlist = dic.getPosibleTags(w);
//			if (dic.getPosibleTags(w) == null) {
//				// unknown word
//				//System.out.println("Unkown word '" + w + "'");
//				w = "<UNK>";
//			}

			for (int i = 0; i < allTags.length; i++) {
				tag = allTags[i];
				p = -Math.exp(100);
				//for (String st1 : dic.getPosibleTags(w)) {
				for (int j = 0; j < allTags.length; j++) {
					st = allTags[j];
					if (ix > 0) {
						// get prev tag
						prev = backPointer.get(st + "/" + (ix - 1));
					}
					//System.out.println("p1 " + getCell(st, ix - 1));
					p1 = getCell(st, ix - 1)
							* dic.getProbTagGiven2Tag(tag, st, prev)
							* dic.getProbWordGivenTag(w, tag);
					if (p1 > p) {
						//System.out.println("replace...");
						p = p1;
						bs = st;
					}

				}
				setCell(tag, ix, p);
				backPointer.put(tag + "/" + ix, bs);

			}
		}

		// return best path
		// get last tag
		p = -1.0;
		int n = words.length - 1;
		for (int i = 0; i < allTags.length; i++) {
			p1 = getCell(allTags[i], n);
			if (p1 > p) {
				bs = allTags[i];
				p = p1;
			}
		}
		
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.add(bs);
		int ind = words.length - 1;
		bs = backPointer.get(bs + "/" + ind);
		while (!bs.equals("_S2_")) {
			tmp.add(0, bs);
			ind--;
			bs = backPointer.get(bs + "/" + ind);
		}
		return tmp.toArray(new String[tmp.size()]);
		
	}

	private Double getCell(String w, int n) {
		Double t = ptable.get(w + "/" + n);
		if (t == null)
			return 0.0;
		else
			return t;
	}

	private void setCell(String w, int n, Double v) {
		ptable.put(w + "/" + n, v);

	}
}
