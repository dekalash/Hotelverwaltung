package frontend.layout.Staff;

import frontend.util.FxHelper;
import frontend.util.PopUpController;
import frontend.util.TopStageBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import frontend.dbUtil.dbConnection;

public class Staff_PopUpController extends PopUpController {

    @FXML
    private Button buttonStaffPopUp;

    @FXML
    private TextField textFieldEMail;

    @FXML
    private DatePicker datePickerBirthDate;

    @FXML
    private TextField textFieldFirstName;

    @FXML
    private TextField textFieldStreet;

    @FXML
    private TextField textFieldLastName;

    StaffController staffController;

    @FXML
    private Button buttonClose;

    @FXML
    private Button buttonMinimize;

    @FXML
    private HBox staffTopPane;

    @FXML
    private Text textBirthDate;

    private String sqlAddStaff = "INSERT INTO person(lastName, firstName, DOB, street, email, role) VALUES (?, ?, ?, ? ,? ,?)";
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
        String birthDate = datePickerBirthDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        textBirthDate.setText(birthDate);
    }

    @FXML
    public void create(ActionEvent event) {
        
        try {
                Connection conn = dbConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlAddStaff);
                
                stmt.setString(1, this.textFieldLastName.getText());
                stmt.setString(2, this.textFieldFirstName.getText());
                stmt.setString(3, this.datePickerBirthDate.getEditor().getText());
                stmt.setString(4, this.textFieldStreet.getText());
                stmt.setString(5, this.textFieldEMail.getText());
                stmt.setString(6, "staff");
                
                stmt.execute();
                conn.close();
        } catch (SQLException e) {
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
        staffController = new StaffController();
    }
}
