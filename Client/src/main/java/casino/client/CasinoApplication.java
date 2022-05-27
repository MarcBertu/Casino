package casino.client;

import Model.EnumSingleton;
import Model.SocketSingleton;
import Model.StageSingleton;
import Model.UserSingleton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONObject;
import xyz.baddeveloper.lwsl.client.SocketClient;
import xyz.baddeveloper.lwsl.client.events.OnPacketReceivedEvent;
import xyz.baddeveloper.lwsl.client.exceptions.ConnectException;
import xyz.baddeveloper.lwsl.packet.Packet;

import java.io.IOException;


public class CasinoApplication extends Application {

    @Override
    public void start(Stage stage) {

        try{

            FXMLLoader fxmlLoader = new FXMLLoader(CasinoApplication.class.getResource("casino-app-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            StageSingleton.getInstance().setStage(stage);

        } catch ( Exception e ) {
            System.out.println("Apparu à Casino-application start()");
            System.out.println(e.getMessage());
        }


    }

    public static void main(String[] args) {

        CasinoApplication.connectToServer();

        launch();
    }

    public static void connectToServer() {

        try{
            SocketClient socketclient = null;

            //socketclient = new SocketClient("192.168.93.136", 25566)
                    socketclient = new SocketClient("localhost", 25566)
                    .setKeepAlive(true)
                    .addConnectEvent(onConnect -> System.out.println("Connected!"))
                    .addDisconnectEvent( onDisconnect -> System.out.println("Disconnected!"))
                    .addPacketReceivedEvent(((socket, packet) -> System.out.println(String.format("Received packet %s from %s.", packet.getObject().toString(), socket.getAddress()))))
                    .addPacketReceivedEvent((socket, packet) -> EnumSingleton.packetManage(packet))
                    .addPacketSentEvent(((socketClient, packet) -> System.out.println(String.format("Sent packet %s to %s.", packet.getObject().toString(), socketClient.getAddress()))))
                    .addPacketReceivedEvent(((socket, packet) -> packetSetMoney(packet) ) );
            socketclient.connect();

            SocketSingleton.getInstance().setSocketClient(socketclient);
        } catch ( Exception e ) {
            System.out.println("Apparu à CasinoApplication - connectToServeur");
            System.out.println(e.getMessage());
        }

    }

    static void packetSetMoney(Packet packet) {

        try {
            JSONObject object = packet.getObject();
            if( object.getString("packetId").contentEquals("playerInformation") ) {
                int money = packet.getObject().getInt("money");
                UserSingleton.getInstance().setArgent(money);
            }
        } catch (Exception e) {
            System.out.println("Apparu a CasinoApplication - packetSetMoney ");
            System.out.println(e.getMessage());
        }

    }
}