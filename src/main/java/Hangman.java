import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman {

    private static final List<Character> playerGuesses = new ArrayList<>();
    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        WordsDataBase wdb = new WordsDataBase();

        final String word = wdb.getWord();
        int wrongCount = 0;

        while (true) {
            HangmanPrinter.printHangedMan(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("You lose!");
                System.out.println("The word was: " + word);
                break;
            }

            printWordState(word);

            if (!getPlayerGuess(word)) {
                wrongCount++;
            }

            if (printWordState(word)) {
                System.out.println("You win!");
                break;
            }

            System.out.println("Please enter your guess for the word: / or press ENTER to continue: ");
            if (keyboard.nextLine().equals(word)) {
                System.out.println("You win!");
                break;
            } else {
                System.out.println("Nope! Try again.");
            }
        }
    }

    private static boolean getPlayerGuess(String word) {
        System.out.println("Please enter a letter:");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));
        return word.contains(letterGuess);
    }

    private static boolean printWordState(String word) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else if (word.charAt(i) == ' ') {
                System.out.print(" ");
            } else {
                System.out.print("-");
            }
        }
        System.out.println();

        return (word.length() == correctCount);
    }
}
