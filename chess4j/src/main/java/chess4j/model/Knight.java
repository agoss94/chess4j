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
		int dirX = end.getColumn() - start.getColumn();
		int dirY = end.getRow() - start.getRow();
		return (Math.abs(dirX) == 2 && Math.abs(dirY) == 1) || (Math.abs(dirX) == 1 && Math.abs(dirY) == 2);
	}
}
