package ca.utoronto.utm.assignment1.othello;

/**
 * Determine whether the first player or second player has the advantage when
 * both are playing a Random Strategy.
 * 
 * Do this by creating two players which use a random strategy and have them
 * play each other for 10000 games. What is your conclusion, does the first or
 * second player have some advantage, at least for a random strategy? 
 * State the null hypothesis H0, the alternate hypothesis Ha and 
 * about which your experimental results support. Place your short report in
 * randomVsRandomReport.txt.
 * 
 * @author arnold
 *
 */
public class OthelloControllerRandomVSRandom extends ParentController{
	/**
	 * Run main to execute the simulation and print out the two line results.
	 * Output looks like 
	 * Probability P1 wins=.75 
	 * Probability P2 wins=.20
	 *
	 * Constructs a new OthelloControllerRandomVSRandom with the specified Othello
	 * game instance and player instances.
	 *
	 * @param othello the Othello game instance this controller manages
	 * @param player1 the first random player (P1)
	 * @param player2 the second random player (P2)
	 */

	public OthelloControllerRandomVSRandom(Othello othello, PlayerRandom player1, PlayerRandom player2) {
		super(othello, player1, player2);
	}

	/**
	 * Main method to execute the simulation of 10,000 games between two random players.
	 * The results are printed to the console, showing the winning probabilities for both players.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		int p1wins = 0, p2wins = 0, numGames = 10000;

		// playing out 10000 games to record the probabilities
		for (int games = 0; games < numGames; games++) {

			// Constructing a new game
			Othello othello = new Othello();
			PlayerRandom player1 = new PlayerRandom(othello, OthelloBoard.P1);
			PlayerRandom player2 = new PlayerRandom(othello, OthelloBoard.P2);
			OthelloControllerRandomVSRandom oc = new OthelloControllerRandomVSRandom(othello, player1, player2);

			// recording the winner and wins
			char winner = oc.play();
			if (winner == OthelloBoard.P1) p1wins++;
			if (winner == OthelloBoard.P2) p2wins++;

		}

		// printing out the results
		System.out.println("Probability P1 wins=" + (float) p1wins / numGames);
		System.out.println("Probability P2 wins=" + (float) p2wins / numGames);
	}
}
