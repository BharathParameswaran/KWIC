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

import controller.Controller;

import org.junit.Test;

/**
 * @author thyagesh93
 *
 */

public class ControllerTest {


	@Test
	public void testLoadInformationFromFiles() {
		// tested in testControllerString() test method
	}
	
	@Test
	public void testControllerString() {
		setupFilesWithValidTitles();
		
		testControllerStringEmptyInput();
		testControllerStringInvalidFilesInput();
		testControllerStringTypicalInput();
		
		clearFilesCreatedForTesting();
	}

	private void testControllerStringEmptyInput() {
		// both empty
		Controller c = new Controller("", "");
		List<String> expectedTitlesList = new ArrayList<String>();
		List<String> expectedIgnoreList = new ArrayList<String>();
		List<String> expectedResultList = new ArrayList<String>();
		
		assertEquals(expectedTitlesList, c.getGivenTitles());
		assertEquals(expectedIgnoreList, c.getIgnoreWordsList());
		assertEquals(expectedResultList, c.getCurrentResult());
		
		// titles empty
		Controller c1 = new Controller("tmp/validInput.txt", "");
		expectedTitlesList.add("Line 1");
		expectedTitlesList.add("Line 2");
		
		expectedResultList.add("1 Line");
		expectedResultList.add("2 Line");
		expectedResultList.add("Line 1");
		expectedResultList.add("Line 2");
		
		assertEquals(expectedTitlesList, c1.getGivenTitles());
		assertEquals(expectedIgnoreList, c1.getIgnoreWordsList());
		assertEquals(expectedResultList, c1.getCurrentResult());
		
		expectedTitlesList.clear();
		expectedResultList.clear();
		
		// ignore words empty
		Controller c2 = new Controller("", "tmp/validInput.txt");
		expectedIgnoreList.add("line 1");
		expectedIgnoreList.add("line 2");
		
		assertEquals(expectedTitlesList, c2.getGivenTitles());
		assertEquals(expectedIgnoreList, c2.getIgnoreWordsList());
		assertEquals(expectedResultList, c2.getCurrentResult());
	}

	private void testControllerStringInvalidFilesInput() {
		// both invalid
		Controller c = new Controller("invalid", "invalid");
		List<String> expectedTitlesList = new ArrayList<String>();
		List<String> expectedIgnoreList = new ArrayList<String>();
		List<String> expectedResultList = new ArrayList<String>();
		
		assertEquals(expectedTitlesList, c.getGivenTitles());
		assertEquals(expectedIgnoreList, c.getIgnoreWordsList());
		assertEquals(expectedResultList, c.getCurrentResult());
		
		// one invalid
		Controller c1 = new Controller("invalid", "");
		
		assertEquals(expectedTitlesList, c1.getGivenTitles());
		assertEquals(expectedIgnoreList, c1.getIgnoreWordsList());
		assertEquals(expectedResultList, c1.getCurrentResult());
	}


	private void testControllerStringTypicalInput() {
		Controller c = new Controller("tmp/validInput.txt", "tmp/validInput.txt");
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
		
		assertEquals(expectedTitlesList, c.getGivenTitles());
		assertEquals(expectedIgnoreList, c.getIgnoreWordsList());
		assertEquals(expectedResultList, c.getCurrentResult());
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
			// TODO Auto-generated catch block
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
	public void testRemoveTitle() {
		// TODO: add this when the function is released
	}
	
	@Test
	public void testGetCurrentResult() {
		// tested in other tests
	}

	/**
	 * Test method {@link controller.Controller#getIgnoreWordsList(java.util.List)}
	 */
	@Test
	public void testGetIgnoreWordsList() {
		testGetIgnoreListWithDefaultConstructor();
		testGetIgnoreListWithCustomConstructor();
	}

	private void testGetIgnoreListWithDefaultConstructor() {
		Controller controller = new Controller();
		
		assertEquals(0, controller.getIgnoreWordsList().size());		
	}

	private void testGetIgnoreListWithCustomConstructor() {
		Controller controller = new Controller();
		List<String> wordsToIgnore = new ArrayList<String>();
		
		wordsToIgnore.add("of");
		wordsToIgnore.add("a");
		wordsToIgnore.add("the");
		wordsToIgnore.add("to");
		controller.addWordsToIgnore(wordsToIgnore);
		
		List<String> actualList = controller.getIgnoreWordsList();
		assertEquals(wordsToIgnore, actualList);
	}

	/**
	 * Test method for {@link controller.Controller#addWordsToIgnore(java.util.List)}.
	 */
	@Test
	public void testAddWordsToIgnore() {
		testAddWordsToIgnoreNullInput();
		testAddWordsToIgnoreEmptyInput();
		testAddWordsToIgnoreTypicalInput();	
		testAddWordsToIgnoreRepeatedInput();
	}

	private void testAddWordsToIgnoreNullInput() {
		Controller controller = new Controller();
		assertNull(controller.addWordsToIgnore(null));
	}
	
	private void testAddWordsToIgnoreEmptyInput() {
		Controller controller = new Controller();
		// should run without any errors
		List<String> result = controller.addWordsToIgnore(new ArrayList<String>());
		assertEquals(0, result.size());
	}

	private void testAddWordsToIgnoreRepeatedInput() {
		Controller controller = new Controller();
		List<String> wordsToAdd = new ArrayList<String>();
		wordsToAdd.add("of");
		wordsToAdd.add("Of");
		wordsToAdd.add("OF");
		
		controller.addWordsToIgnore(wordsToAdd);
		
		assertEquals(1, controller.getIgnoreWordsList().size());
		assertEquals("of", controller.getIgnoreWordsList().get(0));
	}

	private void testAddWordsToIgnoreTypicalInput() {
		Controller controller = new Controller();
		List<String> wordsToAdd = new ArrayList<String>();
		
		wordsToAdd.add("of");
		wordsToAdd.add("a");
		wordsToAdd.add("the");
		wordsToAdd.add(" ");
		controller.addWordsToIgnore(wordsToAdd);
		wordsToAdd.remove(" ");
		
		List<String> actualList = controller.getIgnoreWordsList();
		assertEquals(wordsToAdd, actualList);
	}
	
	/**
	 * Test method for {@link controller.Controller#addIgnoreWord(java.lang.String)}.
	 */
	@Test
	public void testAddWordToIgnore() {
		testAddWordToIgnoreNullInput();
		testAddWordToIgnoreEmptyInput();
		testAddWordToIgnoreTypicalInputWithoutTypicalTitles();
		testAddWordToIgnoreTypicalInputWithTypicalTitles();
	}

	private void testAddWordToIgnoreNullInput() {
		Controller controller = new Controller();
		
		assertFalse(controller.addWordToIgnore(null));
		assertEquals(new ArrayList<String>(), controller.getIgnoreWordsList());		
	}

	private void testAddWordToIgnoreEmptyInput() {
		Controller controller = new Controller();
		
		assertFalse(controller.addWordToIgnore(""));
		assertEquals(new ArrayList<String>(), controller.getIgnoreWordsList());
		
		assertFalse(controller.addWordToIgnore(" "));
		assertEquals(new ArrayList<String>(), controller.getIgnoreWordsList());
		
	}

	private void testAddWordToIgnoreTypicalInputWithoutTypicalTitles() {
		Controller controller = new Controller();
		List<String> expectedOutput = new ArrayList<String>();
		
		expectedOutput.add("word");
		assertTrue(controller.addWordToIgnore("woRd"));
		assertEquals(expectedOutput, controller.getIgnoreWordsList());

		expectedOutput.add("newword");
		assertTrue(controller.addWordToIgnore("neWWord"));
		assertEquals(expectedOutput, controller.getIgnoreWordsList());
	}
	
	private void testAddWordToIgnoreTypicalInputWithTypicalTitles() {
		Controller c = new Controller();
		c.addTitles(generateTypicalTitles());
		
		List<String> ignoreWords = generateTypicalIgnoreWords();
		c.addWordsToIgnore(ignoreWords);
		
		List<String> expectedList = generateResultsListForTypicalInputWithTypicalIgnoreWords();
		assertEquals(expectedList, c.getCurrentResult());
		
	}

	/**
	 * Test method for {@link controller.Controller#removeIgnoreWord(java.util.List)}.
	 */
	@Test
	public void testRemoveWordFromIgnoreList() {
		testRemoveWordFromIgnoreListNullInput();
		testRemoveWordFromIgnoreListEmptyInput();
		testRemoveWordFromIgnoreListNonExistentWord();
		testRemoveWordFromIgnoreListTypicalInput();
	}

	private void testRemoveWordFromIgnoreListNullInput() {
		Controller controller = new Controller();
		assertFalse(controller.removeIgnoreWord(null));
	}

	private void testRemoveWordFromIgnoreListEmptyInput() {
		Controller controller = new Controller();
		
		assertFalse(controller.removeIgnoreWord(""));
		assertEquals(new ArrayList<String>(), controller.getIgnoreWordsList());
		
		assertFalse(controller.removeIgnoreWord(" "));
		assertEquals(new ArrayList<String>(), controller.getIgnoreWordsList());
		
	}
	
	private void testRemoveWordFromIgnoreListNonExistentWord() {
		Controller controller = new Controller();
		
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("of");
		wordsToIgnore.add("a");
		wordsToIgnore.add("the");
		wordsToIgnore.add("to");
		controller.addWordsToIgnore(wordsToIgnore);
		
		controller.removeIgnoreWord("_");
		List<String> actualList = controller.getIgnoreWordsList();
		
		assertEquals(wordsToIgnore, actualList);		
	}

	private void testRemoveWordFromIgnoreListTypicalInput() {
		Controller controller = new Controller();
		
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("of");
		wordsToIgnore.add("a");
		wordsToIgnore.add("the");
		wordsToIgnore.add("to");
		controller.addWordsToIgnore(wordsToIgnore);
		
		controller.removeIgnoreWord("of");
		wordsToIgnore.remove("of");
		
		List<String> actualList = controller.getIgnoreWordsList();
		assertEquals(wordsToIgnore, actualList);
	}
	
	/**
	 * Test method for {@link controller.Controller#getGivenTitles()}.
	 */
	@Test
	public void testGetGivenTitles() {
		testGetGivenTitlesDefaultValue();
		testGetGivenTitlesTypicalSuccess();
	}
	
	private void testGetGivenTitlesDefaultValue() {
		Controller c = new Controller();
		assertEquals(new ArrayList<String>(), c.getGivenTitles());
	}
	
	private void testGetGivenTitlesTypicalSuccess() {
		// tested everywhere else		
	}
	
	/**
	 * Test method for {@link controller.Controller#addTitles(java.util.List)}.
	 */
	@Test
	public void testAddTitles() {
		testAddTitlesForNullInput();
		testAddTitlesForEmptyInput();
		testAddTitlesForTypicalInputWithoutIgnoreWords();
		testAddTitlesForTypicalInputWithIgnoreWords();
	}

	private void testAddTitlesForNullInput() {
		Controller c = new Controller();
		assertNull(c.addTitles(null));
	}

	private void testAddTitlesForEmptyInput() {
		Controller c = new Controller();
		assertEquals(new ArrayList<String>(), c.addTitles(new ArrayList<String>()));
	}

	private void testAddTitlesForTypicalInputWithoutIgnoreWords() {
		Controller c = new Controller();
		List<String> titles = generateTypicalTitles();
		List<String> failingTitles = new ArrayList<String>();
		failingTitles.add(" ");
		failingTitles.add(null);
		assertEquals(failingTitles, c.addTitles(titles));
		
		List<String> expectedList = generateResultsListForTypicalInputWithoutTypicalIgnoreWords();
		assertEquals(expectedList, c.getCurrentResult());
	}

	private void testAddTitlesForTypicalInputWithIgnoreWords() {
		Controller c = new Controller();
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
	 * Test method for {@link controller.Controller#addTitle(java.lang.String)}.
	 */
	@Test
	public void testAddTitle() {
		testAddTitleNullInput();
		testAddTitleEmptyInput();
		testAddTitleRepeatedInput();
		testAddTitleTypicalInput();
	}

	
	private void testAddTitleNullInput() {
		Controller c = new Controller();
		assertFalse(c.addTitle(null));
		assertEquals(new ArrayList<String>(), c.getGivenTitles());
		assertEquals(new ArrayList<String>(), c.getCurrentResult());
	}

	
	private void testAddTitleEmptyInput() {
		Controller c = new Controller();
		assertFalse(c.addTitle(""));
		assertEquals(new ArrayList<String>(), c.getGivenTitles());
		assertEquals(new ArrayList<String>(), c.getCurrentResult());
	}
	
	private void testAddTitleRepeatedInput() {
		Controller c = new Controller();
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
		Controller c = new Controller();
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
}