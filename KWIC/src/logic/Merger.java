package logic;

import java.util.List;

public class Merger {
	
	public static void mergeTitleToList(List<String> newTitle, List<String> kwicTitleIndex) {
		
		mergeUsingBinarySearch( newTitle,kwicTitleIndex, 0, kwicTitleIndex.size());
	}
	
	private static void mergeUsingBinarySearch(List<String> newTitle, List<String> kwicTitleIndex, int start, int end) {
		int i=0;
		while(start < end) {
			int mid = (start+end)/2;
			
			if(kwicTitleIndex.get(mid).compareToIgnoreCase(newTitle.get(i)) > 0) {
				mergeUsingBinarySearch(newTitle, kwicTitleIndex, start, mid-1);
			}
			
			else if (kwicTitleIndex.get(mid).compareToIgnoreCase(newTitle.get(i)) < 0) {
				mergeUsingBinarySearch(newTitle, kwicTitleIndex, mid+1, end);

			}
				
		}
		
	}

}
