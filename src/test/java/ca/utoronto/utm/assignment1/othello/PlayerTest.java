package ca.utoronto.utm.assignment1.othello;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

/**
 * This class contains junit tests for player classes, playerGreedy and playerRandom
 * It tests the functionality of selecting moves based on the greedy strategy and the random strategy.
 */

public class PlayerTest {

    Othello othello;
    PlayerRandom playerRandom;

    /**
     * Sets up the Othello game and board with an initial set of moves
     * before each test. This ensures that each test starts from the same board state.
     */
    @BeforeEach
    public void setUp(){

        othello = new Othello();
        playerRandom = new PlayerRandom(othello, OthelloBoard.P2);

        OthelloBoard board = new OthelloBoard(Othello.DIMENSION);
        othello.move(2, 4);
        othello.move(2, 3);
        othello.move(5, 2);
        othello.move(1, 5);
        othello.move(1, 3);
        /*

        Board:

          0 1 2 3 4 5 6 7
         +-+-+-+-+-+-+-+-+
        0| | | | | | | | |0
         +-+-+-+-+-+-+-+-+
        1| | | |X| |O| | |1
         +-+-+-+-+-+-+-+-+
        2| | | |X|O| | | |2
         +-+-+-+-+-+-+-+-+
        3| | | |X|X| | | |3
         +-+-+-+-+-+-+-+-+
        4| | | |X|X| | | |4
         +-+-+-+-+-+-+-+-+
        5| | |X| | | | | |5
         +-+-+-+-+-+-+-+-+
        6| | | | | | | | |6
         +-+-+-+-+-+-+-+-+
        7| | | | | | | | |7
         +-+-+-+-+-+-+-+-+
          0 1 2 3 4 5 6 7
         */
    }


    /**
     * Tests the getGreedyMove method for playerGreedy.
     * This method tests whether the greedy move (the move that maximizes the number
     * of pieces captured) is correctly chosen consistently at various stages of the game.
     */
    @Test
    public void testGetGreedyMove() {

        Move move = othello.getGreedyMove(OthelloBoard.P2);
        othello.move(move.getRow(), move.getCol());
        assertEquals(move.getRow(), 5);
        assertEquals(move.getCol(), 4);

        othello.move(3, 5);
        move = othello.getGreedyMove(OthelloBoard.P2);
        othello.move(move.getRow(), move.getCol());
        assertEquals(move.getRow(), 4);
        assertEquals(move.getCol(), 2);

        othello.move(5, 3);
        move = othello.getGreedyMove(OthelloBoard.P2);
        othello.move(move.getRow(), move.getCol());
        assertEquals(move.getRow(), 4);
        assertEquals(move.getCol(), 5);

        othello.move(5, 6);
        move = othello.getGreedyMove(OthelloBoard.P2);
        othello.move(move.getRow(), move.getCol());
        assertEquals(move.getRow(), 0);
        assertEquals(move.getCol(), 3);

        othello.move(4, 1);
        move = othello.getGreedyMove(OthelloBoard.P2);
        othello.move(move.getRow(), move.getCol());
        assertEquals(move.getRow(), 3);
        assertEquals(move.getCol(), 6);

        othello.move(1, 4);
        move = othello.getGreedyMove(OthelloBoard.P2);
        othello.move(move.getRow(), move.getCol());
        assertEquals(move.getRow(), 0);
        assertEquals(move.getCol(), 4);

        assertEquals(6, othello.getCount(OthelloBoard.P1));
        assertEquals(14, othello.getCount(OthelloBoard.P2));

    }

    /**
     * Tests the getMove method for the PlayerRandom class.
     * This method tests whether the random move is valid (i.e., it belongs
     * to the valid moves for player P2).
     * * It is just a random move so there is only so much we can test *
     */
    @Test
    public void testRandomGetMove(){
        Move move = playerRandom.getMove();
        othello.move(move.getRow(), move.getCol());
        assertEquals(OthelloBoard.P2, othello.checkMove(move.getRow(), move.getCol()));
    }

}
