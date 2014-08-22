package controller;

import java.util.ArrayList;
import java.util.List;

import logic.Alphabetizer;
import logic.Capitalizer;
import logic.Filter;
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
	}
	
	/**
	 * Tries to add all the given titles,
	 * returns those titles that had issues
	 * @param titles
	 * @return
	 */
	public List<String> addTitles(List<String> titles) {
		List<String> result = new ArrayList<String>();
		for (String title : titles) {
			if (!addTitle(title)) {
				result.add(title);
			}
		}
		
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
		if (title.isEmpty()) return false;
		
		_titlesGiven.add(title);
		return true;
	}
	
	/**
	 * Adds the given word as an ignore Word
	 * Refreshes the output list based on this
	 * addition
	 * @param words
	 * @return
	 */
	public List<String> addWordsToIgnore(List<String> words) {
		List<String> result = new ArrayList<String>();
		
		for(String word : words) {
			if(addWordToIgnoreList(word)) {
				result.add(word);
			}
		}
		
		return result;
	}
	
	/**
	 * Adds the given word as an ignore Word
	 * Refreshes the output list based on this
	 * addition
	 * @param words
	 * @return
	 */
	public boolean addWordToIgnoreList(String word) {
		if (word == null) return false;
		if (word.trim().isEmpty()) return false;
		
		if (!_wordsToIgnore.contains(word.toLowerCase())) {
			_wordsToIgnore.add(word.toLowerCase());
		}
		
		updateResult();
		return true;
	}
	
	public void removeWordFromIgnoreList(String word) {
		assert word != null : "Unexpected null word given";
		
		_wordsToIgnore.remove(word);
		updateResult();
	}
	
	private void updateResult() {
		List<String> intermediateResult = new ArrayList<String>();
		for(String s : _titlesGiven) {
			intermediateResult.add(Capitalizer.capitalize(s, _wordsToIgnore));
		}
		
		intermediateResult = Rotator.rotateList(intermediateResult);
		intermediateResult = Filter.filterList(intermediateResult, _wordsToIgnore);
		intermediateResult = Alphabetizer.alphabetize(intermediateResult);
		// intermediateResult = Merger.merge(intermediateResult, _resultList);
		_resultList = intermediateResult;		
	}

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
}
