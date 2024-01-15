package org.chess4j.simple;

import org.chess4j.Board;
import org.chess4j.Boards;
import org.chess4j.History;
import org.chess4j.moves.Move;
import org.chess4j.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimpleHistory implements History {

    /**
     * Internal list that holds all added moves that is forwarded.
     */
    private final List<Move> chronicle;

    /**
     * The initial position at the start of the game.
     */
    private final Board initial;

    /**
     * Constructs a new chronicle with the given initial position.
     *
     * @param initial the initial position of the board.
     */
    public SimpleHistory(Board initial) {
        chronicle = new ArrayList<>();
        this.initial = Boards.copy(initial);
    }

    /**
     * Constructs a new history with the moves contained in the given list. The
     * list cannot be empty.
     *
     * @param history the given history.
     */
    public SimpleHistory(List<? extends Move> history) {
        Objects.requireNonNull(history);
        if (history.isEmpty()) {
            throw new IllegalArgumentException("History cannot be empty.");
        }
        this.chronicle = new ArrayList<>(history);
        this.initial = Boards.copy(history.get(0).initial());
    }

    /**
     * Adds a move to the game. An {@link IllegalStateException} is thrown if
     * the initial position of the board does not match the end position of the
     * previous move.
     *
     * @throws IllegalStateException if the initial position of the board does
     *                               not match the end position of the previous
     *                               move.
     */
    @Override
    public void add(Move element) {
        Objects.requireNonNull(element);
        if (currentPosition().equals(element.initial())) {
            chronicle.add(element);
        } else {
            throw new IllegalArgumentException("The initial board position of" +
                    " the move does not match the current board position of " +
                    "the game.");
        }
    }

    /**
     * {@inheritDoc}
     */
    public Move get(int index) {
        return chronicle.get(index);
    }

    /**
     * {@inheritDoc}
     */
    public int turnNumber() {
        return chronicle.size();
    }

    /**
     * Returns the last entry of the list. This method must not return
     * {@code null}
     *
     * @return the current position of the game.
     */
    public Board currentPosition() {
        return chronicle.isEmpty() ? initial : get(turnNumber() - 1).result();
    }

    /**
     * Returns {@code true} if no move has been played yet.
     *
     * @return {@code true} if no move has been played yet.
     */
    public boolean isEmpty() {
        return chronicle.isEmpty();
    }

    /**
     * Clears the history.
     */
    public void clear() {
        chronicle.clear();
    }

    /**
     * Returns {@code true} if the piece has been moved {@code false}
     * otherwise.
     *
     * @param piece the given piece.
     * @return {@code true} if the piece has been moved {@code false} otherwise.
     */
    public boolean hasBeenMoved(Piece piece) {
        Objects.requireNonNull(piece);
        return chronicle.stream().anyMatch(m -> m.moved().equals(piece));
    }

    /**
     * Reverts the last move of the game. Returns the reverted Move or null if
     * none is present.
     */
    public Move revert() {
        return isEmpty() ? null : chronicle.remove(turnNumber() - 1);
    }
}
