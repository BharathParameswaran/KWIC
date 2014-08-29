package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dataSource.Data;

/**
 * 
 * @author Bharath
 *
 */
public class KWICCapitalizerTest {

	@Test
	public void testCapitalizeAll() {
		testMethodForEmptyList();
		testMethodForListWithOneString();
		testMethodForStringWithNoKeyword();
		testMethodForTypicalList();
	}

	private void testMethodForStringWithNoKeyword() {
		Data data = Data.inst();
		data.reset();
		
		List<String> outputTitles = new ArrayList<String>();
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("is");
		wordsToIgnore.add("the");
		wordsToIgnore.add("an");
		
		assertEquals(new ArrayList<String>(), data.addWordsToIgnore(wordsToIgnore));
		assertTrue(data.addTitle("is THE An"));
		outputTitles.add("is the an");
		data.finalizeInteermediateResult();
		
		assertEquals(outputTitles, KWICCapitalizer.capitalizeList());
		assertEquals(outputTitles, data.getIntermediateList());
	}

	private void testMethodForEmptyList() {
		Data.inst().reset();
		try {
			KWICCapitalizer.capitalizeList();
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected empty list to be capitalized",
					ae.getMessage());
		}
	}

	private void testMethodForListWithOneString() {
		Data data = Data.inst();
		data.reset();
		
		List<String> wordsToIgnore = new ArrayList<String>();
		List<String> outputTitles = new ArrayList<String>();
		wordsToIgnore.add("after");
		wordsToIgnore.add("the");
		
		data.addWordsToIgnore(wordsToIgnore);
		
		data.addTitle("The Day after tomorrow");
		outputTitles.add("the Day after Tomorrow");
		data.finalizeInteermediateResult();
		
		assertEquals(outputTitles, KWICCapitalizer.capitalizeList());
		assertEquals(outputTitles, data.getIntermediateList());
	}

	private void testMethodForTypicalList() {
		Data data = Data.inst();
		data.reset();
		
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("of");
		wordsToIgnore.add("the");
		wordsToIgnore.add("after");
		wordsToIgnore.add("and");
		List<String> titlesList = new ArrayList<String>();
		titlesList.add("The Day after tomorrow ?");
		titlesList.add("Fast AND furious");
		titlesList.add("man Of Steel");
		
		data.addWordsToIgnore(wordsToIgnore);
		data.addTitles(titlesList);
		data.finalizeInteermediateResult();

		List<String> outputList = new ArrayList<String>();
		outputList.add("the Day after Tomorrow ?");
		outputList.add("Fast and Furious");
		outputList.add("Man of Steel");
		
		assertEquals(outputList, KWICCapitalizer.capitalizeList());
		assertEquals(outputList, data.getIntermediateList());
	}
}
