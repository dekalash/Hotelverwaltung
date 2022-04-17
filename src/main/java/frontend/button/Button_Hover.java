package frontend.button;

import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.control.skin.ButtonSkin;
import javafx.util.Duration;

public class Button_Hover extends ButtonSkin
{
    public Button_Hover(Button hover)
    {
        super(hover);
        final FadeTransition fadeIn = new FadeTransition(Duration.millis(500));
        fadeIn.setNode(hover);
        fadeIn.setToValue(1);
        hover.setOnMouseEntered(e -> fadeIn.playFromStart());

        final FadeTransition fadeOut = new FadeTransition(Duration.millis(500));
        fadeOut.setNode(hover);
        fadeOut.setToValue(0.5);
        hover.setOnMouseExited(e -> fadeOut.playFromStart());

        hover.setOpacity(0.5);
    }
}
