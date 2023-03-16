package mastermindgui;

import java.util.Random;


/* Lager en tilfeldig kode med 4 bokstaver fra A - F. Bare uppercase. */

public class Codegenerator {
    public String code;

    public String codegen(){
        int leftLimit = 65; // 'A'
        int rightLimit = 70; // 'F'
        int targetStringLength = 4;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        code = buffer.toString();
        return code;
    }
}
