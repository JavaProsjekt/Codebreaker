package mastermindgui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;



public class highScoreController {
    @FXML private TextArea output;



    public static void readFileIntoTextArea(String filePath, TextArea textArea) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                textArea.appendText(line + "\n");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void refresher(){
        readFileIntoTextArea("highscores.txt", output );
    }


}