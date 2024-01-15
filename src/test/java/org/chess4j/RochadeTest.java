package org.chess4j;

import org.chess4j.moves.NormalMove;
import org.chess4j.moves.Rochade;
import org.chess4j.pieces.King;
import org.chess4j.pieces.Knight;
import org.chess4j.pieces.Rook;
import org.chess4j.simple.EnumMapBoard;
import org.chess4j.simple.SimpleHistory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RochadeTest {

    @Test
    void invalidCoordinates() {
        Board board = new EnumMapBoard();
        board.put(Tile.e1, King.white());
        board.put(Tile.h1, Rook.white());
        History history = new SimpleHistory(board);
        assertFalse(Rochade.isValid(Tile.e1, Tile.h1, history));
    }

    @Test
    void wrongColorPiece() {
        Board board = new EnumMapBoard();
        board.put(Tile.e1, King.white());
        board.put(Tile.h1, Rook.black());
        History history = new SimpleHistory(board);
        assertFalse(Rochade.isValid(Tile.e1, Tile.g1, history));
    }

    @Test
    void whiteShortRochadeIsValid() {
        Board board = new EnumMapBoard();
        board.put(Tile.e1, King.white());
        board.put(Tile.h1, Rook.white());
        History history = new SimpleHistory(board);
        assertTrue(Rochade.isValid(Tile.e1, Tile.g1, history));
    }

    @Test
    void blackShortRochadeIsValid() {
        Board board = new EnumMapBoard();
        board.put(Tile.e8, King.white());
        board.put(Tile.h8, Rook.white());
        History history = new SimpleHistory(board);
        assertTrue(Rochade.isValid(Tile.e8, Tile.g8, history));
    }

    @Test
    void whiteLongRochadeIsValid() {
        Board board = new EnumMapBoard();
        board.put(Tile.e1, King.white());
        board.put(Tile.a1, Rook.white());
        History history = new SimpleHistory(board);
        assertTrue(Rochade.isValid(Tile.e1, Tile.c1, history));
    }

    @Test
    void blackLongRochadeIsValid() {
        Board board = new EnumMapBoard();
        board.put(Tile.e8, King.white());
        board.put(Tile.a8, Rook.white());
        History history = new SimpleHistory(board);
        assertTrue(Rochade.isValid(Tile.e8, Tile.c8, history));
    }

    @Test
    void invalidWhiteLongRochadeWithoneTileInCheck() {
        Board board = new EnumMapBoard();
        board.put(Tile.e1, King.white());
        board.put(Tile.a1, Rook.white());
        board.put(Tile.d8, Rook.black());
        History history = new SimpleHistory(board);
        assertFalse(Rochade.isValid(Tile.e1, Tile.c1, history));
    }

    @Test
    void invalidWhiteRochadeWithMovedPiece() {
        Board board = new EnumMapBoard();
        board.put(Tile.e1, King.white());
        board.put(Tile.a1, Rook.white());
        History history = new SimpleHistory(board);
        history.add(NormalMove.perform(Tile.a1, Tile.a3, history.currentPosition()));
        history.add(NormalMove.perform(Tile.a3, Tile.a1, history.currentPosition()));
        assertFalse(Rochade.isValid(Tile.e1, Tile.c1, history));
    }

    @Test
    void invalidWhiteRochadeWithWhiteKnightInTheWay() {
        Board board = new EnumMapBoard();
        board.put(Tile.e1, King.white());
        board.put(Tile.a1, Rook.white());
        board.put(Tile.b1, Knight.white());
        History history = new SimpleHistory(board);
        assertFalse(Rochade.isValid(Tile.e1, Tile.c1, history));
    }
}
