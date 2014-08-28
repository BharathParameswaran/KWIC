package logic;

import java.util.List;

import dataSource.Data;

/**
 * 
 * @author Bharath
 * 
 */
public class KWICMerger {

	private static Data _data = Data.inst();

	/**
	 * Accepts a sorted list of Strings and a new list of Strings to be added
	 * and merges them into one sorted list and returns it
	 * 
	 * @param newTitles
	 * @param kwicTitleIndex
	 * @return
	 */

	public static List<String> mergeTitlesToExistingList() {
		List<String> newTitles = _data.getIntermediateList();
		List<String> kwicTitleIndex = _data.getCurrentResult();

		for (int index1 = 0, index2 = 0; index2 < kwicTitleIndex.size(); index1++) {
			if (index1 == newTitles.size()
					|| newTitles.get(index1).compareToIgnoreCase(
							kwicTitleIndex.get(index2)) > 0) {
				newTitles.add(index1, kwicTitleIndex.get(index2++));
			}
		}

		_data.setResultSetToIntermediateResult();
		return _data.getCurrentResult(); // for testing purposes only

	}

}
