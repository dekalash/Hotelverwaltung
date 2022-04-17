package frontendtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import frontend.util.FileNotFoundRuntimeException;
import frontend.util.FxHelper;
import javafx.scene.Parent;

@Testable
public class FxHelperTest {

    @BeforeAll
    public static void setTestModeFxHelper() {
        FxHelper.setTestMode();
    }

    /**
     * A file name of which netiher a .fxml nor a .css file does exist.
     */
    private static final String NON_EXISTENT_FILE = "ThisFileDoesNotExist";

    /**
     * Name of an existing .fxml file.
     * The file may not contain more than one element as tests are not executed on a javafx thread
     * and may not modify the scene graph - such as adding child nodes to a pane.
     */
    private static final String EXISTENT_FXML_FILE = "fxmlFile";

    /**
     * Name of an existing .css file.
     */
    private static final String EXISTENT_CSS_FILE = "cssFile";

    private void verifyLoadingExistentFxmlFile(Parent node) {
        assertEquals(0, node.getChildrenUnmodifiable().size());
    }

    @Test
    public void getLoaderNull() {
        assertThrows(NullPointerException.class, () -> FxHelper.getLoader(null));
    }

    @Test
    public void getLoaderFileDoesNotExist() {
        assertThrows(FileNotFoundRuntimeException.class, () -> FxHelper.getLoader(NON_EXISTENT_FILE));
    }

    @Test
    public void getLoaderFileDoesExist() throws IOException {
        Parent root = FxHelper.getLoader(EXISTENT_FXML_FILE).load();
        verifyLoadingExistentFxmlFile(root);
    }

    @Test
    public void loadFxmlNull() {
        assertThrows(NullPointerException.class, () -> FxHelper.loadFxml(null));
    }

    @Test
    public void loadFxmlFileDoesNotExist() {
        assertThrows(FileNotFoundRuntimeException.class, () -> FxHelper.loadFxml(NON_EXISTENT_FILE));
    }

    @Test
    public void loadFxmlFileDoesExist() {
        Parent root = FxHelper.loadFxml(EXISTENT_FXML_FILE);
        verifyLoadingExistentFxmlFile(root);
        /* assertTrue(list.get(0) instanceof AnchorPane);
        AnchorPane child = (AnchorPane) list.get(0);
        List<Node> childsList = child.getChildren();
        assertEquals(1, childsList.size());
        assertTrue(list.get(0) instanceof Button); */
    }

    @Test
    public void addStyleSceneNull() {
        assertThrows(NullPointerException.class, () -> FxHelper.addStyle(null, "ThisFileDoesNotExist"));
    }

    @Test
    public void addStyleSceneAndFileNull() {
        assertThrows(NullPointerException.class, () -> FxHelper.addStyle(null, null));
    }

    //tests with a scene != null are not possible as scene objects may only be constructed on a javafx thread

    //addSytle cannot be tested because scenes cannot be constructed

    //loadMainView cannot be tested

    //displayPopUp cannot be tested
}
