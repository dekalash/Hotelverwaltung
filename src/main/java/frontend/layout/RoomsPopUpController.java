package frontend.layout;

import frontend.data.Room;
import frontend.util.BackendException;
import frontend.util.BackendExceptionHandler;
import frontend.util.FxHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class RoomsPopUpController extends PopUpController {

    @FXML
    private TextField textFieldNames;

    @FXML
    private TextField textFieldFloor;

    @FXML
    private TextField textFieldRoomNumber;


    @FXML
    private Button buttonClose;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonMinimize;

    @FXML
    private HBox roomTopPane;

    @FXML
    private ComboBox<String> comboBoxRoomType;

    @FXML
    void closeRoomPopup(ActionEvent event) {
        TopStageBar.close(event, buttonClose);
    }

    @FXML
    void minimizeRoomPopup(ActionEvent event) {
        TopStageBar.minimize(event, buttonMinimize);

    }

    @FXML
    void roomPopupMovementOnClick(MouseEvent event) {
        TopStageBar.handleClickAction(event, roomTopPane);

    }

    @FXML
    void roomPopupMovementOnDrag(MouseEvent event) {
        TopStageBar.handleMovementAction(event, roomTopPane);

    }

    @FXML
    public void erstellen(ActionEvent event) {
        
        int floor = Integer.parseInt(textFieldFloor.getText());
        int roomNr = Integer.parseInt(textFieldRoomNumber.getText());
        String name = textFieldNames.getText();
        String roomType = comboBoxRoomType.getSelectionModel().getSelectedItem();
        Long tmp = 0L;
        if(roomType.equals("Standard Room")){
        tmp = 1L;
        } else if(roomType.equals("Luxury Room")){
            tmp = 2L;
        }else {
            tmp = 3L;  
        }
        try {
            Room.add(tmp, floor, roomNr, name);
        } catch (BackendException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
       
        try {

            closeRoomPopup(event);

        } catch (Exception e) {
            System.out.println(e);
            buttonAdd.setDisable(true);
            FxHelper.displayPopUp("Error_PopUp", () -> {
                buttonAdd.setDisable(false);
            });
        }
    }

    @FXML
    void selectRoomType(ActionEvent event) {
        comboBoxRoomType.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void initialize() {
       //fetch data from backend; if exception occurs: display warning and set combobox content to null (empty)
        ObservableList<String> roomTypeList = BackendExceptionHandler
                .executeReturnNullIfException(() -> FXCollections.observableArrayList(Room.fetchRoomTypes()));

        comboBoxRoomType.setItems(roomTypeList);

        assert buttonClose != null : "fx:id=\"close\" was not injected: check your FXML file 'Rooms_PopUp.fxml'.";
        assert textFieldNames != null : "fx:id=\"textFieldNames\" was not injected: check your FXML file 'Rooms_PopUp.fxml'.";
        assert textFieldFloor != null : "fx:id=\"Floor\" was not injected: check your FXML file 'Rooms_PopUp.fxml'.";
        assert buttonAdd != null : "fx:id=\"addButton\" was not injected: check your FXML file 'Rooms_PopUp.fxml'.";
        assert buttonMinimize != null : "fx:id=\"minimize\" was not injected: check your FXML file 'Rooms_PopUp.fxml'.";
        assert textFieldRoomNumber != null
                : "fx:id=\"roomNumber\" was not injected: check your FXML file 'Rooms_PopUp.fxml'.";
        assert roomTopPane != null : "fx:id=\"roomTopPane\" was not injected: check your FXML file 'Rooms_PopUp.fxml'.";
    }

}
