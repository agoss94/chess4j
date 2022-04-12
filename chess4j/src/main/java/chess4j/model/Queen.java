package chess4j.model;

public class Queen extends AbstractPiece implements Piece {

	private Queen(Color color) {
		super(Type.QUEEN, color);
	}

	public static Queen white() {
		return new Queen(Color.WHITE);
	}

	public static Queen black() {
		return new Queen(Color.BLACK);
	}

	@Override
	public boolean isValid(Position start, Position end) {
		int dirX = end.getColumn() - start.getColumn();
		int dirY = end.getRow() - start.getRow();
		return Math.abs(dirX) == Math.abs(dirY) || dirX == 0 || dirY == 0;
	}

}
