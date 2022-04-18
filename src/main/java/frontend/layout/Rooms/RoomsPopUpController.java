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
            int floorAsInt = Integer.parseInt(textFieldFloor.getText());
            int roomIdAsInt = Integer.parseInt(textFieldRoomNumber.getText());
            double price = Double.parseDouble(this.textFieldPrice.getText());
            
            stmt.setString(1, "Frei");
            stmt.setString(2, fourdigits(floorAsInt, roomIdAsInt));
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

    public String fourdigits(int floor, int roomId){
        String roomNr = "";

        if (floor < 10 && roomId >= 10) {
            roomNr = "0" + textFieldFloor.getText() + textFieldRoomNumber.getText(); 
            return roomNr;
        } else if (floor >= 10 && roomId < 10) {
            roomNr =  textFieldFloor.getText() + "0" + textFieldRoomNumber.getText(); 
            return roomNr;
        } else if (floor < 10 && roomId < 10) {
            roomNr = "0" + textFieldFloor.getText() + "0" + textFieldRoomNumber.getText(); 
            return roomNr;
        } else {
            roomNr = textFieldFloor.getText() + textFieldRoomNumber.getText();
            return roomNr;
        }
    }

    // @FXML
    // void selectRoomType(ActionEvent event) {
    //     comboBoxRoomType.getSelectionModel().getSelectedItem().toString();
    // }

    @FXML
    void initialize() {
        //comboBoxRoomType.setItems(roomTypeList);


    }

}
