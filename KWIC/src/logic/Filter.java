package logic;

import java.util.ArrayList;
import java.util.List;

public class Filter {
	
	private static final String DELIMITER = " ";
	
	/**
	 * Filters the list and returns only those elements that
	 * do not start with and string in the given list of
	 * stringsToIgnore
	 * @param listToFilter
	 * @param stringsToIgnore
	 * @return
	 */
	public static List<String> filterList(List<String> listToFilter, List<String> stringsToIgnore) {
		assert listToFilter != null : "Unexpected null list to filter";
		assert stringsToIgnore != null : "Unexpected null for list of ignore words";
		
		List<String> outputList = new ArrayList<String>(); 
		
		for(String s : listToFilter) {
			if(startsWithKeyword(s, stringsToIgnore)) {
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
