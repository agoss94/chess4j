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
	public boolean isValidMove(Position start, Position end) {
		int dirX = end.getColumn() - start.getColumn();
		int dirY = end.getRow() - start.getRow();
		int direction = getColor() == Color.WHITE ? 1 : -1;
		return dirX == 0 && dirY == 1 * direction;
	}

	@Override
	public boolean isValidCapture(Position start, Position end) {
		int dirX = end.getColumn() - start.getColumn();
		int dirY = end.getRow() - start.getRow();
		int direction = getColor() == Color.WHITE ? 1 : -1;
		return dirX == Math.abs(1) && dirY == 1 * direction;
	}
}
