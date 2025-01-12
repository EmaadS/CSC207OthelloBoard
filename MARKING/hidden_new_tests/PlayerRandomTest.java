package ca.utoronto.utm.assignment1.othello;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class PlayerRandomTest {

    @Test
    public void testGetMoveWithTwoPossibleMoves() {
        Othello othello = new Othello();
        PlayerRandom playerRandom = new PlayerRandom(othello, OthelloBoard.P1);
        // P1 should have exactly four possible moves: (2,4),(4,2),(3,5),(5,3)
        Set<String> uniqueMoves = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            Move move = playerRandom.getMove();
            uniqueMoves.add(String.valueOf(move));

        }
        System.out.println(uniqueMoves);

        assertTrue(uniqueMoves.size() == 4,
                "PlayerRandom should generate different moves in different calls.");
    }

    @Test
    public void testGetMoveWithNoPossibleMoves() {
        Othello othello = new Othello();
        // set up othello with no possible moves
        Move[] moves = {new Move(2, 4), new Move(2, 5),
                new Move(2, 6), new Move(2, 3), new Move(2, 2), new Move(3, 2),
                new Move(4, 2), new Move(5, 4), new Move(6, 4)};
        for (int i = 0; i < moves.length - 1; i++) {
            assertEquals(OthelloBoard.EMPTY, othello.getWinner(), "During play");
            othello.move(moves[i].getRow(), moves[i].getCol());
        }

        // P1 should have one possible move
        PlayerRandom playerRandom = new PlayerRandom(othello, OthelloBoard.P1);
        Set<String> uniqueMoves = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            Move move = playerRandom.getMove();
            uniqueMoves.add(String.valueOf(move));

        }
        assertTrue(uniqueMoves.size() == 1,
                "PlayerRandom should generate different moves in different calls.");

    }

}
