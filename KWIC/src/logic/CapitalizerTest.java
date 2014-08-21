package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CapitalizerTest {

	@Test
	public void testCapitalize() {
		testMethodForNullString();
		testMethodForNullIgnoredWordList();
		testMethodForEmptyString();
		testMethodForStringWithNoKeyword();
		testMethodForTypicalInput();
	}

	private void testMethodForNullString() {
		try {
			Capitalizer.capitalize(null, null);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected null string to be capitalized",
					ae.getMessage());
		}

		try {
			List<String> wordsToIgnore = new ArrayList<String>();
			wordsToIgnore.add("of");
			wordsToIgnore.add("a");
			wordsToIgnore.add("the");
			wordsToIgnore.add("to");

			Capitalizer.capitalize(null, wordsToIgnore);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected null string to be capitalized",
					ae.getMessage());
		}

	}

	private void testMethodForNullIgnoredWordList() {

		try {
			Capitalizer.capitalize("Test Input", null);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected null for list of ignored words",
					ae.getMessage());
		}

	}

	private void testMethodForEmptyString() {

		try {
			Capitalizer.capitalize("", null);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected empty string to be capitalized",
					ae.getMessage());
		}
	}
	
	private void testMethodForStringWithNoKeyword() {
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("is");
		wordsToIgnore.add("the");
		wordsToIgnore.add("an");
		String input = "is THE An";
		assertEquals("is the an",Capitalizer.capitalize(input, wordsToIgnore));
		
	}
	
	private void testMethodForTypicalInput() {
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("is");
		wordsToIgnore.add("the");
		wordsToIgnore.add("after");
		String input = "The Day after tomorrow";
		assertEquals("the Day after Tomorrow",Capitalizer.capitalize(input, wordsToIgnore));
		
	}


	
}
