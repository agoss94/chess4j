package org.chess4j;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public interface Board extends Map<Tile, Piece> {

    /**
     * A private implementation of a Board that is backed by a given board. The
     * returned instance is unmodifiable. All pieces
     */
     static class FilteredBoard extends AbstractMap<Tile, Piece> implements Board {

        /**
         * The filter condition.
         */
        private final Predicate<Piece> condition;

        /**
         * The entry set will be updated with the original map.
         */
        private final Board board;

        /**
         * Private constructor
         *
         * @param condition the predicate which is used for filtering.
         */
        private FilteredBoard(Board board, Predicate<Piece> condition) {
            this.condition = condition;
            this.board = board;
        }

        @Override
        public Set<Entry<Tile, Piece>> entrySet() {
            return new AbstractSet<Entry<Tile, Piece>>() {

                @Override
                public Iterator<Entry<Tile, Piece>> iterator() {
                    return board.entrySet().stream().filter(e -> condition.test(e.getValue())).iterator();
                }

                @Override
                public int size() {
                    return (int) board.entrySet().stream().filter(e -> condition.test(e.getValue())).count();
                }
            };
        }

    }

}
