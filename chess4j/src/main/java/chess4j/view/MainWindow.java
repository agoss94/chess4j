package chess4j.view;

import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainWindow extends Application {
	
	private URL URL_SYTLE_SHEET = getClass().getResource("style.css");
	
	private BorderPane pane;
	private BoardCenter center;
	private BoardSide sideRight;
	private BoardSide sideLeft;
	private BoardBottom bottom;
	private BoardBottom top;

	@Override
	public void start(Stage primaryStage) throws Exception {
		pane = new BorderPane();
	    center = new BoardCenter();
	    sideLeft = new BoardSide();
	    sideRight = new BoardSide();
	    bottom = new BoardBottom();
	    top = new BoardBottom();
		
		primaryStage.setTitle("Chess for Java");
		pane.setCenter(center);
		pane.setLeft(sideLeft);
		pane.setRight(sideRight);
		pane.setBottom(bottom);
		pane.setTop(top);
		primaryStage.setScene(new Scene(pane, 1000, 1000));
		primaryStage.getScene().getStylesheets().add(URL_SYTLE_SHEET.toExternalForm());
		primaryStage.show();
	}
}
