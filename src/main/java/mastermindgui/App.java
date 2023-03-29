package mastermindgui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
  
 Main funksjon, denne starter programmet og instansierer vinduet start.fxml, 
 som er det første vinduet som vises. 
 
 */




public class App extends Application {
    @Override
    public void start(final Stage primaryStage) throws Exception {
        try{

			Parent root = FXMLLoader.load(getClass().getResource("start.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }


    public static void main(String[] args) {
        launch(args);
    }
}
    

