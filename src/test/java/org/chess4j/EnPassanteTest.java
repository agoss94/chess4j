package org.chess4j;

import org.chess4j.moves.EnPassante;
import org.chess4j.moves.NormalMove;
import org.chess4j.moves.PawnLeap;
import org.chess4j.pieces.Bishop;
import org.chess4j.pieces.Pawn;
import org.chess4j.simple.EnumMapBoard;
import org.chess4j.simple.SimpleHistory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnPassanteTest {

    @Test
    void enPassanteOnlyForPawns() {
        Board board = new EnumMapBoard();
        board.put(Tile.e2, Pawn.white());
        board.put(Tile.f4, Bishop.black());
        History game = new SimpleHistory(board);
        game.add(PawnLeap.perform(Tile.e2, Tile.e4, board));
        assertTrue(NormalMove.isValid(Tile.f4, Tile.e3, board));
        assertFalse(EnPassante.isValid(Tile.f4, Tile.e3, game));
    }

    @Test
    void enPassanteOnlyAfterPawnLeap() {
        Board board = new EnumMapBoard();
        board.put(Tile.e4, Pawn.white());
        board.put(Tile.f4, Pawn.black());
        History game = new SimpleHistory(board);
        assertFalse(EnPassante.isValid(Tile.f4, Tile.e3, game));
    }

    @Test
    void validEnPassanteBlackPawn() {
        Board board = new EnumMapBoard();
        board.put(Tile.e2, Pawn.white());
        board.put(Tile.f4, Pawn.black());
        History game = new SimpleHistory(board);
        game.add(PawnLeap.perform(Tile.e2, Tile.e4, board));
        assertTrue(EnPassante.isValid(Tile.f4, Tile.e3, game));
    }

    @Test
    void validEnPassanteWhitePawn() {
        Board board = new EnumMapBoard();
        board.put(Tile.e7, Pawn.black());
        board.put(Tile.f5, Pawn.white());
        History game = new SimpleHistory(board);
        game.add(PawnLeap.perform(Tile.e7, Tile.e5, board));
        assertTrue(EnPassante.isValid(Tile.f5, Tile.e6, game));
    }

    @Test
    void performEnPassanteWhitePawn() {
        Board board = new EnumMapBoard();
        Pawn black = Pawn.black();
        board.put(Tile.e7, black);
        Pawn white = Pawn.white();
        board.put(Tile.f5, white);
        History game = new SimpleHistory(board);
        game.add(PawnLeap.perform(Tile.e7, Tile.e5, board));
        Move enPassante = EnPassante.perform(Tile.f5, Tile.e6, game);
        assertNotNull(enPassante);
        assertEquals(white, enPassante.moved());
        assertEquals(black, enPassante.captured().get());
    }

}
