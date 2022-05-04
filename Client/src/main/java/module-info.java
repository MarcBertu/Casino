module casino.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires LWSL;


    opens casino.client to javafx.fxml;
    exports casino.client;
}