package frontend.util;

import frontend.layout.ContainerController;

/**
 * Controllers controlling main views should extend this class.
 * <br><br>
 * This class stores a reference to the ContainerController instance.
 * The reference is automatically injected through FxHelper when calling {@link FxHelper#loadMainView(ContainerController, String)}.
 * <br><br>
 * By extending this class controllers can disable and reenable buttons  controlled by the ContainerController.
 */
public abstract class MainController {
    private ContainerController containerController;

    public void setContainerController(ContainerController containerController) {
        this.containerController = containerController;
    }

    /**
     * Disables all of the navigation buttons (Home, Reservierung, Raeume, Personal)
     */
    public void disableAllButtons() {
        this.containerController.disableAllButtons();
    }

    /**
     * Reenables all buttons but the one loading the currently displayed view.
     * (e.g. if home screen is displayed, home buttton will not be enabled)
     */
    public void reenableButtons() {
        this.containerController.reenableButtons();
    }
}
