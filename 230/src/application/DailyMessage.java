package application;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * 
 * @author Gideon Davies
 * @version 1.0
 */
public class DailyMessage {
	private static final String PUZZLE_URL = "http://cswebcat.swan.ac.uk/puzzle";
	private static final String SOLUTION_URL = "http://cswebcat.swan.ac.uk/message?solution=";
	private static String dailyMessage = retrieveMessage();
	
	public static String getMessage() {
		return dailyMessage;
	}
	
	private static String getPuzzle() throws IOException {
		URL url = null;
		HttpURLConnection connect = null; 
		
		url = new URL(PUZZLE_URL);
		connect = (HttpURLConnection) url.openConnection();

		connect.setRequestMethod("GET");
		Scanner scan = new Scanner(connect.getInputStream());
		String puzzle = scan.nextLine();
		scan.close();
		
		return puzzle;
	}
	
	private static String solvePuzzle(String puzzle) {
		char[] alphabet = new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		
		String solution = "";
		
		for (int i = 0; i < puzzle.length(); i++) {
			char puzzleChar = puzzle.charAt(i);
			
			int index = -1;
			
			for (int j = 0; j < alphabet.length; j++) {
				if (puzzleChar == alphabet[j]) {
					index = j;
				}
			}
			
			if (i % 2 == 0) {
				index = (index + 1) % alphabet.length;
			} else {
				index -= 1;
				if (index == -1) {
					index = 25;
				}
			}
			
			solution += alphabet[index];
		}
		
		return solution;
	}
	
	private static String inputSolution(String solution) throws IOException {
		URL url = null;
		HttpURLConnection connect = null; 
		
		url = new URL(SOLUTION_URL + solution);
		connect = (HttpURLConnection) url.openConnection();

		connect.setRequestMethod("GET");
		Scanner scan = new Scanner(connect.getInputStream());
		String puzzle = "";
		
		while (scan.hasNextLine()) {
			puzzle += scan.nextLine();
		}
		scan.close();
		return puzzle;
	}
	
	private static String retrieveMessage() {
		String message = null;
		
		try {
			message = inputSolution(solvePuzzle(getPuzzle()));
		} catch (IOException e) {
			message = "Welcome to Legend of Liam!";
		}
		
		return message;
	}
}
