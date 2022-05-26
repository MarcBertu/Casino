package casino.client.controller;

import Model.SocketSingleton;
import Model.StageSingleton;
import Model.UserSingleton;
import casino.client.CasinoApplication;
import casino.client.task.ConnectionTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class HomeController {

    public Label moneyLabel;

    @FXML
    public void initialize() {

        try {

            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    moneyLabel.setText( UserSingleton.getInstance().getArgent() + "€" );
                }
            });

        } catch ( Exception e ) {
            System.out.println("Apparu a GameController - init");
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public void back() {

        SocketSingleton.getInstance().getSocketClient().shutdown();

        CasinoApplication.connectToServer();

        StageSingleton.getInstance().changeScene("casino-app-view.fxml");

    }

    public void startGame() {
        StageSingleton.getInstance().changeScene("home-see-party-view.fxml");
    }


}
