package ca.utoronto.utm.assignment1.othello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A human player that that is able to manually input the desired moves onto the board
 * via the console. Player input is taken as a row and column (between 0 and 7) and
 * is checked for validity.
 * 
 * @author arnold
 *
 */
public class PlayerHuman extends ParentPlayer{
	
	private static final String INVALID_INPUT_MESSAGE = "Invalid number, please enter 1-8";
	private static final String IO_ERROR_MESSAGE = "I/O Error";
	private static BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

	private Othello othello;
	private char player;

	/**
	 * Constructs a PlayerHuman object, associating it with the provided Othello game
	 * instance and the player character ('X' or 'O').
	 *
	 * @param othello the Othello game instance that the player is participating in
	 * @param player the character representing the player ('X' or 'O')
	 */
	public PlayerHuman(Othello othello, char player) {
		super(othello, player);
	}

	/**
	 * Prompts the player to input a move by entering the row and column numbers.
	 * The input is validated to ensure it falls within the range [0, 7]. If the input is valid,
	 * a new Move object representing the player's move is returned.
	 *
	 * @return a Move object representing the player's chosen move
	 */
	@Override
	public Move getMove() {
		int row = getMove("row: ");
		int col = getMove("col: ");
		return new Move(row, col);
	}

	/**
	 * Prompts the user for an input (row or column) and validates the input to ensure
	 * it is between 0 and 7. If the input is invalid, an error message is displayed
	 * and the user is prompted again.
	 *
	 * @param message the message to prompt the user (e.g., "row: " or "col: ")
	 * @return integer input from the user, between 0 and 7
	 */
	private int getMove(String message) {
		
		int move, lower = 0, upper = 7;
		while (true) {
			try {
				System.out.print(message);
				String line = PlayerHuman.stdin.readLine();
				move = Integer.parseInt(line);
				if (lower <= move && move <= upper) {
					return move;
				} else {
					System.out.println(INVALID_INPUT_MESSAGE);
				}
			} catch (IOException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
				break;
			} catch (NumberFormatException e) {
				System.out.println(INVALID_INPUT_MESSAGE);
			}
		}
		return -1;
	}
}
