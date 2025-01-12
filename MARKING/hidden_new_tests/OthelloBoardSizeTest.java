package ca.utoronto.utm.assignment1.othello;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OthelloBoardSizeTest {
    OthelloBoard smallBoard;
    OthelloBoard largeBoard;

    @BeforeEach
    public void setUp() {
        smallBoard = new OthelloBoard(4);  // Initialize a 4x4 board
        largeBoard = new OthelloBoard(16); // Initialize a 16x16 board
    }

    @Test
    public void testInitialSetupOnSmallBoard() {
        // Check initial position of the four centre pieces on the small 4x4 board
        assertEquals(OthelloBoard.P1, smallBoard.get(1, 1), "Small board initial P1 token");
        assertEquals(OthelloBoard.P1, smallBoard.get(2, 2), "Small board initial P1 token");
        assertEquals(OthelloBoard.P2, smallBoard.get(1, 2), "Small board initial P2 token");
        assertEquals(OthelloBoard.P2, smallBoard.get(2, 1), "Small board initial P2 token");

        // Ensure all other spaces are empty
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if ((row == 1 || row == 2) && (col == 1 || col == 2)) continue;
                assertEquals(OthelloBoard.EMPTY, smallBoard.get(row, col), "All other spaces on small board should be empty");
            }
        }
    }

    @Test
    public void testInitialSetupOnLargeBoard() {
        // Check initial position of the four centre pieces on the small 4x4 board
        assertEquals(OthelloBoard.P1, largeBoard.get(7, 7), "Large board initial P1 token");
        assertEquals(OthelloBoard.P1, largeBoard.get(8, 8), "Large board initial P1 token");
        assertEquals(OthelloBoard.P2, largeBoard.get(7, 8), "Large board initial P2 token");
        assertEquals(OthelloBoard.P2, largeBoard.get(8, 7), "Large board initial P2 token");

        // Ensure all other spaces are empty
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                if ((row == 7 || row == 8) && (col == 7 || col == 8)) continue;
                assertEquals(OthelloBoard.EMPTY, largeBoard.get(row, col), "All other spaces on large board should be empty");
            }
        }
    }
}
