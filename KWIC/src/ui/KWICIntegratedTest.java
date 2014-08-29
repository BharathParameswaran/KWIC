package ui;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

import dataSource.Data;

public class KWICIntegratedTest {

	@Test
	public void testUi() {
		Data.inst().reset();
		if(setInputAndOutputStreams()) {
		KWICMain.main(null);
		testOutput();
		}
	}

	private void testOutput() {
		Scanner actualOutput = null;
		try {
			actualOutput = new Scanner(new File("src/filesForTesting/actualOutput.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Test output file not found!");
		}

		Scanner expectedOutput = null;
		try {
			expectedOutput = new Scanner(new File("src/filesForTesting/expectedOutput.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Expected output file not found!");
		}

       
		while (actualOutput.hasNextLine() && expectedOutput.hasNextLine()) {
			String ex = expectedOutput.nextLine();
			String ac = actualOutput.nextLine();
			assertEquals(ex, ac);
			
		}
	}

	private boolean setInputAndOutputStreams() {
		try {
			System.setIn(new FileInputStream("src/filesForTesting/input.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Test input file not found!");
			return false;
		}
		try {
			System.setOut(new PrintStream("src/filesForTesting/actualOutput.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Test output file not found!");
			return false;
		}
		
		return true;
	}

}
