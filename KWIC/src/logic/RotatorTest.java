package logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.Rotator;

public class RotatorTest {

	@Test
	public void testGetDelimiter() {
		testMethodForDefaultConstructor();
		testMethodForCustomConstructor();
	}

	private void testMethodForDefaultConstructor() {
		Rotator r = new Rotator();
		
		assertEquals(" ", r.getDelimiter());
	}

	private void testMethodForCustomConstructor() {
		String testDelimiter1 = "_";
		
		Rotator r = new Rotator(testDelimiter1);
		assertEquals("_", r.getDelimiter());
	}

	@Test
	public void testRotate() {
		testMethodForNullInput();
		testMethodForEmptyInput();
		testMethodForOneWordInput();
		testMethodForTypicalInput();
		
	}

	private void testMethodForNullInput() {
		Rotator r = new Rotator();
		
		try {
			r.rotate(null);
		} catch(AssertionError ae) {
			assertEquals("Unexpected null input to be rotated", ae.getMessage());
		}
	}

	private void testMethodForEmptyInput() {
		Rotator r = new Rotator();
		
		try {
			r.rotate("");
		} catch(AssertionError ae) {
			assertEquals("Unexpected empty string to be rotated", ae.getMessage());
		}
		
	}

	private void testMethodForOneWordInput() {
		Rotator r = new Rotator();
		String input = "one";
		String expectedOutput = "ONE";
		
		List<String> actual = r.rotate(input);
		assertEquals(1, actual.size());
		assertEquals(expectedOutput, actual.get(0));
		
	}

	private void testMethodForTypicalInput() {
		Rotator r = new Rotator();
		String input = "The Day After Tomorrow";
		List<String> expectedOutput = new ArrayList<String>();
		expectedOutput.add("THE day after tomorrow");
		expectedOutput.add("DAY after tomorrow the");
		expectedOutput.add("AFTER tomorrow the day");
		expectedOutput.add("TOMORROW the day after");
		
		List<String> actual = r.rotate(input);
		assertEquals(4, actual.size());
		for (int i = 0; i < 4; i++) {
			assertEquals(expectedOutput.get(i), actual.get(i));
		}
	}
	
	@Test
	public void testSetDelimiter() {
		testSetDelimiterNullInput();
		testSetDelimiterEmptyInput();
		testSetDelimiterTypicalInput();
	}

	private void testSetDelimiterNullInput() {
		Rotator r = new Rotator();
		
		try {
			r.setDelimiter(null);
		} catch(AssertionError ae) {
			assertEquals("Unexpected null delimiter given", ae.getMessage());
		}
		
	}

	private void testSetDelimiterEmptyInput() {
		Rotator r = new Rotator();
		
		try {
			r.setDelimiter("");
		} catch(AssertionError ae) {
			assertEquals("Unexpected empty delimiter given", ae.getMessage());
		}
		
	}

	private void testSetDelimiterTypicalInput() {
		Rotator r = new Rotator();
		String delimiter1 = "_";
		
		r.setDelimiter(delimiter1);
		assertEquals(delimiter1, r.getDelimiter());
	}
}
