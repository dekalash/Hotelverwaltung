package frontend.util;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class TopStageBar {
    private static double xOffset = 0, yOffset = 0;

    public static void close(ActionEvent event, Button close) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public static void minimize(ActionEvent event, Button minimize) {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    public static void handleClickAction(MouseEvent event, HBox topPane) {
        Stage stage = (Stage) topPane.getScene().getWindow();
        xOffset = stage.getX() - event.getScreenX();
        yOffset = stage.getY() - event.getScreenY();
    }

    public static void handleMovementAction(MouseEvent event, HBox topPane) {
        Stage stage = (Stage) topPane.getScene().getWindow();
        stage.setX(event.getScreenX() + xOffset);
        stage.setY(event.getScreenY() + yOffset);
    }

    private void getStage(Label label) {//try Node
        Stage stage = (Stage) label.getScene().getWindow();
    }
}
