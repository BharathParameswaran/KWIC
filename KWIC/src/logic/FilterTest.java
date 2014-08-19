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
		fail("Not yet implemented");
	}
	
	@Test
	public void testFilterList() {
		fail("Not yet implemented");
	}

}
