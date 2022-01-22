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
	private BoardRoof top;
	private BoardRoof bottom;

	@Override
	public void start(Stage primaryStage) throws Exception {
		pane = new BorderPane();
	    center = new BoardCenter();
	    sideLeft = new BoardSide();
	    sideRight = new BoardSide();
	    top = new BoardRoof();
	    bottom = new BoardRoof();
		
		primaryStage.setTitle("Chess for Java");
		pane.setCenter(center);
		pane.setLeft(sideLeft);
		pane.setRight(sideRight);
		pane.setTop(top);
		pane.setBottom(bottom);
		primaryStage.setScene(new Scene(pane, 1200, 1200));
		primaryStage.getScene().getStylesheets().add(URL_SYTLE_SHEET.toExternalForm());
		primaryStage.show();
	}
}
