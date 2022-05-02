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
//TODO: Delete price from popup
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

    @FXML
    private ComboBox<String> comboBoxRoomType;
    RoomTypeENUM roomType;
    public enum RoomTypeENUM {Bilo, Normal, Krass}
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
            String roomType = comboBoxRoomType.getSelectionModel().getSelectedItem().toString();
            stmt.setString(1, "Frei");
            stmt.setString(2, fourdigits(floorAsInt, roomIdAsInt));
            stmt.setString(3, this.textFieldFloor.getText());
            stmt.setString(4, this.textFieldRoomNumber.getText());
            stmt.setString(5, roomType);
            stmt.setDouble(6, setPrice(roomType));
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

    public double setPrice(String roomType){
        double price = 0.0;
        if (roomType.equals("Bilo")) {
            price = 100.00;
            return price;
        } else if (roomType.equals("Normal")) {
            price = 250.00; 
            return price;
        } else {
            price = 10000.00;
            return price;
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
    void setupComboBox(){
        ObservableList<String> roomTypeList = FXCollections.observableArrayList();
        roomTypeList.add(RoomTypeENUM.Bilo.toString());
        roomTypeList.add(RoomTypeENUM.Normal.toString());
        roomTypeList.add(RoomTypeENUM.Krass.toString());
        comboBoxRoomType.setItems(roomTypeList);
    }
    @FXML
    void initialize() {
        setupComboBox();


    }

}
