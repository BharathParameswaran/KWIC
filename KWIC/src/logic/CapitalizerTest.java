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
public class CapitalizerTest {

	@Test
	public void testCapitalize() {
		testMethodForNullString();
		testMethodForNullIgnoredWordList();
		testMethodForEmptyString();
		testMethodForStringWithNoKeyword();
		testMethodForTypicalString();
	}

	@Test
	public void testCapitalizeAll() {
		testMethodForNullList();
		testMethodForEmptyList();
		testMethodForListWithOneString();
		testMethodForTypicalList();
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

		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("of");
		wordsToIgnore.add("a");
		wordsToIgnore.add("the");
		wordsToIgnore.add("to");

		try {
			Capitalizer.capitalize("", wordsToIgnore);
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
		assertEquals("is the an", Capitalizer.capitalize(input, wordsToIgnore));

	}

	private void testMethodForTypicalString() {
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("is");
		wordsToIgnore.add("the");
		wordsToIgnore.add("after");
		String input = "The Day after tomorrow";
		assertEquals("the Day after Tomorrow",
				Capitalizer.capitalize(input, wordsToIgnore));

	}

	private void testMethodForNullList() {
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("is");
		wordsToIgnore.add("the");

		// test with null titlesList and valid wordsToIgnore List
		try {
			Capitalizer.capitalizeList(null, wordsToIgnore);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected null list to be capitalized",
					ae.getMessage());
		}

		List<String> titlesList = new ArrayList<String>();
		titlesList.add("The Day after tomorrow");

		// test with valid titlesList and null wordsToIgnore List
		try {
			Capitalizer.capitalizeList(titlesList, null);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected null for list of ignored words",
					ae.getMessage());
		}

	}

	private void testMethodForEmptyList() {

		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("is");
		wordsToIgnore.add("the");
		List<String> titlesList = new ArrayList<String>();
		try {
			Capitalizer.capitalizeList(titlesList, wordsToIgnore);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected empty list to be capitalized",
					ae.getMessage());
		}

	}

	private void testMethodForListWithOneString() {
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("after");
		wordsToIgnore.add("the");
		List<String> titlesList = new ArrayList<String>();
		titlesList.add("The Day after tomorrow");
		assertEquals("the Day after Tomorrow",
				Capitalizer.capitalizeList(titlesList, wordsToIgnore).get(0));

	}

	private void testMethodForTypicalList() {
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("of");
		wordsToIgnore.add("the");
		wordsToIgnore.add("after");
		wordsToIgnore.add("and");
		List<String> titlesList = new ArrayList<String>();
		titlesList.add("The Day after tomorrow");
		titlesList.add("Fast AND furious");
		titlesList.add("man Of Steel");

		List<String> outputList = new ArrayList<String>();
		outputList.add("the Day after Tomorrow");
		outputList.add("Fast and Furious");
		outputList.add("Man of Steel");
		assertEquals(outputList,
				Capitalizer.capitalizeList(titlesList, wordsToIgnore));
	}

}
