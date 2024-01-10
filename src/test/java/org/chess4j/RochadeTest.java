package org.chess4j;

import org.chess4j.moves.NormalMove;
import org.chess4j.moves.Rochade;
import org.chess4j.pieces.King;
import org.chess4j.pieces.Knight;
import org.chess4j.pieces.Rook;
import org.chess4j.simple.EnumMapBoard;
import org.chess4j.simple.History;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RochadeTest {

    @Test
    void invalidCoordinates() {
        Board board = new EnumMapBoard();
        board.put(Tile.e1, King.white());
        board.put(Tile.h1, Rook.white());
        History History = new History(board);
        assertFalse(Rochade.isValid(Tile.e1, Tile.h1, History));
    }

    @Test
    void wrongColorPiece() {
        Board board = new EnumMapBoard();
        board.put(Tile.e1, King.white());
        board.put(Tile.h1, Rook.black());
        History History = new History(board);
        assertFalse(Rochade.isValid(Tile.e1, Tile.g1, History));
    }

    @Test
    void whiteShortRochadeIsValid() {
        Board board = new EnumMapBoard();
        board.put(Tile.e1, King.white());
        board.put(Tile.h1, Rook.white());
        History History = new History(board);
        assertTrue(Rochade.isValid(Tile.e1, Tile.g1, History));
    }

    @Test
    void blackShortRochadeIsValid() {
        Board board = new EnumMapBoard();
        board.put(Tile.e8, King.white());
        board.put(Tile.h8, Rook.white());
        History History = new History(board);
        assertTrue(Rochade.isValid(Tile.e8, Tile.g8, History));
    }

    @Test
    void whiteLongRochadeIsValid() {
        Board board = new EnumMapBoard();
        board.put(Tile.e1, King.white());
        board.put(Tile.a1, Rook.white());
        History History = new History(board);
        assertTrue(Rochade.isValid(Tile.e1, Tile.c1, History));
    }

    @Test
    void blackLongRochadeIsValid() {
        Board board = new EnumMapBoard();
        board.put(Tile.e8, King.white());
        board.put(Tile.a8, Rook.white());
        History History = new History(board);
        assertTrue(Rochade.isValid(Tile.e8, Tile.c8, History));
    }

    @Test
    void invalidWhiteLongRochadeWithoneTileInCheck() {
        Board board = new EnumMapBoard();
        board.put(Tile.e1, King.white());
        board.put(Tile.a1, Rook.white());
        board.put(Tile.d8, Rook.black());
        History History = new History(board);
        assertFalse(Rochade.isValid(Tile.e1, Tile.c1, History));
    }

    @Test
    void invalidWhiteRochadeWithMovedPiece() {
        Board board = new EnumMapBoard();
        board.put(Tile.e1, King.white());
        board.put(Tile.a1, Rook.white());
        History History = new History(board);
        History.add(NormalMove.perform(Tile.a1, Tile.a3, History.current()));
        History.add(NormalMove.perform(Tile.a3, Tile.a1, History.current()));
        assertFalse(Rochade.isValid(Tile.e1, Tile.c1, History));
    }

    @Test
    void invalidWhiteRochadeWithWhiteKnightInTheWay() {
        Board board = new EnumMapBoard();
        board.put(Tile.e1, King.white());
        board.put(Tile.a1, Rook.white());
        board.put(Tile.b1, Knight.white());
        History History = new History(board);
        assertFalse(Rochade.isValid(Tile.e1, Tile.c1, History));
    }
}
