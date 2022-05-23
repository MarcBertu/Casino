module casino.client {
    requires javafx.controls;
    requires javafx.fxml;
    requires LWSL;
    requires json;


    opens casino.client to javafx.fxml;
    opens casino.client.controller to javafx.fxml;
    exports casino.client;
}