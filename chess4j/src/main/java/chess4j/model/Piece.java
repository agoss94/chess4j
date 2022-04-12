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
    boolean isValid(Position start, Position end);

    /**
     * All types of chess pieces that can be placed on a board.
     */
    enum Type {

        /**
         * Type PAWN
         */
        PAWN,

        /**
         * Type ROOK
         */
        ROOK,

        /**
         * Type KNIGHT
         */
        KNIGHT,

        /**
         * Type BISHOP
         */
        BISHOP,

        /**
         * Type QUEEN
         */
        QUEEN,

        /**
         * Type KING
         */
        KING;
    }
}
