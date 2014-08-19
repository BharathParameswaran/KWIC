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
	
	public void setDelimiter(String delimiter) {
		assert delimiter != null : "Unexpected null delimiter given";
		assert !delimiter.isEmpty() : "Unexpected empty delimiter given";
		
		_delimiter = delimiter;
	}
	
	/**
	 * This function takes in an input and rotates the words
	 * in the string clockwise one word at a time and returns
	 * a list of string formed from this process.
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
		
		output.add(input);
		
		for (int index = input.indexOf(_delimiter) 
				; index >= 0 
				; index = input.indexOf(_delimiter, index + 1)) {
			
			String rotatedString = modifiedInput.substring(index + 1, index + inputLength + 1);
			output.add(rotatedString);
		}
	
		return output;
	}
}
