package controller;

import inputReader.FileReader;

import java.util.ArrayList;
import java.util.List;

import logic.Alphabetizer;
import logic.Capitalizer;
import logic.Filter;
import logic.Merger;
import logic.Rotator;

/**
 * This class manages the flow control of information 
 * through the logic components
 * @author thyagesh93
 *
 */
public class Controller {
	private List<String> _titlesGiven;
	private List<String> _wordsToIgnore;
	private List<String> _resultList;
	
	
	public Controller() {
		_titlesGiven = new ArrayList<String>();
		_wordsToIgnore = new ArrayList<String>();
		_resultList = new ArrayList<String>();
	}
	
	public Controller(String titlesFileName, String wordsToIgnoreFileName) {
		this();
		loadInformationFromFiles(titlesFileName, wordsToIgnoreFileName);
	}
	
	public List<String> loadInformationFromFiles(String titlesFileName,
			String wordsToIgnoreFileName) {
		List<String> newIgnoreWords = FileReader.readFromFile(wordsToIgnoreFileName);
		List<String> newTitles = FileReader.readFromFile(titlesFileName);
		
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
		
		List<String> result = new ArrayList<String>();
		List<String> newTitles = new ArrayList<String>();
		
		for (String title : titles) {
			if (!addTitle(title, false)) {
				result.add(title);
			} else {
				newTitles.add(title);
			}
		}
		
		updateResultsListForNewTitles(newTitles);
		return result;
	}
	
	/**
	 * Tries to add the given title,
	 * returns false if there was an issue
	 * @param title
	 * @return
	 */
	private boolean addTitle(String title, boolean updateResult) {
		if (title == null) return false;
		if (title.trim().isEmpty()) return false;
		
		if (!_titlesGiven.contains(title)) {
			_titlesGiven.add(title);
			if (updateResult) {
				updateResultsListForNewTitle(title);
			}
		}
		return true;
	}
	
	public boolean addTitle(String title) {
		return addTitle(title, true);
	}
	
	//--------------------------------------------------
	
	/**
	 * Tries to remove the given title,
	 * if the title is found and removed,
	 * the results are updated and returns true.
	 * 
	 * Returns false if there was an issue
	 * @param title
	 * @return
	 */
	public boolean removeTitle(String title) {
		if (title == null) return false;
		if (title.isEmpty()) return false;
		
		if(_titlesGiven.remove(title)) {
			updateResultsListForRemovedTitle(title);
		}
		
		return true;
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
		
		List<String> result = new ArrayList<String>();
		
		for(String word : words) {
			if(addWordToIgnoreList(word, false)) {
				result.add(word);
			}
		}
		
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
	private boolean addWordToIgnoreList(String word, boolean updateResult) {
		if (word == null) return false;
		if (word.trim().isEmpty()) return false;
		
		if (!_wordsToIgnore.contains(word.toLowerCase())) {
			_wordsToIgnore.add(word.toLowerCase());
			if (updateResult) {
				updateResultsListForAddedIgnoreWord();
			}
		}
		return true;
	}
	
	public boolean addWordToIgnore(String title) {
		return addWordToIgnoreList(title, true);
	}
	
	//--------------------------------------------------

	public boolean removeIgnoreWord(String word) {
		if (word == null) return false;
		if (word.trim().isEmpty()) return false;
		
		_wordsToIgnore.remove(word);		
		return true;
	}
	
	//--------------------------------------------------

	public List<String> getIgnoreWordsList() {
		return _wordsToIgnore;
	}
	
	/**
	 * Returns the current list of
	 * string for output
	 * @return
	 */
	public List<String> getCurrentResult() {
		return _resultList;
	}
	
	/**
	 * Returns all the titles given by user
	 */
	public List<String> getGivenTitles() {
		return _titlesGiven;
	}
	
	//--------------------------------------------------
	
	public void reset() {
		_resultList.clear();
		_titlesGiven.clear();
		_wordsToIgnore.clear();
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
		if (_resultList.isEmpty()) return;
		
		_resultList = Filter.filterList(_resultList, _wordsToIgnore);
		_resultList = Capitalizer.capitalizeList(_resultList, _wordsToIgnore);
	}
	
	/**
	 * This method is run when a new title is provided
	 */
	private void updateResultsListForNewTitles(List<String> newTitles) {
		assert newTitles != null : "Unexpected null list given as titles";
		if (newTitles.isEmpty()) return;
		
		List<String> intermediateResult = Capitalizer.capitalizeList(newTitles, _wordsToIgnore);
		intermediateResult = Rotator.rotateList(intermediateResult);
		intermediateResult = Filter.filterList(intermediateResult, _wordsToIgnore);
		intermediateResult = Alphabetizer.alphabetize(intermediateResult);
		
		_resultList = Merger.mergeTitlesToExistingList(intermediateResult, _resultList);
	}

	private void updateResultsListForNewTitle(String title) {
		assert title != null : "Unexpected null given as title";
		if (title.isEmpty()) return;
		
		List<String> newTitles = new ArrayList<String>();
		newTitles.add(title);
		updateResultsListForNewTitles(newTitles);
	}
	
	/**
	 * This method is run when a title is removed
	 */
	private void updateResultsListForRemovedTitle(String removedTitle) {
		assert removedTitle != null : "Unexpected null given as removed title";
		if (removedTitle.isEmpty()) return;
		
		String capitalizedString= Capitalizer.capitalize(removedTitle, _wordsToIgnore);
		List<String> intermediateResult = Rotator.rotate(capitalizedString);
		List<String> stringsToIgnore = new ArrayList<String>();
		stringsToIgnore.addAll(_wordsToIgnore);
		stringsToIgnore.addAll(intermediateResult);
		
		_resultList = Filter.filterList(_resultList, _wordsToIgnore);
	}
}
