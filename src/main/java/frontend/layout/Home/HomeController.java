package frontend.layout.Home;

import frontend.data.Booking;
import frontend.data.IndicatorEnum;
import frontend.layout.Booking.BookingController;
import frontend.layout.Rooms.RoomsController;
import frontend.util.MainController;
import frontend.util.Table;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeController extends MainController{

    public static ObservableList<Booking> bookingListActual;

    public static ObservableList<Booking> bookingListPending;

    @FXML
    private Text textBookedRooms;

    @FXML
    private Text textFreeRooms;

    @FXML
    private TableView<Booking> tableViewPendingRooms;

    @FXML
    private TableView<Booking> tableViewActualRooms;

    @FXML
    private TableColumn<Booking, String> columnPendingRoomNr;

    @FXML
    public TableColumn<Booking,String> columnPendingRoomName;

    @FXML
    public TableColumn<Booking, String> columnPendingRoomLastName;

    @FXML
    private TableColumn<Booking, String> columnActualRoomNr;

    @FXML
    private TableColumn<Booking, String> columnActualRoomName;

    @FXML
    public TableColumn<Booking, String> columnActualRoomLastName;

    Table<Booking> tableActuel;
    Table<Booking> tablePending;


    private void getBookedRoomsNumber(){
       long bookedRoomsNumber = RoomsController.getRoomList1().stream().filter(room -> room.getIndicator() == IndicatorEnum.Gebucht).count();
       String bookedRoomsNumberString = String.valueOf(bookedRoomsNumber);
        if(bookedRoomsNumberString.length() != 1){
            textBookedRooms.setText(bookedRoomsNumberString);
        }else{
            String string = "0" + bookedRoomsNumberString;
            textBookedRooms.setText(string);
        }
    }

    private void getFreeRoomsNumber(){
        long freeRoomsNumber = RoomsController.getRoomList1().stream().filter(room -> room.getIndicator() == IndicatorEnum.Frei).count();
        String freeRoomsNumberString = String.valueOf(freeRoomsNumber);
        if(freeRoomsNumberString.length() != 1){
            textFreeRooms.setText(freeRoomsNumberString);
        }else{
            String string = "0" + freeRoomsNumberString;
            textFreeRooms.setText(string);
        }
    }

    private ObservableList<Booking> getBookedRoomsActual(){
        ObservableList<Booking> bookinglist = BookingController.getBookingListActualy();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Today = dtf.format(LocalDateTime.now());

        //ObservableList<Booking>  bookinglistActual = bookinglist.filtered(boonking -> boonking.endDate.get().equals(Today));
        ObservableList<Booking>  bookinglistActual = bookinglist.filtered(boonking -> boonking.startDate.get().equals(Today));
        return bookinglistActual;
    }

    private ObservableList<Booking> getBookedRoomsPending(){
        ObservableList<Booking> bookinglist = BookingController.getBookingList();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String Today = dtf.format(LocalDateTime.now());

       // ObservableList<Booking>  bookinglistPending = bookinglist.filtered(boonking -> boonking.startDate.get().equals(Today));
        ObservableList<Booking>  bookinglistPending = bookinglist.filtered(boonking -> boonking.endDate.get().equals(Today));
        return bookinglistPending;
    }

    @FXML
    void initialize() {
       getFreeRoomsNumber();
       getBookedRoomsNumber();


        
        //weil eine ungültige reservierung in der datenbank sich befindet, funktioniert reservierung nicht
        

        //tableActuel = new Table<Booking>(tableViewActualRooms, () -> getBookedRoomsActual());
        //tablePending = new Table<Booking>(tableViewPendingRooms, () -> getBookedRoomsPending());

        setUpCellFactories();

        assert textBookedRooms != null : "fx:id=\"bookedRooms\" was not injected: check your FXML file 'Home.fxml'.";
        assert textFreeRooms != null : "fx:id=\"freeRooms\" was not injected: check your FXML file 'Home.fxml'.";
    }

    /**
     * Sets the columns' cell factories.
     */
    public void setUpCellFactories() {
        columnActualRoomNr.setCellValueFactory(new PropertyValueFactory<Booking, String>("roomNr"));
        columnActualRoomName.setCellValueFactory(new PropertyValueFactory<Booking, String>("firstName"));
        columnActualRoomLastName.setCellValueFactory(new PropertyValueFactory<Booking,String>("lastName"));
        columnPendingRoomNr.setCellValueFactory(new PropertyValueFactory<Booking, String>("roomNr"));
        columnPendingRoomName.setCellValueFactory(new PropertyValueFactory<Booking, String>("firstName"));
        columnPendingRoomLastName.setCellValueFactory(new PropertyValueFactory<Booking,String>("lastName"));
    }
}
