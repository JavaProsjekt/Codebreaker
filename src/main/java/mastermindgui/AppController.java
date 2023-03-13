package mastermindgui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;





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
            for (int i = 0; i < gjett.length(); i++) {
                charGrid.add(new TextField(String.valueOf(gjett.charAt(i))), i, currentRow);
                
            }
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


    @FXML
    public void reset(){
        startNewGame(); // start a new game
    }

    @FXML
    public void entername(){
        name = BrukerInput.getText();
        if(name.length() != 4){
            namecharwrong.setVisible(true);
        }
        for (int i = 0; i < name.length(); i++) {
            nameGrid.add(new TextField(String.valueOf(name.charAt(i))), i, 0);
            
        }

    }
    
    public void ScoreReset(){
        highscore.clearScores();
    }



}
    

