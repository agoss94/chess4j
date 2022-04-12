package chess4j.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chess4j.model.Piece;
import chess4j.model.Position;
import chess4j.model.Queen;

public class QueenTest {

	@Test
	void queenLeft() {
		Piece queen = Queen.white();
		assertEquals(true, queen.isValid(Position.d5, Position.a5));
	}

	@Test
	void queenUp() {
		Piece queen = Queen.white();
		assertEquals(true, queen.isValid(Position.d5, Position.d7));
	}

	@Test
	void queenRight() {
		Piece queen = Queen.white();
		assertEquals(true, queen.isValid(Position.d5, Position.h5));
	}

	@Test
	void queenDown() {
		Piece queen = Queen.white();
		assertEquals(true, queen.isValid(Position.d5, Position.d2));
	}

	@Test
	void queenUpRight() {
		Piece queen = Queen.white();
		assertEquals(true , queen.isValid(Position.d4, Position.g7));
	}

	@Test
	void queenUpLeft() {
		Piece queen = Queen.white();
		assertEquals(true , queen.isValid(Position.d4, Position.b6));
	}

	@Test
	void queenDownRight() {
		Piece queen = Queen.white();
		assertEquals(true , queen.isValid(Position.d4, Position.g1));
	}

	@Test
	void queenDownLeft() {
		Piece queen = Queen.white();
		assertEquals(true , queen.isValid(Position.d4, Position.c3));
	}

	@Test
	void queenFalseMove() {
		Piece queen = Queen.white();
		assertEquals(false , queen.isValid(Position.d4, Position.e2));
	}
}
