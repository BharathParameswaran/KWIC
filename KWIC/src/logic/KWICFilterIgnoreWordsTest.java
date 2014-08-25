package logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class KWICFilterIgnoreWordsTest {

	@Test
	public void testFilterList() {
		testFilterListNullInput();
		testFilterListEmptyListInput();
		testFilterListTypicalInput();
	}

	private void testFilterListNullInput() {		
		try {
			KWICFilterIgnoreWords.filterList(null, new ArrayList<String>());
			assertFalse("Expected AssertionError", true);
		} catch(AssertionError ae) {
			assertEquals("Unexpected null list to filter", ae.getMessage());
		}
		
		try {
			KWICFilterIgnoreWords.filterList(new ArrayList<String>(), null);
			assertFalse("Expected AssertionError", true);
		} catch(AssertionError ae) {
			assertEquals("Unexpected null for list of ignore words", ae.getMessage());
		}
	}

	private void testFilterListEmptyListInput() {
		// both lists are empty
		List<String> wordsToIgnore = new ArrayList<String>();
		List<String> actualList = KWICFilterIgnoreWords.filterList(new ArrayList<String>(), wordsToIgnore);
		assertEquals(0, actualList.size());
		
		// list to filter is empty
		wordsToIgnore.add("to");
		
		actualList = KWICFilterIgnoreWords.filterList(new ArrayList<String>(), wordsToIgnore);
		assertEquals(0, actualList.size());
		
		// ignore words is empty
		wordsToIgnore.clear();
		List<String> inputList = new ArrayList<String>();
		
		inputList.add("The day After tomorrow");
		inputList.add("day After tomorrow The");
		inputList.add("After tomorrow The day");
		inputList.add("tomorrow The day After");
		
		actualList = KWICFilterIgnoreWords.filterList(inputList, wordsToIgnore);
		
		assertEquals(inputList.size(), actualList.size());
		for (int i = 0 ; i < inputList.size() ; i ++) {
			assertEquals(inputList.get(i), actualList.get(i));
		}
	}

	private void testFilterListTypicalInput() {
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("the");
		wordsToIgnore.add("after");
		List<String> inputList = new ArrayList<String>();
		List<String> expectedList = new ArrayList<String>();
		
		inputList.add("The day After tomorrow");
		inputList.add("day After tomorrow The");
		inputList.add("After tomorrow The day");
		inputList.add("tomorrow The day After");
		
		expectedList.add("day After tomorrow The");
		expectedList.add("tomorrow The day After");
		assertEquals(expectedList, KWICFilterIgnoreWords.filterList(inputList, wordsToIgnore));

	}

}
