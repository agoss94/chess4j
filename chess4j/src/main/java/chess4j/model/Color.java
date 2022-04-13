package chess4j.model;

public enum Color {
    /**
     * The color white.
     */
    WHITE,

    /**
     * The color black.
     */
    BLACK;

    public Color switchColor() {
        switch (this) {
        case BLACK: return WHITE;
        case WHITE: return BLACK;
        default: throw new RuntimeException();
        }
    }

}
