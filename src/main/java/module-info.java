module frontend {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive org.json;
    requires transitive java.net.http;
    requires transitive com.google.gson;

    opens frontend.layout to javafx.fxml, javafx.controls;
    opens frontend.button to javafx.fxml, javafx.controls;

    exports frontend.layout;
    exports frontend.button;
    exports frontend.util;
    exports frontend.data;
}
