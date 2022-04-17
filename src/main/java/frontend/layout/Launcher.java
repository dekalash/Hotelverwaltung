package frontend.layout;

import frontend.Fetchers.PersonFetcher;
import frontend.util.Box;
import frontend.util.FxHelper;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

//TODO write javadoc
public class Launcher extends Application {
    private static double x, y;
    private static Stage mainStage; // TODO find a better solution than using a static field
    static PersonFetcher pf = new PersonFetcher();

    public static void main(String[] args) throws Exception {

        launch(args);
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        this.mainStage = mainStage; // this. needed to prevent shadowing
        try {

            Box.runApplicationBoxed();
        } catch (Exception e) {
            Box.log(e, "Launcher.start() - this should not have happened");
        }
    }

    public static void displayContainer() {
        // update fxhelper calls
        Parent root = FxHelper.loadFxml("Container");
        mainStage.initStyle(StageStyle.UNDECORATED);

        mainStage.setScene(new Scene(root, 1400, 800));
        mainStage.show();

         //throw new TunneledIOException(new Exception("display container"));  //uncomment to test exception handling
    }

    /**
     * Display login window on a seperate stage before revealing main application.
     */
    public static void displayLogin() {
        FxHelper.displayPopUp("Login", () -> {
            Box.abortApplication();
        });
        // throw new RuntimeException("display login"); //uncomment to test exception handling
        // throw new TunneledIOException(new Exception("display login"));
    }

}
