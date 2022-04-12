package chess4j.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chess4j.model.Pawn;
import chess4j.model.Piece;
import chess4j.model.Position;

class PawnTest {

	@Test
	void whitePawnMoveTrue() {
		Piece pawn = Pawn.white();
		assertEquals(true , pawn.isValid(Position.a2, Position.a3));
	}

	@Test
	void whitePawnMoveFalse() {
		Piece pawn = Pawn.white();
		assertEquals(false , pawn.isValid(Position.a2, Position.a1));
	}

	@Test
	void blackPawnMoveTrue() {
		Piece pawn = Pawn.black();
		assertEquals(true , pawn.isValid(Position.a3, Position.a2));
	}

	@Test
	void blackPawnMoveFalse() {
		Piece pawn = Pawn.black();
		assertEquals(false , pawn.isValid(Position.a6, Position.a7));
	}
}
