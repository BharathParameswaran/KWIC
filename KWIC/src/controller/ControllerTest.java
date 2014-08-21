/**
 * 
 */
package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import logic.Rotator;

import org.junit.Test;

/**
 * @author thyagesh93
 *
 */
public class ControllerTest {

	@Test
	public void testGetIgnoreList() {
		testGetIgnoreListWithDefaultConstructor();
		testGetIgnoreListWithCustomConstructor();
	}

	private void testGetIgnoreListWithDefaultConstructor() {
		Controller controller = new Controller();
		
		assertEquals(0, controller.getIgnoreWordsList().size());		
	}

	private void testGetIgnoreListWithCustomConstructor() {
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("of");
		wordsToIgnore.add("a");
		wordsToIgnore.add("the");
		wordsToIgnore.add("to");
		
		Controller controller = new Controller();
		List<String> actualList = controller.getIgnoreWordsList();
		assertEquals(4, actualList.size());
		
		for (int i = 0; i < 4 ; i++) {
			assertEquals(wordsToIgnore.get(i), actualList.get(i));
		}
	}
	
	@Test
	public void testAddWordToIgnoreList() {
		testAddWordsToIgnoreListNullInput();
		testAddWordsToIgnoreListEmptyInput();
		testAddWordsToIgnoreListTypicalInput();	
		testAddWordsToIgnoreListRepeatedInput();
	}

	private void testAddWordsToIgnoreListNullInput() {
		Controller controller = new Controller();
		
		try {
			controller.addWordToIgnoreList(null);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected null word given", ae.getMessage());
		}
		
	}

	private void testAddWordsToIgnoreListEmptyInput() {
		Controller controller = new Controller();
		
		try {
			controller.addWordToIgnoreList("");
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected empty word given", ae.getMessage());
		}
		
	}

	private void testAddWordsToIgnoreListRepeatedInput() {
		Controller controller = new Controller();
		controller.addWordToIgnoreList("of");
		controller.addWordToIgnoreList("Of");
		controller.addWordToIgnoreList("OF");
		
		assertEquals(1, controller.getIgnoreWordsList().size());
		assertEquals("of", controller.getIgnoreWordsList().get(0));
	}

	private void testAddWordsToIgnoreListTypicalInput() {
		Controller controller = new Controller();
		controller.addWordToIgnoreList("of");
		controller.addWordToIgnoreList("a");
		controller.addWordToIgnoreList("the");
		controller.addWordToIgnoreList("to");
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
		
		try {
			controller.removeWordFromIgnoreList(null);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected null word given", ae.getMessage());
		}
	}
	
	private void testRemoveWordFromIgnoreListNonExistentWord() {
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("of");
		wordsToIgnore.add("a");
		wordsToIgnore.add("the");
		wordsToIgnore.add("to");
		
		Controller controller = new Controller();
		controller.removeWordFromIgnoreList("_");
		List<String> actualList = controller.getIgnoreWordsList();
		
		assertEquals(4, actualList.size());
		assertEquals("of", actualList.get(0));
		assertEquals("a", actualList.get(1));
		assertEquals("the", actualList.get(2));
		assertEquals("to", actualList.get(3));
		
	}

	private void testRemoveWordFromIgnoreListTypicalInput() {
		List<String> wordsToIgnore = new ArrayList<String>();
		wordsToIgnore.add("of");
		wordsToIgnore.add("a");
		wordsToIgnore.add("the");
		wordsToIgnore.add("to");
		
		Controller controller = new Controller();
		controller.removeWordFromIgnoreList("of");
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
	 * Test method for {@link controller.Controller#addWordsToIgnore(java.util.List)}.
	 */
	@Test
	public void testAddWordsToIgnore() {
		fail("Not yet implemented");
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
		
		assertFalse(controller.addWordToIgnoreList(null));
		assertEquals(new ArrayList<String>(), controller.getIgnoreWordsList());		
	}

	private void testAddWordToIgnoreEmptyInput() {
		Controller controller = new Controller();
		
		assertFalse(controller.addWordToIgnoreList(""));
		assertEquals(new ArrayList<String>(), controller.getIgnoreWordsList());
		
		assertFalse(controller.addWordToIgnoreList(" "));
		assertEquals(new ArrayList<String>(), controller.getIgnoreWordsList());
		
	}

	private void testAddWordToIgnoreTypicalInput() {
		Controller controller = new Controller(new Rotator());
		List<String> expectedOutput = new ArrayList<String>();
		
		expectedOutput.add("word");
		assertTrue(controller.addWordToIgnoreList("word"));
		assertEquals(expectedOutput, controller.getIgnoreWordsList());

		expectedOutput.add("newword");
		assertTrue(controller.addWordToIgnoreList("newword"));
		assertEquals(expectedOutput, controller.getIgnoreWordsList());
	}

	/**
	 * Test method for {@link controller.Controller#getCurrentResult()}.
	 */
	@Test
	public void testGetCurrentResult() {
		fail("Not yet implemented");
	}

}
