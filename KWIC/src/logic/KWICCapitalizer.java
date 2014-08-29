package logic;

import java.util.ArrayList;
import java.util.List;

import dataSource.Data;

/**
 * 
 * @author Bharath
 * 
 */
public class KWICCapitalizer {

	private static Data _data = Data.inst();

	/**
	 * Accepts an input string and capitalizes the first letter of keywords and
	 * converts the other words to lower case.
	 * 
	 * @param input
	 * @param wordsToIgnore
	 * @return
	 */
	private static String capitalize(String input) {

		assert input != null : "Unexpected null string to be capitalized";
		input = input.trim();
		if (input.isEmpty()) {
			return input;
		}

		input = input.replaceAll("\\s+", " ");
		String[] words = input.split(" ");
		String output = "";
		for (String word : words) {
			if (isWordToIgnore(word)) {
				word = convertWordToLowerCase(word);
			} else {
				word = capitalizeFirstLetterOfWord(word);
			}
			output += word + " ";
		}
		output = output.trim();
		return output;
	}

	/**
	 * Capitalizes the current intermediate result in Data
	 * returns a list of string that could not be capitalized
	 * or null if no string was there to be capitalized
	 * @return
	 */
	public static List<String> capitalizeList() {
		List<String> titlesList = _data.getIntermediateList();
		assert titlesList != null : "Unexpected null list to be capitalized";
		if (titlesList.isEmpty()) {
			return null;
		}
		
		List<String> outputList = new ArrayList<String>();
		for (String title : titlesList) {
			outputList.add(capitalize(title));
		}

		_data.setIntermediateList(outputList);

		return outputList; // only for testing purposes
	}

	private static String capitalizeFirstLetterOfWord(String word) {
		Character c = word.charAt(0);
		if (Character.isAlphabetic(c)) {
			word = word.replaceFirst(""+word.charAt(0), ""+Character.toUpperCase(c));
		}
		return word;
	}

	private static String convertWordToLowerCase(String word) {
		word = word.toLowerCase();
		return word;
	}

	private static boolean isWordToIgnore(String word) {
		return _data.getIgnoreWordsList().contains(word.toLowerCase());
	}

}
