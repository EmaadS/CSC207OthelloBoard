package ca.utoronto.utm.assignment1.othello;
/**
 * ParentPlayer is an abstract class that serves as a base for different types of players
 * (e.g., human players, random strategy players, greedy strategy players) in the Othello game.
 * Each player is associated with an Othello game instance and a player character (either P1 or P2).

 * The class contains common attributes shared among different types of players, including the
 * Othello game instance and the player character ('X' for P1 or 'O' for P2).
 *
 * Subclasses must provide their own implementation for determining and returning a move
 * appropriate to their strategy
 *
 */

abstract class ParentPlayer {

    protected Othello othello;
    protected char player;

    /**
     * Constructs a ParentPlayer with the given Othello game and player character.
     *
     * @param othello the Othello game instance that this player is participating in
     * @param player the character representing the player (either 'X' or 'O')
     */
    public ParentPlayer(Othello othello, char player) {
        this.othello = othello;
        this.player = player;
    }

    /**
     * Abstract method that returns their next move. Subclasses will implement this
     * class according to their strategy.
     *
     * @return Move that coincides with player's strategy
     */
    public abstract Move getMove();

}
