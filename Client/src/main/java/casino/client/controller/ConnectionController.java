package casino.client.controller;

import Model.SocketSingleton;
import Model.StageSingleton;
import casino.client.task.ConnectionTask;
import com.casino.packet.LoginPacket;
import com.casino.packet.RegisterPacket;
import com.casino.utils.Utils;
import javafx.application.Platform;
import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import xyz.baddeveloper.lwsl.client.SocketClient;
import xyz.baddeveloper.lwsl.client.exceptions.ConnectException;

import java.io.IOException;

public class ConnectionController implements ConnectionTask.ConnectionResponse {

    public TextField userText;
    public Button connectionButton;
    public TextField passwordText;
    public Label errorText;

    @FXML
    protected void connect() {

        try{
            String user = userText.getText();
            String password = passwordText.getText();

            int connectionType = SocketSingleton.getInstance().getConnectionType();

            errorText.setVisible(false);
            errorText.setText("");

            ConnectionTask task = new ConnectionTask(this, user, password, String.valueOf(connectionType));
        } catch ( Exception e ) {
            System.out.println("Apparu à ConnectionController - connect");
            System.out.println(e.getMessage());
        }


    }

    @FXML
    public void back() {
        StageSingleton.getInstance().changeScene("casino-app-view.fxml");
    }

    @Override
    public void didConnect() {

        try{
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    StageSingleton.getInstance().changeScene("home-view.fxml");
                }
            });
        } catch ( Exception e ) {
            System.out.println("Apparu à ConnectionController - didConnect");
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void didFailedConnect( String errorMsg) {

        try{
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    errorText.setVisible(true);
                    errorText.setText(errorMsg);
                }
            });

            SocketSingleton.getInstance().resetSocketConnection();
            this.connect();

        } catch ( Exception e ) {
            System.out.println("Apparu à ConnectionController - didFailedConnect");
            System.out.println(e.getMessage());
        }
    }
}
