package logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.KWICRotator;

public class KWICRotatorTest {

	@Test
	public void testRotate() {
		testMethodForNullInput();
		testMethodForEmptyInput();
		testMethodForOneWordInput();
		testMethodForTypicalInput();
	}

	private void testMethodForNullInput() {

		try {
			KWICRotator.rotate(null);
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected null input to be rotated", ae.getMessage());
		}
	}

	private void testMethodForEmptyInput() {

		try {
			KWICRotator.rotate("");
			assertFalse("Expected AssertionError", true);
		} catch (AssertionError ae) {
			assertEquals("Unexpected empty string to be rotated",
					ae.getMessage());
		}

	}

	private void testMethodForOneWordInput() {

		String input = "One";
		String expectedOutput = "One";

		List<String> actual = KWICRotator.rotate(input);
		assertEquals(1, actual.size());
		assertEquals(expectedOutput, actual.get(0));

	}

	private void testMethodForTypicalInput() {

		String input = "The day After tomorrow";
		List<String> expectedOutput = new ArrayList<String>();
		expectedOutput.add("The day After tomorrow");
		expectedOutput.add("day After tomorrow The");
		expectedOutput.add("After tomorrow The day");
		expectedOutput.add("tomorrow The day After");

		List<String> actual = KWICRotator.rotate(input);
		assertEquals(4, actual.size());
		assertEquals(expectedOutput, actual);
	}
}
