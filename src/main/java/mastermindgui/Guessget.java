package mastermindgui;


import java.util.Vector;



public class Guessget {
    public int guesscount = 0;
    public String guess;
    
    
    Codegenerator Kode = new Codegenerator(); // lager et objekt av klassen Codegenerator
    
    String code = Kode.codegen(); // kaller på metoden codegen
    
    


        // Sjekker gjetningen for validitet
    public int Validitycheck(String Guess){
        guesscount += 1;    
        if(Guess.length() != 4){
            return 1;

        }

        for(int i = 0; i < Guess.length(); i++){
            if(Guess.charAt(i) < 65 || Guess.charAt(i) > 70){
                return 2;
            }
        }
        return 3;
    }

    
    
    public void positionreturn(){
        Vector<Integer> position = new Vector<Integer>();
        for(int i = 0; i < guess.length(); i++){
            if(guess.charAt(i) == code.charAt(i)){
                
                position.add(i);
            }
        }
        if (position.isEmpty()){
            System.out.println("No letters are in the right position");
        }
        if(position.size() < 2)
            System.out.println("You have a correct letter at" + position);

        else
        System.out.println("You have correct letters at" + position);
    }
    

   
    
   
}
