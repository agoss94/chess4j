package org.chess4j.simple;

import org.chess4j.Board;
import org.chess4j.Boards;
import org.chess4j.Color;
import org.chess4j.moves.Move;
import org.chess4j.Piece;
import org.chess4j.Tile;
import org.chess4j.exceptions.InvalidMoveException;
import org.chess4j.moves.EnPassante;
import org.chess4j.moves.NormalMove;
import org.chess4j.moves.PawnCapture;
import org.chess4j.moves.PawnLeap;
import org.chess4j.moves.PawnMove;
import org.chess4j.moves.Rochade;

import java.util.Objects;

import static java.util.Objects.isNull;
import static org.chess4j.moves.Move.INVALID_MOVE;
import static org.chess4j.Piece.isKing;
import static org.chess4j.Piece.isPawn;

/**
 * A Player moves pieces throughout a game of chess. The player can move all the
 * pieces through legal moves given that he is not {@link #inCheck()}
 * {@link #isStalemate()} or {@link #isCheckmate()}. Additionally the Player
 * cannot move a piece to a position such that the player is {@link #inCheck()}
 * as a result of the move.
 */
public class Player {

    /**
     * The color of the chess player determines which pieces he can move.
     */
    private final Color color;

    /**
     * The history of the game the player plays.
     */
    private History chronicle;

    /*
     * Private Constructor.
     */
    private Player(Color color, History chronicle) {
        this.color = color;
        this.chronicle = Objects.requireNonNull(chronicle);
    }

    /**
     * Returns a white player which moves pieces on the given board with the given
     * game history.
     *
     * @param chronicle the game the player is supposed to play.
     * @return a white player.
     */
    public static Player white(History chronicle) {
        return new Player(Color.WHITE, chronicle);
    }

    /**
     * Returns a black player that moves pieces in the given game.
     *
     * @param chronicle the game the player is supposed to play.
     * @return a black player.
     */
    public static Player black(History chronicle) {
        return new Player(Color.BLACK, chronicle);
    }

    /**
     * Returns the player color.
     *
     * @return the color of the player.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the chronicle on which the player operates.
     *
     * @return the current chronicle of the game.
     */
    public History getChronicle() {
        return chronicle;
    }

    /**
     * Sets the current chronicle of the player.
     *
     * @param chronicle the chronicle of the game.
     */
    public void setChronicle(History chronicle) {
        this.chronicle = Objects.requireNonNull(chronicle);
    }

    /**
     * Returns {@code true} if the player can successfully move a piece from the
     * start coordinate to the end coordinate.
     *
     * @param start the start coordinate.
     * @param end   the end coordinate.
     * @return {@code true} if the move is valid.
     */
    public boolean isValid(Tile start, Tile end) {
        Move move = createMove(chronicle, start, end);
        return move != INVALID_MOVE && Piece.isOfColor(color).test(move.moved()) && !inCheck(move.result(), color);
    }

    /**
     * Moves the piece from start to end if the given move is valid as indicated by
     * {@link #isValid(Tile, Tile)}.
     *
     * @throws InvalidMoveException if there is no piece on the start position, if
     *                              the piece has the wrong color if the move is
     *                              invalid in principle for the piece on the board
     *                              or if the player is in check as a result of the
     *                              move.
     */
    public void move(Tile start, Tile end) throws InvalidMoveException {
        Board current = chronicle.current();
        Piece piece = current.get(start);
        if (isNull(piece)) {
            throw new InvalidMoveException(String.format("There is no piece on %s", start));
        }
        if (piece.color() != color) {
            throw new InvalidMoveException("The player cannot move a piece of the opposite color");
        }
        Move move = createMove(chronicle, start, end);
        if (move == INVALID_MOVE) {
            throw new InvalidMoveException("The move is invalid for the given piece");
        }
        if (inCheck(move.result(), color)) {
            throw new InvalidMoveException("Cannot move the piece. The player is in check!");
        }
        // If no prior exception occurred add the move to the chronicle
        chronicle.add(move);
    }

    /**
     * Creates a move only if there can be a valid move from start to end. Otherwise
     * {@code null} is returned.
     *
     * @param chronicle the current game position on whic
     * @param start     the start position
     * @param end       the end position
     * @return a valid move or {@code null} if none can be created.
     */
    private static Move createMove(History chronicle, Tile start, Tile end) {
        Board current = chronicle.current();
        Piece piece = current.get(start);

        // First try a simple move.
        Move move = NormalMove.perform(start, end, current);

        if (isPawn(piece)) {
            // The move itself is invalid if the piece is a pawn.
            move = PawnMove.perform(start, end, current);

            if (move == INVALID_MOVE) {
                move = PawnCapture.perform(start, end, current);
            }
            if (move == INVALID_MOVE) {
                move = PawnLeap.perform(start, end, current);
            }
            if (move == INVALID_MOVE) {
                // An EnPassante move requires knowledge about the history of the game.
                move = EnPassante.perform(start, end, chronicle);
            }
        }
        if (move == INVALID_MOVE && isKing(piece)) {
            // Try rochade as last.
            move = Rochade.perform(start, end, chronicle);
        }
        return move;
    }

    /**
     * Returns {@code true} if the king of the given player can be captured by enemy
     * pieces on the next move.
     *
     * @return {@code true} if the king of the player is in check, {@code false}
     *         otherwise.
     */
    public boolean inCheck() {
        return inCheck(chronicle.current(), color);
    }

    /**
     * Returns true if the player of the given color is in check on the given Board.
     * That is that an enemy piece can capture the king on the next move.
     *
     * @param board the current board position.
     * @param color the color of the player
     * @return {@code true} if the player of the given color is in check,
     *         {@code false} otherwise.
     */
    public static boolean inCheck(Board board, Color color) {
        Board enemyPieces = Boards.filter(board, Piece.isOfColor(color.opposite()));
        Board kingMapping = Boards.filter(board, Piece.isOfColor(color).and(Piece::isKing));
        Tile kingPosition = kingMapping.keySet().stream().findFirst().orElse(null);

        if (kingPosition == null) {
            return false;
        }
        for (Tile enemyPosition : enemyPieces.keySet()) {
            if (NormalMove.isValid(enemyPosition, kingPosition, board)
                    || PawnCapture.isValid(enemyPosition, kingPosition, board)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the player cannot make a valid move that does not result with
     * the king in check.
     *
     * @return {@code true} if the player is unable to make a move that does not
     *         result in check.
     */
    public boolean isStalemate() {
        return !inCheck() && isMate();
    }

    /**
     * A Player is mate if he is unable to make a valid move.
     *
     * @return {@code true} if the player cannot make any valid move {@code false}
     *         otherwise.
     */
    private boolean isMate() {
        Board pieces = Boards.filter(chronicle.current(), Piece.isOfColor(color));
        for (Tile piecePosition : pieces.keySet()) {
            if (canMove(piecePosition)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a {@code true} if any given piece on the start coordinate can make at
     * least one valid move.
     *
     * @param start the given start tile.
     * @return {@code true} if any valid move from start is possible.
     */
    private boolean canMove(Tile start) {
        for (Tile tile : Tile.values()) {
            if (isValid(start, tile)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if the given player is checkmate. That means the capture of the
     * king in the next move is inevitable.
     *
     * @return {@code true} if he player is checkmate.
     */
    public boolean isCheckmate() {
        return inCheck() && isMate();
    }
}
