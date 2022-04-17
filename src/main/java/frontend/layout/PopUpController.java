package frontend.layout;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controllers controlling popups should extend this class.
 * <br><br>
 * This class stores references to the popups stage and scene.
 * The references are automatically injected through FxHelper when calling {@link FxHelper#displayPopUp(String, String, Action)}.
 * <br><br>
 * By using these references controllers extending this class can f.e.x close their own window.
 */
public abstract class PopUpController {
    private Stage stage;
    private Scene scene;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene() {
        return scene;
    }

}
