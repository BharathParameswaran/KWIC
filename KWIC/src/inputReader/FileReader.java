package inputReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class FileReader {
	private final static Logger _log = Logger.getLogger(FileReader.class
			.getName());

	public static List<String> readFromFile(String pathToFile) {
		if (!pathToFile.toLowerCase().endsWith(".txt")) {
			_log.severe("Invalid file type");
			return null;

		}
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new File(pathToFile));
		} catch (FileNotFoundException e) {
			_log.severe("File cannot be found");
			return null;
		}
		List<String> list = new ArrayList<String>();
		while (fileScanner.hasNextLine()) {
			list.add(fileScanner.nextLine());
		}
		fileScanner.close();
		return list;
	}

}