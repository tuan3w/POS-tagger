package utils;

import java.util.Iterator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class StringIterator implements Iterable<String> {
	public static final String SENTENCE_DELIMITER = "!\n.?";
	private String s;
	private String delimiter;
	private int start, end;

	public StringIterator(String s, String delimiter) {
		this.s = s.trim();
		this.delimiter = delimiter;
		start = 0;
		end = 0;
	}

	public Iterator<String> iterator() {
		Iterator<String> it = new Iterator<String>() {

			@Override
			public boolean hasNext() {
				return start < s.length()
						&& s.substring(start).trim().length() > 0;
			}

			@SuppressWarnings("deprecation")
			@Override
			public String next() {
				while (start < s.length() && Character.isSpace(s.charAt(start)))
					start++;
				end = start;
				while (end < s.length() && !isDelimiterCharacter(s.charAt(end)))
					end++;
				String tmp = "";
				if (end == s.length())
					tmp = s.substring(start);
				else
					tmp = s.substring(start, end + 1);
				start = end;
				while (start < s.length()
						&& isDelimiterCharacter(s.charAt(start)))
					start++;
				if (start < s.length() && tmp == "")
					// try another iterator
					return next();

				return tmp;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				throw new NotImplementedException();
			}
		};
		return it;
	}

	private boolean isDelimiterCharacter(char c) {
		return delimiter.indexOf(c) != -1;
	}

	public static void main(String[] args) {
		String s = "hello !!\n! world.   \r";
		String paragraph = "Đây là chiếc cuối cùng của lô tàu ngầm Project 636, được Nga đóng cho Việt Nam theo hợp đồng đã ký vào năm 2009 . "
				+ "Bên cạnh việc xây dựng tàu ngầm, thỏa thuận còn kèm theo việc cung cấp thiết bị cần thiết và đào tạo thủy thủ cho Việt Nam .\n\n"
				+ "Chiếc tàu đầu tiên và thứ hai \"Hà Nội\" và \"Thành phố Hồ Chí Minh\" đã gia nhập Hải quân Việt Nam mới đây ."
				+ "Vào giữa tháng 3 vừa qua biên bản kỹ thuật bàn giao cho Hải quân Việt Nam chiếc tàu ngầm thứ ba đã được ký trong khi đó vào cuối tháng 3, tàu ngầm thứ tư đã được hạ thủy";
		// paragraph = "hello \n world ";
		StringIterator it = new StringIterator(paragraph, "!\n.?");
		for (String c : it)
			System.out.println(c);

		// System.out.println("end");
	}
}
