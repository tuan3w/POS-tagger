package tagger;

public interface IPOSTagger {
	public String[] parse(String[] words);
	public String[] parse(String sentence);
}
