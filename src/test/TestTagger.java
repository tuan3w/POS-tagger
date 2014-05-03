package test;

import tagger.HMMTagger;
import tagger.IPOSTagger;

/**
 * 
 * @author lol
 * <b> Tuấn đẹp trai quá</b>
 */
public class TestTagger {
	public static void main(String[] args) {
		IPOSTagger tagger = new HMMTagger("data");
		String[] tests = {
				"tôi là Tuấn"
		};
		String tagString;
		for (int i = 0; i < tests.length; i++) {
			String s = tests[i];
			System.out.println("+++++++++++++++++++++++");
			System.out.println(s);
			tagString = "";
			String[] tags = tagger.parse(s);
			
			for (int j = 0; j < tags.length; j++)
				tagString += " " + tags[j];
			
			System.out.println(tagString);
			
		}
	}
}
