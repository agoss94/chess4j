package chess4j.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import chess4j.model.Bishop;
import chess4j.model.Piece;
import chess4j.model.Position;

public class BishopTest {

	@Test
	void whiteBishopMoveUpRight() {
		Piece pawn = Bishop.white();
		assertEquals(true , pawn.isValid(Position.d4, Position.g7));
	}

	@Test
	void whiteBishopMoveUpLeft() {
		Piece pawn = Bishop.white();
		assertEquals(true , pawn.isValid(Position.d4, Position.b6));
	}

	@Test
	void whiteBishopMoveDownRight() {
		Piece pawn = Bishop.white();
		assertEquals(true , pawn.isValid(Position.d4, Position.g1));
	}

	@Test
	void whiteBishopMovDownLeft() {
		Piece pawn = Bishop.white();
		assertEquals(true , pawn.isValid(Position.d4, Position.c3));
	}

}
