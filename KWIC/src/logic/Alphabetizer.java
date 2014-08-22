package logic;

import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Bharath
 *
 */

public class Alphabetizer {

	/**
	 * Accepts a list of String and arranges them in alphabetic order
	 * Returns the alphabetized list
	 * @param listToAlphabetize
	 * @return
	 */
	public static List<String> alphabetize(List<String> listToSort){
		assert listToSort != null : "Unexpected null list to be sorted";
		assert !listToSort.isEmpty() : "Unexpected empty list to be sorted";

		Collections.sort(listToSort, String.CASE_INSENSITIVE_ORDER);
		return listToSort;
		
	}

}
