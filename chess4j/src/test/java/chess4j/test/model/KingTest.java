package chess4j.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chess4j.model.King;
import chess4j.model.Piece;
import chess4j.model.Position;

class KingTest {

	@Test
	void kingUp() {
		Piece king = King.black();
		assertEquals(true, king.isValid(Position.d3, Position.d4));
	}

	@Test
	void kingUpRight() {
		Piece king = King.black();
		assertEquals(true, king.isValid(Position.d3, Position.e4));
	}

	@Test
	void kingRight() {
		Piece king = King.black();
		assertEquals(true, king.isValid(Position.d3, Position.c3));
	}

	@Test
	void kingRightDown() {
		Piece king = King.black();
		assertEquals(true, king.isValid(Position.d3, Position.c2));
	}

	@Test
	void kingDown() {
		Piece king = King.black();
		assertEquals(true, king.isValid(Position.d3, Position.d2));
	}

	@Test
	void kingDownLeft() {
		Piece king = King.black();
		assertEquals(true, king.isValid(Position.d3, Position.c2));
	}

	@Test
	void kingLeft() {
		Piece king = King.black();
		assertEquals(true, king.isValid(Position.d3, Position.c3));
	}

	@Test
	void kingLeftUp() {
		Piece king = King.black();
		assertEquals(true, king.isValid(Position.d3, Position.c4));
	}

	@Test
	void kingFalseMove() {
		Piece king = King.black();
		assertEquals(false, king.isValid(Position.d3, Position.d5));
	}

}
