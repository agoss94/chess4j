package chess4j.model;

public interface Piece {

	/**
	 * Get the type of the piece
	 *
	 * @return the type of the piece.
	 */
	Type getType();

	/**
	 * Get the color of the piece.
	 *
	 * @return the color of the piece.
	 */
	Color getColor();

	/**
	 * Determines if the move is valid for the piece.
	 *
	 * @param start position of the piece at the beginning.
	 * @param end   position of the piece at the end.
	 * @return true is the move is valid false otherwise.
	 */
	boolean isValidMove(Position start, Position end);

	/**
	 * Determines if the capture is valid for the piece.
	 *
	 * @param start position of the piece at the beginning.
	 * @param end   position of the piece at the end.
	 * @return true is the move is valid false otherwise.
	 */
	boolean isValidCapture(Position start, Position end);

	enum Type {

		PAWN,

		ROOK,

		KNIGHT,

		BISHOP,

		QUEEN,

		KING;
	}
}
