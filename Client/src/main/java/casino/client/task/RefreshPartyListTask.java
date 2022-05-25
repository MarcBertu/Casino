package casino.client.task;

import Model.SocketSingleton;
import casino.client.controller.SeePartyController;
import com.casino.entity.GameInfo;
import com.casino.packet.AskInfoPacket;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.baddeveloper.lwsl.client.SocketClient;
import xyz.baddeveloper.lwsl.client.events.OnPacketReceivedEvent;
import xyz.baddeveloper.lwsl.packet.Packet;

import java.util.ArrayList;
import java.util.List;

public class RefreshPartyListTask {

    private OnPacketReceivedEvent event = null;
    private SeePartyController delegate = null;

    public interface PartyListResponse {
        void didReceiveList(ObservableList<GameInfo> partyList);
        void didFailedGettingList(String errorMsg);
    }

    public RefreshPartyListTask(SeePartyController delegate) {

        try{
            this.delegate = delegate;

            SocketClient socketClient = SocketSingleton.getInstance().getSocketClient();

            event = new OnPacketReceivedEvent() {
                @Override
                public void onPacketReceived(SocketClient socket, Packet packet) {

                    JSONObject object = packet.getObject();

                    if( object.getString("packetId").contentEquals("gameInfo") ) {
                        packetController(packet);
                    }
                }
            };

            socketClient.addPacketReceivedEvent( event );

            socketClient.sendPacket( new AskInfoPacket() );
        } catch ( Exception e ) {
            System.out.println("Apparu à RefreshPartyListTask - constructor");
            System.out.println(e.getMessage());
        }

    }

    private void packetController(Packet packet) {

        try{
            JSONObject object = packet.getObject();
            String packetID = object.getString("packetId");

            ObservableList<GameInfo> listePartie = FXCollections.observableArrayList();

            if( packetID.contentEquals("gameInfo") ) {

                JSONArray list = object.getJSONArray("games");

                list.forEach( entity -> {
                    GameInfo game = GameInfo.initFrom( (JSONObject) entity );
                    listePartie.add(game);
                } );

                this.delegate.didReceiveList(listePartie);
            }
        } catch ( Exception e ) {
            System.out.println("Apparu à RefreshPartyListTask - packetController");
            System.out.println(e.getMessage());
        }

    }
}
