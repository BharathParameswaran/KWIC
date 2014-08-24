package logic;

import java.util.List;

/**
 * 
 * @author Bharath
 *
 */
public class Merger {

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

		for (String title : newTitles)
			mergeUsingBinarySearch(title, kwicTitleIndex, 0,
					kwicTitleIndex.size() - 1);
		return kwicTitleIndex;
	}

	private static void mergeUsingBinarySearch(String title,
			List<String> kwicTitleIndex, int start, int end) {
		if (start <= end) {
			int mid = (start + end) / 2;

			if (kwicTitleIndex.get(mid).compareToIgnoreCase(title) > 0) {
				if (start == mid) {
					kwicTitleIndex.add(mid, title);
					System.out.println("inserted:" + title);
				} else if (mid == 0) {
					kwicTitleIndex.add(0, title);
					System.out.println("inserted:" + title);
				}
				else
					mergeUsingBinarySearch(title, kwicTitleIndex, start,
							mid - 1);
			}

			else if (kwicTitleIndex.get(mid).compareToIgnoreCase(title) <= 0) {
				if (start == mid)
					if (mid != kwicTitleIndex.size() - 1) {
						kwicTitleIndex.add(mid + 1, title);
						System.out.println("inserted:" + title);
					} else {
						kwicTitleIndex.add(title);
						System.out.println("inserted:" + title);
					}

				else
					mergeUsingBinarySearch(title, kwicTitleIndex, mid + 1, end);

			}

		}

	}

}
