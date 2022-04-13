package chess4j.view;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Map;
import java.util.function.Function;

import chess4j.controller.Controller;
import chess4j.model.Piece;
import chess4j.model.Position;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainWindow extends Application {

    /**
     * Location of the used stylesheet
     */
    private final URL URL_SYTLE_SHEET = getClass().getResource("style.css");

    /**
     * GridPane contains all tile and the labels on the edge
     */
    private GridPane pane;

    /**
     * Controller connects the view with the underlying logic
     */
    private Controller controller;

    /**
     * Array contains all button that are used as tiles
     */
    private Button[][] buttons;

    private Function<Position, Position> transformation;

    @Override
    public void start(Stage primaryStage) throws Exception {
        controller = new Controller();
        buttons = new Button[8][8];
        transformation = p -> Position.valueOf(8 - p.getRow() + 1, p.getColumn());

        // Setup Pane and Scene
        pane = new GridPane();
        setupPane();
        updateBoard();
        primaryStage.setTitle("Chess for Java");
        primaryStage.setScene(new Scene(pane, 800, 800));
        primaryStage.getScene().getStylesheets().add(URL_SYTLE_SHEET.toExternalForm());
        primaryStage.show();
    }

    private void setupPane() throws FileNotFoundException {
        // Columns at top
        pane.add(createLabel("A"), 1, 0);
        pane.add(createLabel("B"), 2, 0);
        pane.add(createLabel("C"), 3, 0);
        pane.add(createLabel("D"), 4, 0);
        pane.add(createLabel("E"), 5, 0);
        pane.add(createLabel("F"), 6, 0);
        pane.add(createLabel("G"), 7, 0);
        pane.add(createLabel("H"), 8, 0);

        // Columns a bottom
        pane.add(createLabel("A"), 1, 9);
        pane.add(createLabel("B"), 2, 9);
        pane.add(createLabel("C"), 3, 9);
        pane.add(createLabel("D"), 4, 9);
        pane.add(createLabel("E"), 5, 9);
        pane.add(createLabel("F"), 6, 9);
        pane.add(createLabel("G"), 7, 9);
        pane.add(createLabel("H"), 8, 9);

        // Numbers left
        pane.add(createLabel("1"), 0, 8);
        pane.add(createLabel("2"), 0, 7);
        pane.add(createLabel("3"), 0, 6);
        pane.add(createLabel("4"), 0, 5);
        pane.add(createLabel("5"), 0, 4);
        pane.add(createLabel("6"), 0, 3);
        pane.add(createLabel("7"), 0, 2);
        pane.add(createLabel("8"), 0, 1);

        // Numbers right
        pane.add(createLabel("1"), 9, 8);
        pane.add(createLabel("2"), 9, 7);
        pane.add(createLabel("3"), 9, 6);
        pane.add(createLabel("4"), 9, 5);
        pane.add(createLabel("5"), 9, 4);
        pane.add(createLabel("6"), 9, 3);
        pane.add(createLabel("7"), 9, 2);
        pane.add(createLabel("8"), 9, 1);

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                Button b = createTile(i, j);
                pane.add(b, j, i);
                buttons[i - 1][j - 1] = b;
            }
        }
        for(Position position : Position.values()) {
            Button b = getTile(position);
            b.setOnAction(e -> {
                controller.processInput(position);
                updateBoard();
            });
        }
    }

    private Label createLabel(String text) {
        Label label = new Label();
        label.setText(text);
        label.setMaxSize(80, 80);
        label.setMinSize(80, 80);
        label.getStyleClass().add("board-label");
        return label;
    }

    private Button createTile(int i, int j) {
        Button b = new Button();
        if ((i + j) % 2 == 0) {
            b.getStyleClass().add("tile-black");
        } else {
            b.getStyleClass().add("tile-white");
        }
        b.setMaxSize(80, 80);
        b.setMinSize(80, 80);
        return b;
    }

    private void updateBoard() {
        Map<Position, Piece> board = controller.getView();
        for (Position pos : Position.values()) {
            Button b = getTile(pos);
            if(board.containsKey(pos)) {
                Piece piece = board.get(pos);
                Image image = new Image(getClass().getResource(getPieceIcon(piece)).toExternalForm(), 80, 80, true, true);
                ImageView view = new ImageView(image);
                b.setGraphic(view);
            } else {
                b.setGraphic(null);
            }
        }
    }

    private Button getTile(Position p) {
        Position tPos = transformation.apply(p);
        return buttons[tPos.getRow() - 1][tPos.getColumn() - 1];
    }

    private String getPieceIcon(Piece piece) {
        switch (piece.getColor()) {
        case BLACK:
            switch (piece.getType()) {
            case BISHOP:
                return "BlackBishop.png";
            case KING:
                return "BlackKing.png";
            case KNIGHT:
                return "BlackKnight.png";
            case PAWN:
                return "BlackPawn.png";
            case QUEEN:
                return "BlackQueen.png";
            case ROOK:
                return "BlackRook.png";
            default:
                throw new RuntimeException();
            }
        case WHITE:
            switch (piece.getType()) {
            case BISHOP:
                return "WhiteBishop.png";
            case KING:
                ;
                return "WhiteKing.png";
            case KNIGHT:
                ;
                return "WhiteKnight.png";
            case PAWN:
                ;
                return "WhitePawn.png";
            case QUEEN:
                ;
                return "WhiteQueen.png";
            case ROOK:
                ;
                return "WhiteRook.png";
            default:
                throw new RuntimeException();
            }
        default:
            throw new RuntimeException();
        }
    }
}
