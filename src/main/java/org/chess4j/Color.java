package org.chess4j;

/**
 * The two different chess colors black and white in an enum class.
 */
public enum Color {

    /**
     * The color white.
     */
    WHITE,

    /**
     * The color black.
     */
    BLACK;

    /**
     * Return the opposite color.
     *
     * @return the other color.
     */
    public Color opposite() {
        switch (this) {
            case BLACK:
                return WHITE;
            case WHITE:
                return BLACK;
            default:
                throw new RuntimeException();
        }
    }
}
