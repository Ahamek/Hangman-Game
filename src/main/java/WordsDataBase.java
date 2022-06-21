import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordsDataBase {
    private static List<String> words = new ArrayList<>();

    WordsDataBase() {
        readFile();
    }

    private void readFile() {
        try {
            words = new Scanner(new File("src/main/resources/words"))
                    .tokens()
                    .toList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found check file path");
        }
    }

    public String getWord() {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

}
