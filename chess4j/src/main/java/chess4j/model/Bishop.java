package chess4j.model;

public class Bishop extends AbstractPiece implements Piece {

	private Bishop(Color color) {
		super(Type.BISHOP, color);
	}

	public static Bishop white() {
		return new Bishop(Color.WHITE);
	}

	public static Bishop black() {
		return new Bishop(Color.BLACK);
	}

	@Override
	public boolean isValidMove(Position start, Position end) {
		int dirX = end.getColumn() - start.getColumn();
		int dirY = end.getRow() - start.getRow();
		return Math.abs(dirX) == Math.abs(dirY);
	}
}
