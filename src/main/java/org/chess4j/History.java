package org.chess4j;

import java.util.List;

public interface History {

    /**
     * Adds a move to the game. An {@link IllegalStateException} is thrown if
     * the initial position of the board does not match the end position of the
     * previous move.
     *
     * @throws IllegalStateException if the initial position of the board does
     *                               not match the end position of the previous
     *                               move.
     */
    void add(Move element);

    /**
     * Get the move with the given index.
     *
     * @param index the index of the move.
     * @return the move with the given index.
     */
    Move get(int index);

    /**
     * Returns the last entry of the list. This method must not return
     * {@code null}
     *
     * @return the current position of the game.
     */
    Board currentPosition();

    /**
     * Returns the number of moves played.
     *
     * @return the number of moves played.
     */
    int turnNumber();

    /**
     * Returns {@code true} if no move has been played yet.
     *
     * @return {@code true} if no move has been played yet.
     */
    boolean isEmpty();

    /**
     * Clears the history.
     */
    void clear();

    /**
     * Returns {@code true} if the piece has been moved {@code false}
     * otherwise.
     *
     * @param piece the given piece.
     * @return {@code true} if the piece has been moved {@code false} otherwise.
     */
    boolean hasBeenMoved(Piece piece);

    /**
     * Reverts the last move of the game. Returns the reverted Move or null if
     * none is present.
     */
    Move revert();

    /**
     * Returns this history as an unmodifiable view.
     *
     * @return this history as an unmodifiable view.
     */
    History view();


}
