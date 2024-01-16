package org.chess4j.moves;

import org.chess4j.Board;
import org.chess4j.Move;
import org.chess4j.Piece;
import org.chess4j.Tile;

import java.util.Objects;

/**
 * A normal move is a rook, knight, bishop, queen and king move given that the
 * move is not a rochade. All Pawn moves are not considered normal.
 */
public class NormalMove extends AbstractMove {

    // Private constructor is only invoked after a validity check.
    private NormalMove(Tile start, Tile end, Board initial) {
        super(start, end, initial);
    }

    /**
     * Returns a standard move or {@link Move#INVALID_MOVE} if the move would be
     * invalid. A move is valid if the piece can move from start to end legally
     * as specified by {@link Piece#isValid(Tile, Tile)}. Secondly the path of
     * the piece must be clear as specified by {@link Tile#path(Tile, Tile)} and
     * lastly the end tile must be either empty or host a piece of opposite
     * color. If there is no Piece associated with the start position then the
     * move is also invalid.
     *
     * @param initial the initial position.
     * @param start   the start coordinate.
     * @param end     the end coordinate.
     * @return a valid move or {@link Move#INVALID_MOVE} if the move would be
     * invalid.
     */
    public static Move perform(Tile start, Tile end, Board initial) {
        return isValid(start, end, initial) ?
                new NormalMove(start, end, initial) : INVALID_MOVE;
    }

    /**
     * Checks if the standard move with the given position is valid. A move is
     * valid if the piece can move from start to end legally as specified by
     * {@link Piece#isValid(Tile, Tile)}. Secondly the path of the piece must be
     * clear as specified by {@link Tile#path(Tile, Tile)} and lastly the end
     * tile must be either empty or host a piece of opposite color. If there is
     * no Piece associated with the start position then false is returned.
     *
     * @param initial the initial position.
     * @param start   the start coordinate.
     * @param end     the end coordinate.
     * @return {@code true} if the move is valid.
     */
    public static boolean isValid(Tile start, Tile end, Board initial) {
        // If there is no piece on start then the move is invalid.
        if (!initial.containsKey(start)) {
            return false;
        }
        Piece startPiece = initial.get(start);

        // Check if the move in principle is possible
        if (!startPiece.isValid(start, end)) {
            return false;
        }

        // The path of the piece must be completely clear
        if (Tile.path(start, end).stream().anyMatch(initial::containsKey)) {
            return false;
        }

        // The piece placed on the end Position must have a different color
        // than the
        // start piece.
        Piece endPiece = initial.get(end);
        return Objects.isNull(endPiece) ||
                Piece.isOpposite(startPiece, endPiece);
    }
}
