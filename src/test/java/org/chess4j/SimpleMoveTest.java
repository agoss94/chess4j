package org.chess4j;

import org.chess4j.moves.Move;
import org.chess4j.moves.NormalMove;
import org.chess4j.pieces.Rook;
import org.chess4j.simple.EnumMapBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NormalMoveTest {

    @Test
    void noPieceOnStart() {
        Board board = new EnumMapBoard();
        assertFalse(NormalMove.isValid(Tile.b1, Tile.c3, board));
    }

    @Test
    void pieceOnStartMovedInvalid() {
        assertFalse(NormalMove.isValid(Tile.b1, Tile.b3, Boards.newGame()));
    }

    @Test
    void pathNotClear() {
        Board board = new EnumMapBoard();
        board.put(Tile.a1, Rook.white());
        board.put(Tile.a3, Rook.white());
        assertFalse(NormalMove.isValid(Tile.a1, Tile.a5, board));
    }

    @Test
    void capturedPieceOfWrongColor() {
        Board board = new EnumMapBoard();
        board.put(Tile.a1, Rook.white());
        board.put(Tile.a5, Rook.white());
        assertFalse(NormalMove.isValid(Tile.a1, Tile.a5, board));
    }

    @Test
    void validMove() {
        assertTrue(NormalMove.isValid(Tile.b1, Tile.a3, Boards.newGame()));
    }

    @Test
    void invalidPerformedMoveIsNull() {
        assertEquals(Move.INVALID_MOVE, NormalMove.perform(Tile.b1, Tile.b4, Boards.newGame()));
    }

    @Test
    void validPerformedMoveIsNotNull() {
        assertNotNull(NormalMove.perform(Tile.b1, Tile.c3, Boards.newGame()));
    }
}