package ca.utoronto.utm.assignment1.othello;

import java.util.Random;

/**
 * The goal here is to print out the probability that Random wins and Greedy
 * wins as a result of playing 10000 games against each other with P1=Random and
 * P2=Greedy. What is your conclusion, which is the better strategy?
 * @author arnold
 *
 */
public class OthelloControllerRandomVSGreedy extends ParentController{

	/**
	 * Run main to execute the simulation and print out the two line results.
	 * Output looks like: 
	 * Probability P1 wins=.75 
	 * Probability P2 wins=.20
	 *
	 * Constructs a new OthelloControllerRandomVSGreedy with the specified Othello
	 * game instance and player instances.
	 *
	 * @param othello the Othello game instance this controller manages
	 * @param player1 the random player
	 * @param player2 the greedy player
	 *
	 */
	public OthelloControllerRandomVSGreedy(Othello othello, PlayerRandom player1, PlayerGreedy player2) {
		super(othello, player1, player2);
	}

	/**
	 * Main method to execute the simulation of 10000 games between a random player
	 * and a greedy player. The results are printed to the console, showing the winning
	 * probabilities for both players.
	 *
	 * @param args
	 */
	public static void main(String[]args){

		int p1wins = 0, p2wins = 0, numGames = 10000;
		for (int games = 0; games < numGames; games++) {

			// creating a new game instance
			Othello othello = new Othello();
			PlayerRandom player1 = new PlayerRandom(othello, OthelloBoard.P1);
			PlayerGreedy player2 = new PlayerGreedy(othello, OthelloBoard.P2);
			OthelloControllerRandomVSGreedy oc = new OthelloControllerRandomVSGreedy(othello, player1, player2);

			// recoding the winner and wins
			char winner = oc.play();
			if (winner == OthelloBoard.P1) p1wins++;
			if (winner == OthelloBoard.P2) p2wins++;

		}

		// printing out the results
		System.out.println("Probability P1 wins=" + (float) p1wins / numGames);
		System.out.println("Probability P2 wins=" + (float) p2wins / numGames);
	}

}