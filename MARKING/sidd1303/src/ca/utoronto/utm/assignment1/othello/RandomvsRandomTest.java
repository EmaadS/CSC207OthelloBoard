package ca.utoronto.utm.assignment1.othello;

public class RandomvsRandomTest {
    public static void main(String[] args) {
        int p1 = 0;
        int p2 = 0;
        int draw = 0;
        for (int i = 0; i < 10000; i++) {
            Othello othello = new Othello();
            PlayerRandom player1 = new PlayerRandom(othello, OthelloBoard.P1);
            PlayerRandom player2 = new PlayerRandom(othello, OthelloBoard.P2);
            OthelloControllerRandomVSRandom oc = new OthelloControllerRandomVSRandom(othello, player1, player2);
            char winner = oc.play();
            if (winner == OthelloBoard.P1) p1++;
            else if (winner == OthelloBoard.P2) p2++;
            else draw++;
        }
        System.out.println("Player 1 wins: " + p1 + " (" + (double) p1 / 10000 * 100 + "%)");
        System.out.println("Player 2 wins: " + p2 + " (" + (double) p2 / 10000 * 100 + "%)");
        System.out.println("Draws: " + draw + " (" + (double) draw / 10000 * 100 + "%)");
    }
}
