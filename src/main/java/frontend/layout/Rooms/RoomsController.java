package frontend.layout.Rooms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import frontend.data.Booking;
import frontend.data.IndicatorEnum;
import frontend.data.Room;
import frontend.data.RoomsData;
import frontend.dbUtil.dbConnection;
import frontend.util.BackendExceptionHandler;
import frontend.util.ContextMenuTable;
import frontend.util.FxHelper;
import frontend.util.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LongStringConverter;

public class RoomsController extends MainController {

    //TODO test initialisation
    @FXML
    public TableView<RoomsData> tableViewRooms;

    @FXML
    private Button buttonCreateRoom;

    @FXML
    private Button buttonReloadRooms;

    @FXML
    private TableColumn<RoomsData, String> columnIndicator;

    @FXML
    private TableColumn<RoomsData, String> columnRoomNumber;
    @FXML
    private TableColumn<RoomsData, String> columnRoomType;
    @FXML
    private TableColumn<RoomsData, Double> columnPrice;
    @FXML
    private TableColumn<RoomsData, String> columnOneBedRoom;
    @FXML
    private TableColumn<RoomsData, String> columnTwoBedRoom;

    private static final String MENU_ITEM_MAINTENANCE = "Wartung an/aus";
    private static final String MENU_ITEM_EDIT = "bearbeiten";
    private static final String MENU_ITEM_DELETE = "löschen";
    
    private String sqlloadRoomsData = "SELECT * FROM rooms";
    private ContextMenuTable<RoomsData> table;

    private dbConnection dc;

    static ObservableList<RoomsData> roomList;
    /**
     * Sets the columns' cell factories.
     */
    private void setUpCellFactories() {
        columnIndicator.setCellValueFactory(new PropertyValueFactory<RoomsData, String>("status"));
        columnRoomNumber.setCellValueFactory(new PropertyValueFactory<RoomsData, String>("roomNr"));
        columnRoomType.setCellValueFactory(new PropertyValueFactory<RoomsData, String>("roomType"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<RoomsData, Double>("price"));
        columnOneBedRoom.setCellValueFactory(new PropertyValueFactory<RoomsData, String>("singleBeds"));
        columnTwoBedRoom.setCellValueFactory(new PropertyValueFactory<RoomsData, String>("doubleBeds"));
        this.tableViewRooms.setItems(null);
        this.tableViewRooms.setItems(roomList);
    }

    /**
     * Executed when the button for creating a new room is hit.
     */
    public void createRoom() {
        buttonCreateRoom.setDisable(true);
        this.disableAllButtons();

        FxHelper.displayPopUp("Rooms_PopUp", "Reservierungs_Style", () -> {
            buttonCreateRoom.setDisable(false);
            this.reenableButtons();
        });
    }

    // private void editCellFactories(){
    //     tableView.setEditable(true);
    //     columnRoomNumber.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
    //     columnRoomNumber.setOnEditCommit(event -> {
    //         Room room = event.getRowValue();
    //         room.setRoomNumber(event.getNewValue());
    //     });
    //     columnRoomType.setCellFactory(TextFieldTableCell.forTableColumn());
    //     columnRoomType.setOnEditCommit(event -> {
    //         Room room = event.getRowValue();
    //         room.setRoomType(event.getNewValue());
    //     });
    //     columnPrice.setCellFactory(TextFieldTableCell.forTableColumn());
    //     columnPrice.setOnEditCommit(event -> {
    //         Room room = event.getRowValue();
    //         room.setPrice(event.getNewValue());
    //     });
    //     columnOneBedRoom.setCellFactory(TextFieldTableCell.forTableColumn());
    //     columnOneBedRoom.setOnEditCommit(event -> {
    //         Room room = event.getRowValue();
    //         room.setOneBedRoom(event.getNewValue());
    //     });
    //     columnTwoBedRoom.setCellFactory(TextFieldTableCell.forTableColumn());
    //     columnTwoBedRoom.setOnEditCommit(event -> {
    //         Room room = event.getRowValue();
    //         room.setTwoBedRoom(event.getNewValue());
    //     });
    // }

    /**
     * Initializes the context menu.
     */
    //  private void setUpMenuItems() {
    //      table.addAutoUpdatingMenuItem(MENU_ITEM_MAINTENANCE, room -> room.setIndicator(IndicatorEnum.Gewartet));
    //      table.addAutoUpdatingMenuItem(MENU_ITEM_DELETE, room -> {
    //         BackendExceptionHandler.execute( () -> tableView.getItems().removeAll(room));
    //      });
    //  }

    public void loadRoomsData(){

        try {
            
            Connection conn = dbConnection.getConnection();
            this.roomList = FXCollections.observableArrayList();

            ResultSet rs = conn.createStatement().executeQuery(sqlloadRoomsData);
            while (rs.next()) {
                this.roomList.add(new RoomsData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getString(7), rs.getString(8)));
            }
        
        
        
        } catch (SQLException e) {
            System.err.println("Error " + e);
        }
        setUpCellFactories();
    }
    /**
     * Executed when right clicking the table view.
     */
    public void showContextMenu() {
        table.supressMenuIfNoSelection();
    }
    
    @FXML
    public void initialize() {
        this.dc = new dbConnection();
        loadRoomsData();
        //table = new ContextMenuTable<RoomsData>(tableView, () -> roomList);
        //setUpMenuItems();
        //editCellFactories();
    }

}
