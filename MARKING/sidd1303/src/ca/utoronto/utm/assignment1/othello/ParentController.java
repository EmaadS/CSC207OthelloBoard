package ca.utoronto.utm.assignment1.othello;

/**
 * ParentController serves as an abstract base class for controlling the
 * flow of the Othello game. It manages two players and the game state,
 * facilitating turns, move reporting, and determining the game outcome.
 *
 * This class defines the structure for subclasses that implement specific
 * gameplay behaviors.
 *
 */

abstract class ParentController {

    protected Othello othello;
    protected ParentPlayer player1;
    protected ParentPlayer player2;

    /**
     * Constructs a ParentController for managing a game of Othello between
     * two players.
     *
     * @param othello the Othello game instance that this controller will manage
     * @param player1 the first player participating in the game
     * @param player2 the second player participating in the game
     */
    public ParentController(Othello othello, ParentPlayer player1, ParentPlayer player2) {
        this.othello = othello;
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Manages the gameplay loop, alternating turns between the two players
     * until the game is over. Reports the current state of the game, handles
     * player moves, and determines the winner.
     *
     * @return the character representing the winner of the game ('X' for P1, 'O' for P2)
     */
    public char play(){
        Move move = null;
        char turn;
        while (!this.othello.isGameOver()) {
            this.report();
            turn = othello.getWhosTurn();

            // If it is player1's turn
            if (turn == OthelloBoard.P1) {
                // skip if there are no moves left
                if (this.othello.movesLeft() != OthelloBoard.BOTH && this.othello.movesLeft() != OthelloBoard.P1) {
                    this.othello.skipTurn();
                    continue;
                }
                move = player1.getMove();
            }

            // If it is player2's turn
            else if (turn == OthelloBoard.P2) {
                // skip if there are no moves left
                if (this.othello.movesLeft() != OthelloBoard.BOTH && this.othello.movesLeft() != OthelloBoard.P2) {
                    this.othello.skipTurn();
                    continue;
                }
                move = player2.getMove();
            }
            this.reportMove(turn, move);
            this.othello.move(move.getRow(), move.getCol());
        }
        this.reportFinal();
        return this.othello.getWinner();
    }

    /**
     * Reports the move made by a player, including the player's identifier
     * and the move's coordinates.
     *
     * @param whosTurn the character representing the player making the move
     * @param move the move that was made by the player
     */
    protected void reportMove(char whosTurn, Move move) {
        System.out.println(whosTurn + " makes move " + move + "\n");
    }

    /**
     * Reports the current state of the game, including the board layout,
     * player scores, and the current player's turn.
     */
    protected void report() {

        String s = othello.getBoardString() + OthelloBoard.P1 + ":"
                + othello.getCount(OthelloBoard.P1) + " "
                + OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2) + "  "
                + othello.getWhosTurn() + " moves next";
        System.out.println(s);
    }

    /**
     * Reports the final state of the game, including the board layout,
     * player scores, and the winner.
     */
    protected void reportFinal() {

        String s = othello.getBoardString() + OthelloBoard.P1 + ":"
                + othello.getCount(OthelloBoard.P1) + " "
                + OthelloBoard.P2 + ":" + othello.getCount(OthelloBoard.P2)
                + "  " + othello.getWinner() + " won\n";
        System.out.println(s);
    }
}
