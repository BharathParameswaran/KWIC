package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Bharath
 *
 */
public class KWICMerger {

	/**
	 * Accepts a sorted list of Strings and a new list of
	 * Strings to be added and merges them into one sorted list and returns it
	 * 
	 * @param newTitles
	 * @param kwicTitleIndex
	 * @return
	 */

	public static List<String> mergeTitlesToExistingList(
			List<String> newTitles, List<String> kwicTitleIndex) {

		assert (newTitles != null && kwicTitleIndex != null) : "Unexpected null list to be merged";

		if (kwicTitleIndex.size() == 0)
			return newTitles;
		if(newTitles.size() ==0)
			return kwicTitleIndex;
		
		    for (int index1 = 0, index2 = 0; index2 < kwicTitleIndex.size(); index1++) {
		        if (index1 == newTitles.size() || newTitles.get(index1).compareToIgnoreCase(kwicTitleIndex.get(index2)) > 0) {
		        	newTitles.add(index1, kwicTitleIndex.get(index2++));
		        }
		    }
		    
		    return newTitles;
		 
	}	

}
