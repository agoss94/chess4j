package chess4j.model;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class EnumMapBoard extends AbstractMap<Position, Piece> implements Board {

	private final Map<Position, Piece> board;

	public EnumMapBoard() {
		board = new EnumMap<>(Position.class);
	}

	@Override
	public Piece put(Position key, Piece value) {
		Objects.requireNonNull(key);
		return board.put(key, value);
	}

	@Override
	public Move move(Position start, Position end) throws IllegalMoveException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Move> moves() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Position> path(Position start, Position end) {
		int deltaColumn = end.getColumn() - start.getColumn();
		int deltaRow = end.getRow() - start.getRow();
		boolean isLine = Math.abs(deltaColumn) == Math.abs(deltaRow) || deltaColumn == 0 || deltaRow == 0;
		if (isLine) {
			int length = Math.max(Math.abs(deltaColumn), Math.abs(deltaRow));
			int dirColumn = Integer.signum(deltaColumn);
			int dirRow = Integer.signum(deltaRow);
			Set<Position> path = EnumSet.noneOf(Position.class);
			for (int i = 0; i < length; i++) {
				path.add(Position.valueOf(start.getRow() + i * dirRow, start.getColumn() + i * dirColumn));
			}
			return path;
		} else {
			return Collections.emptySet();
		}
	}

	@Override
	public Set<Entry<Position, Piece>> entrySet() {
		return board.entrySet();
	}
}
