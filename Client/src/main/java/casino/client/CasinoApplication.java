package casino.client;

import Model.EnumSingleton;
import Model.SocketSingleton;
import Model.StageSingleton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import xyz.baddeveloper.lwsl.client.SocketClient;
import xyz.baddeveloper.lwsl.client.exceptions.ConnectException;

import java.io.IOException;


public class CasinoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CasinoApplication.class.getResource("casino-app-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        StageSingleton.getInstance().setStage(stage);
    }

    public static void main(String[] args) {

        CasinoApplication.connectToServer();

        launch();
    }

    public static void connectToServer() {
        SocketClient socketclient = null;
        try {
            socketclient = new SocketClient("192.168.93.136", 25566)
//            socketclient = new SocketClient("localhost", 25566)
                    .setKeepAlive(true)
                    .addConnectEvent(onConnect -> System.out.println("Connected!"))
                    .addDisconnectEvent( onDisconnect -> System.out.println("Disconnected!"))
                    .addPacketReceivedEvent(((socket, packet) -> System.out.println(String.format("Received packet %s from %s.", packet.getObject().toString(), socket.getAddress()))))
                    .addPacketReceivedEvent((socket, packet) -> EnumSingleton.packetManage(packet))
                    .addPacketSentEvent(((socketClient, packet) -> System.out.println(String.format("Sent packet %s to %s.", packet.getObject().toString(), socketClient.getAddress()))));
            socketclient.connect();

            SocketSingleton.getInstance().setSocketClient(socketclient);

            System.out.println(socketclient.isConnected());

        } catch (ConnectException e) {
            e.printStackTrace();
        }

    }
}