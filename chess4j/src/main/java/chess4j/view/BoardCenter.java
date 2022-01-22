package chess4j.view;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardCenter extends GridPane {

	public BoardCenter() {
		// Columns at top
		add(new Label("A"), 1, 0);
		add(new Label("B"), 2, 0);
		add(new Label("C"), 3, 0);
		add(new Label("D"), 4, 0);
		add(new Label("E"), 5, 0);
		add(new Label("F"), 6, 0);
		add(new Label("G"), 7, 0);
		add(new Label("H"), 8, 0);

		// Columns a bottom
		add(new Label("A"), 1, 9);
		add(new Label("B"), 2, 9);
		add(new Label("C"), 3, 9);
		add(new Label("D"), 4, 9);
		add(new Label("E"), 5, 9);
		add(new Label("F"), 6, 9);
		add(new Label("G"), 7, 9);
		add(new Label("H"), 8, 9);

		// Numbers left
		add(new Label(String.valueOf(1)), 0, 8);
		add(new Label(String.valueOf(2)), 0, 7);
		add(new Label(String.valueOf(3)), 0, 6);
		add(new Label(String.valueOf(4)), 0, 5);
		add(new Label(String.valueOf(5)), 0, 4);
		add(new Label(String.valueOf(6)), 0, 3);
		add(new Label(String.valueOf(7)), 0, 2);
		add(new Label(String.valueOf(8)), 0, 1);

		// Numbers right
		add(new Label(String.valueOf(1)), 9, 8);
		add(new Label(String.valueOf(2)), 9, 7);
		add(new Label(String.valueOf(3)), 9, 6);
		add(new Label(String.valueOf(4)), 9, 5);
		add(new Label(String.valueOf(5)), 9, 4);
		add(new Label(String.valueOf(6)), 9, 3);
		add(new Label(String.valueOf(7)), 9, 2);
		add(new Label(String.valueOf(8)), 9, 1);

		for (int i = 1; i < 9; i++) {
			for(int j = 1; j<9; j++) {
				add(new Tile(), i, j);
			}
		}
	}
	
	private static class Tile extends Button {
		
		public Tile() {
			setMaxSize(100, 100);
			setMinSize(100, 100);
		}
	}

	private static class Label extends javafx.scene.control.Label {

		public Label(String text) {
			setText(text);
			setMaxSize(100, 100);
			setMinSize(100, 100);
		}
	}
}
