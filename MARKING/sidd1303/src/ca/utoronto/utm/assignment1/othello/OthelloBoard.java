package ca.utoronto.utm.assignment1.othello;

import javax.sql.rowset.JdbcRowSet;

/**
 * Keep track of all of the tokens on the board. This understands some
 * interesting things about an Othello board, what the board looks like at the
 * start of the game, what the players tokens look like ('X' and 'O'), whether
 * given coordinates are on the board, whether either of the players have a move
 * somewhere on the board, what happens when a player makes a move at a specific
 * location (the opposite players tokens are flipped).
 *
 * Othello makes use of the OthelloBoard.
 *
 * @author arnold
 *
 */
public class OthelloBoard {

	public static final char EMPTY = ' ', P1 = 'X', P2 = 'O', BOTH = 'B';
	private int dim = 8;
	private char[][] board;

	/**
	 * Constructs an Othello board of the given dimension.
	 * The board is initialized with all positions set to empty except for the center four,
	 * which are filled with the starting pieces for Player 1 ('x') and Player 2 ('O').
	 *
	 * @param dim the dimension of the Othello board (must be an even number)
	 */
	public OthelloBoard(int dim) {
		this.dim = dim;
		board = new char[this.dim][this.dim];
		for (int row = 0; row < this.dim; row++) {
			for (int col = 0; col < this.dim; col++) {
				this.board[row][col] = EMPTY;
			}
		}
		int mid = this.dim / 2;
		this.board[mid - 1][mid - 1] = this.board[mid][mid] = P1;
		this.board[mid][mid - 1] = this.board[mid - 1][mid] = P2;
	}

	/**
	 *
	 * @return the dimension of the Othello board
	 */
	public int getDimension() {
		return this.dim;
	}

	/**
	 *
	 * @param player either P1 or P2
	 * @return P2 or P1, the opposite of player
	 */
	public static char otherPlayer(char player) {
		if (player == P1) return P2;
		else if (player == P2) return P1;
		return EMPTY;
	}

	/**
	 *
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @return P1,P2 or EMPTY, EMPTY is returned for an invalid (row,col)
	 */
	public char get(int row, int col) {
		if (row < 0 || row >= dim || col < 0 || col >= dim) return EMPTY; // what if there is no player?
		return this.board[row][col];
	}

	/**
	 *
	 * @param row starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @return whether (row,col) is a position on the board. Example: (6,12) is not
	 *         a position on the board.
	 */
	private boolean validCoordinate(int row, int col) {
		return row >= 0 && row < dim && col >= 0 && col < dim;
	}

	/**
	 * Check if there is an alternation of P1 next to P2, starting at (row,col) in
	 * direction (drow,dcol). That is, starting at (row,col) and heading in
	 * direction (drow,dcol), you encounter a sequence of at least one P1 followed
	 * by a P2, or at least one P2 followed by a P1. The board is not modified by
	 * this method. Why is this method important? If
	 * alternation(row,col,drow,dcol)==P1, then placing P1 right before (row,col),
	 * assuming that square is EMPTY, is a valid move, resulting in a collection of
	 * P2 being flipped.
	 *
	 * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow the row direction, in {-1,0,1}
	 * @param dcol the col direction, in {-1,0,1}
	 * @return P1, if there is an alternation P2 ...P2 P1, or P2 if there is an
	 *         alternation P1 ... P1 P2 in direction (dx,dy), EMPTY if there is no
	 *         alternation
	 */
	private char alternation(int row, int col, int drow, int dcol) {
		if (!validCoordinate(row, col) || this.get(row, col) == EMPTY) return EMPTY;
		char player = this.board[row][col];
		int crow = row + drow;
		int ccol = col + dcol;

		// while current row and column are valid coordinates on the board, keep checking for
		// the alternation pattern
		while (validCoordinate(crow, ccol)) {
			char current = this.get(crow, ccol);
			if (current == EMPTY) return EMPTY;
			else if (current != player) return otherPlayer(player);
			crow += drow;
			ccol += dcol;
		}
		return EMPTY;
	}

	/**
	 * flip all other player tokens to player, starting at (row,col) in direction
	 * (drow, dcol). Example: If (drow,dcol)=(0,1) and player==O then XXXO will
	 * result in a flip to OOOO
	 *
	 * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow   the row direction, in {-1,0,1}
	 * @param dcol   the col direction, in {-1,0,1}
	 * @param player Either OthelloBoard.P1 or OthelloBoard.P2, the target token to
	 *               flip to.
	 * @return the number of other player tokens actually flipped, -1 if this is not
	 *         a valid move in this one direction, that is, EMPTY or the end of the
	 *         board is reached before seeing a player token.
	 */
	private int flip(int row, int col, int drow, int dcol, char player) {

		// checking if move has alternation before actually flipping
		char move = alternation(row, col, drow, dcol);
		int flips = 0;
		if (move == player) flips += flipCount(row, col, drow, dcol, player, true);
		if (flips > 0) return flips;
		return -1;

	}

	/**
	 * Return which player has a move (row,col) in direction (drow,dcol).
	 *
	 * @param row  starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col  starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param drow the row direction, in {-1,0,1}
	 * @param dcol the col direction, in {-1,0,1}
	 * @return P1,P2,EMPTY
	 */
	private char hasMove(int row, int col, int drow, int dcol) {
		if (!validCoordinate(row, col) || this.board[row][col] != EMPTY) return EMPTY;
		return alternation(row + drow, col + dcol, drow, dcol);
	}

	/**
	 *
	 * @return whether P1,P2 or BOTH have a move somewhere on the board, EMPTY if
	 *         neither do.
	 */
	public char hasMove() {
		boolean p1HasMove = false;
		boolean p2HasMove = false;

		// iterate through all positions on the board
		for (int i = 0; i < this.dim; i++) {
			for (int j = 0; j < this.dim; j++) {
				if (this.board[i][j] == EMPTY) {

					// looking at all directions
					for (int drow = -1; drow <= 1; drow++) {
						for (int dcol = -1; dcol <= 1; dcol++) {
							if (drow == 0 && dcol == 0) continue;

							// check if either/both players have a move in this direction
							char playerMoves = this.hasMove(i, j, drow, dcol);
							if (playerMoves == P1) p1HasMove = true;
							else if (playerMoves == P2) p2HasMove = true;
							if (p1HasMove && p2HasMove) return BOTH;
						}
					}
				}
			}
		}
		// returning who has moves
		if (p1HasMove) return P1;
		else if (p2HasMove) return P2;
		else return EMPTY;
	}

	/**
	 * Make a move for player at position (row,col) according to Othello rules,
	 * making appropriate modifications to the board. Nothing is changed if this is
	 * not a valid move.
	 *
	 * @param row    starting row, in {0,...,dim-1} (typically {0,...,7})
	 * @param col    starting col, in {0,...,dim-1} (typically {0,...,7})
	 * @param player P1 or P2
	 * @return true if player moved successfully at (row,col), false otherwise
	 */
	public boolean move(int row, int col, char player) {
		// HINT: Use some of the above helper methods to get this methods
		// job done!!

		boolean moved = false;

		// only perform move if space is empty
		if (this.board[row][col] == EMPTY) {

			// looking at all directions
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i == 0 && j == 0) continue;

					// call the flip method to flip tokens, if possible
					int flips = flip(row + i, col + j, i, j, player);

					// if any flips were made, put a player token on the current space
					if (flips > 0) {
						moved = true;
						this.board[row][col] = player;
					}
				}
			}
		}
		return moved;
	}

	/**
	 *
	 * @param player P1 or P2
	 * @return the number of tokens on the board for player
	 */
	public int getCount(char player) {
		int count = 0;
		for (int i = 0; i < this.dim; i++) {
			for (int j = 0; j < this.dim; j++) {
				if (this.board[i][j] == player) count++;
			}
		}
		return count;
	}

	// HELPER FUNCTIONS BY ME

	/**
	 * Checks and counts the number of opponent's pieces that can be flipped in a given direction
	 *  from a specific starting position. If <flip> is set to true, it will also
	 *  flip the opponent's pieces to the current player's pieces.
	 *
	 * @param crow current row being checked
	 * @param ccol current column being checked
	 * @param drow direction of row change
	 * @param dcol direction of column change
	 * @param player the current player (X or O)
	 * @param flip if tokens should be flipped or not
	 * @return total number of opponent's pieces that can be flipped
	 */
	private int flipCount(int crow, int ccol, int drow, int dcol, char player, boolean flip) {
		char otherPlayer = otherPlayer(player);
		int flips = 0;
		while (validCoordinate(crow, ccol) && this.get(crow, ccol) == otherPlayer) {
			if (flip) this.board[crow][ccol] = player;
			flips++;
			crow += drow;
			ccol += dcol;
		}
		return flips;
	}

	/**
	 * Find and return the best move for a greedy strategy for the player
	 * (i.e. the move that will result in the most flips for the player).
	 *
	 * @param player
	 * @return Move that coincides with the greedy strategy
	 */
	public Move getGreedyMove(char player) {
		int bestRow = 1;
		int bestCol = 1;
		int maxFlips = 0;
		char otherPlayer = this.otherPlayer(player);

		// iterate through all spaces on the board
		for (int i = 0; i < this.dim; i++) {
			for (int j = 0; j < this.dim; j++) {
				if (this.board[i][j] == EMPTY) {
					int flips = 0;

					// look at all directions
					for (int drow = -1; drow <= 1; drow++) {
						for (int dcol = -1; dcol <= 1; dcol++) {
							int crow = i + drow;
							int ccol = j + dcol;
							if (drow == 0 && dcol == 0) continue;

							// check if an alternation exists
							char move = alternation(crow, ccol, drow, dcol);

							// if it does, then return how many flips can be made from this move
							if (move == player) flips += flipCount(crow, ccol, drow, dcol, player, false);
						}
					}
					// check if this move was the best move so far
					if (flips > maxFlips) {
						maxFlips = flips;
						bestRow = i;
						bestCol = j;
					}
				}
			}
		}
		return new Move(bestRow, bestCol);
	}

	// END OF HELPER FUNCTIONS

	/**
	 * @return a string representation of this, just the play area, with no
	 *         additional information. DO NOT MODIFY THIS!!
	 */
	public String toString() {
		/**
		 * See assignment web page for sample output.
		 */
		String s = "";
		s += "  ";
		for (int col = 0; col < this.dim; col++) {
			s += col + " ";
		}
		s += '\n';

		s += " +";
		for (int col = 0; col < this.dim; col++) {
			s += "-+";
		}
		s += '\n';

		for (int row = 0; row < this.dim; row++) {
			s += row + "|";
			for (int col = 0; col < this.dim; col++) {
				s += this.board[row][col] + "|";
			}
			s += row + "\n";

			s += " +";
			for (int col = 0; col < this.dim; col++) {
				s += "-+";
			}
			s += '\n';
		}
		s += "  ";
		for (int col = 0; col < this.dim; col++) {
			s += col + " ";
		}
		s += '\n';
		return s;
	}

	/**
	 * A quick test of OthelloBoard. Output is on assignment page.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		ca.utoronto.utm.assignment1.othello.OthelloBoard ob = new ca.utoronto.utm.assignment1.othello.OthelloBoard(8);
		System.out.println(ob.toString());
		System.out.println("getCount(P1)=" + ob.getCount(P1));
		System.out.println("getCount(P2)=" + ob.getCount(P2));
		for (int row = 0; row < ob.dim; row++) {
			for (int col = 0; col < ob.dim; col++) {
				ob.board[row][col] = P1;
			}
		}
		System.out.println(ob.toString());
		System.out.println("getCount(P1)=" + ob.getCount(P1));
		System.out.println("getCount(P2)=" + ob.getCount(P2));

		// Should all be blank
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("alternation=" + ob.alternation(4, 4, drow, dcol));
			}
		}

		for (int row = 0; row < ob.dim; row++) {
			for (int col = 0; col < ob.dim; col++) {
				if (row == 0 || col == 0) {
					ob.board[row][col] = P2;
				}
			}
		}
		System.out.println(ob.toString());

		// Should all be P2 (O) except drow=0,dcol=0
		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("direction=(" + drow + "," + dcol + ")");
				System.out.println("alternation=" + ob.alternation(4, 4, drow, dcol));
			}
		}

		// Can't move to (4,4) since the square is not empty
		System.out.println("Trying to move to (4,4) move=" + ob.move(4, 4, P2));

		ob.board[4][4] = EMPTY;
		ob.board[2][4] = EMPTY;

		System.out.println(ob.toString());

		for (int drow = -1; drow <= 1; drow++) {
			for (int dcol = -1; dcol <= 1; dcol++) {
				System.out.println("direction=(" + drow + "," + dcol + ")");
				System.out.println("hasMove at (4,4) in above direction =" + ob.hasMove(4, 4, drow, dcol));
			}
		}
		System.out.println("who has a move=" + ob.hasMove());
		System.out.println("Trying to move to (4,4) move=" + ob.move(4, 4, P2));
		System.out.println(ob.toString());

	}
}
