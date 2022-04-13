package chess4j.controller;

import java.util.Collections;
import java.util.Map;

import chess4j.model.Bishop;
import chess4j.model.Board;
import chess4j.model.Color;
import chess4j.model.EnumMapBoard;
import chess4j.model.IllegalMoveException;
import chess4j.model.King;
import chess4j.model.Knight;
import chess4j.model.Pawn;
import chess4j.model.Piece;
import chess4j.model.Position;
import chess4j.model.Queen;
import chess4j.model.Rook;

public class Controller {

    private final Board board;

    private Position input;

    private Color playersTurn;

    public Controller() {
        board = new EnumMapBoard();
        newGame();
        playersTurn = Color.WHITE;
    }

    public void newGame() {
        board.clear();

        //Put white Pieces on Board
        //First Row
        board.put(Position.a1, Rook.white());
        board.put(Position.b1, Knight.white());
        board.put(Position.c1, Bishop.white());
        board.put(Position.d1, Queen.white());
        board.put(Position.e1, King.white());
        board.put(Position.f1, Bishop.white());
        board.put(Position.g1, Knight.white());
        board.put(Position.h1, Rook.white());

        //Second Row of Pawns
        board.put(Position.a2, Pawn.white());
        board.put(Position.b2, Pawn.white());
        board.put(Position.c2, Pawn.white());
        board.put(Position.d2, Pawn.white());
        board.put(Position.e2, Pawn.white());
        board.put(Position.f2, Pawn.white());
        board.put(Position.g2, Pawn.white());
        board.put(Position.h2, Pawn.white());

        //Put black Pieces on Board
        //First Row
        board.put(Position.a8, Rook.black());
        board.put(Position.b8, Knight.black());
        board.put(Position.c8, Bishop.black());
        board.put(Position.d8, Queen.black());
        board.put(Position.e8, King.black());
        board.put(Position.f8, Bishop.black());
        board.put(Position.g8, Knight.black());
        board.put(Position.h8, Rook.black());

        //Second Row of Pawns
        board.put(Position.a7, Pawn.black());
        board.put(Position.b7, Pawn.black());
        board.put(Position.c7, Pawn.black());
        board.put(Position.d7, Pawn.black());
        board.put(Position.e7, Pawn.black());
        board.put(Position.f7, Pawn.black());
        board.put(Position.g7, Pawn.black());
        board.put(Position.h7, Pawn.black());

        playersTurn = Color.WHITE;
    }

    public void processInput(Position p) {
        if(input == null) {
            Piece piece = board.get(p);
            if(piece != null && piece.getColor() == playersTurn) {
                input = p;
            }
        } else {
            try {
                board.move(input, p);
                playersTurn = playersTurn.switchColor();
            } catch (IllegalMoveException e) {
                System.err.println("Illegal input");
            }
            input = null;
        }
    }

    public Map<Position, Piece> getView() {
        return Collections.unmodifiableMap(board);
    }
}
