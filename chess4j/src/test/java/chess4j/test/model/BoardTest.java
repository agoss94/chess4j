package chess4j.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import chess4j.model.Bishop;
import chess4j.model.Board;
import chess4j.model.Board.Move;
import chess4j.model.EnumMapBoard;
import chess4j.model.Pawn;
import chess4j.model.Piece;
import chess4j.model.Position;

class BoardTest {

    @Test
    void simpleMoveTrue() {
        Board board = new EnumMapBoard();
        Piece piece = Bishop.white();
        board.put(Position.d4, piece);
        assertTrue(board.isValid(Position.d4, Position.f6));
        Move move = board.move(Position.d4, Position.f6);
        assertEquals(Position.d4, move.start());
        assertEquals(Position.f6, move.end());
        assertEquals(piece, move.movedPiece());
        assertTrue(move.capturedPiece().isEmpty());
    }

    @Test
    void simpleMoveCapturePiece() {
        Board board = new EnumMapBoard();
        Piece piece = Bishop.white();
        board.put(Position.d4, piece);
        assertTrue(board.isValid(Position.d4, Position.f6));
        Move move = board.move(Position.d4, Position.f6);
        assertEquals(Position.d4, move.start());
        assertEquals(Position.f6, move.end());
        assertEquals(piece, move.movedPiece());
        assertTrue(move.capturedPiece().isEmpty());
    }

    @Test
    void simpleMoveFalse() {
        Board board = new EnumMapBoard();
        board.put(Position.d4, Bishop.white());
        assertFalse(board.isValid(Position.d3, Position.f6));
    }

    @Test
    void simpleMovePieceOnPath() {
        Board board = new EnumMapBoard();
        board.put(Position.d4, Bishop.white());
        board.put(Position.e5, Pawn.white());
        assertFalse(board.isValid(Position.d4, Position.f6));
    }
}
