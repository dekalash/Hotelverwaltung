package frontend.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import frontend.layout.ContainerController;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * Class containting static methods for loading .fxml and .css file's,
 * as well as displaying main and pop up windows.
 */
public class FxHelper {
    //these strings are not actually final for testing purposes
    private static String RESOURCE_DIRECTORY_PATH = "src/main/resources/";
    private static String FXML_RESOURCE_DIRECTORY_PATH = RESOURCE_DIRECTORY_PATH + "fxml/";
    private static String CSS_RESOURCE_DIRECTORY_PATH = RESOURCE_DIRECTORY_PATH + "css/";
    private static String IMAGE_RESOURCE_DIRECTORY_PATH = RESOURCE_DIRECTORY_PATH + "image/";

    private static final String NOT_A_POPUP = "the fxml file is not controlled by a popup controller";
    private static final String NOT_A_MAIN_VIEW = "the fxml file is not controlled by a main controller";
    private static final String RESOURCE_DIRECTORY = "resource directory";
    private static final String FILE_EXTENSION = "file extension";

    /**
     * <b>For testing purposes only.</b>
     * <br><br>
     * Makes this class look for recources in the test directory.
     */
    public static void setTestMode() {
        RESOURCE_DIRECTORY_PATH = "src/test/resources/";
        //update sub folder paths
        FXML_RESOURCE_DIRECTORY_PATH = RESOURCE_DIRECTORY_PATH + "fxml/";
        CSS_RESOURCE_DIRECTORY_PATH = RESOURCE_DIRECTORY_PATH + "css/";
        IMAGE_RESOURCE_DIRECTORY_PATH = RESOURCE_DIRECTORY_PATH + "image/";
    }

    /**
    * Creates a FXMLLoader for a .fxml file located in the resources folder.
    * <br><br>
    * To get the root of the fxml simply call
    * <pre>FXHelper.getLoader("file name").load()</pre>
    * Returning the FXMLLoader instance instead of the root of the loaded fxml 
    * allows for getting the controller instance by using
    * <pre>
    * FXMLLoader loader = FxHelper.getLoader("file name");
    * Parent root = loader.load(); //otherwise controller will not be instantiated
    * Controller controller = loader.getController();
    * </pre>
    * <b>Note:</b>
    * The fxml file's controller class (and all of its members) will be instantiated
    * only upon loading the fxml (calling FXMLLoader.load())
    * 
    * @param file name of the .fxml file (without .fxml)
    * @return a FXMLLoader instance pointed at the specified file
    * @throws FileNotFoundRuntimeException if the file does not exist
    * @throws TunneledIOException if an IOException (other than file not found) occurs
    */
    public static FXMLLoader getLoader(String file) {
        return new FXMLLoader(buildUrl(FXML_RESOURCE_DIRECTORY_PATH, file, ".fxml"));
    }

    /**
     * Constructs an URL for a file located in the resources directory.
     * @param file name of the file (without file extension)
     * @param fileExtension extension of the file (e.g. .fxml)
     * @return URL of the file
     * @throws NullPointerException if any parameter is null
     * @throws FileNotFoundRuntimeException if the file does not exist
     * @throws TunneledIOException if an IOException (other than file not found) occurs
     */
    private static URL buildUrl(String resourceDirectory, String file, String fileExtension) {
        ExceptionFactory.ensureFileNotNull(file);
        ExceptionFactory.ensureNotNullNamed(resourceDirectory, RESOURCE_DIRECTORY);
        ExceptionFactory.ensureNotNullNamed(fileExtension, FILE_EXTENSION);
        try {
            URL url = new URL("file:" + resourceDirectory + file + fileExtension);
            String path = url.getPath();
            File testFile = new File(path);
            if (!testFile.exists()) {
                throw new FileNotFoundRuntimeException(path);
            }
            return url;
        } catch (MalformedURLException exception) {
            //tunnel exception
            throw new TunneledIOException(exception); //MalformedURLException is an IOException

        }
    }

    /**
     * Adds a style sheet to the scene.
     * @param scene the scene to be styled
     * @param file the stylesheet without file extension .css
     * @throws FileNotFoundRuntimeException if the file does not exist
     * @throws TunneledIOException if an IOException (other than file not found) occurs
     */
    public static void addStyle(Scene scene, String file) {
        scene.getStylesheets().add(buildUrl(CSS_RESOURCE_DIRECTORY_PATH, file, ".css").toExternalForm());
    }

    /**
     * Loads the specified fxml file and returns its root.
     * Convenience method.
     * <br><br>
     * Equivalent to
     * <pre>FXHelper.getLoader("file name").load()</pre>
     * @param file the fxml file's name without file extension
     * @return the root of the loaded fxml file
     * @throws FileNotFoundRuntimeException if the file does not exist
     * @throws TunneledIOException if an IOException (other than file not found) occurs
     */
    public static Parent loadFxml(String file) {
        try {
            return getLoader(file).load();
        } catch (IOException exception) {
            /* getLoader throws a different exception if the file does not exist
             -> a different kind of io issue occured
            but application cannot run without successfully loading the fxml file -> should crash */
            //tunnel exception
            throw new TunneledIOException(exception);
        }
    }

    /**
    * Registers the passed action with the passed stage 
    * so that it will be executed when the stage (e.g. popup window) is closed.
    * @param onClosed code that will be executed when the stage  is closed
    * @param popUpWindow the stage
    */
    private static void executeOnClose(Action onClosed, Stage popUpWindow) {
        EventHandler<WindowEvent> handler = new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                onClosed.execute();
                //otherwise the window will not be closed
                popUpWindow.close();
            }
        };
        popUpWindow.setOnCloseRequest(handler);
    }

    /**
     * Injects a reference to the container controller into the loader's controller.
     * <b>Only works with MainControllers.</b>
     * @param loader the loader into whose controller should be injected
     * @param containerController the containerController that is injected
     * @throws IllegalArgumentException if the .fxml is not controlled by an instance of MainController
     */
    private static void injectIntoMainController(FXMLLoader loader, ContainerController containerController) {
        Object controller = loader.getController();
        if (controller instanceof MainController) {
            MainController mainController = (MainController) controller;
            mainController.setContainerController(containerController);
        } else {
            throw new IllegalArgumentException(NOT_A_MAIN_VIEW);
        }
    }

    /**
     * Loads the fxml of a main view and returns its root.
     * <br><br>
     * Injects the ContainerController instance controlling the stage into the view's controller.
     * @param containerController the instance of the containerController
     * @param fxmlFile the fxml file to be loaded
     * @return the root of the loaded fxml
     * @throws FileNotFoundRuntimeException if the file does not exist
     * @throws IllegalArgumentException if the .fxml is not controlled by an instance of MainController
     * @throws TunneledIOException if an IOException (other than file not found) occurs
     */
    public static Parent loadMainView(ContainerController containerController, String fxmlFile) {
        try {
            FXMLLoader loader = getLoader(fxmlFile);
            Parent root = loader.load();
            injectIntoMainController(loader, containerController);
            return root;
        } catch (IOException exception) {
            //tunnel exception
            throw new TunneledIOException(exception);
        }
    }

    /**
     * Returns the instance of PopUpController controlling the passed loader.
     * @param loader the loader used to load the fxml
     * @throws IllegalArgumentException if the .fxml is not controlled by an instance of PopUpController
     * @return
     */
    private static PopUpController getPopUpController(FXMLLoader loader) {
        Object controller = loader.getController();
        if (controller instanceof PopUpController) {
            return (PopUpController) controller;
        } else {
            throw new IllegalArgumentException(NOT_A_POPUP);
        }
    }
    /**
     * Injects references to stage and scene into the loader's controller.
     * <b>Only works with PopUpControllers.</b>
     * @param popUpController the controller into which should be injected
     * @param stage the injected stage
     * @param scene the injected scene
     */
    private static void injectIntoPopUpController(PopUpController popUpController, Stage stage, Scene scene) {
            popUpController.setStage(stage);
            popUpController.setScene(scene);
    }

    /**
     * Displays the fxml as popup window.
     * <br><br>
     * Applies the specified stylesheet to the scene.
     * <br><br>
     * Executes onClosed when the popup window is closed.
     * Injects the popup's stage and scene into the .fxml's controller.
     * (They can be accessed through the controllers inherited getStage() and getScene() methods.)
     * <br><br>
     * <b>Note:</b>
     * The controller class of the .fxml must extend PopUpController.
     * @param fxmlFile the fxml to be loaded
     * @param cssFile the style sheet to be applied
     * @param onClosed action to be executed when the popup is closed
     * @throws FileNotFoundRuntimeException if the file does not exist
     * @throws IllegalArgumentException if the .fxml is not controlled by an instance of PopUpController
     * @throws TunneledIOException if an IOException (other than file not found) occurs
     * @return the controller controlling the popup
     */
    public static PopUpController displayPopUp(String fxmlFile, String cssFile, Action onClosed) {
        try {
            FXMLLoader loader = getLoader(fxmlFile);
            Scene popUpScene = new Scene(loader.load());
            Stage popUpWindow = new Stage();
            popUpWindow.initStyle(StageStyle.UNDECORATED);

            PopUpController controller = getPopUpController(loader);
            injectIntoPopUpController(controller, popUpWindow, popUpScene);

            if (cssFile != null) {
                addStyle(popUpScene, cssFile);
            }

            if(fxmlFile != "Error_PopUp"){
                popUpWindow.setY(185);
                popUpWindow.setX(450);
            } else{
                popUpWindow.setY(350);
                popUpWindow.setX(495);
            }

            popUpWindow.setScene(popUpScene);

            executeOnClose(onClosed, popUpWindow);
            popUpWindow.show();

            return controller;

        } catch (IOException exception) {
            //tunnel exception
            throw new TunneledIOException(exception);
        }
    }

    /**
    * Like {@link #displayPopUp(String, String, Action)} but without applying css.
    */
    public static void displayPopUp(String fxmlFile, Action onClosed) {
        displayPopUp(fxmlFile, null, onClosed);
    }
}
