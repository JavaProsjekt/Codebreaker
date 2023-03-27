package mastermindgui;



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
    @FXML public TextArea output;
    private Parent root;
    private Stage stage;
    private Scene scene;

    public void initialize() {
        // Call the refresher method to refresh the TextArea on open
        refresher();
    }

    public void refresher(){
        Highscore.readFileIntoTextArea("highscores.txt", output );
    }

    
    public void clear(ActionEvent event) throws IOException {
        Highscore.clearScores();
        refresher();
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