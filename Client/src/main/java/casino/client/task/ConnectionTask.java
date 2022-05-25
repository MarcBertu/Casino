package casino.client.task;

import Model.SocketSingleton;
import casino.client.controller.ConnectionController;
import com.casino.enums.Messages;
import com.casino.packet.LoginPacket;
import com.casino.packet.RegisterPacket;
import com.casino.utils.Utils;
import javafx.event.Event;
import org.json.JSONObject;
import xyz.baddeveloper.lwsl.client.SocketClient;
import xyz.baddeveloper.lwsl.client.events.OnPacketReceivedEvent;
import xyz.baddeveloper.lwsl.packet.Packet;

import java.util.Objects;

public class ConnectionTask
{

    private OnPacketReceivedEvent event = null;
    private ConnectionController delegate = null;

    public interface ConnectionResponse {
        void didConnect();
        void didFailedConnect(String errorMsg);
    }

    public ConnectionTask(ConnectionController delegate, String... params) {

        try{

            this.delegate = delegate;

            SocketClient socketClient = SocketSingleton.getInstance().getSocketClient();

            event = new OnPacketReceivedEvent() {
                @Override
                public void onPacketReceived(SocketClient socket, Packet packet) {
                    JSONObject object = packet.getObject();
                    if( object.getString("packetId").contentEquals("responseMsg") ) {
                        packetController(packet);
                    }
                }
            };

            socketClient.addPacketReceivedEvent( event );

            String user = params[0];
            String password = params[1];
            int connectionType = Integer.parseInt(params[2]);

            if( connectionType == SocketSingleton.USER_REGISTER_REQUEST) {
                socketClient.sendPacket(new RegisterPacket(user, Utils.hash(password)));
            }
            else if( connectionType == SocketSingleton.USER_LOGIN_REQUEST) {
                socketClient.sendPacket(new LoginPacket(user, Utils.hash(password)));
            }

        } catch ( Exception e ) {
            System.out.println("Apparu à ConnectionTask - constructor");
            System.out.println(e.getMessage());
        }

    }

    private void packetController(Packet packet) {

        try{

            JSONObject object = packet.getObject();
            Messages message = Messages.fromString(object.getString("msg"));

            switch (Objects.requireNonNull(message)) {
                case LOGIN_SUCCESS, REGISTER_SUCCESS -> this.delegate.didConnect();
                case LOGIN_ERROR, REGISTER_ERROR, WRONG_PASSWORD -> this.delegate.didFailedConnect(message.getMsg());
            }

        } catch ( Exception e ) {
            System.out.println("Apparu à ConnectionTask - packetController");
            System.out.println(e.getMessage());
        }

    }

}
