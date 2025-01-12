package ca.utoronto.utm.assignment1.othello;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerHumanTest {
    Othello othello;
    PlayerHuman playerHuman;

    @BeforeEach
    public void setUp() throws Exception {
        othello = new Othello();
    }

    private void setPlayerHumanStdin(InputStream inputStream) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        Field stdinField = PlayerHuman.class.getDeclaredField("stdin");
        stdinField.setAccessible(true);
        stdinField.set(null, reader);
    }

    @Test
    public void testValidInput() throws Exception {
        // Simulate user input: row = 2, col = 3
        String simulatedInput = "2\n3\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        // Use reflection to set stdin to our simulated input
        setPlayerHumanStdin(inputStream);

        playerHuman = new PlayerHuman(othello, OthelloBoard.P1);

        Move move = playerHuman.getMove();
        System.out.println(move);
        assertEquals(new Move(2, 3).toString(), move.toString(), "PlayerHuman should move to (2,3)");
    }

    @Test
    public void testInvalidInputThenValid() throws Exception {
        // Simulate user input: invalid row (-1), valid row (3), invalid col (9), valid col (4)
        String simulatedInput = "-1\n3\n9\n4\n";
        InputStream inputStream = new ByteArrayInputStream(simulatedInput.getBytes());

        // Use reflection to set stdin to our simulated input
        setPlayerHumanStdin(inputStream);

        playerHuman = new PlayerHuman(othello, OthelloBoard.P1);

        Move move = playerHuman.getMove();
        System.out.println(move);
        assertEquals(new Move(3, 4).toString(), move.toString(), "PlayerHuman should move to (3,4)");
    }
}
