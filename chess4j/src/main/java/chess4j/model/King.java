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
	public boolean isValidMove(Position start, Position end) {
		int dirX = end.getColumn() - start.getColumn();
		int dirY = end.getRow() - start.getRow();
		return Math.max(Math.abs(dirX), Math.abs(dirY)) <= 1 ;
	}
}
