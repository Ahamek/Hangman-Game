import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman {
    private static final List<Character> playerGuesses = new ArrayList<>();
    private static final Scanner keyboard = new Scanner(System.in);
    public static final WordsDataBase wdb = new WordsDataBase();

    public static void play() {
        final String word = wdb.getWord();
        int wrongCount = 0;

        while (true) {
            HangmanPrinter.printHangedMan(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("You lose!");
                System.out.println("The word was: " + word);
                break;
            }

            printWordEncrypted(word);

            if (!getPlayerGuess(word)) {
                wrongCount++;
            }

            if (printWordEncrypted(word)) {
                System.out.println("You win!");
                break;
            }

            attemptToGuessWord(word);
        }
    }

    private static void attemptToGuessWord(String word) {
        System.out.println("Please enter your guess for the word: / or press ENTER to continue: ");
        String userGuessWord = keyboard.nextLine();
        if (userGuessWord.equals(word)) {
            System.out.println("You win!");
        } else if (userGuessWord.equals("")) {
            System.out.println();
        } else {
            System.out.println("Nope! Try again.");
        }
    }

    private static boolean getPlayerGuess(String word) {
        System.out.println("Please enter a letter:");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));
        return word.contains(letterGuess);
    }

    private static boolean printWordEncrypted(String word) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else if (word.charAt(i) == ' ') {
                System.out.print(" ");
            } else {
                System.out.print("*");
            }
        }
        System.out.println();

        return (word.length() == correctCount);
    }
}

