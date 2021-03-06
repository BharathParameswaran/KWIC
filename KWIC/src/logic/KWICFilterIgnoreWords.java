package logic;

import java.util.ArrayList;
import java.util.List;

import dataSource.Data;

public class KWICFilterIgnoreWords {
	
	private static final String DELIMITER = " ";
	
	private static Data _data = Data.inst();
	
	/**\
	 * Filters the new list of titles index and returns only those elements that
	 * do not start with a word to ignore
	 * @return
	 */
	public static List<String> filterList() {
		List<String> tempList = _data.getIntermediateList();
		assert  tempList!= null : "Unexpected null list to filter";
		assert _data.getIgnoreWordsList() != null : "Unexpected null for list of ignore words";
		
		List<String> outputList = new ArrayList<String>(); 
		
		for(String s : tempList) {
			if(startsWithKeyword(s)) {
				outputList.add(s);
			}
		}
		
		_data.setIntermediateList(outputList);
		return outputList;
	}

	private static boolean startsWithKeyword(String s) {
		String firstWord = getFirstWord(s);
		return !_data.getIgnoreWordsList().contains(firstWord.toLowerCase());
	}

	private static String getFirstWord(String s) {
		
		return s.split(DELIMITER)[0];
	}
}
