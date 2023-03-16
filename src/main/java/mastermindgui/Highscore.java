package mastermindgui;

import java.io.*;
import java.util.*;

public class Highscore {
    
    private List<Score> scores;
    private String filename;
  
    
    public Highscore(String filename) {
        this.filename = filename;
        this.scores = new ArrayList<>();
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
    
    private void saveScores() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename, false))) {
            for (Score score : scores) {
                writer.println(score.getName() + "," + score.getGuesses());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void clearScores() {
        scores.clear();
        saveScores();
    }
    
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
        
        public String getName() {
            return name;
        }
        
        public int getGuesses() {
            return guesses;
        }
        
    }



    
    
}
