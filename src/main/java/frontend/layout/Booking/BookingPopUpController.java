package frontend.layout.Booking;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import frontend.data.Booking;
import frontend.data.IndicatorEnum;
import frontend.data.Room;
import frontend.util.BackendExceptionHandler;
import frontend.util.FxHelper;
import frontend.util.PopUpController;
import frontend.util.TopStageBar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

//weil eine ungültige reservierung in der datenbank sich befindet, funktioniert reservierung nicht
public class BookingPopUpController extends PopUpController {
     @FXML
     private ResourceBundle resources;

     @FXML
     private URL location;

     @FXML
     private Button buttonBooking;

     @FXML
     private TextField textFieldEMail;

     @FXML
     private DatePicker datePickerStart;

     @FXML
     private DatePicker datePickerEnd;

     @FXML
     private DatePicker datePickerBirthDate;

     @FXML
     private TextField textFieldFirstName;

     @FXML
     private TextField textFieldPLZ;

     @FXML
     private TextField textFieldState;

     @FXML
     private TextField textFieldPrice;

     @FXML
     private TextField textFieldCity;

     @FXML
     private TextField textFieldStreet;

     @FXML
     private TextField textFieldPhoneNr;

     @FXML
     private TextField textFieldHouseNr;

     @FXML
     private TextField textFieldLastName;

     @FXML
     private TextField textFieldGender;

     @FXML
     private ComboBox<Room> comboBoxSelectRoom;

     @FXML
     private ComboBox<String> comboBoxRoomtype;

     @FXML
     private HBox bookingTopPane;

     @FXML
     private Button buttonClose;

     @FXML
     private Button buttonMinimize;

     @FXML
     public Text startDateText;

     @FXML
     public Text endDateText;

     @FXML
     public Text birthDateText;

     @FXML
     void bookingPopupClose(ActionEvent event) {
          TopStageBar.close(event, buttonClose);
     }

     @FXML
     void bookingPopupMinimize(ActionEvent event) {
          TopStageBar.minimize(event, buttonMinimize);
     }

     @FXML
     void bookingPopupMovementOnClick(MouseEvent event) {
          TopStageBar.handleClickAction(event, bookingTopPane);
     }

     @FXML
     void bookingPopupMovementOnDrag(MouseEvent event) {
          TopStageBar.handleMovementAction(event, bookingTopPane);
     }

     @FXML
     void selectRoom(ActionEvent event) {
          comboBoxSelectRoom.getSelectionModel().getSelectedItem();
     }

     public void getEndDate(ActionEvent event) {
          String endDate = datePickerEnd.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          endDateText.setText(endDate);
     }

     public void getStartDate(ActionEvent event) {
          String startDate = datePickerStart.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          startDateText.setText(startDate);
     }

     public void getBirthDate(ActionEvent event) {
          String birthDate = datePickerBirthDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
          birthDateText.setText(birthDate);
     }

     @FXML
     public void book(ActionEvent event) {

          try {

               long roomNr = comboBoxSelectRoom.getSelectionModel().getSelectedItem().getRoomNumber();

               String roomType = comboBoxSelectRoom.getSelectionModel().getSelectedItem().getRoomType();

               Booking booking = new Booking(IndicatorEnum.Gebucht, textFieldFirstName.getText(),
                         textFieldLastName.getText(), birthDateText.getText(), textFieldPLZ.getText(),
                         textFieldCity.getText(), textFieldStreet.getText(), textFieldEMail.getText(),
                         textFieldPhoneNr.getText(), roomType, roomNr, startDateText.getText(), endDateText.getText(),
                         textFieldPrice.getText());

               BookingController.addBooking(booking);
               // booking has to be created in backend and then fetched by frontend invoking
               // table.update() instead

               bookingPopupClose(event);

          } catch (Exception e) {
               buttonBooking.setDisable(true);

               FxHelper.displayPopUp("Error_PopUp", () -> {
                    buttonBooking.setDisable(false);
               });
          }
     }

     @FXML
     void initialize() {
          ObservableList<String> roomType = BackendExceptionHandler
                    .executeReturnNullIfException(() -> FXCollections.observableArrayList(Room.fetchRoomTypes()));
          comboBoxRoomtype.setItems(roomType);
          String selectedRoomType = comboBoxRoomtype.getSelectionModel().getSelectedItem();

          ObservableList<Room> roomList = BackendExceptionHandler.executeReturnNullIfException(() -> Room.fetchAll())
                    .filtered(room -> room.getIndicator() == IndicatorEnum.Frei );
          comboBoxSelectRoom.setItems(roomList);

          assert buttonBooking != null
                    : "fx:id=\"Button_Booking_Reservieren\" was not injected: check your FXML file 'Booking_PopUp.fxml'.";
          assert datePickerStart != null
                    : "fx:id=\"TextField_Booking_Start\" was not injected: check your FXML file 'Booking_PopUp.fxml'.";
          assert textFieldEMail != null
                    : "fx:id=\"TextField_Booking_E_Mail\" was not injected: check your FXML file 'Booking_PopUp.fxml'.";
          assert datePickerEnd != null
                    : "fx:id=\"TextField_Booking_End\" was not injected: check your FXML file 'Booking_PopUp.fxml'.";
          assert datePickerBirthDate != null
                    : "fx:id=\"TextField_Booking_BirthDate\" was not injected: check your FXML file 'Booking_PopUp.fxml'.";
          assert textFieldFirstName != null
                    : "fx:id=\"TextField_Booking_FirstName\" was not injected: check your FXML file 'Booking_PopUp.fxml'.";
          assert textFieldPLZ != null
                    : "fx:id=\"TextField_Booking_PLZ\" was not injected: check your FXML file 'Booking_PopUp.fxml'.";
          assert textFieldPrice != null
                    : "fx:id=\"TextField_Booking_Price\" was not injected: check your FXML file 'Booking_PopUp.fxml'.";
          assert textFieldCity != null
                    : "fx:id=\"TextField_Booking_City\" was not injected: check your FXML file 'Booking_PopUp.fxml'.";
          assert textFieldStreet != null
                    : "fx:id=\"TextField_Booking_Street\" was not injected: check your FXML file 'Booking_PopUp.fxml'.";
          assert textFieldPhoneNr != null
                    : "fx:id=\"TextField_Booking_PhoneNr\" was not injected: check your FXML file 'Booking_PopUp.fxml'.";
          assert textFieldLastName != null
                    : "fx:id=\"TextField_Booking_LastName\" was not injected: check your FXML file 'Booking_PopUp.fxml'.";

     }

}
