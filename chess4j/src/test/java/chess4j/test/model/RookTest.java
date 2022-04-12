package chess4j.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chess4j.model.Piece;
import chess4j.model.Position;
import chess4j.model.Rook;

class RookTest {

	@Test
	void rookLeft() {
		Piece rook = Rook.white();
		assertEquals(true, rook.isValid(Position.d5, Position.a5));
	}

	@Test
	void rookUp() {
		Piece rook = Rook.white();
		assertEquals(true, rook.isValid(Position.d5, Position.d7));
	}

	@Test
	void rookRight() {
		Piece rook = Rook.white();
		assertEquals(true, rook.isValid(Position.d5, Position.h5));
	}

	@Test
	void rookDown() {
		Piece rook = Rook.white();
		assertEquals(true, rook.isValid(Position.d5, Position.d2));
	}

	@Test
	void rookFalseMove() {
		Piece rook = Rook.white();
		assertEquals(false, rook.isValid(Position.d5, Position.f6));
	}

}
