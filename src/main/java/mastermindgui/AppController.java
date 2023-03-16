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

   /* Setter name variablen som blir definert i startController-filen slik at man kan 
    bruke den i denne klassen.*/
    public void setString(String name){
        this.name = name;
    }
    
    
    /* Setter displayer navnet man inputer i startController-filen slik at den syns pi vinduet. */
    @FXML
    Label nameLabel;
    public String displayname(String name){
        nameLabel.setText(name);
        return this.name;
    }


    /* Gameloopen rettogslett */
    @FXML
    public void gameloop(){
        if (guesscount < 5 && isGameOver == false) {
            onEnter();
        }
    }

    /* Kjører når spillet starter og initerer en rekke klasser samt generer koden og gjør den synlig for debuggingpurposes. */
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
    private void onEnter() { /*  Når man trykker enterknappen i vinduet, sender den gjettet inn i denne metoden, som sjekker om gjettet er gyldig. 
        ved å sende det gjennom 4 ifsetninger. Hvis det er gyldig, så sender den gjettet videre til updateCharGrid, som oppdaterer charGrid med riktig farge.*/
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
    /*Denne sjekker wincondition og om alt er til rette for en win, så blir winscreen satt til 
    true og highscoredokumentet blir oppdatert */
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
    /*Sletter alle highscorene i highscoredokumentet */
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



    // sender deg til highscorevinduet
    @FXML
    public void highscoreShow(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("highscore.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


  


}
    

