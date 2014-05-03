package test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tagger.GenericTable;

public class TestGenericTable {
	public static void main(String[] args) {
		GenericTable<String, String, Integer> table = new GenericTable<>();
		String sentence = "Stay Hungry Stay Foolish Stay Foolish";
		String[] words = sentence.split("\\s+");
		int n = words.length - 1;
		Integer t;
//		for (int i = 0; i < n+1; i++)
//			System.out.println(words[i]);
		for (int i = 0; i < n; i++) {
			t = table.get(words[i], words[i+1]);
			if (t == null)
				table.set(words[i], words[i+1], 1);
			else {
				table.set(words[i], words[i+1], t + 1);
			}
		}
		
		System.out.println("List Bigram...\n+++++++++++++++++++++++");
		n = words.length - 1;
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n+1; j++) {
				System.out.println(words[i] + "_" + words[j] + "  " + table.get(words[i], words[j]));
			}
			
		}
	}
}
