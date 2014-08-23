/**
 * 
 */
package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;

import org.junit.Test;

/**
 * @author thyagesh93
 *
 */

public class ControllerTest {

	@Test
	public void testControllerStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testLoadInformationFromFiles() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveTitle() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetCurrentResult() {
		fail("Not yet implemented");
	}

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
		assertEquals(4, actualList.size());
		
		for (int i = 0; i < 4 ; i++) {
			assertEquals(wordsToIgnore.get(i), actualList.get(i));
		}
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
		wordsToAdd.add("to");
		controller.addWordsToIgnore(wordsToAdd);
		
		List<String> actualList = controller.getIgnoreWordsList();
		
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
		Controller controller = new Controller();
		assertFalse(controller.removeIgnoreWord(null));
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
		
		assertEquals(4, actualList.size());
		assertEquals("of", actualList.get(0));
		assertEquals("a", actualList.get(1));
		assertEquals("the", actualList.get(2));
		assertEquals("to", actualList.get(3));
		
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
		List<String> actualList = controller.getIgnoreWordsList();
		
		assertEquals(3, actualList.size());
		assertEquals("a", actualList.get(0));
		assertEquals("the", actualList.get(1));
		assertEquals("to", actualList.get(2));
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
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link controller.Controller#addTitle(java.lang.String)}.
	 */
	@Test
	public void testAddTitle() {
		testAddTitleNullInput();
		testAddTitleEmptyInput();
		testAddTitleTypicalInput();
	}

	private void testAddTitleNullInput() {
		Controller c = new Controller();
		assertFalse(c.addTitle(null));
		assertEquals(new ArrayList<String>(), c.getGivenTitles());
	}

	private void testAddTitleEmptyInput() {
		Controller c = new Controller();
		assertFalse(c.addTitle(""));
		assertEquals(new ArrayList<String>(), c.getGivenTitles());
	}

	private void testAddTitleTypicalInput() {
		Controller c = new Controller();
		List<String> expectedOutput = new ArrayList<String>();
		
		expectedOutput.add("The Day after Tomorrow");
		assertTrue(c.addTitle("The Day after Tomorrow"));
		assertEquals(expectedOutput, c.getGivenTitles());
		
		expectedOutput.add("The");
		assertTrue(c.addTitle("The"));
		assertEquals(expectedOutput, c.getGivenTitles());		
	}

	/**
	 * Test method for {@link controller.Controller#addWordToIgnoreList(java.lang.String)}.
	 */
	@Test
	public void testAddWordToIgnore() {
		testAddWordToIgnoreNullInput();
		testAddWordToIgnoreEmptyInput();
		testAddWordToIgnoreTypicalInput();
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

	private void testAddWordToIgnoreTypicalInput() {
		Controller controller = new Controller();
		List<String> expectedOutput = new ArrayList<String>();
		
		expectedOutput.add("word");
		assertTrue(controller.addWordToIgnore("word"));
		assertEquals(expectedOutput, controller.getIgnoreWordsList());

		expectedOutput.add("newword");
		assertTrue(controller.addWordToIgnore("newword"));
		assertEquals(expectedOutput, controller.getIgnoreWordsList());
	}

}