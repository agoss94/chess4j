package org.chess4j;

import org.chess4j.moves.PawnLeap;
import org.chess4j.pieces.Pawn;
import org.chess4j.pieces.Rook;
import org.chess4j.simple.EnumMapBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnLeapTest {

    @Test
    void noPieceOnStart() {
        Board board = new EnumMapBoard();
        assertFalse(PawnLeap.isValid(Tile.e2, Tile.e4, board));
    }

    @Test
    void noPawnOnStart() {
        Board board = new EnumMapBoard();
        board.put(Tile.e2, Rook.white());
        assertFalse(PawnLeap.isValid(Tile.e2, Tile.e4, board));
    }

    @Test
    void pawnMovesInTheWrongDirection() {
        Board board = new EnumMapBoard();
        board.put(Tile.e3, Rook.white());
        assertFalse(PawnLeap.isValid(Tile.e3, Tile.e1, board));
    }

    @Test
    void pawnCannotCaptureInLeap() {
        Board board = new EnumMapBoard();
        board.put(Tile.e2, Pawn.white());
        board.put(Tile.e4, Rook.black());
        assertFalse(PawnLeap.isValid(Tile.e2, Tile.e4, board));
    }

    @Test
    void pawnLeapIsValid() {
        assertTrue(PawnLeap.isValid(Tile.e2, Tile.e4, Boards.newGame()));
    }

    @Test
    void invalidPerformedMoveIsNull() {
        assertEquals(Move.INVALID_MOVE, PawnLeap.perform(Tile.e2, Tile.e3, Boards.newGame()));
    }

    @Test
    void validPerformedMoveIsNotNull() {
        assertNotNull(PawnLeap.perform(Tile.e2, Tile.e4, Boards.newGame()));
    }
}
