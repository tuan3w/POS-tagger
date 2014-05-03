package tagger;

import java.util.Arrays;

import vn.hus.nlp.tokenizer.VietTokenizer;

public class HMMTagger implements IPOSTagger {
	ReadDictionary dic;
	VietTokenizer tokenizer;
	ViterbiSolver solver;
	public HMMTagger(String path) {
		dic = new ReadDictionary(path);
		//build model
		dic.buildModel();
		
		solver = new ViterbiSolver(dic);
		tokenizer = new VietTokenizer();
	}
	
	public String[] parse(String sentence) {
		String[] words = tokenizer.tokenize(sentence)[0].split(" ");
		//System.out.println(Arrays.toString(words));
		return parse(words);
	}
	public String[] parse(String[] words) {
		//System.out.println(Arrays.toString(words));
		dic.clearCache();
		return solver.getSolution(words);
	}
}
