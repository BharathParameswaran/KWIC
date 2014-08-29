package dataSource;

import java.util.ArrayList;
import java.util.List;

public class Data {
	private List<String> _titlesGiven;
	private List<String> _wordsToIgnore;
	private List<String> _resultList;
	private List<String> _intermediateList;
	private static Data _data;
	private boolean _hasNewWordToIgnore;

	private Data() {
		_titlesGiven = new ArrayList<String>();
		_wordsToIgnore = new ArrayList<String>();
		_resultList = new ArrayList<String>();
		 _intermediateList = new ArrayList<String>();
		 _hasNewWordToIgnore=false;
	}

	public static Data inst() {
		if (_data == null)
			_data = new Data();
		return _data;
	}

	public boolean addTitle(String title) {
		if (!_titlesGiven.contains(title)) {
			_titlesGiven.add(title);
			_intermediateList.add(title);
			return true;
		}
		return false;
	}

	public List<String> addTitles(List<String> titles) {

		List<String> result = new ArrayList<String>();

		for (String title : titles) {
			if (!addTitle(title)) {
				result.add(title);
			}
		}

		return result;

	}

	public boolean addWordToIgnore(String word) {
		
		if (!_wordsToIgnore.contains(word.toLowerCase())) {
			_wordsToIgnore.add(word.toLowerCase());
			_hasNewWordToIgnore = true;
			
			return true;
		}
		return false;
	}

	public List<String> addWordsToIgnore(List<String> words) {
		List<String> result = new ArrayList<String>();

		for (String word : words) {
			if (!addWordToIgnore(word)) {
				result.add(word);
			}
		}
		
		return result;

	}

	/**
	 * Returns the current list of wordsToIgnore
	 * 
	 * @return
	 */

	public List<String> getIgnoreWordsList() {
		return _wordsToIgnore;
	}

	/**
	 * Returns the current list of string for output
	 * 
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

	/**
	 * Returns an intermediate result set for the filters to work on
	 * 
	 * @return
	 */
	public List<String> getIntermediateList() {
		return _intermediateList;
	}

	// --------------------------------------------------

	public void reset() {
		_resultList.clear();
		_intermediateList.clear();
		_titlesGiven.clear();
		_wordsToIgnore.clear();
	}

	public void setResultList(List<String> resultList) {
		this._resultList = resultList;
	}

	public void setIntermediateList(List<String> intermediateList) {
		this._intermediateList = intermediateList;
	}
	
	public void setResultSetToIntermediateResult() {
		_resultList.clear();
		_resultList.addAll(_intermediateList);
		_intermediateList.clear();
	}
	
	private void copyAllTitlesToIntermediateResult() {
		for (String s : _titlesGiven) {
			if (!_intermediateList.contains(s)) {
				_intermediateList.add(s);
			}
		}
	}
	
	public void finalizeInteermediateResult(){
		if(_hasNewWordToIgnore)
			copyAllTitlesToIntermediateResult();
	}

}