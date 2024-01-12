package org.chess4j;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/**
 * A board maps tiles to pieces
 */
public interface Board extends Map<Tile, Piece> {

}
