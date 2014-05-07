package test;

import tagger.HMMTagger;
import tagger.IPOSTagger;
import utils.ArrayUtils;

/**
 * 
 * @author lol
 * <b> Tuấn đẹp trai quá</b>
 */
public class TestTagger {
	public static void main(String[] args) {
		HMMTagger tagger = new HMMTagger(".");
		String[] tests = {
				"tôi là Tuấn"
		};
		String tagString;
		for (int i = 0; i < tests.length; i++) {
			String s = tests[i];
			System.out.println("+++++++++++++++++++++++");
			System.out.println(s);
			tagString = "";
			String[] tags = tagger.getTags(s);
			
			tagString = ArrayUtils.join(tags, " ");
			
			System.out.println(tagString);
			
		}
	}
}
