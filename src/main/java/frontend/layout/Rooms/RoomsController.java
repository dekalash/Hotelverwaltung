package frontend.layout.Rooms;

import frontend.data.Booking;
import frontend.data.IndicatorEnum;
import frontend.data.Room;
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
    public TableView<Room> tableView;

    @FXML
    private Button buttonCreateRoom;

    @FXML
    private TableColumn<Room, IndicatorEnum> columnIndicator;

    @FXML
    private TableColumn<Room, Long> columnRoomNumber;
    @FXML
    private TableColumn<Room, String> columnRoomType;
    @FXML
    private TableColumn<Room, String> columnPrice;
    @FXML
    private TableColumn<Room, String> columnOneBedRoom;
    @FXML
    private TableColumn<Room, String> columnTwoBedRoom;

    private static final String MENU_ITEM_MAINTENANCE = "Wartung an/aus";
    private static final String MENU_ITEM_EDIT = "bearbeiten";
    private static final String MENU_ITEM_DELETE = "löschen";

    private ContextMenuTable<Room> table;

    static ObservableList<Room> roomList;

    @FXML
    public void initialize() {
        roomList =  getRoomList1();
        table = new ContextMenuTable<Room>(tableView, () -> roomList);

        setUpCellFactories();
        setUpMenuItems();
        editCellFactories();
    }

    public static void addRoom(Room room) {
        roomList.add(room);
    }

   public  static  ObservableList<Room> getRoomList1() {
       //ObservableList<Booking> bookinglist = BookingController.getBookingList();
       ObservableList<Room> roomlist = BackendExceptionHandler.executeReturnNullIfException(() -> Room.fetchAll());

       

      // for (int i = bookinglist.size() - 1; i >= 0; i--) {
       //    for (int j = roomlist.size() - 1; j >= 0; j--){
       //       Booking booking = bookinglist.get(i);
       //       Room room = roomlist.get(j);
       //      if (booking.roomNr.get() == room.getRoomNumber()) {
        //         room.setIndicator(booking.getIndicator());
       //       }
       ///  }
//
       return roomlist;
    }

    /**
     * Sets the columns' cell factories.
     */
    private void setUpCellFactories() {
        columnIndicator.setCellValueFactory(new PropertyValueFactory<Room, IndicatorEnum>("Indicator"));
        columnRoomNumber.setCellValueFactory(new PropertyValueFactory<Room, Long>("RoomNumber"));
        columnRoomType.setCellValueFactory(new PropertyValueFactory<Room, String>("RoomType"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Room, String>("price"));
        columnOneBedRoom.setCellValueFactory(new PropertyValueFactory<Room, String>("OneBedRoom"));
        columnTwoBedRoom.setCellValueFactory(new PropertyValueFactory<Room, String>("TwoBedRoom"));
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

    private void editCellFactories(){
        tableView.setEditable(true);
        columnRoomNumber.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        columnRoomNumber.setOnEditCommit(event -> {
            Room room = event.getRowValue();
            room.setRoomNumber(event.getNewValue());
        });
        columnRoomType.setCellFactory(TextFieldTableCell.forTableColumn());
        columnRoomType.setOnEditCommit(event -> {
            Room room = event.getRowValue();
            room.setRoomType(event.getNewValue());
        });
        columnPrice.setCellFactory(TextFieldTableCell.forTableColumn());
        columnPrice.setOnEditCommit(event -> {
            Room room = event.getRowValue();
            room.setPrice(event.getNewValue());
        });
        columnOneBedRoom.setCellFactory(TextFieldTableCell.forTableColumn());
        columnOneBedRoom.setOnEditCommit(event -> {
            Room room = event.getRowValue();
            room.setOneBedRoom(event.getNewValue());
        });
        columnTwoBedRoom.setCellFactory(TextFieldTableCell.forTableColumn());
        columnTwoBedRoom.setOnEditCommit(event -> {
            Room room = event.getRowValue();
            room.setTwoBedRoom(event.getNewValue());
        });
    }

    /**
     * Initializes the context menu.
     */
    private void setUpMenuItems() {
        table.addAutoUpdatingMenuItem(MENU_ITEM_MAINTENANCE, room -> room.setIndicator(IndicatorEnum.Gewartet));
        table.addAutoUpdatingMenuItem(MENU_ITEM_DELETE, room -> {
            BackendExceptionHandler.execute( () -> tableView.getItems().removeAll(room));
        });
    }

    /**
     * Executed when right clicking the table view.
     */
    public void showContextMenu() {
        table.supressMenuIfNoSelection();
    }
}
