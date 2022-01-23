package chess4j.model;

import java.util.EnumSet;
import java.util.Set;

public enum Position {

	h1(1, 1), h2(1, 2), h3(1, 3), h4(1, 4), h5(1, 5), h6(1, 6), h7(1, 7), h8(1, 8),

	g1(2, 1), g2(2, 2), g3(2, 3), g4(2, 4), g5(2, 5), g6(2, 6), g7(2, 7), g8(2, 8),

	f1(3, 1), f2(3, 2), f3(3, 3), f4(3, 4), f5(3, 5), f6(3, 6), f7(3, 7), f8(3, 8),

	e1(4, 1), e2(4, 2), e3(4, 3), e4(4, 4), e5(4, 5), e6(4, 6), e7(4, 7), e8(4, 8),

	d1(5, 1), d2(5, 2), d3(5, 3), d4(5, 4), d5(5, 5), d6(5, 6), d7(5, 7), d8(5, 8),

	c1(6, 1), c2(6, 2), c3(6, 3), c4(6, 4), c5(6, 5), c6(6, 6), c7(6, 7), c8(6, 8),

	b1(7, 1), b2(7, 2), b3(7, 3), b4(7, 4), b5(7, 5), b6(7, 6), b7(7, 7), b8(7, 8),

	a1(8, 1), a2(8, 2), a3(8, 3), a4(8, 4), a5(8, 5), a6(8, 6), a7(8, 7), a8(8, 8);

//----------------------------class fields and definitions----------------------------

	private final static Set<Position> ALL = EnumSet.allOf(Position.class);

	private final int row;

	private final int column;

	Position(int i, int j) {
		row = i;
		column = j;
	}

	public static Position valueOf(int row, int column) {
		return ALL.stream().filter(p -> p.row == row).filter(p -> p.column == column).findFirst().get();
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
}
