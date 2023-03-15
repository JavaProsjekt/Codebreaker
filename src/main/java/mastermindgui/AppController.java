package mastermindgui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;





public class AppController {
    @FXML private TextField BrukerInput;
    @FXML private GridPane codeGrid;
    @FXML private GridPane charGrid;
    @FXML private GridPane nameGrid;
    @FXML private ImageView charerror;
    @FXML private ImageView aferror;
    @FXML private ImageView winscreen;
    @FXML private ImageView lose;
    @FXML private ImageView noguess;
    @FXML private ImageView namecharwrong;


    private Highscore highscore;
    private Guessget guess = new Guessget();
    private int currentRow = 0;
    public int guesscount = 0;
    private boolean isGameOver = false;
    public String name;

    // Highscoreshow ----
    private Parent root;
    private Stage stage;
    private Scene scene;
    //-------------------

    
    public void setString(String name){
        this.name = name;
    }
    
    

    @FXML
    Label nameLabel;
    public String displayname(String name){
        nameLabel.setText(name);
        return this.name;
    }

    @FXML
    public void gameloop(){
        if (guesscount < 5 && isGameOver == false) {
            onEnter();
        }
    }

    @FXML
    public void initialize() { // Denne og starter overraskende bra. 
        highscore = new Highscore("highscores.txt");
        highscore.loadScores();
        highscore.sortScores();
        for (int i = 0; i < guess.code.length(); i++) {
            codeGrid.add(new TextField(String.valueOf(guess.code.charAt(i))), i, 0);
            
        }
    }


    
    @FXML
    private void onEnter() { // Funker faktisk helt ok as, litt wild egt. veldig wild
        String gjett = BrukerInput.getText().toUpperCase();
        int validity = guess.Validitycheck(gjett);

        if(validity == 1){
            charerror.setVisible(true);
            aferror.setVisible(true);
            guesscount++;
        }
        if(validity == 2){
            charerror.setVisible(true);
            guesscount++;
        }
        if(validity == 3){
            aferror.setVisible(true);
            guesscount++;
        }
        if (validity == 4) {
            aferror.setVisible(false);
            charerror.setVisible(false);
            guesscount++;
            updateCharGrid(gjett);
            currentRow++;
            Win(gjett);
        }
    }


    @FXML
    private void Win(String gjett) {
        if (isGameOver) {
            return;
        }
        if (guesscount == 5) {
            noguess.setVisible(true);
            lose.setVisible(true);
            isGameOver = true;
            highscore.addScore(name, guesscount);
            return;
        }
        
        if (gjett.equals(guess.code)) {
            winscreen.setVisible(true);
            highscore.addScore(name, guesscount);
        } else {
            winscreen.setVisible(false);
        }
    }
    
    
    @FXML
    private void startNewGame() {
        guess = new Guessget(); // create a new instance of Guessget
        currentRow = 0; // reset the current row to 0
        guesscount = 0; // reset the guess count to 0
        isGameOver = false; // reset the game over flag to false
        codeGrid.getChildren().clear(); // clear the code grid
        charGrid.getChildren().clear(); // clear the character grid
        initialize(); // initialize the code grid with a new code
        winscreen.setVisible(false); // hide the win screen
        lose.setVisible(false); // hide the lose screen
        noguess.setVisible(false); // hide the no guess screen
    }


   

    
    public void ScoreReset(){
        highscore.clearScores();
    }

 // Declare a List to store the previous guesses
private List<String> previousGuesses = new ArrayList<>();

private void updateCharGrid(String gjett) {
    // Add the current guess to the List of previous guesses
    previousGuesses.add(gjett);

    // Create a new row in the charGrid for the current guess
    charGrid.getRowConstraints().add(new RowConstraints(20));
    charGrid.setVgap(5);

    // Add the characters of the current guess to the existing charGrid
    for (int i = 0; i < gjett.length(); i++) {
        int pos = i;
        boolean correctChar = false;
        for (int j = 0; j < gjett.length(); j++) {
            if (gjett.charAt(pos) == guess.code.charAt(j)){
                correctChar = true;
            }   
        }
        boolean correctPos = (gjett.charAt(pos) == guess.code.charAt(pos));
        TextField charField = new TextField(String.valueOf(gjett.charAt(i)));
        String color = "red";
        if(correctChar){
            color = "yellow";
        }
        if(correctChar && correctPos){
            color = "green";
        }
        charField.setStyle("-fx-background-color: " + color);
        charGrid.add(charField, i, currentRow);
    }

    // Increment the currentRow for the next guess
    currentRow+=1;
}

    // sender deg til highscore
    @FXML
    public void highscoreShow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("highscore.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


   
    


















}
    

