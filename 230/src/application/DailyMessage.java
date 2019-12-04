package application;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * This class retrieve the Message of the Day for the program, using a process
 * outlined at cswebcat.swan.ac.uk.
 * 
 * @author Gideon Davies
 * @version 1.0
 */
public class DailyMessage {

	private static final String PUZZLE_URL = "http://cswebcat.swan.ac.uk/puzzle";
	private static final String SOLUTION_URL = "http://cswebcat.swan.ac.uk/message?solution=";
	private static final String DEFAULT_MESSAGE = "Welcome to Legend of Liam!";

	/**
	 * The daily message to be displayed.
	 */
	private static String dailyMessage = retrieveMessage();

	/**
	 * Get the daily message.
	 * 
	 * @return The daily message
	 */
	public static String getMessage() {
		return dailyMessage;
	}

	/**
	 * Connects to {@link #PUZZLE_URL} and retrieves a puzzle.
	 * 
	 * @return A puzzle as a String of capital letters.
	 * @throws IOException if the connection fails.
	 */
	private static String getPuzzle() throws IOException {
		// Establish a connection to PUZZLE_URL
		URL url = new URL(PUZZLE_URL);
		HttpURLConnection connect = (HttpURLConnection) url.openConnection();
		connect.setRequestMethod("GET");

		// Read the puzzle.
		Scanner scan = new Scanner(connect.getInputStream());
		String puzzle = scan.nextLine();
		scan.close();

		return puzzle;
	}

	/**
	 * Solves the input puzzle using the method described at cswebcat.swan.ac.uk.
	 * 
	 * @param puzzle The string of capital letters to be solved.
	 * @return The puzzle solution.
	 */
	private static String solvePuzzle(String puzzle) {
		char[] puzzleChars = puzzle.toCharArray();

		String solution = "";

		// Changes each letter.
		for (int i = 0; i < puzzleChars.length; i++) {

			// Every second letter changes to the previous letter.
			// The 1st, 3rd etc. letters change to the next letter.
			if (i % 2 == 0) {

				if (puzzleChars[i] != 'Z') {
					puzzleChars[i]++;
				} else {
					// Z cycles back to A
					puzzleChars[i] = 'A';
				}

			} else {

				if (puzzleChars[i] != 'A') {
					puzzleChars[i]--;
				} else {
					// A cycles to Z
					puzzleChars[i] = 'Z';
				}

			}

			// Add the changed letter to the solution string.
			solution += puzzleChars[i];
		}

		return solution;
	}

	/**
	 * Inputs a puzzle solution to the url and retrieves the daily message from the
	 * page.
	 * 
	 * @param solution The solution to the puzzle.
	 * @return The daily message.
	 * @throws IOException if the connection fails.
	 */
	private static String inputSolution(String solution) throws IOException {
		// Establish a connection. Solution added to URL.
		URL url = new URL(SOLUTION_URL + solution);
		HttpURLConnection connect = (HttpURLConnection) url.openConnection();
		connect.setRequestMethod("GET");

		// Read the message from the page.
		Scanner scan = new Scanner(connect.getInputStream());
		String message = "";

		while (scan.hasNextLine()) {
			message += scan.nextLine();
		}
		scan.close();

		return message;
	}

	/**
	 * This method retrieves the daily message from the web-site.
	 * 
	 * @return The daily message.
	 */
	private static String retrieveMessage() {
		String message = null;

		try {
			// Combines the three other private methods in order.
			message = inputSolution(solvePuzzle(getPuzzle()));
		} catch (IOException e) {
			// Uses default message if any errors occur.
			message = DEFAULT_MESSAGE;
		}

		return message;
	}
}
