package tagger;

import java.util.ArrayList;

public class MockTokenizer implements ITokenizer {
	public 	MockTokenizer() {
	}
	public String[] tokenize(String s) {
		s = s.trim();
		ArrayList<String> ts = new ArrayList<String>();
		int start = 0, end = 0;
		String tmp;
		for (int i = 0; i < s.length(); i++) {
			if (Character.isSpace(s.charAt(i))) {
				if (!Character.isSpace(s.charAt(i-1))) {
					tmp = s.substring(start, end);

					ts.add(tmp);

				}
					
			}
			else if (i > 0 && Character.isSpace(s.charAt(i-1))) {
				start = i;
			}
			end ++;
		}
		ts.add(s.substring(start, end));
		return ts.toArray(new String[ts.size()]);
		
		//return s.split("\w+");
	}

	
}
