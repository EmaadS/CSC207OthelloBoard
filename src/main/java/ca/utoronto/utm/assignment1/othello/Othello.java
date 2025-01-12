package ca.utoronto.utm.assignment1.othello;

import java.util.Random;

/**
 * Capture an Othello game. This includes an OthelloBoard as well as knowledge
 * of how many moves have been made, whosTurn is next (OthelloBoard.P1 or
 * OthelloBoard.P2). It knows how to make a move using the board and can tell
 * you statistics about the game, such as how many tokens P1 has and how many
 * tokens P2 has. It knows who the winner of the game is, and when the game is
 * over.
 * 
 * See the following for a short, simple introduction.
 * https://www.youtube.com/watch?v=Ol3Id7xYsY4
 * 
 * @author arnold
 *
 */
public class Othello {
	public static final int DIMENSION = 8; // This is an 8x8 game
	private char whosTurn = OthelloBoard.P1; // P1 moves first!
	private int numMoves = 0;
	private OthelloBoard board;

	public Othello() {
		this.board = new OthelloBoard(DIMENSION);
	}

	/**
	 * return P1,P2 or EMPTY depending on who moves next.
	 * 
	 * @return P1, P2 or EMPTY
	 */
	public char getWhosTurn() {
		return this.whosTurn;
	}

	/**
	 * Attempt to make a move for P1 or P2 (depending on whos turn it is) at
	 * position row, col. A side effect of this method is modification of whos turn
	 * and the move count.
	 * 
	 * @param row
	 * @param col
	 * @return whether the move was successfully made.
	 */
	public boolean move(int row, int col) {
		boolean moved = this.board.move(row, col, this.whosTurn);
		if (moved) {
			numMoves++;
			this.whosTurn = OthelloBoard.otherPlayer(this.whosTurn);
		}
		return moved;
	}

	/**
	 * 
	 * @param player P1 or P2
	 * @return the number of tokens for player on the board
	 */
	public int getCount(char player) {
		return this.board.getCount(player);
	}

	/**
	 * Returns the winner of the game.
	 * 
	 * @return P1, P2 or EMPTY for no winner, or the game is not finished.
	 */
	public char getWinner() {
		if (isGameOver()) {
			int countP1 = getCount(OthelloBoard.P1);
			int countP2 = getCount(OthelloBoard.P2);
			if (countP1 > countP2) return OthelloBoard.P1;
			else if (countP2 > countP1) return OthelloBoard.P2;
			else return OthelloBoard.EMPTY;
		}
		return OthelloBoard.EMPTY;
	}

	/**
	 * 
	 * @return whether the game is over (no player can move next)
	 */
	public boolean isGameOver() {
		return this.board.hasMove() == OthelloBoard.EMPTY;
	}

	// HELPER FUNCTIONS CREATED BY ME:

	/**
	 * check if the row and col given is occupied by P1, P2 or EMPTY.
	 *
	 * @param row
	 * @param col
	 * @return P1, P2 or EMPTY
	 */
	public char checkMove(int row, int col) {
		return this.board.get(row, col);
	}

	/**
	 * Determines whether there are any valid moves left for Player 1 and/or Player 2.
	 *
	 * @return P1, P2, BOTH or EMPTY
	 */
	public char movesLeft() {
		return this.board.hasMove();
	}

	/**
	* Skips the current player's turn. The turn is passed to the other player,
	* switching the current player to the opponent (P1 to P2 or P2 to P1).
	*/
	public void skipTurn(){
		this.whosTurn = OthelloBoard.otherPlayer(this.whosTurn);
	}

	/**
	 * Finds and returns the best move for the given player using a greedy strategy.
	 * The greedy strategy selects the move that maximizes the player's number
	 * of tokens on the board.
	 *
	 * @param player the player
	 * @return Move that is coincides with the greedy strategy
	 */
	public Move getGreedyMove(char player) {
		return this.board.getGreedyMove(player);
	}

	// END OF HELPER FUNCTIONS

	/**
	 * 
	 * @return a string representation of the board.
	 */
	public String getBoardString() {
		return this.board.toString();
	}

	/**
	 * run this to test the current class. We play a completely random game. DO NOT
	 * MODIFY THIS!! See the assignment page for sample outputs from this.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Random rand = new Random();

		Othello o = new Othello();
		System.out.println(o.getBoardString());
		while (!o.isGameOver()) {
			int row = rand.nextInt(8);
			int col = rand.nextInt(8);

			if (o.move(row, col)) {
				System.out.println("makes move (" + row + "," + col + ")");
				System.out.println(o.getBoardString() + o.getWhosTurn() + " moves next");
			}
		}

	}
}