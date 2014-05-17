package tagger;

import tagger.Constant.ModelSize;
import tokenizer.VietTokenizerWrapper;
import utils.StringIterator;

public class HMMTagger implements IPOSTagger {
	ReadDictionary dic;
	VietTokenizerWrapper tokenizer;
	ViterbiSolver solver;
	private ModelSize size;

	public HMMTagger(String folderpath, ModelSize size) {
		System.out.println(folderpath);
		System.out.println(size == ModelSize.LARGE);
		this.size = size;
		dic = new ReadDictionary(size, folderpath
				+ System.getProperty("file.separator") + "data");
		// build model
		dic.buildModel();

		solver = new ViterbiSolver(dic);
		tokenizer = new VietTokenizerWrapper(folderpath);
	}

	public HMMTagger(String folderpath) {
		this(folderpath, ModelSize.SMALL);
	}

	public ReadDictionary getDictionary() {
		return dic;
	}

	public VietTokenizerWrapper getTokenizer() {
		return tokenizer;
	}

	public String[] getTags(String sentence) {
		String[] words = tokenize(sentence).split(" ");
		return getTags(words);
	}

	public void optimize() {
		dic.optimize();
	}

	public ModelSize getModelSize() {
		return size;
	}

	public String normalizeTagName(String tag) {
		tag = tag.toUpperCase();
		if (size == ModelSize.SMALL && tag.matches("[A-Z]+")) {
			return "" + tag.charAt(0);
		} else
			return tag;
	}

	public String[] getTags(String[] words) {
		// System.out.println(Arrays.toString(words));
		return solver.getSolution(words);
	}

	public String tokenize(String sentence) {
		return tokenizer.tokenize(sentence)[0];
	}

	public Iterable<String> sentenceIterator(String par) {
		return new StringIterator(par, StringIterator.SENTENCE_DELIMITER);
	}

}
