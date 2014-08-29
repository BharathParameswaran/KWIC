package logic;

import java.util.Collections;
import java.util.List;

import dataSource.Data;

/**
 * 
 * @author Bharath
 *
 */

public class KWICAlphabetizer {
	
	private static Data _data = Data.inst();

	/**
	 * Accepts a list of String and arranges them in alphabetic order
	 * Returns the alphabetized list
	 * or returns null if there was no string to be alphabetized
	 * @param listToAlphabetize
	 * @return
	 */
	public static List<String> alphabetize(){
		List<String> listToSort = _data.getIntermediateList();
		assert listToSort != null : "Unexpected null list to be sorted";
		if(listToSort.isEmpty()) {
			return null;
		}

		Collections.sort(listToSort, String.CASE_INSENSITIVE_ORDER);
		return listToSort;
		
	}

}
