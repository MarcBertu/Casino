package Model;

import xyz.baddeveloper.lwsl.client.SocketClient;
import xyz.baddeveloper.lwsl.client.exceptions.ConnectException;

public class SocketSingleton {

    public static final int USER_REGISTER_REQUEST = 0;
    public static final int USER_LOGIN_REQUEST = 1;

    private SocketClient socketClient = null;
    private String username = null;
    static SocketSingleton instance = null;

    private int connectionType = USER_REGISTER_REQUEST;

    private SocketSingleton() {
        this.socketClient = null;
    }

    public static SocketSingleton getInstance() {
        if( instance == null) {
            instance = new SocketSingleton();
        }

        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public SocketClient getSocketClient() {
        return socketClient;
    }

    public void setSocketClient(SocketClient socketClient) {
        this.socketClient = socketClient;
    }

    public int getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(int connectionType) {
        this.connectionType = connectionType;
    }

    public void resetSocketConnection() {

        try{
            SocketClient socketclient = null;

            socketClient = new SocketClient("192.168.93.136", 25566)
                    //socketclient = new SocketClient("localhost", 25566)
                    .setKeepAlive(true).setTimeout(999999999)
                    .addConnectEvent(onConnect -> System.out.println("Connected!"))
                    .addDisconnectEvent( onDisconnect -> {
                        System.out.println("Disconnected!");
                        System.out.println("1 " + onDisconnect.isConnected());
                        System.out.println("2 " +onDisconnect.isClosed());
                    })
                    .addPacketReceivedEvent(((socket, packet) -> System.out.println(String.format("Received packet %s from %s.", packet.getObject().toString(), socket.getAddress()))))
                    .addPacketReceivedEvent((socket, packet) -> EnumSingleton.packetManage(packet))
                    .addPacketSentEvent(((socketClient, packet) -> System.out.println(String.format("Sent packet %s to %s.", packet.getObject().toString(), socketClient.getAddress()))));
            socketClient.connect();

            SocketSingleton.getInstance().setSocketClient(socketClient);
        } catch ( Exception e ) {
            System.out.println("Apparu à SocketSingleton - resetSocketConnection");
            System.out.println(e.getMessage());
        }

    }
}
