package controller;

import inputReader.KWICFileReader;

import java.util.ArrayList;
import java.util.List;
import dataSource.Data;
import logic.KWICAlphabetizer;
import logic.KWICCapitalizer;
import logic.KWICFilterIgnoreWords;
import logic.KWICMerger;
import logic.KWICRotator;

/**
 * This class manages the flow control of information 
 * through the logic components
 * @author thyagesh93
 *
 */
public class KWICController {
	 Data _data;
	
	
	public KWICController() {
		
		_data = Data.inst();
	}
	
	public KWICController(String titlesFileName, String wordsToIgnoreFileName) {
		this();
		loadInformationFromFiles(titlesFileName, wordsToIgnoreFileName);
	}
	
	public List<String> loadInformationFromFiles(String titlesFileName,
			String wordsToIgnoreFileName) {
		List<String> newIgnoreWords = KWICFileReader.readFromFile(wordsToIgnoreFileName);
		List<String> newTitles = KWICFileReader.readFromFile(titlesFileName);
		
		boolean couldNotLoadIgnoreWords = newIgnoreWords == null && !wordsToIgnoreFileName.isEmpty();
		boolean couldNotLoadTitles = newTitles == null && !titlesFileName.isEmpty();
		
		if (couldNotLoadIgnoreWords || couldNotLoadTitles) {
			return null;
		}
		
		List<String> erroneousStrings = new ArrayList<String>();
		List<String> errors = addWordsToIgnore(newIgnoreWords);
		if (errors != null) {
			erroneousStrings.addAll(errors);
		}
		errors = addTitles(newTitles);
		
		if (errors != null) {
			erroneousStrings.addAll(errors);
		}
		
		return erroneousStrings;
	}
	
	//--------------------------------------------------

	/**
	 * Tries to add all the given titles,
	 * returns those titles that had issues
	 * @param titles
	 * @return
	 */
	public List<String> addTitles(List<String> titles) {
		if (titles == null) return null;
		
		List<String> result = _data.addTitles(titles);
		updateData(true);
		return result;
	}
	
	/**
	 * Tries to add the given title,
	 * returns false if there was an issue
	 * @param title
	 * @return
	 */
	public boolean addTitle(String title) {
		if (title == null) return false;
		if (title.trim().isEmpty()) return false;	
		if(_data.addTitle(title)) {
				updateData(true);
		}
		return true;
	}

	private void updateData(boolean isMerge) {
		KWICCapitalizer.capitalizeList();
		KWICRotator.rotateList();
		KWICFilterIgnoreWords.filterList();
		KWICAlphabetizer.alphabetize();
		if(isMerge)
		KWICMerger.mergeTitlesToExistingList();
		else
			_data.setResultSetToIntermediateResult();
	}
	
	//--------------------------------------------------
	
	/**
	 * Adds the given word as an ignore Word
	 * Refreshes the output list based on this
	 * addition
	 * @param words
	 * @return
	 */
	public List<String> addWordsToIgnore(List<String> words) {
		if (words ==  null) return null;
		
		List<String> result = _data.addWordsToIgnore(words);
		_data.copyAllTitlesToIntermediateResult();
		updateResultsListForAddedIgnoreWord();
		return result;
	}
	
	/**
	 * Adds the given word as an ignore Word
	 * Refreshes the output list based on this
	 * addition
	 * @param words
	 * @return
	 */
	public boolean addWordToIgnore(String word) {
		if (word == null) return false;
		if (word.trim().isEmpty()) return false;
		
		if(_data.addWordToIgnore(word)) {			
				updateResultsListForAddedIgnoreWord();
		}
		return true;
	}
	
	
	///////////////////////////////////////////////////////////
	///////////// Routines using the components ///////////////
	///////////////////////////////////////////////////////////
	
	/**
	 * This method is run when a new ignore word 
	 * has been added.
	 * 
	 * @Precondition: 
	 * 		Assumes that the new word has
	 * 		been added to the field _wordsToIgnore
	 */
	private void updateResultsListForAddedIgnoreWord() {
		if (_data.getCurrentResult().isEmpty()) return;		
		updateData(false);
	}
	
}
