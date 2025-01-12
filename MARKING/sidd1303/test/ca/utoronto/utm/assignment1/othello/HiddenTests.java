package ca.utoronto.utm.assignment1.othello;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HiddenTests {
    Move[] moves = {
            new Move(4, 2), new Move(5, 2), new Move(3, 5),
            new Move(2, 5), new Move(1, 5), new Move(3, 6),
            new Move(2, 6), new Move(3, 2), new Move(6, 1),
            new Move(1, 6), new Move(2, 3), new Move(6, 2),
            new Move(5, 1), new Move(5, 4), new Move(5, 3),
            new Move(2, 4), new Move(7, 3), new Move(0, 6),
            new Move(0, 7), new Move(6, 0), new Move(3, 7),
            new Move(1, 3), new Move(0, 4), new Move(6, 4),
            new Move(3, 1), new Move(4, 1), new Move(0, 3),
            new Move(7, 2), new Move(1, 4), new Move(3, 0),
            new Move(5, 5), new Move(4, 5), new Move(4, 0),
            new Move(0, 5), new Move(1, 2), new Move(6, 3),
            new Move(5, 0), new Move(0, 1), new Move(1, 1),
            new Move(6, 5), new Move(5, 6), new Move(7, 4),
            new Move(2, 2), new Move(2, 0), new Move(7, 1),
            new Move(6, 6), new Move(7, 5), new Move(1, 7),
            new Move(6, 7), new Move(7, 7), new Move(7, 0),
            new Move(0, 0), new Move(7, 6), new Move(4, 6),
            new Move(5, 7), new Move(4, 7), new Move(2, 1),
            new Move(1, 0), new Move(2, 7), new Move(0, 2)
    };

    Move[] moves2 = {
            new Move(4, 2), new Move(3, 2), new Move(2, 3),
            new Move(5, 2), new Move(3, 1), new Move(5, 4),
            new Move(5, 5), new Move(5, 6), new Move(6, 2),
            new Move(6, 1), new Move(2, 4), new Move(2, 1),
            new Move(2, 2), new Move(7, 2), new Move(6, 6),
            new Move(1, 4), new Move(5, 3), new Move(1, 2),
            new Move(1, 0), new Move(6, 4), new Move(7, 3),
            new Move(4, 0), new Move(4, 6), new Move(3, 0),
            new Move(2, 5), new Move(3, 6), new Move(0, 2),
            new Move(3, 5), new Move(4, 5), new Move(6, 5),
            new Move(2, 6), new Move(2, 7), new Move(7, 6),
            new Move(0, 3), new Move(5, 1), new Move(0, 1),
            new Move(2, 0), new Move(6, 0), new Move(1, 1),
            new Move(4, 7), new Move(3, 7), new Move(7, 4),
            new Move(7, 0), new Move(5, 0), new Move(1, 3),
            new Move(6, 3), new Move(7, 1), new Move(6, 7),
            new Move(1, 6), new Move(0, 0), new Move(5, 7),
            new Move(0, 6), new Move(7, 7), new Move(4, 1),
            new Move(1, 5), new Move(0, 4), new Move(0, 7),
            new Move(1, 7), new Move(7, 5), new Move(0, 5)
    };

    @Test
    public void testNoWinner() {
        Othello othello = new Othello();

        for (int i = 0; i < moves.length; i++) {
            assertEquals(OthelloBoard.EMPTY, othello.getWinner(), "During play");
            assertFalse(othello.isGameOver(), "During play");
            othello.move(moves[i].getRow(), moves[i].getCol());
        }

        // After all moves, verify if the game ends in a tie.
        assertTrue(othello.isGameOver(), "Game should be over");
        assertEquals(OthelloBoard.EMPTY, othello.getWinner(), "Tie Game");

    }

    @Test
    public void testgetCount() {
        Othello othello = new Othello();
        assertEquals(2, othello.getCount(OthelloBoard.P1));
        assertEquals(2, othello.getCount(OthelloBoard.P2));

        for (int i = 0; i < moves.length; i++) {
            othello.move(moves[i].getRow(), moves[i].getCol());
        }

        assertEquals(32, othello.getCount(OthelloBoard.P1));
        assertEquals(32, othello.getCount(OthelloBoard.P2));

    }

    @Test
    public void testgetPlayer() {
        OthelloBoard ob = new OthelloBoard(8);
        char currPlayer = OthelloBoard.P1;
        for (int i = 0; i < moves.length - 1; i++) {
            ob.move(moves[i].getRow(), moves[i].getCol(), currPlayer);
            currPlayer = ob.otherPlayer(currPlayer);
        }
        assertEquals(ob.get(0, 2), OthelloBoard.EMPTY);
        assertEquals(ob.get(0, 0), OthelloBoard.P2);
        assertEquals(ob.get(0, 1), OthelloBoard.P2);
        assertEquals(ob.get(0, 3), OthelloBoard.P1);
        assertEquals(ob.get(0, 4), OthelloBoard.P1);
    }

    @Test
    public void validVsInvalid() {
        Othello othello = new Othello();

        for (int i = 0; i < moves.length - 3; i++) {
            othello.move(moves[i].getRow(), moves[i].getCol());
        }
        assertFalse(othello.move(6, 6), "Invalid move");
        assertTrue(othello.move(1, 0), "Valid move");
        assertFalse(othello.move(3, 3), "Invalid move");
        assertTrue(othello.move(2, 7), "Valid move");
        assertFalse(othello.move(2, 2), "Invalid move");
        assertTrue(othello.move(0, 2), "Valid move");

    }

    @Test
    public void playerhasMove() {
        OthelloBoard ob = new OthelloBoard(8);
        char currPlayer = OthelloBoard.P1;
        for (int i = 0; i < moves.length - 2; i++) {
            ob.move(moves[i].getRow(), moves[i].getCol(), currPlayer);
            System.out.println(moves[i] + " " + ob.hasMove());
            assertEquals(ob.hasMove(), OthelloBoard.BOTH, "both player have a move");
            currPlayer = ob.otherPlayer(currPlayer);
        }
        ob.move(2, 7, currPlayer);
        assertEquals(ob.hasMove(), OthelloBoard.P2, "one player has a move");
        currPlayer = ob.otherPlayer(currPlayer);
        ob.move(0, 2, currPlayer);
        assertEquals(ob.hasMove(), OthelloBoard.EMPTY, "no player have move");
    }

    @Test
    public void p2Wins() {
        Othello othello = new Othello();

        for (int i = 0; i < moves2.length; i++) {
            assertEquals(OthelloBoard.EMPTY, othello.getWinner(), "During play");
            assertFalse(othello.isGameOver(), "During play");
            othello.move(moves2[i].getRow(), moves2[i].getCol());
        }

        // After all moves, verify if the P2 wins.
        assertTrue(othello.isGameOver(), "Game should be over");
        assertEquals(OthelloBoard.P2, othello.getWinner(), "Tie Game");
    }

}
