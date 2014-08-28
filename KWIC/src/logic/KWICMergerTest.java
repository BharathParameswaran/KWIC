package logic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dataSource.Data;

/**
 * 
 * @author Bharath
 * 
 */
public class KWICMergerTest {

	@Test
	public void testMerger() {
		testMethodForEmptyInput();
		testMethodForCornerCases();
		testMethodForTypicalInput();
	}

	private void testMethodForCornerCases() {
		testMethodForAddingToEndOfList();
		testMethodForAddingToStartOfList();

	}

	private void testMethodForEmptyInput() {
		Data data = Data.inst();
		data.reset();
		List<String> existingTitleList = data.getIntermediateList();
		existingTitleList.add("Man of Steel");
		existingTitleList.add("the Day after Tomorrow");


		List<String> mergedList = new ArrayList<String>();
		mergedList.add("Man of Steel");
		mergedList.add("the Day after Tomorrow");

		assertEquals(mergedList, KWICMerger.mergeTitlesToExistingList());

		assertEquals(mergedList, KWICMerger.mergeTitlesToExistingList());

	}

	private void testMethodForAddingToEndOfList() {
		Data data = Data.inst();
		data.reset();
		List<String> existingTitleList = data.getCurrentResult();
		existingTitleList.add("Fast and Furious");
		existingTitleList.add("Man of Steel");
		List<String> newTitleList = data.getIntermediateList();
		newTitleList.add("the Day after Tomorrow");
		List<String> mergedList = new ArrayList<String>();
		mergedList.add("Fast and Furious");
		mergedList.add("Man of Steel");
		mergedList.add("the Day after Tomorrow");
		assertEquals(mergedList, KWICMerger.mergeTitlesToExistingList());

	}

	private void testMethodForAddingToStartOfList() {
		Data data = Data.inst();
		data.reset();
		List<String> existingTitleList = data.getCurrentResult();
		existingTitleList.add("Fast and Furious");
		existingTitleList.add("Man of Steel");
		List<String> newTitleList = data.getIntermediateList();
		newTitleList.add("Apocalypto");
		List<String> mergedList = new ArrayList<String>();
		mergedList.add("Apocalypto");
		mergedList.add("Fast and Furious");
		mergedList.add("Man of Steel");

		assertEquals(mergedList, KWICMerger.mergeTitlesToExistingList());

	}

	private void testMethodForTypicalInput() {

		Data data= Data.inst();
		data.reset();
		List<String> existingTitleIndex = data.getCurrentResult();
		existingTitleIndex.add("Knight Rises The dark");
		existingTitleIndex.add("Rises The dark Knight");
		existingTitleIndex.add("The dark Knight Rises");

		List<String> newTitleList = data.getIntermediateList();

		newTitleList.add("Knight Lowers The dark");
		newTitleList.add("Lowers The dark Knight");
		newTitleList.add("The dark Knight Lowers");

		List<String> mergedList = new ArrayList<String>();

		mergedList.add("Knight Lowers The dark");
		mergedList.add("Knight Rises The dark");
		mergedList.add("Lowers The dark Knight");
		mergedList.add("Rises The dark Knight");
		mergedList.add("The dark Knight Lowers");
		mergedList.add("The dark Knight Rises");

		assertEquals(mergedList, KWICMerger.mergeTitlesToExistingList());

	}

}
