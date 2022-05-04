package casino.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import xyz.baddeveloper.lwsl.client.SocketClient;
import xyz.baddeveloper.lwsl.client.exceptions.ConnectException;

import java.io.IOException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        try {
            SocketClient socketclient = new SocketClient("localhost", 25566)
                    .addConnectEvent(onConnect -> System.out.println("Connected!"))
                    .addDisconnectEvent(onDisconnect -> System.out.println("Disconnected!"))
                    .addPacketReceivedEvent(((socket, packet) -> System.out.println(String.format("Received packet %s from %s.", packet.getObject().toString(), socket.getAddress()))))
                    .addPacketSentEvent(((socketClient, packet) -> System.out.println(String.format("Sent packet %s to %s.", packet.getObject().toString(), socketClient.getAddress()))));
            socketclient.connect();
        } catch (ConnectException e) {
            e.printStackTrace();
        }

        launch();
    }
}