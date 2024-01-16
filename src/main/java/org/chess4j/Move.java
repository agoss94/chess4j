package org.chess4j;

import org.chess4j.exceptions.InvalidMoveException;

import java.util.Optional;

/**
 * A Move represents a legal change from one board position to another. A chess
 * move can only move one piece at a time with the sole exception of the
 * rochade, which is the only move that moves two pieces. Essentially a move
 * represents a data transfer object (DTO) and must be validated before
 * creation. Therefore, each move class should offer a validation method which
 * is checked before creation and objects should only be created by factory
 * methods.
 */
public interface Move {

    /**
     * The start and the end coordinates of the moved piece.
     *
     * @return the start coordinate
     */
    Tile start();

    /**
     * The end coordinate of the moved piece.
     *
     * @return end coordinate of the moved piece.
     */
    Tile end();

    /**
     * The initial board position before the move.
     *
     * @return initial position before the move.
     */
    Board initial();

    /**
     * The resulting end position after the move.
     *
     * @return the end position after the move.
     */
    Board result();

    /**
     * This method return the moved piece. Note that with a rochade only the
     * king is returned via this method.
     *
     * @return the piece moved in this move.
     */
    default Piece moved() {
        return initial().get(start());
    }

    /**
     * The captured piece if any was captured.
     *
     * @return the captured piece as an optional.
     */
    default Optional<Piece> captured() {
        return Optional.ofNullable(initial().get(end()));
    }

    /**
     * A singelton for an invalid move, which throws an invalid move exception
     * for every method called.
     */
    Move INVALID_MOVE = new Move() {
        @Override
        public Tile start() {
            throw new InvalidMoveException();
        }

        @Override
        public Tile end() {
            throw new InvalidMoveException();
        }

        @Override
        public Board initial() {
            throw new InvalidMoveException();
        }

        @Override
        public Board result() {
            throw new InvalidMoveException();
        }
    };

}
