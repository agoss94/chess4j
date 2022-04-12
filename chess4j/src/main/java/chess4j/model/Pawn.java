package chess4j.model;

public class Pawn extends AbstractPiece implements Piece {

    private Pawn(Color color) {
        super(Type.PAWN, color);
    }

    public static Pawn white() {
        return new Pawn(Color.WHITE);
    }

    public static Pawn black() {
        return new Pawn(Color.BLACK);
    }

    @Override
    public boolean isValid(Position start, Position end) {
        int deltaColumn = end.getColumn() - start.getColumn();
        int deltaRow = end.getRow() - start.getRow();
        int dirRow = getColor() == Color.WHITE ? 1 : -1;
        return deltaColumn == 0 && deltaRow == 1 * dirRow;
    }
}
