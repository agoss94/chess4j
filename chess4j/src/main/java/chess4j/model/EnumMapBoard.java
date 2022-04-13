package chess4j.model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class EnumMapBoard extends AbstractMap<Position, Piece> implements Board {

    private final Map<Position, Piece> board;

    private final List<Move> moves;

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
        } else {
            throw new IllegalMoveException();
        }
    }

    @Override
    public boolean isValid(Position start, Position end) {
        return SimpleMove.isLegal(this, start, end);
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

    public static Set<Position> path(Position start, Position end) {
        int deltaColumn = end.getColumn() - start.getColumn();
        int deltaRow = end.getRow() - start.getRow();
        boolean isLine = Math.abs(deltaColumn) == Math.abs(deltaRow) || deltaColumn == 0 || deltaRow == 0;
        if (isLine) {
            int length = Math.max(Math.abs(deltaColumn), Math.abs(deltaRow));
            int dirColumn = Integer.signum(deltaColumn);
            int dirRow = Integer.signum(deltaRow);
            Set<Position> path = EnumSet.noneOf(Position.class);
            for (int i = 1; i < length; i++) {
                path.add(Position.valueOf(start.getRow() + i * dirRow, start.getColumn() + i * dirColumn));
            }
            return path;
        } else {
            return Collections.emptySet();
        }
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

        private SimpleMove(Board board,  Position start, Position end) {
            if(!isLegal(board, start, end)) {
                throw new IllegalStateException();
            }
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
                boolean secondCondition = path(start, end).stream().noneMatch(board::containsKey);
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
        public void revert(Board board) throws IllegalStateException {
            throw new RuntimeException("Not implemented");
        }
    }
}
