package frontend.layout.Login;

import frontend.layout.Launcher;
import frontend.util.FxHelper;
import frontend.util.PopUpController;
import frontend.util.TopStageBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class LoginController extends PopUpController{

    LoginModel loginModel = new LoginModel();
    private static final String HARD_CODED_USER = "admin";
    private static final String HARD_CODED_PASSWORD = "admin";

    @FXML
    private Button buttonClose;

    @FXML
    private Button buttonMinimize;

    @FXML
    private Label labelDbStatus;

    @FXML
    private Button buttonLogin;

    @FXML
    private HBox topPaneLogin;

    @FXML
    private TextField textFieldUser;

    @FXML
    private PasswordField textFieldPassword;

    private static final String WRONG_ENTRY_ALERT_MESSAGE = "Benutzername oder Passwort ist falsch!";

    /**
     * Checks entered user and password against the hard coded values.
     * @return true - if the entered values are correct, false - otherwise
     */
    private boolean verifyUser() {
         return (HARD_CODED_USER.equals(textFieldUser.getText()) && HARD_CODED_PASSWORD.equals(textFieldPassword.getText()));
    }
    
    private void setButtonAbility(boolean isAble){
        buttonLogin.setDisable(isAble);
        buttonClose.setDisable(isAble);
        buttonMinimize.setDisable(isAble);
    }

    @FXML
    public void login() {
        if (verifyUser()) {
            Launcher.displayContainer();
            this.getStage().close();
        } else {
            setButtonAbility(true);
            FxHelper.displayPopUp("Error_PopUp", () -> {
                setButtonAbility(false);
            });
        }
    }

    @FXML
    void loginMovementOnClick(MouseEvent event) {
        TopStageBar.handleClickAction(event, topPaneLogin);
    }

    @FXML
    void loginMovementOnDrag(MouseEvent event) {
        TopStageBar.handleMovementAction(event, topPaneLogin);
    }

    @FXML
    void minimizeLogin(ActionEvent event) {
        TopStageBar.minimize(event, buttonMinimize);
    }

    @FXML
    void closeLogin(ActionEvent event) {
        TopStageBar.close(event, buttonClose);
    }

    /**
     * Executed when any key is pressed while the user or password text field is focused.
     * Attempts to login when enter is pressed.
     */
    @FXML
    void keyPressedLogin(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            login();
        }
    }


    public void initialize(){
        if(this.loginModel.isDatabaseConnected()){
            this.labelDbStatus.setText("Conntected to Database");
        } else {
            this.labelDbStatus.setText("Not Connected to Database");
        }
    }
}
