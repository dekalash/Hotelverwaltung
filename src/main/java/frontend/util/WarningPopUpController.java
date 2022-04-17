package frontend.util;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

    public class WarningPopUpController extends PopUpController{

        @FXML
        private Button buttonClose;

        @FXML
        private Button buttonNo;

        @FXML
        private HBox warningTopPane;

        @FXML
        private Button buttonYes;

        @FXML
        void closeWarningPopupX(ActionEvent event) {
             TopStageBar.close(event, buttonClose);
        }

        @FXML
        void closeWarningPopupNo(ActionEvent event) {
            TopStageBar.close(event, buttonNo);
        }

        @FXML
        void closeContainer(ActionEvent event) {
            Box.abortApplication();
        }

        @FXML
        void roomPopupMovementOnClick(MouseEvent event) {
            TopStageBar.handleClickAction(event, warningTopPane);
        }

        @FXML
        void roomPopupMovementOnDrag(MouseEvent event) {
            TopStageBar.handleMovementAction(event, warningTopPane);
        }

    }


