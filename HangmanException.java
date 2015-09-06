

public class HangmanException extends Exception {

	public HangmanException() {
		System.out.print("HANGMAN!!\t");
		Hangman.displayWord();
		StringBuilder str1 = new StringBuilder(1024);
		str1.append("\n\n      |\n");
		str1.append("      |\n");
		str1.append("      |\n");
		str1.append("      \\0\n");
		str1.append("     / I \\\n");
		str1.append("      / \\\n");
		System.out.println(str1);
		System.out.println("\nWord was \"" + Hangman.randomWord.toUpperCase() + "\"");
		System.out.print("Incorrect Guesses:  ");
		
		if(!Hangman.wrongGuess.isEmpty()) {
			for(int i=0; i < Hangman.wrongGuess.size()-1; i++) 
				System.out.print(Hangman.wrongGuess.get(i) + ", ");
			System.out.print(Hangman.wrongGuess.get(Hangman.wrongGuess.size()-1));
		}
		System.out.println("\nTOTAL GUESSES:  " + Hangman.guess);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
}
