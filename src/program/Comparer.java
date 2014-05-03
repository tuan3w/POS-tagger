package program;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import tagger.HMMTagger;
import tagger.IPOSTagger;
import utils.TimePerf;

public class Comparer {
	
	private int sumToken, trueToken;
	private IPOSTagger tagger;
	
	public Comparer(String path) {
		sumToken = trueToken = 0;
		tagger = new HMMTagger("data");
		
		File folder = new File(path);
		File[] files = folder.listFiles();
		TimePerf.tic();
		for (int i=0; i<files.length; i++) {
			readfile(files[i]);
		}
		System.out.println("Process time " + TimePerf.toc() + "ms");

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
					while (slash < str.length() && str.charAt(slash) != '/') slash++;
					if (slash == str.length()) continue;
					
					word = str.substring(0, slash);
					tag = str.substring(slash+1);
					
					test = test + word + " ";
					trueTag.add(tag);
					sumToken++;
				}
				sentenceScanner.close();
				//System.out.println(test.split(" "));
				newTag = tagger.parse(test.split(" "));
				for (int i=0; i<newTag.length; i++) {
//					System.out.println(trueTag.get(i)+"\t"+newTag[i]);
					if (trueTag.get(i).equals(newTag[i]))
						trueToken++;
//					else System.out.println(trueTag.get(i)+"\t"+newTag[i]);
				}
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public double getSuccessPercent() {
		return 100*((double)trueToken)/((double)sumToken);
	}
	
	public static void main(String[] args) {
		Comparer comparer = new Comparer("test");
		System.out.println(comparer.getSuccessPercent());
	}
}
