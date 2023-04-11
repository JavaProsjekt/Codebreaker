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
import javafx.scene.control.ToggleButton;
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
    @FXML private ToggleButton debugbutton;


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
        codeGrid.setVisible(false);
        

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
            codeGrid.setVisible(true);
        } else {
            winscreen.setVisible(false);
        }
    }
    
    @FXML
    /*Sletter alle highscorene i highscoredokumentet */
    public void ScoreReset(){
        Highscore.clearScores();
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
        //Teller instanser av hver bokstav for å ordentlig finne gule/grønne bokser senere
        int aCount = guess.code.length() - guess.code.replace("A","").length();
        int bCount = guess.code.length() - guess.code.replace("B","").length();
        int cCount = guess.code.length() - guess.code.replace("C","").length();
        int dCount = guess.code.length() - guess.code.replace("D","").length();            
        int eCount = guess.code.length() - guess.code.replace("E","").length();
        int fCount = guess.code.length() - guess.code.replace("F","").length();
        for (int i = 0; i < gjett.length(); i++) {
            int pos = i;
            boolean correctChar = false;
            boolean correctPos = false;
            //Finner først grønne bokser og teller at en grønn boks har blitt funnet for den spesifikke bokstaven
            if(gjett.charAt(pos) == guess.code.charAt(pos)){
                correctPos = true;
                correctChar = true;
                if (gjett.charAt(pos) == 'A'){
                    aCount -= 1;
                }
                if (gjett.charAt(pos) == 'B'){
                    bCount -= 1;
                }
                if (gjett.charAt(pos) == 'C'){
                    cCount -= 1;
                }
                if (gjett.charAt(pos) == 'D'){
                    dCount -= 1;
                }
                if (gjett.charAt(pos) == 'E'){
                    eCount -= 1;
                }
                if (gjett.charAt(pos) == 'F'){
                    fCount -= 1;
                }
            } else
            for (int j = 0; j < gjett.length(); j++) {
                if (gjett.charAt(pos) == guess.code.charAt(j)){
                    //For å sette riktig mengde bokser til gule sjekkes nå mengden restere instanser av en karakter for å
                    //se om correctChar nok en gang skal settes til True eller om den skal forbli false videre
                    if (aCount > 0 && gjett.charAt(pos) == 'A'){    
                        correctChar = true;
                        aCount -= 1;
                    }
                    if (bCount > 0 && gjett.charAt(pos) == 'B'){    
                        correctChar = true;
                        bCount -= 1;
                    }
                    if (cCount > 0 && gjett.charAt(pos) == 'C'){    
                        correctChar = true;
                        cCount -= 1;
                    }
                    if (dCount > 0 && gjett.charAt(pos) == 'D'){    
                        correctChar = true;
                        dCount -= 1;
                    }
                    if (eCount > 0 && gjett.charAt(pos) == 'E'){    
                        correctChar = true;
                        eCount -= 1;
                    }
                    if (fCount > 0 && gjett.charAt(pos) == 'F'){    
                        correctChar = true;
                        fCount -= 1;
                    }
                }   
            }
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
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
    
    @FXML
    public void debugmode(ActionEvent event) throws IOException {
        if (debugbutton.isSelected()) {
            codeGrid.setVisible(true);
        } else {
            codeGrid.setVisible(false);
        }
    }


   
}