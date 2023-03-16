package mastermindgui;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CodegeneratorTest {
    
    Codegenerator codegenerator;
    
    @BeforeEach
    void setUp() {
        codegenerator = new Codegenerator();
    }
    
    @Test
    void testCodegen() {
        String code = codegenerator.codegen();
        assertEquals(4, code.length());
        for (int i = 0; i < code.length(); i++) {
            assertTrue(code.charAt(i) >= 65 && code.charAt(i) <= 70);
        }
    }


}
