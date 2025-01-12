package ca.utoronto.utm.assignment1.othello;
/**
 * The Move class represents a move in the Othello game, encapsulating the
 * row and column coordinates on the game board.
 *
 * Each instance of the Move class corresponds to a specific position on the
 * board where a player can place their token.
 *
 * @author arnold
 */

public class Move {
	private int row, col;

	/**
	 * Constructs a Move instance with the specified row and column.
	 *
	 * @param row the row coordinate of the move
	 * @param col the column coordinate of the move
	 */
	public Move(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * Retrieve row of the move
	 *
	 * @return row coordinate
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Retrieve column of the move
	 *
	 * @return col coordinate
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Returns a string representation of the move in the format: (row, col).
	 *
	 * @return a string representing the move
	 */
	public String toString() {
		return "(" + this.row + "," + this.col + ")";
	}
}
