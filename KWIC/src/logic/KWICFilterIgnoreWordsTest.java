package logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dataSource.Data;

public class KWICFilterIgnoreWordsTest {

	@Test
	public void testFilterList() {
		testFilterListEmptyListInput();
		testFilterListTypicalInput();
	}

	private void testFilterListEmptyListInput() {
		Data data = Data.inst();
		data.reset();
		// both lists are empty
		List<String> actualList = KWICFilterIgnoreWords.filterList();
		assertEquals(0, actualList.size());
		assertEquals(0, data.getIntermediateList().size());
		
		// list to filter is empty
		data.addWordToIgnore("to");
		
		actualList = KWICFilterIgnoreWords.filterList();
		assertEquals(0, actualList.size());
		assertEquals(0, data.getIntermediateList().size());
		
		// ignore words is empty
		data.reset();
		List<String> inputList = new ArrayList<String>();
		
		inputList.add("The day After tomorrow");
		inputList.add("day After tomorrow The");
		inputList.add("After tomorrow The day");
		inputList.add("tomorrow The day After");
		
		data.addTitles(inputList);
		actualList = KWICFilterIgnoreWords.filterList();
		
		assertEquals(inputList, actualList);
		assertEquals(inputList, data.getIntermediateList());
	}

	private void testFilterListTypicalInput() {
		Data data = Data.inst();
		data.reset();
		List<String> inputList = new ArrayList<String>();
		List<String> expectedList = new ArrayList<String>();
		
		assertTrue(data.addWordToIgnore("the"));
		assertTrue(data.addWordToIgnore("after"));
		
		inputList.add("The day After tomorrow");
		inputList.add("day After tomorrow The");
		inputList.add("After tomorrow The day");
		inputList.add("tomorrow The day After");
		assertEquals(new ArrayList<String>(), data.addTitles(inputList));
		
		expectedList.add("day After tomorrow The");
		expectedList.add("tomorrow The day After");
		
		assertEquals(expectedList, KWICFilterIgnoreWords.filterList());
		assertEquals(expectedList, data.getIntermediateList());

	}

}
