package mastermindgui;
import static org.junit.jupiter.api.Assertions.*;

/*import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter; */
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.io.TempDir; fungerer ikke???? HJELPLPLPL

public class HighscoreTest {
    
    private Highscore highscore;
    
    @BeforeEach
    public void setUp() {
        highscore = new Highscore("test_scores.csv");
        Highscore.clearScores();
    }
    
    @Test
    public void testAddScore() {
        highscore.addScore("Alice", 5);
        highscore.addScore("Bob", 6);
        highscore.addScore("Charlie", 7);
        
        List<Highscore.Score> scores = highscore.getScores();
        assertEquals(3, scores.size());
        assertEquals("Alice", scores.get(0).getName());
        assertEquals(5, scores.get(0).getGuesses());
        assertEquals("Bob", scores.get(1).getName());
        assertEquals(6, scores.get(1).getGuesses());
        assertEquals("Charlie", scores.get(2).getName());
        assertEquals(7, scores.get(2).getGuesses());
    }
    
    @Test
    public void testClearScores() {
        highscore.addScore("Alice", 5);
        highscore.addScore("Bob", 6);
        highscore.addScore("Charlie", 7);
        
        Highscore.clearScores();
        
        List<Highscore.Score> scores = highscore.getScores();
        assertEquals(0, scores.size());
    }
}
   /*  
    @Test
    public void testLoadScores(@TempDir File tempDir) throws Exception {
        File file = new File(tempDir, "test_scores.csv");
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("Alice,5");
            writer.println("Bob,6");
            writer.println("Charlie,7");
        }
        
        highscore = new Highscore(file.getAbsolutePath());
        highscore.loadScores();
        
        List<Highscore.Score> scores = highscore.getScores();
        assertEquals(3, scores.size());
        assertEquals("Alice", scores.get(0).getName());
        assertEquals(5, scores.get(0).getGuesses());
        assertEquals("Bob", scores.get(1).getName());
        assertEquals(6, scores.get(1).getGuesses());
        assertEquals("Charlie", scores.get(2).getName());
        assertEquals(7, scores.get(2).getGuesses());
    }
}

*/