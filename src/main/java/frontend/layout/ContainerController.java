package frontend.layout;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import frontend.util.FxHelper;
import frontend.util.TopStageBar;

public class ContainerController implements Initializable {

    @FXML
    private Text textTime;

    @FXML
    private Text textContainerHeader;

    @FXML
    private Text textDate;

    @FXML
    private StackPane ContentArea;

    @FXML
    public static Button buttonClose;
    @FXML
    private Button buttonMinimize;

    @FXML
    private HBox topContainerPane;

    @FXML
    private Button buttonHome;
    @FXML
    private Button buttonBook;
    @FXML
    private Button buttonRoom;
    @FXML
    private Button buttonStaff;

    private Button[] buttons;

    private Button currentViewButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new Button[] { buttonHome, buttonBook, buttonRoom, buttonStaff };
        currentTime();
        runClock();
        currentDate();
        displayInitialView(); //not yet part of a scene
        /* try {
            Parent fxml = FxHelper.getLoader("Home").load();
            ContentArea.getChildren().removeAll();
            ContentArea.getChildren().setAll(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        } */

    }

    /**
     * Sets the button opening the currently displayed view.
     * The button will be disabled.
     * All other buttons will be enabled.
     * <br><br>
     * This method has to be called every time a new view is displayed.
    * @param currentViewButton the button that opens the current view
    */
    private void changeCurrentViewButton(Button currentViewButton) {
        this.currentViewButton = currentViewButton;
        currentViewButton.setDisable(true);
        reenableButtons();
    }

    /**
     * Disables all of the navigation buttons.
     */
    public void disableAllButtons() {
        for (Button button : buttons) {
            button.setDisable(true);
        }
    }

    /**
     * Reenables all buttons but the one loading the current view
     * (e.g. if home screen is displayed, home buttton will not be enabled)
     */
    public void reenableButtons() {
        for (Button button : buttons) {
            if (button != currentViewButton) {
                button.setDisable(false);
            }
        }
    }

    /**
     * Sets the displayed content.
     * This may be used to set the initial view (it may be called from initialize()).
     * Code withing this method must account for this 
     * (e.g. the loaded fxml is not guaranteed to be part of a scene within this method
     * and the main scene might not even be constructed yet)
     * @param file the fxml to be displayed
     */
    private Parent setContent(String file) {
        Parent fxml = FxHelper.loadMainView(this, file); //calling fxml.getScene() might return null
        ContentArea.getChildren().removeAll();
        ContentArea.getChildren().setAll(fxml);
        return fxml;
    }

    /**
     * Updates the main view.
     * This may <i>not</i> be used to set the initial view.
     * <br><br>
     * This method should be used to add code that is specific to updating the view 
     * (e.g. code that is executed when switching from rooms view to home view,
     * but not when initially displaying the home view)
     * @param file fxml to be displayed
     * @throws IOException
     */
    private void updateContent(String file) {
        setContent(file);
        //put update specific code here
    }

    public void setContainerHeader(String header) {
        textContainerHeader.setText(header);
    }

    private void displayInitialView() {
        changeCurrentViewButton(buttonHome);
        setContent("Home"); //setContent, not updateContent
        setContainerHeader("Startseite");
    }

    public void home(javafx.event.ActionEvent actionEvent) {
        changeCurrentViewButton(buttonHome);
        updateContent("Home");
        setContainerHeader("Startseite");
    }

    public void booking(javafx.event.ActionEvent actionEvent) {
        changeCurrentViewButton(buttonBook);
        updateContent("Booking");
        setContainerHeader("Reservierung");
    }

    public void room(javafx.event.ActionEvent actionEvent) {
        changeCurrentViewButton(buttonRoom);
        updateContent("Rooms");
        setContainerHeader("Räume");
    }

    public void personel(javafx.event.ActionEvent actionEvent) {
        changeCurrentViewButton(buttonStaff);
        updateContent("Staff");
        setContainerHeader("Personal");
    }

    public void closeContainer(ActionEvent event) {
        disableAllButtons();
        ContentArea.setDisable(true);
        FxHelper.displayPopUp("Warning_PopUp", () -> {
            reenableButtons();
            ContentArea.setDisable(false);
        });
    }

    public void minimizeContainer(ActionEvent event) {
        TopStageBar.minimize(event, buttonMinimize);
    }

    public void containerMovementOnClick(MouseEvent event) {
        TopStageBar.handleClickAction(event, topContainerPane);
    }

    public void containerMovementOnDrag(MouseEvent event) {
        TopStageBar.handleMovementAction(event, topContainerPane);
    }

    public void currentTime() {
        Date date = new Date();

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String currentTime = timeFormat.format(date);
        this.textTime.setText(currentTime + " Uhr");
    }

    public void currentDate() {
        Date date = new Date();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String currentDate = dateFormat.format(date);
        this.textDate.setText(currentDate);

    }

    public void runClock() {
        Duration transitionDuration = Duration.seconds(1);
        EventHandler<ActionEvent> onFinished = event -> currentTime();

        //update time after one second of animation has passed
        KeyFrame updateFrame = new KeyFrame(transitionDuration, onFinished);

        //play animation consisting of only that one frame
        //i.e. wait 1 second, update time (because 1 second has passed), end animation
        Timeline timeline = new Timeline(updateFrame);
        //repeat animation forever
        //i.e. wait 1 second, update time, wait 1 second, update time, ...
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
