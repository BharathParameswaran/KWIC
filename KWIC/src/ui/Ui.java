package ui;

import java.util.List;
import java.util.Scanner;

import controller.Controller;

class Ui {

	private static final String INVALID_OPTION_ERROR = "Please enter a valid integer between 1 and 6";
	private static final String WORDS_TO_IGNORE_LIST = "List of words to ignore";
	private static final String TITLES_LIST = "List of titles";
	private static final String RESULT_SET = "Result Set";
	private static final int EXIT_OPTION = 7;
	private static final int INVALID_OPTION = -1;
	private static Controller _controller = new Controller();
	private static Scanner sc = new Scanner(System.in);

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
		System.out.print("Enter your option: ");
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
			System.exit(0);
		default:
			System.out.println(INVALID_OPTION_ERROR);
		}
	}
	
	private static void resetLists() {
		
		System.out.println("All lists reset successfully!");
	}

	private static void printOptionsForUser() {
		System.out.println("1. Add a new title");
		System.out.println("2. Add a new word to ignore");
		System.out.println("3. Add titles and ignored words from a text file");
		System.out
				.println("4. View existing list of tiles and words to ignore");
		System.out.println("5. View current result set");
		System.out.println("6. Reset lists");
		System.out.println("7. Exit");
		System.out.println();
	}

	private static void printResultList() {
		List<String> resultSet = _controller.getCurrentResult();
		if (!resultSet.isEmpty())
			printList(_controller.getCurrentResult(), RESULT_SET);
	}

	private static void getNewTitle() {
		System.out.print("Enter new title: ");
		String title = sc.nextLine();
		_controller.addTitle(title);
	}

	private static void getNewIgnoredWord() {
		System.out.print("Enter new word to ignore: ");
		String ignoredWord = sc.nextLine();
		_controller.addWordToIgnore(ignoredWord);
	}

	private static void getInputFromFile() {
		System.out.print("Enter filename for reading words to ignore: ");
		readWordsToIgnore();
		System.out.print("Enter filename for reading titles: ");
		readTitles();
	}

	private static void readWordsToIgnore() {
		boolean isInvalidFileName = true;
		while (isInvalidFileName) {		
			String ignoreWordsFileName = sc.nextLine();
			isInvalidFileName = false;
			List<String> errors  =   _controller.loadInformationFromFiles("", ignoreWordsFileName);
			if (errors == null) {
				printErrorMsg();
				isInvalidFileName = true;
			}
			else if(!errors.isEmpty()){
				System.out.println("The follwing could not be added-");
				for(String title: errors)
					System.out.println(title);
			}
		}
	}
	
	private static void readTitles() {
		boolean isInvalidFileName = true;
		while (isInvalidFileName) {			
			String titlesFileName = sc.nextLine();
			isInvalidFileName = false;
			List<String> errors = _controller.loadInformationFromFiles(titlesFileName, "");
			if (errors == null) {
				printErrorMsg();
				isInvalidFileName = true;
			}
			else if(!errors.isEmpty()){
				System.out.println("The follwing could not be added-");
				for(String title: errors)
					System.out.println(title);
			}
		}
	}

	private static void printErrorMsg() {
		System.out.println("File path specified is invalid");
		System.out.print("Re-enter file name: ");
	}

	private static void printList(List<String> list, String header) {

		if (list.isEmpty())
			System.out.println("Empty " + header);
		else {
			System.out.println();
			System.out.println("-------" + header + "-------");
			int i = 1;
			for (String element : list)
				System.out.println(i++ + ". " + element);

			System.out.println();
		}
	}

}