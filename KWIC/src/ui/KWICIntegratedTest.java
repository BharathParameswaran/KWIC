package ui;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

public class KWICIntegratedTest {

	@Test
	public void testUi() {
		setInputAndOutputStreams();
		KWICMain.main(null);
		testOutput();
	}

	private void testOutput() {
		Scanner actualOutput = null;
		try {
			actualOutput = new Scanner(new File("actualOutput.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Test output file not found!");
		}

		Scanner expectedOutput = null;
		try {
			expectedOutput = new Scanner(new File("expectedOutput.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Expected output file not found!");
		}

       
		while (actualOutput.hasNextLine() && expectedOutput.hasNextLine()) {
			String ex = expectedOutput.nextLine();
			String ac = actualOutput.nextLine();
			assertEquals(ex, ac);
			
		}
	}

	private void setInputAndOutputStreams() {
		try {
			System.setIn(new FileInputStream("input.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Test input file not found!");
		}
		try {
			System.setOut(new PrintStream("actualOutput.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Test output file not found!");
		}
	}

}
