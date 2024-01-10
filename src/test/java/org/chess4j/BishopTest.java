package org.chess4j;

import org.chess4j.pieces.Bishop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BishopTest {

    @Test
    void upRight() {
        Piece bishop = Bishop.white();
        assertEquals(true , bishop.isValid(Tile.d4, Tile.g7));
    }

    @Test
    void upLeft() {
        Piece bishop = Bishop.white();
        assertEquals(true , bishop.isValid(Tile.d4, Tile.b6));
    }

    @Test
    void downRight() {
        Piece bishop = Bishop.white();
        assertEquals(true , bishop.isValid(Tile.d4, Tile.g1));
    }

    @Test
    void downLeft() {
        Piece bishop = Bishop.white();
        assertEquals(true , bishop.isValid(Tile.d4, Tile.c3));
    }
}
