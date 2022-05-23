package casino.client.controller;

import Model.SocketSingleton;
import Model.StageSingleton;
import casino.client.CasinoApplication;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController {

    @FXML
    public void back() throws IOException {

        SocketSingleton.getInstance().getSocketClient().shutdown();

        CasinoApplication.connectToServer();

        StageSingleton.getInstance().changeScene("casino-app-view.fxml");

    }

}
