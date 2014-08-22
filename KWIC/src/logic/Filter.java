package logic;

import java.util.ArrayList;
import java.util.List;

public class Filter {
	
	private static final String DELIMITER = " ";
	
	public static List<String> filterList(List<String> listToFilter, List<String> wordsToIgnore) {
		assert listToFilter != null : "Unexpected null list to filter";
		assert wordsToIgnore != null : "Unexpected null for list of ignore words";
		
		List<String> outputList = new ArrayList<String>(); 
		
		for(String s : listToFilter) {
			if(startsWithKeyword(s, wordsToIgnore)) {
				outputList.add(s);
			}
		}
		
		return outputList;
	}

	private static boolean startsWithKeyword(String s, List<String> wordsToIgnore) {
		String firstWord = getFirstWord(s);
		return !wordsToIgnore.contains(firstWord.toLowerCase());
	}

	private static String getFirstWord(String s) {
		
		return s.split(DELIMITER)[0];
	}
}
