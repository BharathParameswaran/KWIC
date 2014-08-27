package ui;

import java.util.List;
import java.util.Scanner;

import controller.KWICController;

class KWICMain {

	private static final String EMPTY = "Empty ";
	private static final String TITLE_DECORATION = "-------";
	private static final String RE_ENTER_FILE_NAME = "Re-enter file name: ";
	private static final String INVALID_FILE_PATH = "File path specified is invalid";
	private static final String ERROR_ADDING_FILES = "The follwing could not be added-";
	private static final String ENTER_TITLES_FILENAME = "Enter filename for reading titles: ";
	private static final String ENTER_WORDS_TO_IGNORE_FILENAME = "Enter filename for reading words to ignore: ";
	private static final String ENTER_WORD_TO_IGNORE = "Enter new word to ignore: ";
	private static final String ENTER_NEW_TITLE = "Enter new title: ";
	private static final String RESET_SUCCESSFUL = "All lists reset successfully!";
	private static final String ASK_USER_OPTION = "Enter your option: ";
	private static final String INVALID_OPTION_ERROR = "Please enter a valid integer between 1 and 7";
	private static final String WORDS_TO_IGNORE_LIST = "List of words to ignore";
	private static final String TITLES_LIST = "List of titles";
	private static final String RESULT_SET = "Result Set";
	private static final int EXIT_OPTION = 7;
	private static final int INVALID_OPTION = -1;
	private static KWICController _controller = new KWICController();
	private static Scanner sc = new Scanner(System.in);

	private static String[] options = { "1. Add a new title",
			"2. Add a new word to ignore",
			"3. Add titles and ignored words from a text file",
			"4. View existing list of tiles and words to ignore",
			"5. View current result set", 
			"6. Reset lists", 
			"7. Exit" };
	
	public static void main(String[] args) {
		printOptionsForUser();
		int option = 0;
		while (option != EXIT_OPTION) {
			option = getInputFromUser();
			if (!isValid(option))
				continue;
			processInput(option);

		}
	}

	private static boolean isValid(int option) {
		return (option != INVALID_OPTION);
	}

	private static int getInputFromUser() {
		int option = 0;
		System.out.print(ASK_USER_OPTION);
		try {
			option = sc.nextInt();
			sc.nextLine();
		} catch (Exception e) {
			sc.nextLine();
			System.out.println(INVALID_OPTION_ERROR);
			return INVALID_OPTION;
		}
		return option;
	}

	private static void processInput(int option) {
		switch (option) {
		case 1:
			getNewTitle();
			printResultList();
			break;
		case 2:
			getNewIgnoredWord();
			printResultList();
			break;
		case 3:
			getInputFromFile();
			printResultList();
			break;
		case 4:
			printList(_controller.getGivenTitles(), TITLES_LIST);
			printList(_controller.getIgnoreWordsList(), WORDS_TO_IGNORE_LIST);
			break;
		case 5:
			printList(_controller.getCurrentResult(), RESULT_SET);
			break;
		case 6:
			resetLists();
			break;
		case 7:
			return;
		default:
			System.out.println(INVALID_OPTION_ERROR);
		}
	}

	private static void resetLists() {
		_controller.reset();
		System.out.println(RESET_SUCCESSFUL);
	}

	private static void printOptionsForUser() {
	
		for (String option : options) {
			System.out.println(option);

		}
		System.out.println();
	}

	private static void printResultList() {
		List<String> resultSet = _controller.getCurrentResult();
		if (!resultSet.isEmpty())
			printList(_controller.getCurrentResult(), RESULT_SET);
	}

	private static void getNewTitle() {
		System.out.print(ENTER_NEW_TITLE);
		String title = sc.nextLine();
		_controller.addTitle(title);
	}

	private static void getNewIgnoredWord() {
		System.out.print(ENTER_WORD_TO_IGNORE);
		String ignoredWord = sc.nextLine();
		_controller.addWordToIgnore(ignoredWord);
	}

	private static void getInputFromFile() {
		System.out.print(ENTER_WORDS_TO_IGNORE_FILENAME);
		readWordsToIgnore();
		System.out.print(ENTER_TITLES_FILENAME);
		readTitles();
	}

	private static void readWordsToIgnore() {
		boolean isInvalidFileName = true;
		while (isInvalidFileName) {
			String ignoreWordsFileName = sc.nextLine();
			isInvalidFileName = false;
			List<String> errors = _controller.loadInformationFromFiles("",
					ignoreWordsFileName);
			if (errors == null) {
				printErrorMsg();
				isInvalidFileName = true;
			} else if (!errors.isEmpty()) {
				System.out.println(ERROR_ADDING_FILES);
				for (String title : errors)
					System.out.println(title);
			}
		}
	}

	private static void readTitles() {
		boolean isInvalidFileName = true;
		while (isInvalidFileName) {
			String titlesFileName = sc.nextLine();
			isInvalidFileName = false;
			List<String> errors = _controller.loadInformationFromFiles(
					titlesFileName, "");
			if (errors == null) {
				printErrorMsg();
				isInvalidFileName = true;
			} else if (!errors.isEmpty()) {
				System.out.println(ERROR_ADDING_FILES);
				for (String title : errors)
					System.out.println(title);
			}
		}
	}

	private static void printErrorMsg() {
		System.out.println(INVALID_FILE_PATH);
		System.out.print(RE_ENTER_FILE_NAME);
	}

	private static void printList(List<String> list, String header) {

		if (list.isEmpty())
			System.out.println(EMPTY + header);
		else {
			System.out.println();
			System.out.println(TITLE_DECORATION + header + TITLE_DECORATION);
			int i = 1;
			for (String element : list)
				System.out.println(i++ + ". " + element);

			System.out.println();
		}
	}

}