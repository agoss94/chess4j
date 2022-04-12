package chess4j.model;

public class Rook extends AbstractPiece implements Piece {

	protected Rook(Color color) {
		super(Type.ROOK, color);
	}

	public static Rook white() {
		return new Rook(Color.WHITE);
	}

	public static Rook black() {
		return new Rook(Color.BLACK);
	}

	@Override
	public boolean isValid(Position start, Position end) {
		int dirX = end.getColumn() - start.getColumn();
		int dirY = end.getRow() - start.getRow();
		return Math.abs(dirX) == 0 || Math.abs(dirY) == 0;
	}

}
