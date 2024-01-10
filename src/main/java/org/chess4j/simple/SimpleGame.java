package org.chess4j.simple;

import org.chess4j.Board;
import org.chess4j.Boards;
import org.chess4j.ChessGame;
import org.chess4j.Color;
import org.chess4j.Piece;
import org.chess4j.Tile;
import org.chess4j.exceptions.InvalidMoveException;
import org.chess4j.moves.Move;
import org.chess4j.pieces.Pawn;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;

import static org.chess4j.Piece.isPawn;
import static org.chess4j.Piece.isQueen;
import static org.chess4j.Piece.isRook;

public class SimpleGame implements ChessGame {


    private final History history;
    private final Player white;
    private final Player black;

    private Status status;

    public SimpleGame() {
        this.history = new History(Boards.newGame());
        this.white = Player.white(history);
        this.black = Player.black(history);
        this.status = Status.WHITE_PLAYER_TURN;
    }

    private static boolean isPossiblyHeavyPiece(Map.Entry<Tile, Piece> e) {
        return isQueen(e.getValue()) || isRook(e.getValue()) || isPawn(e.getValue());
    }

    @Override
    public void newGame() {
        history.clear();
        this.status = Status.WHITE_PLAYER_TURN;
    }

    @Override
    public Board getBoardPosition() {
        return history.current();
    }

    @Override
    public Set<Tile> reachableTiles(Tile start) {
        if (isGameOver()) {
            return Collections.emptySet();
        }
        Player player = status == Status.WHITE_PLAYER_TURN ? white : black;
        Set<Tile> reachableTiles = EnumSet.noneOf(Tile.class);
        for (Tile tile : Tile.values()) {
            if (player.isValid(start, tile)) {
                reachableTiles.add(tile);
            }
        }
        return reachableTiles;
    }

    @Override
    public boolean isGameOver() {
        return !(status == Status.WHITE_PLAYER_TURN || status == Status.BLACK_PLAYER_TURN);

    }

    @Override
    public void move(String uciMove) throws InvalidMoveException {
        Matcher matcher = UCI_FORMAT.matcher(uciMove);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(String.format("The given " +
                    "input: \"%s\" has the wrong format", uciMove));
        }

        //Make the move.
        Tile start = Tile.valueOf(matcher.group(1));
        Tile end = Tile.valueOf(matcher.group(2));
        performMove(start, end);

        //Check for a pawn promotion.
        checkPromotion(matcher.group(3), end);

        //update Status for the next turn.
        updateStatus();
    }

    private void updateStatus() {
        status = (turnNumber() % 2) == 0 ? Status.WHITE_PLAYER_TURN :
                Status.BLACK_PLAYER_TURN;
        status = black.isCheckmate() ? Status.WHITE_WON : status;
        status = white.isCheckmate() ? Status.BLACK_WON : status;
        status = white.isStalemate() || black.isStalemate() ?
                Status.STALEMATE : status;
        status = isThreefoldRepetition() ?
                Status.DRAW_BY_THREEFOLD_REPETITION : status;
        status = isFiftyMoveRule() ? Status.DRAW_BY_FIFTY_MOVE_RULE : status;
        status = isDrawByInsufficientMaterial() ?
                Status.DRAW_BY_INSUFFICIENT_MATERIAL : status;
    }

    /**
     * {@inheritDoc}
     */
    private boolean isThreefoldRepetition() {
        if (history.size() < 8) {
            return false;
        } else {
            Board current = history.current();
            Board before = history.get(history.size() - 4).initial();
            Board beforeBefore = history.get(history.size() - 8).initial();
            return current.equals(before) && before.equals(beforeBefore);
        }
    }

    /**
     * {@inheritDoc}
     */
    private boolean isFiftyMoveRule() {
        if (history.size() < 100) {
            return false;
        }
        for (int i = 0; i < 100; i++) {
            Move move = history.get(history.size() - (i + 1));
            if (move.captured().isPresent()) {
                return false;
            }
            if (move.moved().type() == Piece.Type.PAWN) {
                return false;
            }
        }
        return true;
    }

    private boolean isDrawByInsufficientMaterial() {
        // More than four pieces on the board are sufficient for a
        // checkmate.
        Board board = getBoardPosition();
        if (board.size() > 4) {
            return false;
        }
        // Any heavy piece is sufficient with a king for checkmate. Any pawn
        // could be
        // promoted to a heavy piece.
        if (board.entrySet().stream().anyMatch(SimpleGame::isPossiblyHeavyPiece)) {
            return false;
        }
        if (board.size() == 4) {
            // Only two bishops remain on the board.
            Optional<Tile> whiteBishopPos = Boards.filter(board,
                    Piece.isOfColor(Color.WHITE).and(Piece::isBishop)).keySet().stream().findFirst();
            Optional<Tile> blackBishopPos = Boards.filter(board,
                    Piece.isOfColor(Color.BLACK).and(Piece::isBishop)).keySet().stream().findFirst();
            if (whiteBishopPos.isPresent() && blackBishopPos.isPresent()) {
                // It is a draw is only two bishop remain which are
                // placed on
                // the same color.
                return whiteBishopPos.get().parity() == blackBishopPos.get().parity();
            } else {
                return false;
            }
        }

        /*
         * At this point only three or fewer pieces remain on the board
         * which
         *  can only
         * be two kings and one knight or bishop which is a draw by
         * insufficient
         * material.
         */
        return true;
    }

    private void performMove(Tile start, Tile end) {
        if (status == Status.WHITE_PLAYER_TURN) {
            white.move(start, end);
        } else if (status == Status.BLACK_PLAYER_TURN) {
            black.move(start, end);
        } else {
            throw new IllegalStateException(String.format("Cannot make a " +
                    "move" + " with status: %s", status));
        }
    }

    private void checkPromotion(String type, Tile end) {
        if (type != null && !canBePromoted()) {
            throw new IllegalArgumentException("There is nothing to " +
                    "promote");
        } else if (type == null && canBePromoted()) {
            throw new IllegalArgumentException("Pawn must be promoted");
        } else if (type != null && canBePromoted()) {
            Pawn pawn = (Pawn) getBoardPosition().get(end);
            switch (type) {
                case "q":
                    pawn.promote(Piece.Type.QUEEN);
                    break;
                case "b":
                    pawn.promote(Piece.Type.BISHOP);
                    break;
                case "k":
                    pawn.promote(Piece.Type.KNIGHT);
                    break;
                case "r":
                    pawn.promote(Piece.Type.ROOK);
                    break;

            }
        }
    }

    /**
     * Returns {@code true} if a pawn can be promoted.
     *
     * @return {@code true} if a pawn can be promoted.
     */
    private boolean canBePromoted() {
        for (Tile tile : Tile.values()) {
            if (tile.row() == 8 || tile.row() == 1) {
                Piece piece = getBoardPosition().get(tile);
                if (isPawn(piece)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void revert() {
        history.revert();
        updateStatus();
    }

    @Override
    public int turnNumber() {
        return history.size();
    }

    @Override
    public Status getStatus() {
        return status;
    }
}
