package mastermindgui;

import java.io.*;
import java.util.*;

import javafx.scene.control.TextArea;

public class Highscore {
    
    private static List<Score> scores;
    private static String filename;
  
    
    public Highscore(String filename) {
        Highscore.filename = filename;
        Highscore.scores = new ArrayList<>();
    }
    
    public void addScore(String name, int guesses) {
        scores.add(new Score(name, guesses));
        sortScores();
        saveScores();
    }
    
    public List<Score> getScores() {
        return scores;
    }
    
    void sortScores() {
        scores.sort(Comparator.comparingInt(Score::getGuesses));
    }
    
    private static void saveScores() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, false))) {
            for (Score score : scores) {
                writer.println(score.getName() + "," + score.getGuesses());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /* Sletter scorene */
    public static void clearScores() {
        scores.clear();
        saveScores();
    }
    
    /* Laster inn scorene */
    public void loadScores() {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    int guesses = Integer.parseInt(parts[1]);
                    scores.add(new Score(name, guesses));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public class Score {
        private String name;
        private int guesses;
        
        public Score(String name, int guesses) {
            this.name = name;
            this.guesses = guesses;
        }
        /* Henter navn */
        public String getName() {
            return name;
        }
        /*h Henter Gjetninger */
        public int getGuesses() {
            return guesses;
        }
        
    }

    /* Mater score inn i textarea */
    public static void readFileIntoTextArea(String filePath, TextArea textArea) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            textArea.setText("");
            String line = reader.readLine();
            while (line != null) {
                textArea.appendText(line + "\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    
    
}
