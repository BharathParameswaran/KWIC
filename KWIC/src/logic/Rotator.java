package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * This class rotates the words in a given string
 * and generates a list of string
 *  
 * @author thyagesh93
 */
public class Rotator {
	private String _delimiter = " ";
	
	public Rotator() {
		
	}
	
	public Rotator(String delimiter) {
		_delimiter = delimiter;
	}
	
	public String getDelimiter() {
		return _delimiter;
	}
	
	/**
	 * This function takes in an input and rotates the words
	 * in the string clockwise one word at a time and returns
	 * a list of string formed from this process.
	 * The output strings are "highlighted" (look at 
	 * the highlight function).
	 * 
	 * @param input
	 * @return List<String>
	 */
	public List<String> rotate(String input) {
		assert input != null : "Unexpected null input to be rotated";
		assert !input.isEmpty() : "Unexpected empty string to be rotated";
		
		List<String> output = new ArrayList<String>();
		String modifiedInput = input + _delimiter + input;
		int inputLength = input.length();
		
		output.add(highlight(input));
		
		for (int index = input.indexOf(_delimiter) 
				; index >= 0 
				; index = input.indexOf(_delimiter, index + 1)) {
			
			String rotatedString = modifiedInput.substring(index + 1, index + inputLength + 1);
			String highlightedString = highlight(rotatedString);
			output.add(highlightedString);
		}
	
		return output;
	}

	/**
	 * This function "highlights the given string
	 * by changing the first word to upper case and
	 * the rest of the words to lower case
	 * @param input
	 * @return
	 */
	private String highlight(String input) {
		String output = new String(input);
		int endOfFirstWord = input.indexOf(" ");
		if (endOfFirstWord >= 0) {
			return output.substring(0, endOfFirstWord).toUpperCase()
					+ output.substring(endOfFirstWord).toLowerCase();
		} else {
			return output.toUpperCase();
		}
	}
}
