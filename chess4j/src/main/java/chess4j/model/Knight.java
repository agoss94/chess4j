package chess4j.model;

public class Knight extends AbstractPiece implements Piece {

	private Knight(Color color) {
		super(Type.KNIGHT, color);
	}

	public static Knight white() {
		return new Knight(Color.WHITE);
	}

	public static Knight black() {
		return new Knight(Color.BLACK);
	}

	@Override
	public boolean isValidMove(Position start, Position end) {
		int deltaColumn = end.getColumn() - start.getColumn();
		int deltaRow = end.getRow() - start.getRow();
		return (Math.abs(deltaColumn) == 2 && Math.abs(deltaRow) == 1) || (Math.abs(deltaColumn) == 1 && Math.abs(deltaRow) == 2);
	}
}
