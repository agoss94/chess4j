package org.chess4j;

import org.chess4j.exceptions.InvalidMoveException;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Interface for a simple PvP Chess game.
 */
public interface ChessGame {

    static final Pattern UCI_FORMAT =
            Pattern.compile("([a-h][1-8])([a-h][1-8])([bkqr])?");


    /**
     * Starts a new game.
     */
    void newGame();

    /**
     * Returns the current board position.
     *
     * @return the current board position.
     */
    Board getBoardPosition();

    /**
     * Returns a set of all reachable tiles. The returned set is empty if no
     * piece is on the given tile.
     *
     * @param tile the given tile.
     * @return a set of all reachable tiles. The returned set is empty if no
     * piece is on the given tile.
     */
    Set<Tile> reachableTiles(Tile tile);

    /**
     * Performs the move as specified in the UCI-format.
     *
     * @param uciMove the move in UCI-format
     * @throws InvalidMoveException if the move is invalid.
     */
    void move(String uciMove) throws InvalidMoveException;

    /**
     * Revert the last move.
     */
    void revert();

    /**
     * Returns the current turn number.
     *
     * @return the current turn number.
     */
    int turnNumber();

    /**
     * Returns the current status of the game.
     *
     * @return the current status of the game.
     */
    Status getStatus();

    /**
     * Returns {@code true} if the game is over.
     *
     * @return {@code true} if the game is over.
     */
    boolean isGameOver();

    /**
     * Return an unmodifiable view of the game history.
     *
     * @return an unmodifiable view of the game history.
     */
    History getHistory();

    /**
     * This enum represents the status any given game can have.
     */
    enum Status {

        /**
         * This status indicates, that the white player has to make a move.
         */
        WHITE_PLAYER_TURN,

        /**
         * This status indicates, that the black player has to make a move.
         */
        BLACK_PLAYER_TURN,

        /**
         * This status indicates, that the game has ended and white has won.
         */
        WHITE_WON,

        /**
         * This status indicates, that the game has ended and black has won.
         */
        BLACK_WON,

        /**
         * This status indicates, that the game has ended in a stalemate by
         * threefold repetition.
         */
        DRAW_BY_THREEFOLD_REPETITION,

        /**
         * This status indicates, that the material on board is insufficient for
         * either player to win the game.
         */
        DRAW_BY_INSUFFICIENT_MATERIAL,

        /**
         * This status indicates, that the game has ended in a stalemate by the
         * fifty move rule.
         */
        DRAW_BY_FIFTY_MOVE_RULE,

        /**
         * This status indicates, that  game has ended in a stalemate has one
         * player cannot make a valid move.
         */
        STALEMATE,
    }
}
