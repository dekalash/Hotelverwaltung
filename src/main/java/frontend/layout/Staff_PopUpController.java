package frontend.layout;

import frontend.util.BackendException;
import frontend.util.FxHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;

import frontend.data.Staff;

public class Staff_PopUpController extends PopUpController {

    @FXML
    private Button buttonStaffPopUp;

    @FXML
    private TextField textFieldStaffEMail;

    @FXML
    private DatePicker datePickerStaffBirthDate;

    @FXML
    private TextField textFieldStaffFirstName;

    @FXML
    private TextField textFieldStaffPLZ;

    @FXML
    private TextField textFieldStaffCity;

    @FXML
    private TextField textFieldStaffStreet;

    @FXML
    private TextField textFieldStaffPhoneNr;

    @FXML
    private TextField textFieldStaffLastName;

    @FXML
    private TextField textFieldStaffGender;

    @FXML
    private TextField textFieldStaffHouseNr;

    @FXML
    private TextField textFieldStaffState;

    @FXML
    private Button buttonClose;

    @FXML
    private Button buttonMinimize;

    @FXML
    private HBox staffTopPane;

    @FXML
    private Text textBirthDate;

    @FXML
    void closeStaffPopup(ActionEvent event) {
         TopStageBar.close(event, buttonClose);
    }

    @FXML
    void minimizeStaffPopup(ActionEvent event) {
        TopStageBar.minimize(event, buttonMinimize);

    }

    @FXML
    void staffPopupMovementOnClick(MouseEvent event) {
        TopStageBar.handleClickAction(event, staffTopPane);
    }

    @FXML
    void staffPopupMovementOnDrag(MouseEvent event) {
        TopStageBar.handleMovementAction(event, staffTopPane);
    }

    public void getBirthDate(ActionEvent event){
        String birthDate = datePickerStaffBirthDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        textBirthDate.setText(birthDate);
    }

    @FXML
    public void  create(ActionEvent event) {
        String firstName = textFieldStaffFirstName.getText();
        String lastName = textFieldStaffLastName.getText();
        String phone = textFieldStaffPhoneNr.getText();
        String gender = textFieldStaffGender.getText();
        int genderToInt = Integer.parseInt(gender);
        String street = textFieldStaffStreet.getText();
        String houseNR = textFieldStaffHouseNr.getText();
        int houseNRToInt = Integer.parseInt(houseNR);
        String city = textFieldStaffCity.getText();
        String state = textFieldStaffState.getText();
        String zip = textFieldStaffPLZ.getText();
        String email = textFieldStaffEMail.getText();

           
           try {
                Staff.add(firstName, lastName, email, phone, genderToInt, street, houseNRToInt, city, state, zip);
        } catch (BackendException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        try{
        
            closeStaffPopup(event);

        }catch (Exception exception){
            buttonStaffPopUp.setDisable(true);
            FxHelper.displayPopUp("Error_PopUp", () -> {
                buttonStaffPopUp.setDisable(false);
            });
            System.out.println(exception);
        }
    }

    @FXML
    void initialize() {
        assert buttonStaffPopUp != null
                : "fx:id=\"Button_Staff_PopUp\" was not injected: check your FXML file 'Staff_PopUp.fxml'.";
        assert textFieldStaffEMail != null
                : "fx:id=\"TextField_Staff_E_Mail\" was not injected: check your FXML file 'Staff_PopUp.fxml'.";
        assert datePickerStaffBirthDate != null
                : "fx:id=\"DatePicker_Staff_BirthDate\" was not injected: check your FXML file 'Staff_PopUp.fxml'.";
        assert textFieldStaffFirstName != null
                : "fx:id=\"TextField_Staff_FirstName\" was not injected: check your FXML file 'Staff_PopUp.fxml'.";
        assert textFieldStaffPLZ != null
                : "fx:id=\"TextField_Staff_PLZ\" was not injected: check your FXML file 'Staff_PopUp.fxml'.";
        assert textFieldStaffCity != null
                : "fx:id=\"TextField_Staff_City\" was not injected: check your FXML file 'Staff_PopUp.fxml'.";
        assert textFieldStaffStreet != null
                : "fx:id=\"TextField_Staff_Street\" was not injected: check your FXML file 'Staff_PopUp.fxml'.";
        assert textFieldStaffPhoneNr != null
                : "fx:id=\"TextField_Staff_PhoneNr\" was not injected: check your FXML file 'Staff_PopUp.fxml'.";
        assert textFieldStaffLastName != null
                : "fx:id=\"TextField_Staff_SecondName\" was not injected: check your FXML file 'Staff_PopUp.fxml'.";
        assert textFieldStaffHouseNr != null
                : "fx:id=\"TextField_Staff_HouseNr\" was not injected: check your FXML file 'Staff_PopUp.fxml'.";
        assert textFieldStaffState != null
                : "fx:id=\"TextField_Staff_State\" was not injected: check your FXML file 'Staff_PopUp.fxml'.";
        assert textFieldStaffGender != null
                : "fx:id=\"TextField_Staff_Gender\" was not injected: check your FXML file 'Staff_PopUp.fxml'.";

    }

}
