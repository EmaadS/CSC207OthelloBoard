package ca.utoronto.utm.assignment1.othello;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 * This class contains junit tests for all controllers classes.
 * It tests the functionality of different Othello game controllers with
 * varying player types
 */

public class ControllerTest {

    Othello othello;

    /**
     * Set up the game environment before each test.
     * Initializes a new Othello game.
     */
    @BeforeEach
    public void setUp() {
        othello = new Othello();
    }

    /**
     * Test case for a Random player versus a Greedy player.
     * Asserts that the winner is either Player 1, Player 2, or the game is a draw.
     */
    @Test
    public void testRandomVSGreedy() {

        PlayerRandom player1 = new PlayerRandom(othello, OthelloBoard.P1);
        PlayerGreedy player2 = new PlayerGreedy(othello, OthelloBoard.P2);
        OthelloControllerRandomVSGreedy oc = new OthelloControllerRandomVSGreedy(othello, player1, player2);

        // recording the winner and wins
        char winner = oc.play();
        assertTrue(winner == OthelloBoard.P1 || winner == OthelloBoard.P2 || winner == OthelloBoard.EMPTY);
    }

    /**
     * Test case for two Random players playing against each other.
     * Asserts that the winner is either Player 1, Player 2, or the game is a draw.
     */
    @Test
    public void testRandomVSRandom() {

        PlayerRandom player1 = new PlayerRandom(othello, OthelloBoard.P1);
        PlayerRandom player2 = new PlayerRandom(othello, OthelloBoard.P2);
        OthelloControllerRandomVSRandom oc = new OthelloControllerRandomVSRandom(othello, player1, player2);

        // recording the winner and wins
        char winner = oc.play();
        assertTrue(winner == OthelloBoard.P1 || winner == OthelloBoard.P2 || winner == OthelloBoard.EMPTY);
    }


}
