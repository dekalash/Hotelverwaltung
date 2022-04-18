package frontend.layout.Rooms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import frontend.data.Room;
import frontend.dbUtil.dbConnection;
import frontend.util.BackendException;
import frontend.util.BackendExceptionHandler;
import frontend.util.FxHelper;
import frontend.util.PopUpController;
import frontend.util.TopStageBar;
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
    private TextField textFieldRoomType;
    @FXML
    private TextField textFieldPrice;
    @FXML
    private TextField textFieldSingleBeds;
    @FXML
    private TextField textFieldDoubleBeds;
    @FXML
    private Button buttonClose;

    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonMinimize;

    @FXML
    private HBox roomTopPane;

    //@FXML
    //private ComboBox<String> comboBoxRoomType;

    private String sqlAddRooms = "INSERT INTO rooms(status, roomNr, floor, roomId, roomType, price, singleBeds, doubleBeds) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
    public void createRoom(ActionEvent event) {
        
        try {
            Connection conn = dbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlAddRooms);
            String roomnr = ""; 
            int tmp = Integer.parseInt(textFieldFloor.getText());
            if (tmp < 10) {
                roomnr = "0" + textFieldFloor.getText() + textFieldRoomNumber.getText(); 
            } else {
                roomnr = textFieldFloor.getText() + textFieldRoomNumber.getText();
            }
            double price = Double.parseDouble(this.textFieldPrice.getText());
            
            stmt.setString(1, "Frei");
            stmt.setString(2, roomnr);
            stmt.setString(3, this.textFieldFloor.getText());
            stmt.setString(4, this.textFieldRoomNumber.getText());
            stmt.setString(5, this.textFieldRoomType.getText());
            stmt.setDouble(6, price);
            stmt.setString(7, this.textFieldSingleBeds.getText());
            stmt.setString(8, this.textFieldDoubleBeds.getText());
            
            stmt.execute();
            conn.close();
    } catch (SQLException e) {
            e.printStackTrace();
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

    // @FXML
    // void selectRoomType(ActionEvent event) {
    //     comboBoxRoomType.getSelectionModel().getSelectedItem().toString();
    // }

    @FXML
    void initialize() {
        //comboBoxRoomType.setItems(roomTypeList);

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
