package org.chess4j.simple;

import org.chess4j.Board;
import org.chess4j.Piece;
import org.chess4j.Tile;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

/**
 * A Board implementation that is backed by an EnumMap. Essentially an instance
 * {@link EnumMap} is created and forwarded by this class. As stated by the
 * {@link Map} interface this class offers two constructors
 * {@link #EnumMapBoard()} and {@link #EnumMapBoard(Map)}
 */
public class EnumMapBoard extends AbstractMap<Tile, Piece> implements Board {

    private final Map<Tile, Piece> board;

    /**
     * Constructor instantiates an empty board.
     */
    public EnumMapBoard() {
        this.board = new EnumMap<>(Tile.class);
    }

    /**
     * Creates a board with all the mappings as in the given board.
     *
     * @param board the board that is used as template.
     */
    public EnumMapBoard(Map<Tile, Piece> board) {
        this();
        putAll(board);
    }

    /**
     * Associates the given piece with the given tile. If there was previously a
     * piece associated with the tile, then this piece is returned.
     *
     * @return the previously associated mapping or {@code null} if none is
     * present.
     * @throws NullPointerException if the key or value is {@code null}.
     */
    @Override
    public Piece put(Tile key, Piece value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        return board.put(key, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entry<Tile, Piece>> entrySet() {
        return board.entrySet();
    }


}
