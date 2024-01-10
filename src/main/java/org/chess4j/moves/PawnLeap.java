package org.chess4j.moves;

import org.chess4j.Board;
import org.chess4j.Color;
import org.chess4j.Piece;
import org.chess4j.Tile;

/**
 * A pawn leap can only occur the first time a pawn is moved. In the first move
 * a Pawn can move two tiles at once opposed to one normally. As with all moves
 * a static factory method {@link #perform(Tile, Tile, Board)} is offered which
 * performs a validity check before creation. If the move is invalid then
 * {@link Move#INVALID_MOVE} is returned.
 */
public class PawnLeap extends AbstractMove {

    // Private constructor is only invoked after a validity check.
    public PawnLeap(Tile start, Tile end, Board initial) {
        super(start, end, initial);
    }

    /**
     * Returns a standard move or {@code null} if the move would be invalid.
     *
     * @param board the initial position.
     * @param start  the start coordinate.
     * @param end    the end coordinate.
     * @return a valid move or {@link Move#INVALID_MOVE}.
     */
    public static Move perform(Tile start, Tile end, Board board) {
        return isValid(start, end, board) ? new PawnLeap(start, end, board) : INVALID_MOVE;
    }

    /**
     * Checks if the pawn move with the given board position is valid. In order to
     * determine if the given pawn on start has moved or not the row of the start
     * tile is checked. If that is the case then it is checked if the pawn leaps two
     * row in its direction. If not the move is {@code false}.
     *
     * @param board the initial board position.
     * @param start  the start coordinate.
     * @param end    the end coordinate.
     * @return {@code true} if the move is valid.
     */
    public static boolean isValid(Tile start, Tile end, Board board) {
        // There must not be a piece on the end position.
        if (board.containsKey(end)) {
            return false;
        }

        Piece pawn = board.get(start);
        if (Piece.isPawn(pawn)) {
            Color pawnColor = pawn.color();

            int dir = pawnColor == Color.WHITE ? 1 : -1;
            int deltaRow = end.row() - start.row();
            int deltaColumn = end.column() - start.column();

            boolean isOnStart = pawnColor == Color.WHITE ? start.row() == 2 : start.row() == 7;

            return isOnStart && deltaRow * dir == 2 && deltaColumn == 0;
        } else {
            return false;
        }
    }
}
