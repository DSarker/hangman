/*
 * Word game which involves a text file for words,
 * user input, and a custom exception.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

	public static String randomWord;
	private static char[] word;
	private static int sizeOfWord;
	private static int numOfWrongGuesses = 6; // Number of total guesses allowed
	public static List<Character> wrongGuess = new ArrayList<>();
	public static int guess = 1; // Number of guesses so far
	private static Scanner scan = new Scanner(System.in);
	private char choice;// = scan.next().charAt(0);

	public Hangman() {
		createGame();
	}

	private void createGame() {
		pickWord();
		setUp();
		try {
			while (numOfWrongGuesses > 0) {
				prompt();
				guess++;
				System.out.println("\n");

				if (checkGuess()) {
					if (checkWin()) {
						youWin();
						System.exit(0);
					}
				} else
					numOfWrongGuesses--;
			}

			throw new HangmanException();
		}

		catch (HangmanException e) {
		}
	}

	// Picks a word from a file and saves it as the var "word"
	private void pickWord() {
		try {
			File file = new File("C:/Users/David/workspace/hangman/src/words.txt"); 
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;

			HashSet set = new HashSet<String>();

			// Adds to the file line by line
			while ((line = bufferedReader.readLine()) != null) {
				set.add(line);
			}
			fileReader.close();

			int size = set.size();
			int item = new Random().nextInt(size);
			int i = 0;
			for (Object obj : set) {
				if (i == item) {
					randomWord = (String) obj;
					//System.out.println(randomWord);
					break;
				}
				i++;
			}
		}

		catch (IOException e) {
			System.out.println("Trouble opening or reading file.");
		}
	}

	private void setUp() {
		sizeOfWord = randomWord.length();
		word = new char[sizeOfWord];
		for (int i = 0; i < sizeOfWord; i++) {
			word[i] = '_';
		}
	}

	private void prompt() {
		displayWord();
		System.out.print("\nGuess #" + guess + ": ");
		choice = scan.next().trim().charAt(0);
	}

	public static String displayWord() {
		for (int i = 0; i < sizeOfWord; i++) {
			System.out.print(word[i] + " ");
		}
		return "";
	}

	private boolean checkGuess() {
		boolean flag = false;
		for (int i = 0; i < sizeOfWord; i++) {
			if (randomWord.charAt(i) == Character.toLowerCase(choice)) {
				word[i] = Character.toUpperCase(choice);
				flag = true;
			}
		}
		if (!flag)
			wrongGuess.add(Character.toUpperCase(choice));

		return flag;
	}

	private boolean checkWin() {
		for (int i = 0; i < sizeOfWord; i++) {
			if (word[i] == '_') {
				return false;
			}
		}
		return true;
	}

	private void youWin() {
		System.out.println(randomWord.toUpperCase());
		System.out.println("YOU WON after " + guess + " guesses. You had "
				+ (6 - numOfWrongGuesses) + " incorrect guess(es)");
	}

	public static void main(String[] args) throws FileNotFoundException {
		Hangman hangman = new Hangman();
	}
}
