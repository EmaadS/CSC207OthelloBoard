package ca.utoronto.utm.assignment1.othello;

/**
 * This controller uses the Model classes to allow the Human player P1 to play
 * the computer P2. The computer, P2 uses a random strategy. 
 * 
 * @author arnold
 *
 */
public class OthelloControllerHumanVSRandom extends ParentController{
	
	/**
	 * Run main to play a Human (P1) against the computer P2. 
	 * The computer uses a random strategy, that is, it randomly picks 
	 * one of its possible moves.
	 * The output should be almost identical to that of OthelloControllerHumanVSHuman.
	 *
	 * Constructs a new OthelloControllerHumanVSRandom with the specified
	 * Othello game instance and player instances.
	 *
	 * @param othello the Othello game instance this controller manages
	 * @param player1 the human player
	 * @param player2 the player using random strategy
	 */

	public OthelloControllerHumanVSRandom(Othello othello, PlayerHuman player1, PlayerRandom player2) {
		super(othello, player1, player2);
	}

	/**
	 * Main method to run the game, allowing a human player to play against
	 * a computer player that selects moves randomly.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// creating a new game instance
		Othello othello = new Othello();
		PlayerHuman player1 = new PlayerHuman(othello, OthelloBoard.P1);
		PlayerRandom player2 = new PlayerRandom(othello, OthelloBoard.P2);
		OthelloControllerHumanVSRandom oc = new OthelloControllerHumanVSRandom(othello, player1, player2);
		oc.play();
	}
}
