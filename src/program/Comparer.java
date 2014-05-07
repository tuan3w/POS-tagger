package program;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import tagger.Constant.ModelSize;
import tagger.HMMTagger;
import tagger.IPOSTagger;
import tagger.ReadDictionary;
import utils.TimePerf;

public class Comparer {

	private int sumToken, trueToken;
	private HMMTagger tagger;
	private String path;

	public Comparer(String path) {
		sumToken = trueToken = 0;
		tagger = new HMMTagger(".", ModelSize.LARGE);
		// tagger = new HMMTagger(".", ModelSize.SMALL); //small model

		tagger.optimize();
		this.path = path;

	}

	public void process() {
		File folder = new File(path);
		File[] files = folder.listFiles();
		TimePerf.tic();
		for (int i = 0; i < files.length; i++) {
			readfile(files[i]);
		}
		System.out.println("Process time " + TimePerf.toc() + "ms");
	}

	public IPOSTagger getTagger() {
		return tagger;
	}

	private void readfile(File f) {
		System.out.println("read file " + f.getName());
		String sentence, str, word, tag;
		String test = "";
		String[] newTag;
		ArrayList<String> trueTag = new ArrayList<>();
		try {
			Scanner scanner = new Scanner(f);
			while (scanner.hasNext()) {
				sentence = scanner.nextLine();
				Scanner sentenceScanner = new Scanner(sentence);
				test = "";
				trueTag.clear();

				while (sentenceScanner.hasNext()) {
					str = sentenceScanner.next();

					int slash = 0;
					while (slash < str.length() && str.charAt(slash) != '/')
						slash++;
					if (slash == str.length())
						continue;

					word = str.substring(0, slash);
					tag = str.substring(slash + 1);

					test = test + word + " ";
					trueTag.add(tag);
					sumToken++;
				}
				sentenceScanner.close();
				// System.out.println(test.split(" "));
				newTag = tagger.getTags(test.split(" "));
				for (int i = 0; i < newTag.length; i++) {
					// System.out.println(trueTag.get(i)+"\t"+newTag[i]);
					if (tagger.normalizeTagName(trueTag.get(i)).equals(
							newTag[i]))
						trueToken++;
					// else System.out.println(trueTag.get(i)+"\t"+newTag[i]);
				}
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public double getSuccessPercent() {
		return 100 * ((double) trueToken) / ((double) sumToken);
	}

	public static void main(String[] args) {
		Comparer comparer = new Comparer("T1");
		//Comparer comparer = new Comparer("T2");
		comparer.process();
		System.out.println(comparer.getSuccessPercent());
	}
}
