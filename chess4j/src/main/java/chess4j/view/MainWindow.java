package chess4j.view;

import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainWindow extends Application {

    private URL URL_SYTLE_SHEET = getClass().getResource("style.css");

    private BorderPane pane;
    private BoardCenter center;

    @Override
    public void start(Stage primaryStage) throws Exception {
        pane = new BorderPane();
        center = new BoardCenter();

        primaryStage.setTitle("Chess for Java");
        pane.setCenter(center);
        primaryStage.setScene(new Scene(pane, 800, 800));
        primaryStage.getScene().getStylesheets().add(URL_SYTLE_SHEET.toExternalForm());
        primaryStage.show();
    }

    private static class BoardCenter extends GridPane {

        public BoardCenter() {
            // Columns at top
            add(new BoardLabel("A"), 1, 0);
            add(new BoardLabel("B"), 2, 0);
            add(new BoardLabel("C"), 3, 0);
            add(new BoardLabel("D"), 4, 0);
            add(new BoardLabel("E"), 5, 0);
            add(new BoardLabel("F"), 6, 0);
            add(new BoardLabel("G"), 7, 0);
            add(new BoardLabel("H"), 8, 0);

            // Columns a bottom
            add(new BoardLabel("A"), 1, 9);
            add(new BoardLabel("B"), 2, 9);
            add(new BoardLabel("C"), 3, 9);
            add(new BoardLabel("D"), 4, 9);
            add(new BoardLabel("E"), 5, 9);
            add(new BoardLabel("F"), 6, 9);
            add(new BoardLabel("G"), 7, 9);
            add(new BoardLabel("H"), 8, 9);

            // Numbers left
            add(new BoardLabel(String.valueOf(1)), 0, 8);
            add(new BoardLabel(String.valueOf(2)), 0, 7);
            add(new BoardLabel(String.valueOf(3)), 0, 6);
            add(new BoardLabel(String.valueOf(4)), 0, 5);
            add(new BoardLabel(String.valueOf(5)), 0, 4);
            add(new BoardLabel(String.valueOf(6)), 0, 3);
            add(new BoardLabel(String.valueOf(7)), 0, 2);
            add(new BoardLabel(String.valueOf(8)), 0, 1);

            // Numbers right
            add(new BoardLabel(String.valueOf(1)), 9, 8);
            add(new BoardLabel(String.valueOf(2)), 9, 7);
            add(new BoardLabel(String.valueOf(3)), 9, 6);
            add(new BoardLabel(String.valueOf(4)), 9, 5);
            add(new BoardLabel(String.valueOf(5)), 9, 4);
            add(new BoardLabel(String.valueOf(6)), 9, 3);
            add(new BoardLabel(String.valueOf(7)), 9, 2);
            add(new BoardLabel(String.valueOf(8)), 9, 1);

            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    add(new Tile(i, j), i, j);
                }
            }
        }
    }

    private static class Tile extends Button {


        public Tile(int i, int j) {
            if ((i + j) % 2 == 0) {
                getStyleClass().add("tile-black");
            } else {
                getStyleClass().add("tile-white");
            }
            setMaxSize(80, 80);
            setMinSize(80, 80);
        }
    }

    private static class BoardLabel extends Label {

        public BoardLabel(String text) {
            setText(text);
            setMaxSize(80, 80);
            setMinSize(80, 80);

            getStyleClass().add("board-label");
        }
    }
}
