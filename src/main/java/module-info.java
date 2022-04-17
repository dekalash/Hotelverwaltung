module frontend {
    requires transitive javafx.graphics;
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive org.json;
    requires transitive java.net.http;
    requires transitive com.google.gson;
    requires transitive java.sql;

    opens frontend.layout to javafx.fxml, javafx.controls;
    opens frontend.layout.Login to javafx.fxml, javafx.controls;
    opens frontend.layout.Home to javafx.fxml, javafx.controls;
    opens frontend.layout.Rooms to javafx.fxml, javafx.controls;
    opens frontend.layout.Booking to javafx.fxml, javafx.controls;
    opens frontend.layout.Staff to javafx.fxml, javafx.controls;
    opens frontend.button to javafx.fxml, javafx.controls;

    exports frontend.layout;
    exports frontend.layout.Booking;
    exports frontend.layout.Home;
    exports frontend.layout.Login;
    exports frontend.layout.Rooms;
    exports frontend.layout.Staff;
    exports frontend.button;
    exports frontend.util;
    exports frontend.data;
    exports frontend.dbUtil;
}
