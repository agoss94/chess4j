package chess4j.model;

public abstract class AbstractPiece implements Piece {

	private final Type type;

	private final Color color;

	protected AbstractPiece(Type type, Color color) {
		super();
		this.type = type;
		this.color = color;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Color getColor() {
		return color;
	}
}
