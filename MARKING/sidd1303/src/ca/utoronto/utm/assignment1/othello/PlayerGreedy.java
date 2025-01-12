package ca.utoronto.utm.assignment1.othello;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * PlayerGreedy makes a move by considering all possible moves that the player
 * can make. Each move leaves the player with a total number of tokens.
 * getMove() returns the first move which maximizes the number of
 * tokens owned by this player. In case of a tie, between two moves,
 * (row1,column1) and (row2,column2) the one with the smallest row wins. In case
 * both moves have the same row, then the smaller column wins.
 * 
 * Example: Say moves (2,7) and (3,1) result in the maximum number of tokens for
 * this player. Then (2,7) is returned since 2 is the smaller row.
 * 
 * Example: Say moves (2,7) and (2,4) result in the maximum number of tokens for
 * this player. Then (2,4) is returned, since the rows are tied, but (2,4) has
 * the smaller column.
 * 
 * See the examples supplied in the assignment handout.
 * 
 * @author arnold
 *
 */

public class PlayerGreedy extends ParentPlayer{

	/**
	 * Constructs a PlayerGreedy object, representing a player that uses the
	 * greedy strategy to select moves in the game of Othello.
	 *
	 * @param othello the Othello game instance this player participates in
	 * @param player the character representing the player (either 'X' or 'O')
	 */
	public PlayerGreedy(Othello othello, char player) {
		super(othello, player);
	}

	/**
	 * Returns the move that maximizes the number of tokens for this player,
	 * using the greedy strategy. If multiple moves yield the same number of tokens,
	 * the move with the smaller row and, if necessary, the smaller column is chosen.
	 *
	 * @return Move according to the greedy strategy
	 */
	@Override
	public Move getMove() {
		return this.othello.getGreedyMove(player);
	}
}
