package test;

import org.apache.catalina.tribes.util.Arrays;

import utils.ArrayUtils;
import utils.StringIterator;
import utils.TokenizerUtil;
import vn.hus.nlp.tokenizer.VietTokenizer;

public class TestTokenizer {
	public static void main(String[] args) {
		VietTokenizer tokenizer = new VietTokenizer();
		String paragraph = "Đây là chiếc cuối cùng của lô tàu ngầm Project 636, được Nga đóng cho Việt Nam theo hợp đồng đã ký vào năm 2009 . "
				+ "Bên cạnh việc xây dựng tàu ngầm, thỏa thuận còn kèm theo việc cung cấp thiết bị cần thiết và đào tạo thủy thủ cho Việt Nam .\n\n"
				+ "Chiếc tàu đầu tiên và thứ hai \"Hà Nội\" và \"Thành phố Hồ Chí Minh\" đã gia nhập Hải quân Việt Nam mới đây ."
				+ "Vào giữa tháng 3 vừa qua biên bản kỹ thuật bàn giao cho Hải quân Việt Nam chiếc tàu ngầm thứ ba đã được ký trong khi đó vào cuối tháng 3, tàu ngầm thứ tư đã được hạ thủy";
		// String[] sentences = TokenizerUtil.segment(paragraph);
		StringIterator stringIter = new StringIterator(paragraph,
				StringIterator.SENTENCE_DELIMITER);
		for (String sentence : stringIter) {
			// System.out.println(test);
			String tokenString = tokenizer.tokenize(sentence)[0];
			String s = Arrays.toString(tokenString.split("\\s+"));
			System.out.println(s);
		}
	}
}
