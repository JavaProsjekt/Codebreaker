package mastermindgui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javafx.scene.layout.GridPane;





public class AppController {
    @FXML private TextField BrukerInput;
    @FXML private GridPane codeGrid;
    @FXML private GridPane charGrid;
    @FXML private ImageView charerror;
    @FXML private ImageView aferror;
    @FXML private ImageView winscreen;


    private Guessget guess = new Guessget();
    private int currentRow = 0;
    public int guesscount = 0;
    
  
    
    @FXML
    public void initialize() { // Denne og starter overraskende bra. 
        for (int i = 0; i < guess.code.length(); i++) {
            codeGrid.add(new TextField(String.valueOf(guess.code.charAt(i))), i, 0);
            
        }
    }

    
    @FXML
    private void onEnter() { // Funker faktisk helt ok as, litt wild egt.
        String gjett = BrukerInput.getText().toUpperCase();
        int validity = guess.Validitycheck(gjett);

        if(validity == 1){
            charerror.setVisible(true);
            aferror.setVisible(true);
        }
        if(validity == 2){
            charerror.setVisible(true);
        }
        if(validity == 3){
            aferror.setVisible(true);
        }
        if (validity == 4) {
            aferror.setVisible(false);
            charerror.setVisible(false);
            for (int i = 0; i < gjett.length(); i++) {
                charGrid.add(new TextField(String.valueOf(gjett.charAt(i))), i, currentRow);
            }
            currentRow++;
            Win(gjett);
        }
    }


    @FXML
    private void Win(String gjett) {
        if (gjett.equals(guess.code)) {
            winscreen.setVisible(true);
        } else {
            winscreen.setVisible(false);
        }
    }
    
    

    @FXML
    public void reset(){
        guesscount = 0;
        currentRow = 0;
        charGrid.getChildren().clear();
        codeGrid.getChildren().clear();
        initialize();
        winscreen.setVisible(false);
        codeGrid.setVisible(false);
        aferror.setVisible(false);
        charerror.setVisible(false);
    }    
}
    

