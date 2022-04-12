package chess4j.model;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Board extends Map<Position, Piece> {

    /**
     * Moves the piece from the start position to the end position.
     *
     * @param start the start position of the piece.
     * @param end   the end position of the piece.
     * @return the performed move.
     * @throws IllegalMoveException if the move is illegal on the board.
     */
    Move move(Position start, Position end) throws IllegalMoveException;

    /**
     * Returns a list of all moves performed on the board.
     *
     * @return a list of all performed moves.
     */
    List<Move> moves();

    /**
     * Returns true if the board has a Piece placed on start that can legally move
     * to end on the board.
     *
     * @param start the start position
     * @param end   the end position
     * @return true if the move is legal and false otherwise.
     */
    boolean isValid(Position start, Position end);

    /**
     * A Move moves a pieces from a start position to an end position on the board
     * given that certain conditions are met which are typically checked beforehand.
     * The move only capsulates a certain change on the board not if this change was
     * valid or not.
     */
    interface Move {

        /**
         * Returns the start position of the moved Piece
         *
         * @return the position where the piece started to move.
         */
        Position start();

        /**
         * Returns the end position where the piece ended to move.
         *
         * @return the end position.
         */
        Position end();

        /**
         * The moved piece
         *
         * @return the moved piece.
         */
        Piece movedPiece();

        /**
         * If a piece has been captured as a result of this moved this piece is
         * returned.
         *
         * @return an optional captured piece.
         */
        Optional<Piece> capturedPiece();

        /**
         * Performs the move on the given board.
         *
         * @param board the board on which the move is performed
         * @throws IllegalStateException
         */
        void perform(Board board) throws IllegalStateException;

        /**
         * Reverts the move on the given board. The move can only be reverted if this
         * move was the last performed on the board otherwise an exception is thrown.
         *
         * @param board the given board
         * @throws IllegalStateException if this move was not the last move performed on
         *                               the board.
         */
        void revert(Board board) throws IllegalStateException;
    }
}
