/**
 * 
 */
package controller;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.KWICController;

import org.junit.Test;

import dataSource.Data;

/**
 * @author thyagesh93
 *
 */

public class KWICControllerTest {


	@Test
	public void testLoadInformationFromFiles() {
		setupFilesWithValidTitles();
		
		testLoadInformationFromFilesEmptyInput();
		testLoadInformationFromFilesInvalidFilesInput();
		testLoadInformationFromFilesTypicalInput();
		
		clearFilesCreatedForTesting();
	}

	private void testLoadInformationFromFilesEmptyInput() {
		// both empty
		Data data = Data.inst();
		data.reset();
		KWICController c = new KWICController();
		
		List<String> expectedTitlesList = new ArrayList<String>();
		List<String> expectedIgnoreList = new ArrayList<String>();
		List<String> expectedResultList = new ArrayList<String>();
		
		assertEquals(expectedTitlesList, data.getGivenTitles());
		assertEquals(expectedIgnoreList, data.getIgnoreWordsList());
		assertEquals(expectedResultList, data.getCurrentResult());
		
		// titles empty
		c.loadInformationFromFiles("tmp/validInput.txt", "");
		expectedTitlesList.add("Line 1");
		expectedTitlesList.add("Line 2");
		
		expectedResultList.add("1 Line");
		expectedResultList.add("2 Line");
		expectedResultList.add("Line 1");
		expectedResultList.add("Line 2");
		
		assertEquals(expectedTitlesList, data.getGivenTitles());
		assertEquals(expectedIgnoreList, data.getIgnoreWordsList());
		assertEquals(expectedResultList, data.getCurrentResult());
		
		expectedTitlesList.clear();
		expectedResultList.clear();
		data.reset();
		
		// ignore words empty
		c.loadInformationFromFiles("", "tmp/validInput.txt");
		expectedIgnoreList.add("line 1");
		expectedIgnoreList.add("line 2");
		
		assertEquals(expectedTitlesList, data.getGivenTitles());
		assertEquals(expectedIgnoreList, data.getIgnoreWordsList());
		assertEquals(expectedResultList, data.getCurrentResult());
		data.reset();
	}

	private void testLoadInformationFromFilesInvalidFilesInput() {
		// both invalid
		Data data = Data.inst();
		data.reset();
		
		KWICController c = new KWICController();
		c.loadInformationFromFiles("invalid", "invalid");
		
		List<String> expectedTitlesList = new ArrayList<String>();
		List<String> expectedIgnoreList = new ArrayList<String>();
		List<String> expectedResultList = new ArrayList<String>();
		
		assertEquals(expectedTitlesList, data.getGivenTitles());
		assertEquals(expectedIgnoreList, data.getIgnoreWordsList());
		assertEquals(expectedResultList, data.getCurrentResult());
		
		// one invalid
		c.loadInformationFromFiles("invalid", "");
		
		assertEquals(expectedTitlesList, data.getGivenTitles());
		assertEquals(expectedIgnoreList, data.getIgnoreWordsList());
		assertEquals(expectedResultList, data.getCurrentResult());
	}


	private void testLoadInformationFromFilesTypicalInput() {
		Data data = Data.inst();
		data.reset();
		
		KWICController c = new KWICController();
		c.loadInformationFromFiles("tmp/validInput.txt", "tmp/validInput.txt");
		
		List<String> expectedTitlesList = new ArrayList<String>();
		List<String> expectedIgnoreList = new ArrayList<String>();
		List<String> expectedResultList = new ArrayList<String>();
		
		expectedTitlesList.add("Line 1");
		expectedTitlesList.add("Line 2");
		
		expectedIgnoreList.add("line 1");
		expectedIgnoreList.add("line 2");
		
		expectedResultList.add("1 Line");
		expectedResultList.add("2 Line");
		expectedResultList.add("Line 1");
		expectedResultList.add("Line 2");
		
		assertEquals(expectedTitlesList, data.getGivenTitles());
		assertEquals(expectedIgnoreList, data.getIgnoreWordsList());
		assertEquals(expectedResultList, data.getCurrentResult());
	}

	private void setupFilesWithValidTitles() {
		createRequiredFiles();
		addLinesToFile("tmp/validInput.txt");
	}

	private void clearFilesCreatedForTesting() {
		deleteTmpFiles();		
	}

	private void addLinesToFile(String fileName) {
		File file = new File(fileName);
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Line 1\n");
			bw.write("Line 2\n");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void createRequiredFiles() {
		File theDir = new File("tmp");
		if (!theDir.exists()) {
			try {
				theDir.mkdir();
			} catch (SecurityException se) {

			}
		}

		createEmptyFile("tmp/validInput.txt");
	}

	private void createEmptyFile(String fileName) {
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Temporary Files could not be created!");
		}
	}

	private void deleteTmpFiles() {
		File f = new File("tmp");
		if (f.isDirectory()) {
			for (File c : f.listFiles())
				c.delete();
		}
		f.delete();
	}
	
	@Test
	public void testGetCurrentResult() {
		// tested in other tests
	}

	/**
	 * Test method {@link controller.KWICController#getIgnoreWordsList(java.util.List)}
	 */
	@Test
	public void testGetIgnoreWordsList() {
		Data data = Data.inst();
		data.reset();
		
		KWICController c = new KWICController();
		data.addWordToIgnore("test");
		data.addWordToIgnore("test2");
		
		assertEquals(data.getIgnoreWordsList(), c.getIgnoreWordsList());
	}

	/**
	 * Test method for {@link controller.KWICController#addWordsToIgnore(java.util.List)}.
	 */
	@Test
	public void testAddWordsToIgnore() {
		testAddWordsToIgnoreNullInput();
		testAddWordsToIgnoreEmptyInput();
		testAddWordsToIgnoreTypicalInput();	
		testAddWordsToIgnoreRepeatedInput();
	}

	private void testAddWordsToIgnoreNullInput() {
		Data.inst().reset();
		KWICController controller = new KWICController();
		assertNull(controller.addWordsToIgnore(null));
		assertEquals(new ArrayList<String>(), Data.inst().getIgnoreWordsList());
	}
	
	private void testAddWordsToIgnoreEmptyInput() {
		Data.inst().reset();
		KWICController controller = new KWICController();
		// should run without any errors
		List<String> result = controller.addWordsToIgnore(new ArrayList<String>());
		assertEquals(0, result.size());
		assertEquals(new ArrayList<String>(), Data.inst().getIgnoreWordsList());
	}

	private void testAddWordsToIgnoreRepeatedInput() {
		Data.inst().reset();
		KWICController controller = new KWICController();
		List<String> wordsToAdd = new ArrayList<String>();
		wordsToAdd.add("of");
		wordsToAdd.add("Of");
		wordsToAdd.add("OF");
		
		assertEquals(new ArrayList<String>(), controller.addWordsToIgnore(wordsToAdd));
		
		List<String> expectedOutputList = new ArrayList<String>();
		expectedOutputList.add("of");
		
		assertEquals(expectedOutputList, controller.getIgnoreWordsList());
	}

	private void testAddWordsToIgnoreTypicalInput() {
		Data.inst().reset();
		KWICController controller = new KWICController();
		List<String> wordsToAdd = new ArrayList<String>();
		List<String> wordsWithError = new ArrayList<String>();
		
		wordsToAdd.add("of");
		wordsToAdd.add("a");
		wordsToAdd.add("the");
		wordsToAdd.add(" ");
		wordsToAdd.add(null);
		wordsWithError.add(" ");
		wordsWithError.add(null);
		assertEquals(wordsWithError, controller.addWordsToIgnore(wordsToAdd));
		wordsToAdd.remove(" ");
		wordsToAdd.remove(null);
		
		assertEquals(wordsToAdd, controller.getIgnoreWordsList());
	}
	
	/**
	 * Test method for {@link controller.KWICController#addIgnoreWord(java.lang.String)}.
	 */
	@Test
	public void testAddWordToIgnore() {
		testAddWordToIgnoreNullInput();
		testAddWordToIgnoreEmptyInput();
		testAddWordToIgnoreTypicalInputWithoutTypicalTitles();
		testAddWordToIgnoreTypicalInputWithTypicalTitles();
	}

	private void testAddWordToIgnoreNullInput() {
		Data.inst().reset();
		KWICController controller = new KWICController();
		
		assertFalse(controller.addWordToIgnore(null));
		assertEquals(new ArrayList<String>(), controller.getIgnoreWordsList());
	}

	private void testAddWordToIgnoreEmptyInput() {
		Data.inst().reset();
		KWICController controller = new KWICController();
		
		assertFalse(controller.addWordToIgnore(""));
		assertEquals(new ArrayList<String>(), controller.getIgnoreWordsList());
		
		assertFalse(controller.addWordToIgnore(" "));
		assertEquals(new ArrayList<String>(), controller.getIgnoreWordsList());
		
	}

	private void testAddWordToIgnoreTypicalInputWithoutTypicalTitles() {
		Data.inst().reset();
		KWICController controller = new KWICController();
		List<String> expectedOutput = new ArrayList<String>();
		
		expectedOutput.add("word");
		assertTrue(controller.addWordToIgnore("woRd"));
		assertEquals(expectedOutput, controller.getIgnoreWordsList());

		expectedOutput.add("newword");
		assertTrue(controller.addWordToIgnore("neWWord"));
		assertEquals(expectedOutput, controller.getIgnoreWordsList());
	}
	
	private void testAddWordToIgnoreTypicalInputWithTypicalTitles() {
		Data.inst().reset();
		KWICController c = new KWICController();
		c.addTitles(generateTypicalTitles());
		
		List<String> ignoreWords = generateTypicalIgnoreWords();
		c.addWordsToIgnore(ignoreWords);
		
		List<String> expectedList = generateResultsListForTypicalInputWithTypicalIgnoreWords();
		assertEquals(expectedList, c.getCurrentResult());		
	}
	
	/**
	 * Test method for {@link controller.KWICController#getGivenTitles()}.
	 */
	@Test
	public void testGetGivenTitles() {
		Data data = Data.inst();
		KWICController c = new KWICController();
		data.reset();
		data.addTitle("test");
		data.addTitle("test2");
		
		assertEquals(data.getGivenTitles(), c.getGivenTitles());
	}
	
	/**
	 * Test method for {@link controller.KWICController#addTitles(java.util.List)}.
	 */
	@Test
	public void testAddTitles() {
		testAddTitlesForNullInput();
		testAddTitlesForEmptyInput();
		testAddTitlesForTypicalInputWithoutIgnoreWords();
		testAddTitlesForTypicalInputWithIgnoreWords();
	}

	private void testAddTitlesForNullInput() {
		Data.inst().reset();
		KWICController c = new KWICController();
		assertNull(c.addTitles(null));
		assertEquals(new ArrayList<String>(), Data.inst().getGivenTitles());
	}

	private void testAddTitlesForEmptyInput() {
		Data.inst().reset();
		KWICController c = new KWICController();
		
		assertEquals(new ArrayList<String>(), c.addTitles(new ArrayList<String>()));
		assertEquals(new ArrayList<String>(), Data.inst().getGivenTitles());
	}

	private void testAddTitlesForTypicalInputWithoutIgnoreWords() {
		KWICController c = new KWICController();
		List<String> titles = generateTypicalTitles();
		List<String> failingTitles = new ArrayList<String>();
		failingTitles.add(" ");
		failingTitles.add(null);
		assertEquals(failingTitles, c.addTitles(titles));
		
		List<String> expectedList = generateResultsListForTypicalInputWithoutTypicalIgnoreWords();
		assertEquals(expectedList, c.getCurrentResult());
	}

	private void testAddTitlesForTypicalInputWithIgnoreWords() {
		KWICController c = new KWICController();
		c.addWordsToIgnore(generateTypicalIgnoreWords());
		
		List<String> titles = generateTypicalTitles();
		List<String> failingTitles = new ArrayList<String>();
		failingTitles.add(" ");
		failingTitles.add(null);
		assertEquals(failingTitles, c.addTitles(titles));
		
		List<String> expectedList = generateResultsListForTypicalInputWithTypicalIgnoreWords();
		assertEquals(expectedList, c.getCurrentResult());
	}

	private List<String> generateTypicalIgnoreWords() {
		List<String> ignoreWords = new ArrayList<String>();
		ignoreWords.add("of");
		ignoreWords.add("the");
		ignoreWords.add("after");
		ignoreWords.add("to");
		
		return ignoreWords;
	}

	private List<String> generateTypicalTitles() {
		List<String> titles = new ArrayList<String>();
		titles.add("The Day after Tomorrow");
		titles.add("Mission Impossible II");
		titles.add("Journey to the Center of Earth");
		
		titles.add(" ");
		titles.add(null);
		
		return titles;
	}

	private List<String> generateResultsListForTypicalInputWithTypicalIgnoreWords() {
		List<String> resultList = new ArrayList<String>();
		
		resultList.add("Day after Tomorrow the");
		resultList.add("Tomorrow the Day after");
		
		resultList.add("Mission Impossible II");
		resultList.add("Impossible II Mission");
		resultList.add("II Mission Impossible");
		
		resultList.add("Journey to the Center of Earth");
		resultList.add("Center of Earth Journey to the");
		resultList.add("Earth Journey to the Center of");
		
		Collections.sort(resultList, String.CASE_INSENSITIVE_ORDER);
		return resultList;
	}
	
	private List<String> generateResultsListForTypicalInputWithoutTypicalIgnoreWords() {
		List<String> resultList = new ArrayList<String>();
		
		resultList.add("The Day After Tomorrow");
		resultList.add("Day After Tomorrow The");
		resultList.add("After Tomorrow The Day");
		resultList.add("Tomorrow The Day After");
		
		resultList.add("Mission Impossible II");
		resultList.add("Impossible II Mission");
		resultList.add("II Mission Impossible");
		
		resultList.add("Journey To The Center Of Earth");
		resultList.add("To The Center Of Earth Journey");
		resultList.add("The Center Of Earth Journey To");
		resultList.add("Center Of Earth Journey To The");
		resultList.add("Of Earth Journey To The Center");
		resultList.add("Earth Journey To The Center Of");
		
		Collections.sort(resultList, String.CASE_INSENSITIVE_ORDER);
		return resultList;
	}

	/**
	 * Test method for {@link controller.KWICController#addTitle(java.lang.String)}.
	 */
	@Test
	public void testAddTitle() {
		testAddTitleNullInput();
		testAddTitleEmptyInput();
		testAddTitleRepeatedInput();
		testAddTitleTypicalInput();
	}

	
	private void testAddTitleNullInput() {
		KWICController c = new KWICController();
		assertFalse(c.addTitle(null));
		assertEquals(new ArrayList<String>(), c.getGivenTitles());
		assertEquals(new ArrayList<String>(), c.getCurrentResult());
	}

	
	private void testAddTitleEmptyInput() {
		KWICController c = new KWICController();
		assertFalse(c.addTitle(""));
		assertEquals(new ArrayList<String>(), c.getGivenTitles());
		assertEquals(new ArrayList<String>(), c.getCurrentResult());
	}
	
	private void testAddTitleRepeatedInput() {
		KWICController c = new KWICController();
		List<String> expectedResult = new ArrayList<String>();
		expectedResult.add("Day The");
		expectedResult.add("The Day");
		
		c.addTitle("The Day");
		assertEquals(expectedResult,c.getCurrentResult());
		
		// repeat the input, output should not change
		c.addTitle("The Day");
		assertEquals(expectedResult,c.getCurrentResult());
	}

	
	private void testAddTitleTypicalInput() {
		Data.inst().reset();
		KWICController c = new KWICController();
		List<String> expectedTitleList = new ArrayList<String>();
		List<String> expectedResultList = new ArrayList<String>();
		
		expectedTitleList.add("the Day after Tomorrow");
		
		expectedResultList.add("The Day After Tomorrow");
		expectedResultList.add("Day After Tomorrow The");
		expectedResultList.add("After Tomorrow The Day");
		expectedResultList.add("Tomorrow The Day After");
		Collections.sort(expectedResultList, String.CASE_INSENSITIVE_ORDER);
		
		assertTrue(c.addTitle("the Day after Tomorrow"));
		assertEquals(expectedTitleList, c.getGivenTitles());
		assertEquals(expectedResultList, c.getCurrentResult());
		
		expectedTitleList.add("Word");
		expectedResultList.add("Word");
		
		assertTrue(c.addTitle("Word"));
		assertEquals(expectedTitleList, c.getGivenTitles());		
		assertEquals(expectedResultList, c.getCurrentResult());
	}
	
	@Test
	public void testReset() {
		Data data = Data.inst();
		KWICController c = new KWICController();
		data.addTitle("a");
		c.reset();
		
		assertEquals(new ArrayList<String>(), data.getGivenTitles());
		
	}
}