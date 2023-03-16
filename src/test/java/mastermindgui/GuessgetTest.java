package mastermindgui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GuessgetTest {

    Guessget guessget;

    @BeforeEach
    void setUp() {
        guessget = new Guessget();
    }

    @Test
    void testLengthCheck() {
        assertTrue(guessget.lengthcheck("ABC"));
        assertFalse(guessget.lengthcheck("ABCD"));
    }

    @Test
    void testCharCheck() {
        assertTrue(guessget.charcheck("ZZZZ"));
        assertFalse(guessget.charcheck("ABCD"));
    }

    @Test
    void testValidityCheck() {
        assertEquals(1, guessget.Validitycheck("ABCEG")); // contains invalid characters and has incorrect length
        assertEquals(2, guessget.Validitycheck("ABC")); // has incorrect length
        assertEquals(3, guessget.Validitycheck("ABCG")); // contains invalid characters
        assertEquals(4, guessget.Validitycheck("ABCD")); // valid guess
    }
}
