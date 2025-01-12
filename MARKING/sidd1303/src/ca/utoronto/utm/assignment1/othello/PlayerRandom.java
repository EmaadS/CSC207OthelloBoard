package ca.utoronto.utm.assignment1.othello;

import java.util.ArrayList;
import java.util.Random;

/**
 * PlayerRandom makes a move by first determining all possible moves that this
 * player can make, putting them in an ArrayList, and then randomly choosing one
 * of them.
 * 
 * @author arnold
 *
 */
public class PlayerRandom extends ParentPlayer{
	
	private Random rand = new Random();
	private ArrayList <int[]> moveList;


	/**
	 * Constructs a PlayerRandom object, representing a player that uses a random
	 * strategy to select moves in the game of Othello. Also creates the arraylist
	 * that contains all possible moves.
	 *
	 * @param othello the Othello game instance this player participates in
	 * @param player the character representing the player (either 'X' or 'O')
	 */
	public PlayerRandom(Othello othello, char player) {
		super(othello, player);
		this.moveList = new ArrayList<>();
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				int[] coords = {i, j};
				this.moveList.add(coords);
			}
		}
	}

	/**
	 * Returns a randomly chosen move from <moveList>. The method
	 * continues selecting random moves until a valid move is found (i.e., an empty position).
	 * If a selected move is invalid, it is removed from the list, and the random selection
	 * is repeated until a valid move is made.
	 *
	 * @return a valid move selected at random
	 */
	@Override
	public Move getMove() {
		boolean moved = false;
		int row, col, moveFromList;
		do {
			// selecting a random move from moveList
			moveFromList = rand.nextInt(this.moveList.size());
			int[] rowcol = this.moveList.get(moveFromList);
			row = rowcol[0];
			col = rowcol[1];

			// checking if the move is valid
			if (this.othello.checkMove(row, col) == OthelloBoard.EMPTY) moved = this.othello.move(row, col);

			// remove the move from list if there is already a player token on that square
			else if (this.othello.checkMove(row, col) != OthelloBoard.EMPTY) this.moveList.remove(moveFromList);
		}
		while (!moved);

		// remove the used move from the moveList
		this.moveList.remove(moveFromList);
		return new Move(row, col);
	}
}
