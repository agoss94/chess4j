package chess4j.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chess4j.model.Knight;
import chess4j.model.Piece;
import chess4j.model.Position;

public class KnightTest {


	@Test
	void knightUpRight() {
		Piece knight = Knight.black();
		assertEquals(true, knight.isValid(Position.d4, Position.e6));
	}

	@Test
	void knightRightUp() {
		Piece knight = Knight.black();
		assertEquals(true, knight.isValid(Position.d4, Position.f5));
	}

	@Test
	void knightRightDown() {
		Piece knight = Knight.black();
		assertEquals(true, knight.isValid(Position.d4, Position.f3));
	}

	@Test
	void knightDownRight() {
		Piece knight = Knight.black();
		assertEquals(true, knight.isValid(Position.d4, Position.e2));
	}

	@Test
	void knightDownLeft() {
		Piece knight = Knight.black();
		assertEquals(true, knight.isValid(Position.d4, Position.c2));
	}

	@Test
	void knightLeftDown() {
		Piece knight = Knight.black();
		assertEquals(true, knight.isValid(Position.d4, Position.b3));
	}

	@Test
	void knightLeftUp() {
		Piece knight = Knight.black();
		assertEquals(true, knight.isValid(Position.d4, Position.b5));
	}

	@Test
	void knightUpLeft() {
		Piece knight = Knight.black();
		assertEquals(true, knight.isValid(Position.d4, Position.c6));
	}

	@Test
	void knightFalseMove() {
		Piece knight = Knight.black();
		assertEquals(false, knight.isValid(Position.d4, Position.d6));
	}

}
