package mastermindgui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;




public class startController {
    
    @FXML private TextField startinput;
    @FXML private ImageView Namecheck;
    @FXML private Button starts;
    @FXML private Button next;
    private Parent root;
    private Stage stage;
    private Scene scene;


    @FXML
    public void starts(ActionEvent event) throws IOException {

        String name = startinput.getText();
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("codebreaker.fxml"));
        root = loader.load();
        AppController controller = loader.getController();
        controller.setString(name);
        if(name.length() == 0){
            Namecheck.setVisible(true);
        }
        if(name.length() != 4){
            Namecheck.setVisible(true);
        }
        else{
            controller.displayname(name);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }
    
    @FXML
    public void next(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("codebreaker.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}

