package mastermindgui;
import java.io.*;
import java.util.*;

public class highscore {
    private String filename;
    private TreeMap<Integer, String> scores;

    public highscore(String filename) {
        this.filename = filename;
        this.scores = new TreeMap<>();
    }

    public void addScore(int tries, String name) {
        scores.put(tries, name);
    }

    public void writeToFile() {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Map.Entry<Integer, String> entry : scores.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile() {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                int tries = Integer.parseInt(parts[0]);
                String name = parts[1];
                scores.put(tries, name);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    
}


