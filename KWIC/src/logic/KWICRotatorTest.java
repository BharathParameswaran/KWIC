package logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dataSource.Data;
import logic.KWICRotator;

public class KWICRotatorTest {

	Data _data = Data.inst();

	@Test
	public void testRotate() {
		testMethodForEmptyList();
		testMethodForOneWordInput();
		testMethodForTypicalInput();
	}

	private void testMethodForEmptyList() {
		_data.reset();
		assertNull("Unexpected empty string to be rotated",
				KWICRotator.rotateList());

	}

	private void testMethodForOneWordInput() {

		_data.reset();
		_data.getIntermediateList().add("One");
		List<String> outputList = new ArrayList<String>();
		outputList.add("One");
		assertEquals(outputList, KWICRotator.rotateList());

	}

	private void testMethodForTypicalInput() {
		_data.reset();
		String input = "The day After tomorrow";
		_data.getIntermediateList().add(input);

		List<String> expectedOutput = new ArrayList<String>();
		expectedOutput.add("The day After tomorrow");
		expectedOutput.add("day After tomorrow The");
		expectedOutput.add("After tomorrow The day");
		expectedOutput.add("tomorrow The day After");
		assertEquals(expectedOutput, KWICRotator.rotateList());
	}
}
