package logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class FilterTest {

	@Test
	public void testGetDelimiter() {
		testGetDelimiterDefaultConstructor();
		testGetDelimiterTypicalValue();
	}

	private void testGetDelimiterDefaultConstructor() {
		Filter f = new Filter();
		assertEquals(" ", f.getDelimiter());
	}

	private void testGetDelimiterTypicalValue() {
		Filter f = new Filter();
		f.setDelimiter("_");
		assertEquals("_", f.getDelimiter());
	}

	@Test
	public void testSetDelimiter() {
		testSetDelimiterNullInput();
		testSetDelimiterEmptyInput();
		testSetDelimiterTypicalInput();
	}

	private void testSetDelimiterNullInput() {
		Filter f = new Filter();
		
		try {
			f.setDelimiter(null);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected null delimiter given", ae.getMessage());
		}
	}

	private void testSetDelimiterEmptyInput() {
		Filter f = new Filter();
		
		try {
			f.setDelimiter("");
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected empty delimiter given", ae.getMessage());
		}
	}

	private void testSetDelimiterTypicalInput() {
		// tested in GetDelimiter
	}

	@Test
	public void testGetIgnoreList() {
		testGetIgnoreListWithDefaultConstructor();
		testGetIgnoreListWithCustomConstructor();
	}

	private void testGetIgnoreListWithDefaultConstructor() {
		Filter f = new Filter();
		
		assertEquals(0, f.getIgnoreList().size());		
	}

	private void testGetIgnoreListWithCustomConstructor() {
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("of");
		wordsToIgnore.add("a");
		wordsToIgnore.add("the");
		wordsToIgnore.add("to");
		
		Filter f = new Filter(wordsToIgnore);
		List<String> actualList = f.getIgnoreList();
		assertEquals(4, actualList.size());
		
		for (int i = 0; i < 4 ; i++) {
			assertEquals(wordsToIgnore.get(i), actualList.get(i));
		}
		
	}
	
	@Test
	public void testAddWordToIgnoreList() {
		testAddWordsToIgnoreListNullInput();
		testAddWordsToIgnoreListEmptyInput();
		testAddWordsToIgnoreListTypicalInput();	
		testAddWordsToIgnoreListRepeatedInput();
	}

	private void testAddWordsToIgnoreListNullInput() {
		Filter f = new Filter();
		
		try {
			f.addWordToIgnoreList(null);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected null word given", ae.getMessage());
		}
		
	}

	private void testAddWordsToIgnoreListEmptyInput() {
		Filter f = new Filter();
		
		try {
			f.addWordToIgnoreList("");
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected empty word given", ae.getMessage());
		}
		
	}

	private void testAddWordsToIgnoreListRepeatedInput() {
		Filter f = new Filter();
		f.addWordToIgnoreList("of");
		f.addWordToIgnoreList("Of");
		f.addWordToIgnoreList("OF");
		
		assertEquals(1, f.getIgnoreList().size());
		assertEquals("of", f.getIgnoreList().get(0));
	}

	private void testAddWordsToIgnoreListTypicalInput() {
		Filter f = new Filter();
		f.addWordToIgnoreList("of");
		f.addWordToIgnoreList("a");
		f.addWordToIgnoreList("the");
		f.addWordToIgnoreList("to");
		List<String> actualList = f.getIgnoreList();
		
		assertEquals(4, actualList.size());
		assertEquals("of", actualList.get(0));
		assertEquals("a", actualList.get(1));
		assertEquals("the", actualList.get(2));
		assertEquals("to", actualList.get(3));
	}
	
	@Test
	public void testRemoveWordFromIgnoreList() {
		testRemoveWordFromIgnoreListNullInput();
		testRemoveWordFromIgnoreListNonExistentWord();
		testRemoveWordFromIgnoreListTypicalInput();
	}

	private void testRemoveWordFromIgnoreListNullInput() {
		Filter f = new Filter();
		
		try {
			f.removeWordFromIgnoreList(null);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected null word given", ae.getMessage());
		}
	}
	
	private void testRemoveWordFromIgnoreListNonExistentWord() {
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("of");
		wordsToIgnore.add("a");
		wordsToIgnore.add("the");
		wordsToIgnore.add("to");
		
		Filter f = new Filter(wordsToIgnore);
		f.removeWordFromIgnoreList("_");
		List<String> actualList = f.getIgnoreList();
		
		assertEquals(4, actualList.size());
		assertEquals("of", actualList.get(0));
		assertEquals("a", actualList.get(1));
		assertEquals("the", actualList.get(2));
		assertEquals("to", actualList.get(3));
		
	}

	private void testRemoveWordFromIgnoreListTypicalInput() {
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("of");
		wordsToIgnore.add("a");
		wordsToIgnore.add("the");
		wordsToIgnore.add("to");
		
		Filter f = new Filter(wordsToIgnore);
		f.removeWordFromIgnoreList("of");
		List<String> actualList = f.getIgnoreList();
		
		assertEquals(3, actualList.size());
		assertEquals("a", actualList.get(0));
		assertEquals("the", actualList.get(1));
		assertEquals("to", actualList.get(2));
	}

	@Test
	public void testFilterList() {
		testFilterListNullInput();
		testFilterListEmptyListInput();
		testFilterListTypicalInput();
	}

	private void testFilterListNullInput() {
		Filter f = new Filter();
		
		try {
			f.filterList(null);
			assertFalse("Expected AssertionError", true);
		} catch(AssertionError ae) {
			assertEquals("Unexpected null list to filter", ae.getMessage());
		}
	}

	private void testFilterListEmptyListInput() {
		Filter f = new Filter();
		f.addWordToIgnoreList("to");
		
		List<String> actualList = f.filterList(new ArrayList<String>());
		assertEquals(0, actualList.size());
	}

	private void testFilterListTypicalInput() {
		Filter f = new Filter();
		f.addWordToIgnoreList("the");
		f.addWordToIgnoreList("after");
		List<String> inputList = new ArrayList<String>();
		List<String> expectedList = new ArrayList<String>();
		
		inputList.add("The day After tomorrow");
		inputList.add("day After tomorrow The");
		inputList.add("After tomorrow The day");
		inputList.add("tomorrow The day After");
		
		expectedList.add("day After tomorrow The");
		expectedList.add("tomorrow The day After");
		
		List<String> actualList = f.filterList(inputList);
		
		assertEquals(expectedList.size(), actualList.size());
		for (int i = 0 ; i < expectedList.size() ; i ++) {
			assertEquals(expectedList.get(i), actualList.get(i));
		}
		
	}

}
