package inputReader;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class KWICFileReaderTest {

	@Test
	public void testFileReader() {
		createRequiredFiles();
		testForNonExsitentFile();
		testForFileWithInvalidExtension();
		testForExsitentEmptyFile();
		testForValidFile();
		deleteTmpFiles();
	}

	private void testForNonExsitentFile() {
		assertEquals(null, KWICFileReader.readFromFile("tmp/nonExistentFile.txt"));

	}
	
	private void testForFileWithInvalidExtension() {
		assertEquals(null, KWICFileReader.readFromFile("tmp/invalid.jpg"));

	}

	private void testForExsitentEmptyFile() {
		List<String> list = new ArrayList<String>();
		assertEquals(list, KWICFileReader.readFromFile("tmp/emptyFile.txt"));

	}

	private void testForValidFile() {
		List<String> list = new ArrayList<String>();
		list.add("Line 1");
		list.add("Line 2");

		assertEquals(list, KWICFileReader.readFromFile("tmp/validInput.txt"));

	}

	private void createRequiredFiles() {
		File theDir = new File("tmp");
		if (!theDir.exists()) {
			try {
				theDir.mkdir();
			} catch (SecurityException se) {

			}
		}

		createEmptyFile("tmp/emptyFile.txt");
		createEmptyFile("tmp/validInput.txt");
		createEmptyFile("tmp/invalid.jpg");
		addLinesToFile("tmp/validInput.txt");

	}

	private void addLinesToFile(String fileName) {
		File file = new File(fileName);
		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("Line 1\n");
			bw.write("Line 2\n");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void createEmptyFile(String fileName) {
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("Temporary Files could not be created!");
		}
	}

	private void deleteTmpFiles() {
		File f = new File("tmp");
		if (f.isDirectory()) {
			for (File c : f.listFiles())
				c.delete();
		}
		f.delete();
	}
}
