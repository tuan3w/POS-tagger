package tagger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

import tagger.Constant.ModelSize;

/**
 * @author lol Main Dictionary Store all probability information
 * 
 */
public class ReadDictionary {
	private ConditionalDictionary wtProb;
	private FreqDictionary uModel;
	private ConditionalDictionary triModel;
	private ConditionalDictionary biModel;
	private Hashtable<String, HashSet<String>> wordTags;
	private HashSet<String> tags;
	private String path;
	private double l1, l2, l3;
	private ModelSize size;

	public ReadDictionary(ModelSize size, String path) {
		System.out.println("Dic large : " + (size == ModelSize.LARGE));
		this.size = size;
		this.path = path;
		this.biModel = new ConditionalDictionary();
		this.wtProb = new ConditionalDictionary();
		this.triModel = new ConditionalDictionary();
		this.uModel = new FreqDictionary();
		this.wordTags = new Hashtable<String, HashSet<String>>();
		this.tags = new HashSet<String>();
	}

	public ReadDictionary(String path) {
		this(ModelSize.LARGE, path);
	}

	public void buildModel() {
		System.out.println("Add path :  " + path);
		addFolder(path);
	}

	public void optimize() {
		this.biModel.computeFreq();
		this.triModel.computeFreq();
		this.wtProb.computeFreq();
	}

	public void setTrainPath(String path) {
		this.path = path;
	}

	public void addFolder(String path) {
		// list file in the directory
		File f = new File(path);
		if (!f.exists())
			System.out.println("File khong ton tai");
		String[] flist = f.list();

		String fname;
		BufferedReader bf;
		String sentence;
		String w[];

		String D = "_S2_"; // dump
		String prev2, prev1;
		prev2 = D;
		prev1 = D;
		for (int i = 0; i < flist.length; i++) {
			fname = flist[i];
			// System.out.println("process file " + flist[i]);
			// TimePerf.tic();
			try {

				// open file for reading
				bf = new BufferedReader(new InputStreamReader(
						new FileInputStream(path + "/" + fname)));
				while ((sentence = bf.readLine()) != null) {
					// tokenize to words
					String[] wlist = sentence.split("\\s+");
					prev2 = D;
					prev1 = D;
					for (int j = 0; j < wlist.length; j++) {
						w = wlist[j].split("/");

						if (w.length != 2)
							continue;
						w[1] = w[1].toUpperCase();

						if (size == ModelSize.SMALL) {
							if (w[1].matches("[A-Z]+"))
								w[1] = "" + w[1].charAt(0);
						}
						// add tag count
						uModel.inc(w[1]);

						// add new tags
						tags.add(w[1]);

						// add new tag to word
						addTagToWord(w[0], w[1]);

						// increase count of word tag
						wtProb.inc(w[1], w[0]);

						// increase count of bigram
						// if (j > 0) {
						biModel.inc(prev1, w[1]);
						// }

						// increase count of trigram
						// if (j > 1) {
						triModel.inc(prev1 + " " + prev2, w[1]);
						// }

						prev2 = prev1;
						prev1 = w[1];
					}

				}

				bf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// System.out.println("Process time " + TimePerf.toc() + "ms");
		}
		// handle unknown word (word which has word count < 5)
		HashSet<String> tmp = new HashSet<String>();
		for (String wd : wordTags.keySet()) {

			for (String t : wordTags.get(wd)) {
				if (t.matches("\\w+") && wtProb.getCombinationCount(t, wd) < 5) {
					wtProb.inc(t, "<UNK>", wtProb.getCombinationCount(t, wd));
					tmp.add(t);
					System.out.println("<" + wd + "," + t + ">"
							+ wtProb.freq("UNK", t));
				}
				// addTagToWord("<UNK>", t);

			}
		}
		wordTags.put("<UNK>", tmp);

		System.out.println("Total of tags : " + tags.size());
		StringBuffer t = new StringBuffer();
		for (String s : tags)
			if (s.matches("\\w+"))
				t.append(" " + s);
		System.out.println(t.toString());
		this.computeLambda();
		System.out.println("Unkown word type: ");
		for (String t2 : getPosibleTags("<UNK>"))
			System.out.println("Tag " + t + ", p= " + getProbWordGivenTag("<UNK>", t2));

	}

	private double safeDiv(int a, int b) {
		if (b == 0)
			return -1;
		else
			return a * 1.0 / b;
	}

	public void clear() {
		triModel.clear();
		biModel.clear();
		wtProb.clear();
	}

	public void computeLambda() {
		double sum;
		l1 = l2 = l3 = 0.0;
		String t1, t2, t3;
		double c1, c2, c3;
		for (String trigram : triModel.getPosibleCombination()) {
			String[] t = trigram.split(" / ");
			t1 = t[0];
			t = t[1].split(" ");
			t2 = t[0];
			t3 = t[1];
			if (uModel.getCount(t1) < 2)
				continue;
			c3 = safeDiv(triModel.getCombinationCount(t2 + " " + t3, t1) - 1,
					triModel.getConditionalCount(t2 + " " + t3) - 1);
			c2 = safeDiv(biModel.getCombinationCount(t2, t1),
					biModel.getConditionalCount(t2) - 1);
			c1 = safeDiv(uModel.getCount(t1) - 1, uModel.N() - 1);

			int count = triModel.getCombinationCount(t2 + " " + t3, t1);
			if (c1 > c3 && c1 > c2)
				l1 += count;
			else if (c2 > c1 && c2 > c3)
				l2 += count;
			else if (c3 > c1 && c3 > c2)
				l3 += count;
			else if (c3 == c2 && c3 > c1) {
				l2 += count / 2;
				l3 += count / 2;
			} else if (c1 == c2 && c1 > c3) {
				l1 += count / 2;
				l2 += count / 2;
			}

		}
		sum = l1 + l2 + l3;
		l1 = l1 * 1.0 / sum;
		l2 = l2 * 1.0 / sum;
		l3 = l3 * 1.0 / sum;
		System.out.println(l1 + ", " + l2 + "," + l3);
	}

	public double getTagProbability(String t) {
		return uModel.freq(t);
	}

	public void addTagToWord(String word, String tag) {
		HashSet<String> wordTag = wordTags.get(word);
		if (wordTag == null) {
			wordTag = new HashSet<String>();
			wordTags.put(word, wordTag);
		}

		wordTag.add(tag);
	}

	public HashSet<String> getPosibleTags(String word) {
		HashSet<String> wordTag = wordTags.get(word);
		if (wordTag == null)
			return null; // unkown word
		else
			return wordTag;

	}

	public Double getProbTagGiven1Tag(String t1, String t2) {
		return biModel.freq(t2, t1);
	}

	public Double getProbTagGiven2Tag(String t1, String t2, String t3) {
		// return triModel.freq(t2 + " / " + t3, t1);

		return l1 * uModel.freq(t1) + l2 * biModel.freq(t2, t1) + l3
				* triModel.freq(t2 + " " + t3, t1);
	}

	public String[] getAllTags() {
		return tags.toArray(new String[tags.size()]);
	}

	public double getProbWordGivenTag(String word, String t) {
		return wtProb.freq(t, word);

	}

	public String[] getwordTags(String word) {
		ArrayList<String> wordTags = new ArrayList<String>();
		for (String t : tags) {
			if (getProbWordGivenTag(word, t) > 0)
				wordTags.add(t);
		}
		return wordTags.toArray(new String[wordTags.size()]);
	}

	public boolean isKnownWord(String w) {
		return wordTags.keySet().contains(w);
	}
}
