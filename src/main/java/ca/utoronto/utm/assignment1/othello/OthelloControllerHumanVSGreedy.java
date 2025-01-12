package ca.utoronto.utm.assignment1.othello;

/**
 * This controller uses the Model classes to allow the Human player P1 to play
 * the computer P2. The computer, P2 uses a greedy strategy. 
 * 
 * @author arnold
 *
 */
public class OthelloControllerHumanVSGreedy extends ParentController{
	
	/**
	 * Run main to play a Human (P1) against the computer P2. 
	 * The computer uses a greedy strategy, that is, it picks the first
	 * move which maximizes its number of token on the board.
	 * The output should be almost identical to that of OthelloControllerHumanVSHuman.
	 *
	 * Constructs a new OthelloControllerHumanVSGreedy with the specified
	 * Othello game instance and player instances.
	 *
	 * @param othello the Othello game instance that this controller will manage
	 * @param player1 the human player participating in the game
	 * @param player2 the computer player utilizing a greedy strategy
	 */
	public OthelloControllerHumanVSGreedy(Othello othello, PlayerHuman player1, PlayerGreedy player2) {
		super(othello, player1, player2);
	}


	/**
	 * Main method to initiate the game where a human player (P1) competes
	 * against a computer player (P2) that uses a greedy strategy. It initializes
	 * the game and starts the play process.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Othello othello = new Othello();
		PlayerHuman player1 = new PlayerHuman(othello, OthelloBoard.P1);
		PlayerGreedy player2 = new PlayerGreedy(othello, OthelloBoard.P2);
		OthelloControllerHumanVSGreedy oc = new OthelloControllerHumanVSGreedy(othello, player1, player2);
		oc.play();
	}
}
