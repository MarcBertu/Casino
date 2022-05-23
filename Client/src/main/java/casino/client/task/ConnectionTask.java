package casino.client.task;

import Model.SocketSingleton;
import casino.client.controller.ConnectionController;
import com.casino.entity.Messages;
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

        this.delegate = delegate;

        SocketClient socketClient = SocketSingleton.getInstance().getSocketClient();

        event = new OnPacketReceivedEvent() {
            @Override
            public void onPacketReceived(SocketClient socket, Packet packet) {
                packetController(packet);
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
    }

    private void packetController(Packet packet) {
        JSONObject object = packet.getObject();
        Messages message = Messages.fromString(object.getString("msg"));

        //SocketSingleton.getInstance().getSocketClient().removePacketReceivedEvent(this.event);

         switch (Objects.requireNonNull(message)) {
            case LOGIN_SUCCESS, REGISTER_SUCCESS -> this.delegate.didConnect();
            case LOGIN_ERROR, REGISTER_ERROR, WRONG_PASSWORD -> this.delegate.didFailedConnect(message.getMsg());
        }

    }

}
