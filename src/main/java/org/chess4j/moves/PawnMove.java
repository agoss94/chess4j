package org.chess4j.moves;

import org.chess4j.Board;
import org.chess4j.Color;
import org.chess4j.Piece;
import org.chess4j.Tile;

/**
 * A valid pawn move is a normal move one row forward the enemy camp. As with
 * all moves a static factory method {@link #perform(Tile, Tile, Board)} is
 * offered which performs a validity check before creation. If the move is
 * invalid then {@link Move#INVALID_MOVE} is returned.
 */
public class PawnMove extends AbstractMove {

    // Private constructor is only invoked after a validity check.
    private PawnMove(Tile start, Tile end, Board initial) {
        super(start, end, initial);
    }

    /**
     * Returns a standard move or {@code null} if the move would be invalid. A
     * valid pawn move is a normal move by one tile forward in the only
     * direction the pawn is allowed to move or a capture move of an enemy piece
     * by the pawn. A white pawn can move up the board and a black pawn can only
     * move down.
     *
     * @param start the start coordinate.
     * @param end   the end coordinate.
     * @param board the given board position.
     * @return a valid move or {@link Move#INVALID_MOVE} if the move would be
     * invalid.
     */
    public static Move perform(Tile start, Tile end, Board board) {
        return isValid(start, end, board) ? new PawnMove(start, end, board) : INVALID_MOVE;
    }

    /**
     * Checks if the pawn move with the given game position is valid. A valid pawn
     * move is a normal move by one tile forward in the only direction the pawn is
     * allowed to move. A white pawn can move up the board and a black pawn can only
     * move down.
     *
     * @param end   the end coordinate.
     * @param start the start coordinate.
     * @param board the initial position.
     * @return {@code true} if the move is valid.
     */
    public static boolean isValid(Tile start, Tile end, Board board) {
        // If there is no piece on start then the move is invalid. Also, there must not
        // be a piece on the end position.
        if (board.containsKey(end)) {
            return false;
        }

        Piece pawn = board.get(start);
        if (Piece.isPawn(pawn)) {
            Color pawnColor = pawn.color();
            int dir = pawnColor == Color.WHITE ? 1 : -1;

            int deltaRow = end.row() - start.row();
            int deltaColumn = end.column() - start.column();

            return deltaRow * dir == 1 && deltaColumn == 0;
        } else {
            return false;
        }
    }
}
