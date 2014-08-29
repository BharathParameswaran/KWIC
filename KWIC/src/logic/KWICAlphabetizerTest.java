package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dataSource.Data;

/**
 * 
 * @author Bharath
 * 
 */
public class KWICAlphabetizerTest {
	Data _data = Data.inst();
	
	@Test
	public void testAlphabetize() {
		testMethodForEmptyArray();
		testMethodForOrderedList();
		testMethodForTypicalInput();
	}

	private void testMethodForEmptyArray() {
		_data.reset();

		assertNull(KWICAlphabetizer.alphabetize());

	}

	private void testMethodForOrderedList() {
		
		_data.reset();
		
		List<String> listToSort = _data.getIntermediateList();
		listToSort.add("Fast and Furious");
		listToSort.add("the Day after Tomorrow");
		listToSort.add("the Man of Steel");

		List<String> orderedList = KWICAlphabetizer.alphabetize();
		String[] expected = { "Fast and Furious", "the Day after Tomorrow",
				"the Man of Steel" };
		int i = 0;
		for (String output : orderedList) {
			assertEquals(expected[i++], output);
		}
	}

	private void testMethodForTypicalInput() {

		Data _data = Data.inst();
		_data.reset();
		List<String> listToSort = _data.getIntermediateList();
		listToSort.add("the Day after Tomorrow");
		listToSort.add("the Man of Steel");
		listToSort.add("Fast and Furious");

		List<String> expected = new ArrayList<String>();
		expected.add("Fast and Furious");
		expected.add("the Day after Tomorrow");
		expected.add("the Man of Steel");
		assertEquals(expected, KWICAlphabetizer.alphabetize());
	}

}
