package utils;

public class TokenizerUtil {
	public static final String SENTENCE_DELIMITER = "\\s*[!?.\n]+\\s*";

	private TokenizerUtil() {
	}

	public static String[] segment(String sentences) {
		return sentences.split(SENTENCE_DELIMITER);
	}

	public static void main(String[] args) {
		//String sentences = "Xin chào các bạn. Chúc các bạn buổi tối vui vẻ !!! Hi hi";
		String sentences = "hello \n\n\n world";
		for (String s : TokenizerUtil.segment(sentences))
			System.out.println(s);
		
	}
}
