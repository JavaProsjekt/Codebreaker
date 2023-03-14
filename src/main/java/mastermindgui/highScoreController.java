package mastermindgui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;



public class highScoreController {
    @FXML private TextArea output;
    private Parent root;
    private Stage stage;
    private Scene scene;


    


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

    // sender til start igjen.
    @FXML
    public void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}