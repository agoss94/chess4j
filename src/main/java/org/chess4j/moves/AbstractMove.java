package org.chess4j.moves;

import org.chess4j.Board;
import org.chess4j.Boards;
import org.chess4j.Move;
import org.chess4j.Tile;
import org.chess4j.simple.EnumMapBoard;

public class AbstractMove implements Move {

    /**
     * The start position of the move
     */
    private final Tile start;

    /**
     * The end position of the move
     */
    private final Tile end;

    /**
     * The former board position.
     */
    private final Board initial;

    /**
     * The later board position.
     */
    private final Board result;

    /**
     * Creates a new move, where the piece on start is removed and put on
     * start.
     *
     * @param start   the start tile of the move.
     * @param end     the end tile.
     * @param initial the initial position of the board.
     */
    protected AbstractMove(Tile start, Tile end, Board initial) {
        this.start = start;
        this.end = end;
        this.initial = Boards.copy(initial);
        Board result = new EnumMapBoard(initial);
        result.put(end, result.remove(start));
        this.result = Boards.unmodifiable(result);
    }

    @Override
    public Tile start() {
        return start;
    }

    @Override
    public Tile end() {
        return end;
    }

    @Override
    public Board initial() {
        return initial;
    }

    @Override
    public Board result() {
        return result;
    }
}
