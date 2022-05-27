package casino.client.task;

import Model.SocketSingleton;
import casino.client.controller.GameController;
import casino.client.controller.SeePartyController;
import com.casino.entity.GameInfo;
import com.casino.enums.Messages;
import com.casino.packet.JoinStatusPacket;
import com.casino.packet.MoneyPacket;
import org.json.JSONObject;
import xyz.baddeveloper.lwsl.client.SocketClient;
import xyz.baddeveloper.lwsl.client.events.OnPacketReceivedEvent;
import xyz.baddeveloper.lwsl.packet.Packet;

import java.util.Objects;
import java.util.UUID;

public class MoneyTask {

    private OnPacketReceivedEvent event = null;
    private GameController delegate = null;

    public interface MoneyResponse {
        void succesToRemoveMoney();
        void didFailedToRemoveMoney(String errorMsg);
    }

    public MoneyTask(GameController delegate, GameInfo gameInfo, int mise) {

        try{
            this.delegate = delegate;

            SocketClient socketClient = SocketSingleton.getInstance().getSocketClient();

            event = new OnPacketReceivedEvent() {
                @Override
                public void onPacketReceived(SocketClient socket, Packet packet) {
                    JSONObject object = packet.getObject();
                    if( object.getString("packetId").contentEquals("responseMsg") ) {
                        packetController(packet, 1);
                    }
                    else if( object.getString("packetId").contentEquals("playerInformation")) {
                        packetController(packet, 0);
                    }
                }
            };

            socketClient.addPacketReceivedEvent( event );

            socketClient.sendPacket( new MoneyPacket(mise, gameInfo.getGameId()) );


        } catch ( Exception e ) {
            System.out.println("Apparu à MoneyTask - constructor");
            System.out.println(e.getMessage());
        }

    }

    private void packetController(Packet packet, int type) {

        try{
            JSONObject object = packet.getObject();

            if(type == 0) {

                this.delegate.succesToRemoveMoney();

            } else {

                Messages message = Messages.fromString(object.getString("msg"));

                this.delegate.didFailedToJoin(message.getMsg());
            }



        } catch ( Exception e ) {
            System.out.println("Apparu à MoneyTask - packetController");
            System.out.println(e.getMessage());
        }

    }

}
