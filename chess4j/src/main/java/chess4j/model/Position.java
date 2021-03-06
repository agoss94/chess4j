package chess4j.model;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public enum Position {

    a1(1, 1), a2(1, 2), a3(1, 3), a4(1, 4), a5(1, 5), a6(1, 6), a7(1, 7), a8(1, 8),

    b1(2, 1), b2(2, 2), b3(2, 3), b4(2, 4), b5(2, 5), b6(2, 6), b7(2, 7), b8(2, 8),

    c1(3, 1), c2(3, 2), c3(3, 3), c4(3, 4), c5(3, 5), c6(3, 6), c7(3, 7), c8(3, 8),

    d1(4, 1), d2(4, 2), d3(4, 3), d4(4, 4), d5(4, 5), d6(4, 6), d7(4, 7), d8(4, 8),

    e1(5, 1), e2(5, 2), e3(5, 3), e4(5, 4), e5(5, 5), e6(5, 6), e7(5, 7), e8(5, 8),

    f1(6, 1), f2(6, 2), f3(6, 3), f4(6, 4), f5(6, 5), f6(6, 6), f7(6, 7), f8(6, 8),

    g1(7, 1), g2(7, 2), g3(7, 3), g4(7, 4), g5(7, 5), g6(7, 6), g7(7, 7), g8(7, 8),

    h1(8, 1), h2(8, 2), h3(8, 3), h4(8, 4), h5(8, 5), h6(8, 6), h7(8, 7), h8(8, 8);

//----------------------------class fields and definitions----------------------------

    private final static Set<Position> ALL = EnumSet.allOf(Position.class);

    private final int row;

    private final int column;

    Position(int column, int row) {
        this.column = column;
        this.row = row;
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

    public static Set<Position> path(Position start, Position end) {
        int deltaColumn = end.getColumn() - start.getColumn();
        int deltaRow = end.getRow() - start.getRow();
        boolean isLine = Math.abs(deltaColumn) == Math.abs(deltaRow) || deltaColumn == 0 || deltaRow == 0;
        if (isLine) {
            int length = Math.max(Math.abs(deltaColumn), Math.abs(deltaRow));
            int dirColumn = Integer.signum(deltaColumn);
            int dirRow = Integer.signum(deltaRow);
            Set<Position> path = EnumSet.noneOf(Position.class);
            for (int i = 1; i < length; i++) {
                path.add(Position.valueOf(start.getRow() + i * dirRow, start.getColumn() + i * dirColumn));
            }
            return path;
        } else {
            return Collections.emptySet();
        }
    }
}
