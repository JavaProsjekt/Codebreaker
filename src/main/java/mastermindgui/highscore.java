package mastermindgui;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class highscore {
    private static final String FILENAME = "highscores.txt"; // File name of the highscore file
    private List<Integer> scores = new ArrayList<>(); // List to store the scores
    
    public void addScore(int score) {
        scores.add(score); // Add the score to the list
        Collections.sort(scores); // Sort the list in ascending order
        Collections.reverse(scores); // Reverse the list to get descending order (fewer guesses = better)
        saveScores(); // Save the scores to the highscore file
    }
    
    public List<Integer> getScores() {
        return scores; // Return the list of scores
    }
    
    private void saveScores() {
        try {
            File file = new File(FILENAME);
            if (!file.exists()) {
                file.createNewFile(); // Create the highscore file if it doesn't exist
            }
            FileWriter writer = new FileWriter(file);
            for (Integer score : scores) {
                writer.write(score + "\n"); // Write each score to a new line in the file
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadScores() {
        try {
            File file = new File(FILENAME);
            if (!file.exists()) {
                return; // Return if the highscore file doesn't exist
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextInt()) {
                scores.add(scanner.nextInt()); // Add each score from the file to the list
            }
            scanner.close();
            Collections.sort(scores); // Sort the list in ascending order
            Collections.reverse(scores); // Reverse the list to get descending order (fewer guesses = better)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
