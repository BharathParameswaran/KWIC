package dataSource;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

public class DataTest {
	Data _data = Data.inst();

	@Test
	public void testData() {
		testInstantiator();
		testAddTitle();
		testAddTitles();
		testAddIgnoreWord();
		testAddIgnmoreWords();

	}
	
	@Test
	public void testSetters() {
		
		testResultListSetter();
		testSetResultSetToIntermediateResult();
		testSetIntermediateList();
	}

	private void testAddTitle() {
		testAddNullTitle();
		testAddEmptyTitle();
		testAddTypicalTitle();
	}

	private void testAddTitles() {
		testAddNullList();
		testAddEmptyList();
		testAddTypicalList();
	}

	private void testAddNullList() {
		_data.reset();
		assertNull(_data.addTitles(null));
	}

	private void testAddEmptyList() {
		_data.reset();

		_data.addTitles(new ArrayList<String>());
		assertEquals(new ArrayList<String>(), _data.getGivenTitles());
		assertEquals(new ArrayList<String>(), _data.getIntermediateList());

	}

	private void testAddTypicalList() {

		_data.reset();
		List<String> outputList = new ArrayList<String>();
		String title1 = "Testing for typical title";
		String title2 = "Another typical title";

		outputList.add(title1);
		outputList.add(title2);

		_data.addTitles(outputList);

		assertEquals(outputList, _data.getGivenTitles());
		assertEquals(outputList, _data.getIntermediateList());
	}

	private void testAddNullTitle() {
		_data.reset();
		assertFalse(_data.addTitle(null));
	}

	private void testAddEmptyTitle() {
		_data.reset();
		assertFalse(_data.addTitle("   "));
		assertFalse(_data.addTitle(""));
		assertEquals(new ArrayList<String>(), _data.getIntermediateList());
		assertEquals(new ArrayList<String>(), _data.getGivenTitles());
	}

	private void testAddTypicalTitle() {
		_data.reset();
		List<String> outputList = new ArrayList<String>();
		String title1 = "Testing for typical title";
		String title2 = "Another typical title";
		outputList.add(title1);
		outputList.add(title2);
		assertTrue(_data.addTitle(title1));
		assertTrue(_data.addTitle(title2));
		assertEquals(outputList, _data.getGivenTitles());
		assertEquals(outputList, _data.getIntermediateList());
	}

	private void testInstantiator() {
		Data data1 = Data.inst();
		assertEquals(data1, _data);

	}

	private void testAddIgnoreWord() {
		testAddNullIgnoreWord();
		testAddEmptyIgnoreWord();
		testAddTypicalIgnoreWord();

	}

	private void testAddNullIgnoreWord() {
		_data.reset();
		assertFalse(_data.addWordToIgnore(null));
	}

	private void testAddEmptyIgnoreWord() {
		_data.reset();
		assertFalse(_data.addWordToIgnore("   "));
		assertFalse(_data.addWordToIgnore(""));
		assertEquals(new ArrayList<String>(), _data.getIgnoreWordsList());

	}

	private void testAddTypicalIgnoreWord() {
		_data.reset();
		List<String> outputList = new ArrayList<String>();
		String word1 = "the";
		String word2 = " an ";
		outputList.add(word1);
		outputList.add(word2.trim());
		assertTrue(_data.addWordToIgnore(word1));
		assertTrue(_data.addWordToIgnore(word2));

		assertEquals(outputList, _data.getIgnoreWordsList());
	}

	private void testAddIgnmoreWords() {
		testAddNullIgnoreWordList();
		testAddEmptyIgnoreWordList();
		testAddTypicalIgnoreWordList();

	}

	private void testAddNullIgnoreWordList() {
		_data.reset();
		assertNull(_data.addWordsToIgnore(null));
	}

	private void testAddEmptyIgnoreWordList() {
		_data.reset();

		_data.addWordsToIgnore(new ArrayList<String>());
		assertEquals(new ArrayList<String>(), _data.getIgnoreWordsList());

	}

	private void testAddTypicalIgnoreWordList() {

		_data.reset();
		List<String> outputList = new ArrayList<String>();
		String word1 = "there";
		String word2 = "was";

		outputList.add(word1);
		outputList.add(word2);

		_data.addWordsToIgnore(outputList);
		assertEquals(outputList, _data.getIgnoreWordsList());
	}
	
	private void testResultListSetter() {
		_data.reset();
		List<String> outputList = new ArrayList<String>();
		outputList.add("The day after tomorrow");
		_data.setResultList(outputList);
		assertEquals(outputList, _data.getCurrentResult());
	}
	
	private void testSetResultSetToIntermediateResult() {
		_data.reset();
		List<String> outputList = new ArrayList<String>();
		String title1 = "Testing for typical title";
		String title2 = "Another typical title";

		outputList.add(title1);
		outputList.add(title2);

		_data.addTitles(outputList);
		_data.setResultSetToIntermediateResult();
		assertEquals(outputList, _data.getCurrentResult());
		
	}
	
	private void testSetIntermediateList() {
		_data.reset();
		List<String> outputList = new ArrayList<String>();
		String title1 = "Testing for typical title";
		String title2 = "Another typical title";
		outputList.add(title1);
		outputList.add(title2);
		
		_data.setIntermediateList(outputList);
		assertEquals(outputList, _data.getIntermediateList());
		
	}

}
