package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author Bharath
 *
 */
public class MergerTest {
	
	@Test
	public void testMerger() {
		testMethodForNullInput();
		testMethodForEmptyInput();
		testMethodForCornerCases();
		testMethodForTypicalInput();
	}
	
	private void testMethodForCornerCases() {
		testMethodForAddingToEndOfList();
		testMethodForAddingToStartOfList();

		
	}
	
	private void testMethodForNullInput() {
		List<String> titleList = new ArrayList<String>();
		titleList.add("First title");
		
		try {
			Merger.mergeTitlesToExistingList(null, titleList);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected null list to be merged",
					ae.getMessage());
		}
		
		try {
			Merger.mergeTitlesToExistingList(titleList, null);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected null list to be merged",
					ae.getMessage());
		}
		
	}
	
	private void testMethodForEmptyInput() {
		List<String> existingTitleList = new ArrayList<String>();
		existingTitleList.add("Man of Steel");
		existingTitleList.add("the Day after Tomorrow");
		
		List<String> newTitleList = new ArrayList<String>();
		
		List<String> mergedList = new ArrayList<String>();
		mergedList.add("Man of Steel");
		mergedList.add("the Day after Tomorrow");
		
		assertEquals(mergedList, Merger.mergeTitlesToExistingList(newTitleList, existingTitleList));
		
		assertEquals(mergedList, Merger.mergeTitlesToExistingList(existingTitleList, newTitleList));
		
	}
	
	private void testMethodForAddingToEndOfList() {
		List<String> existingTitleList = new ArrayList<String>();
		existingTitleList.add("Fast and Furious");
		existingTitleList.add("Man of Steel");
		List<String> newTitleList = new ArrayList<String>();
		newTitleList.add("the Day after Tomorrow");
		List<String> mergedList = new ArrayList<String>();
		mergedList.add("Fast and Furious");
		mergedList.add("Man of Steel");
		mergedList.add("the Day after Tomorrow");
		assertEquals(mergedList, Merger.mergeTitlesToExistingList(newTitleList, existingTitleList));
		
	}
	
	private void testMethodForAddingToStartOfList() {
		List<String> existingTitleList = new ArrayList<String>();
		existingTitleList.add("Fast and Furious");
		existingTitleList.add("Man of Steel");
		List<String> newTitleList = new ArrayList<String>();
		newTitleList.add("Apocalypto");
		List<String> mergedList = new ArrayList<String>();
		mergedList.add("Apocalypto");
		mergedList.add("Fast and Furious");
		mergedList.add("Man of Steel");
	
		assertEquals(mergedList, Merger.mergeTitlesToExistingList(newTitleList, existingTitleList));
		
	}
	
	private void testMethodForTypicalInput() {
		
		List<String> existingTitleList = new ArrayList<String>();
		existingTitleList.add("Fast and Furious");
		existingTitleList.add("the Day after Tomorrow");
		
		List<String> newTitleList = new ArrayList<String>();
		newTitleList.add("Man of Steel");
		newTitleList.add("Spiderman");	
		newTitleList.add("Apocalypto");				
		
		List<String> mergedList = new ArrayList<String>();
		mergedList.add("Apocalypto");
		mergedList.add("Fast and Furious");
		mergedList.add("Man of Steel");
		mergedList.add("Spiderman");	
		mergedList.add("the Day after Tomorrow");
		
		assertEquals(mergedList, Merger.mergeTitlesToExistingList(newTitleList, existingTitleList));
		
	}

}
