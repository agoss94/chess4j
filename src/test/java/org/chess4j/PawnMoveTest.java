package org.chess4j;

import org.chess4j.moves.Move;
import org.chess4j.moves.PawnCapture;
import org.chess4j.moves.PawnMove;
import org.chess4j.pieces.Pawn;
import org.chess4j.pieces.Rook;
import org.chess4j.simple.EnumMapBoard;
import org.chess4j.simple.History;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnMoveTest {

    @Test
    void noPieceOnStart() {
        Board board = new EnumMapBoard();
        assertFalse(PawnMove.isValid(Tile.e2, Tile.e3, board));
    }

    @Test
    void noPawnOnStart() {
        Board board = new EnumMapBoard();
        board.put(Tile.e2, Rook.white());
        assertFalse(PawnMove.isValid(Tile.e2, Tile.e3, board));
    }

    @Test
    void pawnMovesInTheWrongDirection() {
        Board board = new EnumMapBoard();
        board.put(Tile.e2, Rook.white());
        assertFalse(PawnMove.isValid(Tile.e2, Tile.e1, board));
    }

    @Test
    void pawnCannotCaptureInNormalMoves() {
        Board board = new EnumMapBoard();
        board.put(Tile.e2, Pawn.white());
        board.put(Tile.e3, Rook.black());
        assertFalse(PawnMove.isValid(Tile.e2, Tile.e3, board));
    }

    @Test
    void validWhiteMove() {
        assertTrue(PawnMove.isValid(Tile.e2, Tile.e3, Boards.newGame()));
    }

    @Test
    void validBlackMove() {
        assertTrue(PawnMove.isValid(Tile.e7, Tile.e6, Boards.newGame()));
    }

    @Test
    void invalidPerformedMoveIsNull() {
        assertEquals(Move.INVALID_MOVE, PawnMove.perform(Tile.e2, Tile.e4, Boards.newGame()));
    }

    @Test
    void validPerformedMoveIsNotNull() {
        assertNotNull(PawnMove.perform(Tile.e2, Tile.e3, Boards.newGame()));
    }

    @Test
    void pawnMustCapture() {
        Board board = new EnumMapBoard();
        board.put(Tile.e2, Pawn.white());
        History game = new History(board);
        assertFalse(PawnCapture.isValid(Tile.e2, Tile.d3, game.current()));
    }

    @Test
    void validWhiteMoveCapture() {
        Board board = new EnumMapBoard();
        board.put(Tile.e2, Pawn.white());
        board.put(Tile.d3, Rook.black());
        History game = new History(board);
        assertTrue(PawnCapture.isValid(Tile.e2, Tile.d3, game.current()));
    }

    @Test
    void validBlackMoveCapture() {
        Board board = new EnumMapBoard();
        board.put(Tile.e7, Pawn.black());
        board.put(Tile.d6, Rook.white());
        History game = new History(board);
        assertTrue(PawnCapture.isValid(Tile.e7, Tile.d6, game.current()));
    }
}