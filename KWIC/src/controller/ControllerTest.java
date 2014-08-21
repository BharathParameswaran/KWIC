/**
 * 
 */
package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import logic.Filter;
import logic.Rotator;

import org.junit.Test;

/**
 * @author thyagesh93
 *
 */
public class ControllerTest {

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
	 * Test method for {@link controller.Controller#addWordToIgnore(java.lang.String)}.
	 */
	@Test
	public void testAddWordToIgnore() {
		testAddWordToIgnoreNullInput();
		testAddWordToIgnoreEmptyInput();
		testAddWordToIgnoreTypicalInput();
	}

	private void testAddWordToIgnoreNullInput() {
		Filter filter = new Filter();
		Controller c = new Controller(new Rotator(), filter);
		
		assertFalse(c.addWordToIgnore(null));
		assertEquals(new ArrayList<String>(), filter.getIgnoreList());		
	}

	private void testAddWordToIgnoreEmptyInput() {
		Filter filter = new Filter();
		Controller c = new Controller(new Rotator(), filter);
		
		assertFalse(c.addWordToIgnore(""));
		assertEquals(new ArrayList<String>(), filter.getIgnoreList());
		
		assertFalse(c.addWordToIgnore(" "));
		assertEquals(new ArrayList<String>(), filter.getIgnoreList());
		
	}

	private void testAddWordToIgnoreTypicalInput() {
		Filter filter = new Filter();
		Controller c = new Controller(new Rotator(), filter);
		List<String> expectedOutput = new ArrayList<String>();
		
		expectedOutput.add("word");
		assertTrue(c.addWordToIgnore("word"));
		assertEquals(expectedOutput, filter.getIgnoreList());

		expectedOutput.add("newword");
		assertTrue(c.addWordToIgnore("newword"));
		assertEquals(expectedOutput, filter.getIgnoreList());
	}

	/**
	 * Test method for {@link controller.Controller#getCurrentResult()}.
	 */
	@Test
	public void testGetCurrentResult() {
		fail("Not yet implemented");
	}

}
