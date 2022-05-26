package casino.client.task;

import Model.SocketSingleton;
import casino.client.controller.SeePartyController;
import com.casino.entity.GameInfo;
import com.casino.enums.Messages;
import com.casino.packet.JoinStatusPacket;
import org.json.JSONObject;
import xyz.baddeveloper.lwsl.client.SocketClient;
import xyz.baddeveloper.lwsl.client.events.OnPacketReceivedEvent;
import xyz.baddeveloper.lwsl.packet.Packet;

import java.util.Objects;
import java.util.UUID;

public class MoneyTask {

    private OnPacketReceivedEvent event = null;
    private SeePartyController delegate = null;

    public interface JoinPartyResponse {
        void didJoined();
        void didFailedToJoin(String errorMsg);
    }

    public MoneyTask(SeePartyController delegate, GameInfo gameInfo) {

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

            UUID uuid = gameInfo.getGameId();

            socketClient.sendPacket( new JoinStatusPacket(true, uuid) );

            this.delegate.didJoined();

        } catch ( Exception e ) {
            System.out.println("Apparu à MoneyTask - constructor");
            System.out.println(e.getMessage());
        }

    }

    private void packetController(Packet packet) {

        try{
            JSONObject object = packet.getObject();
            Messages message = Messages.fromString(object.getString("msg"));

            switch (Objects.requireNonNull(message)) {
                case JOIN_SUCCESS -> this.delegate.didJoined();
                case JOIN_ERROR, GAME_FULL -> this.delegate.didFailedToJoin(message.getMsg());
            }

        } catch ( Exception e ) {
            System.out.println("Apparu à MoneyTask - packetController");
            System.out.println(e.getMessage());
        }

    }

}
