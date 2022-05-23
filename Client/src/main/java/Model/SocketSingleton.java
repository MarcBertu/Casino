package Model;

import xyz.baddeveloper.lwsl.client.SocketClient;

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
}
