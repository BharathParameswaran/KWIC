package inputReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class FileReader {


	public static List<String> readFromFile(String pathToFile) {
		if (!pathToFile.toLowerCase().endsWith(".txt")) {
			
			return null;

		}
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new File(pathToFile));
		} catch (FileNotFoundException e) {
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