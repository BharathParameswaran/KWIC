package logic;

import java.util.ArrayList;
import java.util.List;

import dataSource.Data;

/**
 * This class rotates the words in a given string
 * and generates a list of string
 *  
 * @author thyagesh93
 */
public class KWICRotator {
	
	private static final String DELIMITER = " ";
	private static Data _data = Data.inst();
	
	/**
	 * Rotates the list of new titles added one at a time.
	 * Returns the list that contains all rotated combinations of all the new titles
	 * @return
	 */
	public static List<String> rotateList() {
		List<String> inputList = _data.getIntermediateList();
		assert inputList != null : "Unexpected null list to be capitalized";
		if (inputList.isEmpty()) {
			return null;
		}
		List<String> outputList = new ArrayList<String>();
		
		for (String input : inputList) {
			outputList.addAll(rotate(input));
		}		
		_data.setIntermediateList(outputList);
		
		return outputList; // for testing purposes only
	}

	/**
	 * This function takes in an input and rotates the words
	 * in the string clockwise one word at a time and returns
	 * a list of string formed from this process.
	 * 
	 * @param input
	 * @return List<String>
	 */
	private static List<String> rotate(String input) {
		assert input != null : "Unexpected null input to be rotated";
		assert !input.isEmpty() : "Unexpected empty string to be rotated";
		
		List<String> output = new ArrayList<String>();
		String modifiedInput = input + DELIMITER + input;
		int inputLength = input.length();
		
		output.add(input);
		
		for (int index = input.indexOf(DELIMITER) 
				; index >= 0 
				; index = input.indexOf(DELIMITER, index + 1)) {
			
			String rotatedString = modifiedInput.substring(index + 1, index + inputLength + 1);
			output.add(rotatedString);
		}
	
		return output;
	}
}
