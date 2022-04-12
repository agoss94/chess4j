package chess4j.model;

public class King extends AbstractPiece implements Piece {

    private King(Color color) {
        super(Type.KING, color);
    }

    public static King white() {
        return new King(Color.WHITE);
    }

    public static King black() {
        return new King(Color.BLACK);
    }

    @Override
    public boolean isValid(Position start, Position end) {
        int deltaColumn = end.getColumn() - start.getColumn();
        int deltaRow = end.getRow() - start.getRow();
        return Math.max(Math.abs(deltaColumn), Math.abs(deltaRow)) <= 1 ;
    }
}
