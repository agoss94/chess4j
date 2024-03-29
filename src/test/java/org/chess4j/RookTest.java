package org.chess4j;

import org.chess4j.pieces.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RookTest {

    @Test
    void rookLeft() {
        Piece rook = Rook.white();
        assertEquals(true, rook.isValid(Tile.d5, Tile.a5));
    }

    @Test
    void rookUp() {
        Piece rook = Rook.white();
        assertEquals(true, rook.isValid(Tile.d5, Tile.d7));
    }

    @Test
    void rookRight() {
        Piece rook = Rook.white();
        assertEquals(true, rook.isValid(Tile.d5, Tile.h5));
    }

    @Test
    void rookDown() {
        Piece rook = Rook.white();
        assertEquals(true, rook.isValid(Tile.d5, Tile.d2));
    }

    @Test
    void rookFalseMove() {
        Piece rook = Rook.white();
        assertEquals(false, rook.isValid(Tile.d5, Tile.f6));
    }

}
