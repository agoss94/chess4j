package chess4j.test.application;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import chess4j.view.GraphicalUserInterface;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
class StartApplicationTest {
	
	private GraphicalUserInterface ui;

	@Start
	private void start(Stage stage) throws Exception {
	    ui = new GraphicalUserInterface();
		ui.start(stage);
	}

	@Test
	void test(FxRobot robot) {
		assertNotNull(robot.listWindows());
	}

}
