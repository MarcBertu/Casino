module casino.client {
    requires javafx.controls;
    requires javafx.fxml;


    opens casino.client to javafx.fxml;
    exports casino.client;
}