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
    
    
    
    
  
    
    @FXML
    public void initialize() {
        for (int i = 0; i < guess.code.length(); i++) {
            codeGrid.add(new TextField(String.valueOf(guess.code.charAt(i))), i, 0);
            
        }
    }
    
    
    @FXML
    private void onEnter() {
        String gjett = BrukerInput.getText().toUpperCase();
        int validity = guess.Validitycheck(gjett);
        

        
        if(validity == 1){
            charerror.setVisible(true);
        }
        if(validity == 2){
            aferror.setVisible(true);
        }
        
        if (validity == 3) {
            aferror.setVisible(false);
            charerror.setVisible(false);
            for (int i = 0; i < gjett.length(); i++) {
                charGrid.add(new TextField(String.valueOf(gjett.charAt(i))), i, currentRow);
            }
            currentRow++;
        }
    }


    @FXML
    private void Win(String gjett){
        if(gjett == guess.code){
            codeGrid.setVisible(true);
            winscreen.setVisible(true);

            }
        }



    @FXML
    public void reset(){
    
    }    
}
    

