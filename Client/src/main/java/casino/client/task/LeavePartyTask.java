package casino.client.task;

import Model.SocketSingleton;
import casino.client.controller.GameController;
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

public class LeavePartyTask {

    private OnPacketReceivedEvent event = null;
    private GameController delegate = null;

    public interface JoinPartyResponse {
        void didLeaved();
        void didFailedToJoin(String errorMsg);
    }

    public LeavePartyTask(GameController delegate, GameInfo gameInfo) {

        try{
            this.delegate = delegate;

            SocketClient socketClient = SocketSingleton.getInstance().getSocketClient();

            UUID uuid = gameInfo.getGameId();

            socketClient.sendPacket( new JoinStatusPacket(false, uuid) );

            this.delegate.didLeaved();

        } catch ( Exception e ) {
            System.out.println("Apparu à LeavePartyTask - constructor");
            System.out.println(e.getMessage());
        }

    }

}
