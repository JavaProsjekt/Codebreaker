package mastermindgui;

import java.util.Random;

public class Codegenerator {
    public String code;

    public String codegen(){
        int leftLimit = 65; // letter 'a'
        int rightLimit = 70; // letter 'z'
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
