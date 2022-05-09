package chess4j.model;

/**
 * Abstract Base class for all Pieces. The class provides getter for the type and color of any piece.
 */
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
