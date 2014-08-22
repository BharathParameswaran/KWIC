package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

/**
 * 
 * @author Bharath
 *
 */
public class AlphabetizerTest {

	@Test
	public void testAlphabetize() {
		testMethodForNullArray();
		testMethodForEmptyArray();
		testMethodForOrderedList();
		testMethodForTypicalInput();
	}
	
	private void testMethodForNullArray() {
		try {
			Alphabetizer.alphabetize(null);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected null list to be sorted",
					ae.getMessage());
		}
	}
	
	private void testMethodForEmptyArray() {
		
		try {
			Alphabetizer.alphabetize(new ArrayList<String>());
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected empty list to be sorted",
					ae.getMessage());
		}
		
	}
	
	private void testMethodForOrderedList() {
		List<String> listToSort = new ArrayList<String>();
		listToSort.add("Fast and Furious");
		listToSort.add("the Day after Tomorrow");
		listToSort.add("the Man of Steel");
		
		
		List<String> orderedList = Alphabetizer.alphabetize(listToSort);
		String[] expected = {"Fast and Furious", "the Day after Tomorrow", "the Man of Steel"};
		int i=0;
		for(String output: orderedList) {
			assertEquals(expected[i++], output);
		}
	}
	
	private void testMethodForTypicalInput() {
		List<String> listToSort = new ArrayList<String>();
		listToSort.add("the Day after Tomorrow");
		listToSort.add("the Man of Steel");
		listToSort.add("Fast and Furious");
		
		List<String> orderedList = Alphabetizer.alphabetize(listToSort);
		String[] expected = {"Fast and Furious", "the Day after Tomorrow", "the Man of Steel"};
		int i=0;
		for(String output: orderedList) {
			assertEquals(expected[i++], output);
		}
	}

}
