package casino.client;

import Model.SocketSingleton;
import Model.StageSingleton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CasinoController {
    public Button startLoginButton;
    public Button startRegisterButton;

    @FXML
    protected void onStartLogin() {
        SocketSingleton.getInstance().setConnectionType(SocketSingleton.USER_LOGIN_REQUEST);
        StageSingleton.getInstance().changeScene("home-connection-view.fxml");
    }

    @FXML
    protected void onStartRegister() {
        SocketSingleton.getInstance().setConnectionType(SocketSingleton.USER_REGISTER_REQUEST);
        StageSingleton.getInstance().changeScene("home-connection-view.fxml");
    }
}