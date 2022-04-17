package frontend.layout.Booking;


import frontend.data.Booking;
import frontend.data.IndicatorEnum;
import frontend.util.BackendExceptionHandler;
import frontend.util.ContextMenuTable;
import frontend.util.FxHelper;
import frontend.util.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.LongStringConverter;


//weil eine ungültige reservierung in der datenbank sich befindet, funktioniert reservierung nicht
public class BookingController extends MainController {

    // Booking Main Sceen

    @FXML
    public TableColumn<Booking, IndicatorEnum> collumBookingStatus;

    @FXML
    public Button buttonCreateBooking;

    @FXML
    public TableView<Booking> tableViewBooking;

    @FXML
    public TableColumn<Booking, String> columnEndDate;

    @FXML
    public TableColumn<Booking,String> columnLastName;

    @FXML
    public TableColumn<Booking, String> columnFirstName;

    @FXML
    public TableColumn<Booking, Long> columnRoomNumber;

    @FXML
    public TableColumn<Booking, String> columnRoomType;

    @FXML
    public TableColumn<Booking, String> columnStartDate;

    
    private ContextMenuTable<Booking> table;

    static ObservableList<Booking> bookingList;

    @FXML
    void openBookingsPopUp(ActionEvent event) {
        buttonCreateBooking.setDisable(true);
        disableAllButtons();

        FxHelper.displayPopUp("Booking_PopUp", () -> {
            buttonCreateBooking.setDisable(false);
            reenableButtons();
        });
    }

    @FXML
    void initialize() {
        bookingList = BackendExceptionHandler.executeReturnNullIfException(() -> Booking.fetchAll());
        table = new ContextMenuTable<Booking>(tableViewBooking, () -> bookingList);

        setUpCellFactories();
        setUpMenuItems();
        editCellFactories();
    }

    /**
     * Sets the columns' cell factories.
     */
    public void setUpCellFactories() {
        collumBookingStatus.setCellValueFactory(new PropertyValueFactory<Booking, IndicatorEnum>("Indicator"));
        columnRoomNumber.setCellValueFactory(new PropertyValueFactory<Booking, Long>("roomNr"));
        columnRoomType.setCellValueFactory(new PropertyValueFactory<Booking, String>("roomType"));
        columnFirstName.setCellValueFactory(new PropertyValueFactory<Booking, String>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<Booking, String>("lastName"));
        columnStartDate.setCellValueFactory(new PropertyValueFactory<Booking, String>("startDate"));
        columnEndDate.setCellValueFactory(new PropertyValueFactory<Booking, String>("endDate"));
    }

    private void editCellFactories(){
        tableViewBooking.setEditable(true);
        columnRoomNumber.setCellFactory(TextFieldTableCell.forTableColumn(new LongStringConverter()));
        columnRoomNumber.setOnEditCommit(event -> {
            Booking booking = event.getRowValue();
            booking.setRoomNr(event.getNewValue());
        });
        columnRoomType.setCellFactory(TextFieldTableCell.forTableColumn());
        columnRoomType.setOnEditCommit(event -> {
            Booking booking = event.getRowValue();
            booking.setRoomType(event.getNewValue());
        });
        columnFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnFirstName.setOnEditCommit(event -> {
            Booking booking = event.getRowValue();
            booking.setFirstName(event.getNewValue());
        });
        columnLastName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnLastName.setOnEditCommit(event -> {
            Booking booking = event.getRowValue();
            booking.setLastName(event.getNewValue());
        });
        columnStartDate.setCellFactory(TextFieldTableCell.forTableColumn());
        columnStartDate.setOnEditCommit(event -> {
            Booking booking = event.getRowValue();
            booking.setStartDate(event.getNewValue());
        });
        columnEndDate.setCellFactory(TextFieldTableCell.forTableColumn());
        columnEndDate.setOnEditCommit(event -> {
            Booking booking = event.getRowValue();
            booking.setEndDate(event.getNewValue());
        });
    }

    public static void addBooking(Booking booking) {
        bookingList.add(booking);
    }

    public static ObservableList<Booking> getBookingList() {
        return BackendExceptionHandler.executeReturnNullIfException(() -> Booking.fetchAll());
    }

    public static ObservableList<Booking> getBookingListActualy() {
        return BackendExceptionHandler.executeReturnNullIfException(() -> Booking.fetchBeginningToday());
    }

   public static ObservableList<Booking> getBookingListMock() {

        ObservableList<Booking> bookingList3 = FXCollections.observableArrayList();

        Booking booking1 = new Booking(IndicatorEnum.Gebucht,"Dominik", "LastName", "", "", "", "", "", "", "Standard", 101L, "ds1", "de1", "");
        Booking booking2 = new Booking(IndicatorEnum.Gebucht,"Moritz", "LastName", "", "", "", "", "", "", "Personal", 102L, "ds2", "de2", "");
        Booking booking3 = new Booking(IndicatorEnum.Gebucht,"Jonas", "LastName", "", "", "", "", "", "", "Luxus", 103L, "ds1", "de3", "");
        Booking booking4 = new Booking(IndicatorEnum.Gebucht,"Daniel", "LastName", "", "", "", "", "", "", "Billo", 104L, "ds4", "de2", "");
        Booking booking5 = new Booking(IndicatorEnum.Gebucht,"Dennis", "LastName", "", "", "", "", "", "", "Standard", 105L, "ds1", "de5", "");
        Booking booking6 = new Booking(IndicatorEnum.Gebucht,"Simon", "LastName", "", "", "", "", "", "", "Standard", 106L, "ds6", "de2", "");
        Booking booking7 = new Booking(IndicatorEnum.Gebucht,"Emre", "LastName", "", "", "", "", "", "", "Personal", 107L, "ds1", "de7", "");
        Booking booking8 = new Booking(IndicatorEnum.Gebucht,"Martin", "LastName", "", "", "", "", "", "", "Luxus", 108L, "ds8", "de2", "");
        Booking booking9 = new Booking(IndicatorEnum.Gebucht,"Cedrick", "LastName", "", "", "", "", "", "", "Billo", 109L, "ds1", "de9", "");
        Booking booking10 = new Booking(IndicatorEnum.Gebucht,"Julian", "LastName", "", "", "", "", "", "", "Standard", 110L, "ds10", "de2", "");

        bookingList3.add(booking1);
        bookingList3.add(booking2);
        bookingList3.add(booking3);
        bookingList3.add(booking4);
        bookingList3.add(booking5);
        bookingList3.add(booking6);
        bookingList3.add(booking7);
        bookingList3.add(booking8);
        bookingList3.add(booking9);
        bookingList3.add(booking10);

        return bookingList3;
    }

    /**
     * Initializes the context menu.
     */
    private void setUpMenuItems() {
        this.table.addAutoUpdatingMenuItem("Check-In", (booking) -> BackendExceptionHandler.execute( () -> booking.checkIn()));
        this.table.addAutoUpdatingMenuItem("Check-Out", (booking) -> BackendExceptionHandler.execute( () -> booking.checkOut()));
        this.table.addAutoUpdatingMenuItem("Löschen", (booking) -> tableViewBooking.getItems().removeAll(booking));
    }

    /**
     * Executed when right clicking the table view.
     */
    public void showContextMenu() {
        this.table.supressMenuIfNoSelection();
    }
}
