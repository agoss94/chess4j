package chess4j.test.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import chess4j.view.MainWindow;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
class StartApplicationTest {
	
	private MainWindow ui;

	@Start
	private void start(Stage stage) throws Exception {
	    ui = new MainWindow();
		ui.start(stage);
	}

	@Test
	void test(FxRobot robot) {
		assertEquals("Chess for Java", robot.listWindows());
	}

}
