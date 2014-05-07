package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Scanner;

import tagger.ReadDictionary;

/**
 * 
 * @author lol <strong> Tuấn đẹp trai quá </strong>
 */
public class TestReadDictionary {
	public static void main(String[] args) throws IOException {
		ReadDictionary d = new ReadDictionary("data");
		d.buildModel();
		// TagSet tags = d.getTagset("chao");
		// System.out.println(tags);
		BufferedReader s = new BufferedReader(new InputStreamReader(System.in));
		String[] tags = d.getAllTags();
		while (true) {
			System.out.println("$>>");
			String word = s.readLine();
			// word = word.toLowerCase();
			word.replaceAll("\\s+", "_");
			if (word.length() == 0 || word.equals("exit"))
				break;
			HashSet<String> ts = d.getPosibleTags(word);
			if (ts != null)
				for (String t : ts)
					System.out.println(t);
			// System.out.println(word + " -- " + d.getTagset(word));
		}
	}
}
