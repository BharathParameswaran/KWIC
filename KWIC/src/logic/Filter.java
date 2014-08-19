package logic;

import java.util.ArrayList;
import java.util.List;

public class Filter {
	
	private List<String> _wordsToIgnore;
	private String _delimiter = " ";
	
	public Filter() {
		_wordsToIgnore = new ArrayList<String>();
	}
	
	public Filter(List<String> wordsToIgnore) {
		assert wordsToIgnore != null : "Unexpected null for list of words";

		_wordsToIgnore = wordsToIgnore;
	}
	
	public List<String> getIgnoreList() {
		return _wordsToIgnore;
	}
	
	public void addWordToIgnoreList(String word) {
		assert word != null : "Unexpected null word given";
		assert !word.isEmpty() : "Unexpected empty word given";
		
		_wordsToIgnore.add(word);
	}
	
	public String getDelimiter() {
		return _delimiter;
	}
	
	public void setDelimiter(String delimiter) {
		assert delimiter != null : "Unexpected null delimiter given";
		assert !delimiter.isEmpty() : "Unexpected empty delimiter given";
		
		_delimiter = delimiter;
	}
	
	public List<String> filterList(List<String> listToFilter) {
		List<String> outputList = new ArrayList<String>(); 
		
		for(String s : listToFilter) {
			if(startsWithKeyword(s)) {
				outputList.add(s);
			}
		}
		
		return listToFilter;
	}

	private boolean startsWithKeyword(String s) {
		String firstWord = getFirstWord(s);
		
		return !_wordsToIgnore.contains(firstWord);
	}

	private String getFirstWord(String s) {
		
		return s.split(_delimiter)[0];
	}
}
