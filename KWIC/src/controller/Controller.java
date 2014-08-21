package controller;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

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
	private Rotator _rotator;
	
	
	public Controller() {
		_titlesGiven = new ArrayList<String>();
		_rotator = new Rotator();
	}
	
	/**
	 * Allows invocation to specify dependencies
	 */
	public Controller(Rotator rotator) {
		_titlesGiven = new ArrayList<String>();
		
		if (rotator == null) {
			throw new InvalidParameterException("Given rotator was null");
		}
		
		_rotator = rotator;
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
		return true;
	}
	
	public void removeWordFromIgnoreList(String word) {
		assert word != null : "Unexpected null word given";
		
		_wordsToIgnore.remove(word);
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
		return new ArrayList<String>();
	}
	
	/**
	 * Returns all the titles given by user
	 */
	public List<String> getGivenTitles() {
		return _titlesGiven;
	}
}
