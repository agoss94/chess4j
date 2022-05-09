package chess4j.model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Simple {@link EnumMap} implementation of the Board interface.
 */
public class EnumMapBoard extends AbstractMap<Position, Piece> implements Board {

    /**
     * The implementation of this Board is backed by an {@link EnumMap}
     */
    private final Map<Position, Piece> board;

    /**
     * All moves are stored in a list.
     */
    private final List<Move> moves;

    /**
     * Constructor initializes all fields
     */
    public EnumMapBoard() {
        board = new EnumMap<>(Position.class);
        moves = new ArrayList<>();
    }

    @Override
    public Piece put(Position key, Piece value) {
        Objects.requireNonNull(key);
        return board.put(key, value);
    }

    @Override
    public Move move(Position start, Position end) throws IllegalMoveException {
        if (SimpleMove.isLegal(this, start, end)) {
            Move m = new SimpleMove(this, start, end);
            moves.add(m);
            return m;
        } else if (PawnMove.isLegal(this, start, end)) {
            Move m = new PawnMove(this, start, end);
            moves.add(m);
            return m;
        } else {
            throw new IllegalMoveException();
        }
    }

    @Override
    public boolean isValid(Position start, Position end) {
        return SimpleMove.isLegal(this, start, end) || PawnMove.isLegal(this, start, end);
    }

    @Override
    public List<Move> moves() {
        return Collections.unmodifiableList(moves);
    }

    @Override
    public void clear() {
        moves.clear();
        super.clear();
    }

    @Override
    public Set<Entry<Position, Piece>> entrySet() {
        return board.entrySet();
    }

    public static class SimpleMove implements Move {

        private final Position start;

        private final Position end;

        private final Piece movedPiece;

        private final Optional<Piece> capturedPiece;

        protected SimpleMove(Board board, Position start, Position end) {
            this.start = Objects.requireNonNull(start);
            this.end = Objects.requireNonNull(end);
            this.movedPiece = Objects.requireNonNull(board.remove(start));
            this.capturedPiece = Optional.ofNullable(board.put(end, movedPiece));
        }

        @Override
        public Position start() {
            return start;
        }

        @Override
        public Position end() {
            return end;
        }

        @Override
        public Piece movedPiece() {
            return movedPiece;
        }

        @Override
        public Optional<Piece> capturedPiece() {
            return capturedPiece;
        }

        public static boolean isLegal(Board board, Position start, Position end) {
            Objects.requireNonNull(start);
            Objects.requireNonNull(end);
            Objects.requireNonNull(board);
            if (board.containsKey(start)) {
                Piece startPiece = board.get(start);
                // Check if the move in principle is possible
                boolean firstCondition = startPiece.isValid(start, end);
                // The path of the piece must be completely clear
                boolean secondCondition = Position.path(start, end).stream().noneMatch(board::containsKey);
                // The piece placed on the end Position must have a different color than the
                // start piece.
                Piece endPiece = board.get(end);
                boolean thirdCondition = endPiece == null ? true : endPiece.getColor() != startPiece.getColor();

                // All Conditions must be meet
                return firstCondition && secondCondition && thirdCondition;
            } else {
                return false;
            }
        }

        @Override
        public void perform(Board board) throws IllegalStateException {
            // TODO Auto-generated method stub

        }

        @Override
        public void revert(Board board) throws IllegalStateException {
            throw new RuntimeException("Not implemented");
        }
    }

    public static class PawnMove implements Move {


        private final Position start;

        private final Position end;

        private final Piece movedPiece;

        private final Optional<Piece> capturedPiece;

        protected PawnMove(Board board, Position start, Position end) {
            this.start = Objects.requireNonNull(start);
            this.end = Objects.requireNonNull(end);
            this.movedPiece = Objects.requireNonNull(board.remove(start));
            this.capturedPiece = Optional.ofNullable(board.put(end, movedPiece));
        }

        @Override
        public Position start() {
            return start;
        }

        @Override
        public Position end() {
            return end;
        }

        @Override
        public Piece movedPiece() {
            return movedPiece;
        }

        @Override
        public Optional<Piece> capturedPiece() {
            return capturedPiece;
        }

        public static boolean isLegal(Board board, Position start, Position end) {
            Objects.requireNonNull(start);
            Objects.requireNonNull(end);
            Objects.requireNonNull(board);
            if (board.containsKey(start)) {
                Piece startPiece = board.get(start);
                Piece endPiece = board.get(end);

                boolean firstCondition;
                if (endPiece == null) {
                    int deltaColumn = end.getColumn() - start.getColumn();
                    int deltaRow = end.getRow() - start.getRow();
                    int dirRow = startPiece.getColor() == Color.WHITE ? 1 : -1;
                    firstCondition = deltaColumn == 0 && deltaRow == 1 * dirRow;

                    // If the first condition is false we could have a PawnLeap
                    if (firstCondition == false) {
                        firstCondition = deltaColumn == 0 && deltaRow == 2 * dirRow
                                && board.moves().stream().map(Move::movedPiece).noneMatch(p -> startPiece == p);
                    }
                } else {
                    int deltaColumn = end.getColumn() - start.getColumn();
                    int deltaRow = end.getRow() - start.getRow();
                    int dirRow = startPiece.getColor() == Color.WHITE ? 1 : -1;
                    firstCondition = Math.abs(deltaColumn) == 1 && deltaRow == 1 * dirRow;
                }

                // The path of the piece must be completely clear
                boolean secondCondition = Position.path(start, end).stream().noneMatch(board::containsKey);
                // start piece.
                boolean thirdCondition = endPiece == null ? true : endPiece.getColor() != startPiece.getColor();

                return firstCondition && secondCondition && thirdCondition;
            } else {
                return false;
            }
        }

        @Override
        public void perform(Board board) throws IllegalStateException {
            // TODO Auto-generated method stub
        }

        @Override
        public void revert(Board board) throws IllegalStateException {
            throw new RuntimeException("Not implemented");
        }
    }
}
