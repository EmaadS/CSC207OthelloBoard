package ca.utoronto.utm.assignment1.othello;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerGreedyTest {
    Othello othello;
    PlayerGreedy playerGreedy;
    Move[] moves;

    @BeforeEach
    public void setUp() throws Exception {
        othello = new Othello();
        playerGreedy = new PlayerGreedy(othello, OthelloBoard.P1);
    }

    @Test
    public void testGreedyPlayerTieBreakerOnRow() {

        System.out.println("Board setup before GreedyPlayer's move:");
        System.out.println(othello.getBoardString());

        // Board should look like this:
        //   0 1 2 3 4 5 6 7
        //  +-+-+-+-+-+-+-+-+
        // 0| | | | | | | | |0
        //  +-+-+-+-+-+-+-+-+
        // 1| | | | | | | | |1
        //  +-+-+-+-+-+-+-+-+
        // 2| | | | | | | | |2
        //  +-+-+-+-+-+-+-+-+
        // 3| | | |X|O| | | |3
        //  +-+-+-+-+-+-+-+-+
        // 4| | | |O|X| | | |4
        //  +-+-+-+-+-+-+-+-+
        // 5| | | | | | | | |5
        //  +-+-+-+-+-+-+-+-+
        // 6| | | | | | | | |6
        //  +-+-+-+-+-+-+-+-+
        // 7| | | | | | | | |7
        //  +-+-+-+-+-+-+-+-+
        //   0 1 2 3 4 5 6 7
        // P1: 2, P2: 2  P1 moves next

        Move bestMove = playerGreedy.getMove();
        othello.move(bestMove.getRow(), bestMove.getCol());

        System.out.println("Board after GreedyPlayer's move:");
        System.out.println(othello.getBoardString());

        // GreedyPlayer has 4 equally efficient moves: (2,4), (3,5), (5,3), (4,2)
        // GreedyPlayer chooses move with the smallest row, then smallest column: (2,4)
        assertEquals(new Move(2, 4).toString(), bestMove.toString(), "GreedyPlayer should move to (2,4)");
    }

    @Test
    public void testGreedyPlayerEfficientMove() {

        moves = new Move[] {
                new Move(5, 3), new Move(5, 4), new Move(5, 5), new Move(3, 2),
                new Move(2, 3), new Move(6, 4),
        };
        for (Move move : moves) {
            othello.move(move.getRow(), move.getCol());
        }
        System.out.println("Board setup before GreedyPlayer's move:");
        System.out.println(othello.getBoardString());

        // Board should look like this:
        //   0 1 2 3 4 5 6 7
        //  +-+-+-+-+-+-+-+-+
        // 0| | | | | | | | |0
        //  +-+-+-+-+-+-+-+-+
        // 1| | | | | | | | |1
        //  +-+-+-+-+-+-+-+-+
        // 2| | | |X| | | | |2
        //  +-+-+-+-+-+-+-+-+
        // 3| | |O|X|O| | | |3
        //  +-+-+-+-+-+-+-+-+
        // 4| | | |X|O| | | |4
        //  +-+-+-+-+-+-+-+-+
        // 5| | | |X|O|X| | |5
        //  +-+-+-+-+-+-+-+-+
        // 6| | | | |O| | | |6
        //  +-+-+-+-+-+-+-+-+
        // 7| | | | | | | | |7
        //  +-+-+-+-+-+-+-+-+
        //   0 1 2 3 4 5 6 7
        // P1: 5, P2: 5  P1 moves next

        Move bestMove = playerGreedy.getMove();
        othello.move(bestMove.getRow(), bestMove.getCol());

        System.out.println("Board after GreedyPlayer's move:");
        System.out.println(othello.getBoardString());

        // GreedyPlayer has 2 possible moves: (3,1) and (3,5)
        // GreedyPlayer chooses one with most tokens earned: (3,5)
        assertEquals(new Move(3, 5).toString(), bestMove.toString(), "GreedyPlayer should move to (3,5)");
    }

}
