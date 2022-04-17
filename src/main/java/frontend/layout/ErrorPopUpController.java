package frontend.layout;

import frontend.util.PopUpController;
import frontend.util.TopStageBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class ErrorPopUpController extends PopUpController {

    @FXML
    private Button buttonClose;

    @FXML
    private Button buttonOk;

    @FXML
    private HBox errorTopPane;

    @FXML
    void closeErrorPopupX(ActionEvent event) {
        TopStageBar.close(event, buttonClose);
    }

    @FXML
    void closeErrorPopupOk(ActionEvent event) {
        TopStageBar.close(event, buttonOk);
    }

    @FXML
    void errorPopupMovementOnClick(MouseEvent event) {
        TopStageBar.handleClickAction(event, errorTopPane);
    }

    @FXML
    void errorPopupMovementOnDrag(MouseEvent event) {
        TopStageBar.handleMovementAction(event, errorTopPane);
    }

    @FXML
    void initialize() {

        assert buttonClose != null
                : "fx:id=\"close\" was not injected: check your FXML file 'Error_PopUp.fxml'.";
        assert buttonOk != null
                : "fx:id=\"ok\" was not injected: check your FXML file 'Error_PopUp.fxml'.";
        assert errorTopPane != null
                : "fx:id=\"errorTopPane\" was not injected: check your FXML file 'Error_PopUp.fxml'.";

    }
}
