package mastermindgui;


import java.util.Vector;



public class Guessget {
    public int guesscount = 0;
    public String guess;
    
    
    Codegenerator Kode = new Codegenerator(); // lager et objekt av klassen Codegenerator
    
    String code = Kode.codegen(); // kaller på metoden codegen


    public boolean lengthcheck(String guess){ // sjekker lengden på gjetningen
        if(guess.length() != 4){
            return true;
        }
        return false;
    }

    public boolean charcheck(String guess){ // sjekker om gjetningen inneholder gyldige bokstaver
        for(int i = 0; i < guess.length(); i++){
            if(guess.charAt(i) < 65 || guess.charAt(i) > 70){
                return true;
            }
        }
        return false;
    }

    

        // Sjekker gjetningen for validitet
    public int Validitycheck(String guess){
        guesscount += 1;    
        if(lengthcheck(guess) && charcheck(guess)){
            return 1;
        }
        if(lengthcheck(guess)){
            return 2;
        }
        if(charcheck(guess)){
            return 3;
        }
        return 4;
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
