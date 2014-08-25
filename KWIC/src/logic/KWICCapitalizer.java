package logic;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Bharath
 *
 */
public class KWICCapitalizer {

	/**
	 * Accepts an input string and capitalizes the first letter of keywords and 
	 * converts the other words to lower case 
	 * @param input
	 * @param wordsToIgnore
	 * @return
	 */
	public static String capitalize(String input, List<String> wordsToIgnore) {
		assert input != null : "Unexpected null string to be capitalized";
		assert input.trim().length()!=0 : "Unexpected empty string to be capitalized";
		assert wordsToIgnore != null : "Unexpected null for list of ignored words";
		String[] words = input.split(" ");
		String output = "";
		for (String word : words) {
			if (isWordToIgnore(wordsToIgnore, word)) {
				word = convertWordToLowerCase(word);

			} else {
				word = capitalizeFirstLetterOfWord(word);
			}
			output += word + " ";
		}
		output = output.trim();
		return output;
	}

	public static List<String> capitalizeList (List<String> titlesList, List<String> wordsToIgnore){
		assert titlesList != null : "Unexpected null list to be capitalized";
		assert !titlesList.isEmpty() : "Unexpected empty list to be capitalized";
		List<String> outputList = new ArrayList<String>();
		for(String title: titlesList) {
			outputList.add(capitalize(title, wordsToIgnore));
		}
		return outputList;
	}

	private static String capitalizeFirstLetterOfWord(String word) {
		return word.replaceFirst(word.substring(0, 1),
				word.substring(0, 1).toUpperCase());
	}

	private static String convertWordToLowerCase(String word) {
		word = word.toLowerCase();
		return word;
	}

	private static boolean isWordToIgnore(List<String> wordsToIgnore, String word) {
		return wordsToIgnore.contains(word.toLowerCase());
	}

}
