package casino.client.controller;

import Model.SocketSingleton;
import Model.StageSingleton;
import Model.UserSingleton;
import casino.client.task.LeavePartyTask;
import casino.client.task.MoneyTask;
import com.casino.entity.Card;
import com.casino.entity.GameInfo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.baddeveloper.lwsl.packet.Packet;

import java.util.ArrayList;
import java.util.List;

public class GameController implements LeavePartyTask.JoinPartyResponse, MoneyTask.MoneyResponse {

    public HBox actionLayout;
    public HBox miseLayout;
    public TextField miseField;
    public Label moneyLabel;
    public Label miseLabel;
    public Text pseudoPlayerOne;
    public Text pseudoPlayerTwo;
    public Text pseudoPlayerThree;
    public Text pseudoPlayerFour;
    public Text pseudoPlayerFive;
    public HBox cardListPlayerOne;
    public HBox cardListPlayerCroupier;

    public List<Card> cardPlayer = new ArrayList<>();
    public List<Card> cardCroupier = new ArrayList<>();

    private int mise = 0;

    @FXML
    public void initialize() {

        try {

            int nbJoueur = UserSingleton.getInstance().getGame().getPlayerInGame();

            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    moneyLabel.setText( UserSingleton.getInstance().getArgent() + "€" );

                    pseudoPlayerOne.setText( UserSingleton.getInstance().getPseudo() );

                    switch (nbJoueur) {
                        case 2:
                            pseudoPlayerTwo.setText("Unknown");
                            break;
                        case 3:
                            pseudoPlayerThree.setText("Unknown");
                            break;
                        case 4:
                            pseudoPlayerFour.setText("Unknown");
                            break;
                        case 5:
                            pseudoPlayerFive.setText("Unknown");
                            break;
                    }
                }
            });

        } catch ( Exception e ) {
            System.out.println("Apparu a GameController - init");
            System.out.println(e.getMessage());
        }

    }

    public void back() {
        try {
            GameInfo game = UserSingleton.getInstance().getGame();

            LeavePartyTask task = new LeavePartyTask(this, game);

        } catch (Exception e ) {
            System.out.println("Apparu a GameController - back");
            System.out.println(e.getMessage());
        }
    }

    public void miser() {
        try {

            SocketSingleton.getInstance().getSocketClient().addPacketReceivedEvent(((socket, packet) -> getCardsList(packet) ) );

            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    String miseText = miseField.getText();
                    mise = Integer.parseInt(miseText);
                    actionMiser(mise);
                }
            });

        } catch ( Exception e ) {
            System.out.println("Apparu a GameController - miser");
            System.out.println(e.getMessage());
        }
    }

    private void actionMiser(int mise) {
        MoneyTask moneyTask = new MoneyTask(this, UserSingleton.getInstance().getGame(), -mise);
    }

    public void jouer() {
        try {

        } catch ( Exception e ) {
            System.out.println("Apparu a GameController - jouer");
            System.out.println(e.getMessage());
        }
    }

    public void passer() {
        try {

        } catch ( Exception e ) {
            System.out.println("Apparu a GameController - passer");
            System.out.println(e.getMessage());
        }
    }

    public void doubler() {
        try {

        } catch ( Exception e ) {
            System.out.println("Apparu a GameController - doubler");
            System.out.println(e.getMessage());
        }
    }

    public void isWin() {

    }

    public void isLooose() {

    }
    @Override
    public void didLeaved() {
        try {
            StageSingleton.getInstance().changeScene("home-see-party-view.fxml");
        } catch (Exception e ) {
            System.out.println("Apparu a GameController - didLeaved");
        }

    }

    @Override
    public void didFailedToJoin(String errorMsg) {

    }

    @Override
    public void succesToRemoveMoney() {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                String miseText = miseField.getText();
                int argent = UserSingleton.getInstance().getArgent();

                miseLabel.setText(miseText + "€");

                moneyLabel.setText( argent + "€" );

                miseLayout.setVisible(false);
                actionLayout.setVisible(true);
            }
        });
    }

    @Override
    public void didFailedToRemoveMoney(String errorMsg) {
        // Flemme
    }

    public void getCardsList(Packet packet) {
        try {
            JSONObject object = packet.getObject();
            if( object.getString("packetId").contentEquals("bankCards") ) {
                JSONArray liste = object.getJSONArray("cards");

                cardCroupier.clear();

                liste.forEach( o -> {
                    Card card = Card.initFrom( (JSONObject) o );
                    cardCroupier.add(card);
                } );

                refreshCroupierCard();

            }
        } catch ( Exception e ) {
            System.out.println("Apparu a GameController - getCardList");
            System.out.println(e.getMessage() );
        }
    }

    private void refreshCroupierCard() {
        try {
            cardCroupier.forEach(card -> ajouterCard(card, cardListPlayerCroupier));
        } catch (Exception e) {
            System.out.println("Apparu a GameController - refreshCroupierCard");
            System.out.println(e.getMessage());
        }
    }

    public void ajouterCard(Card card, HBox box) {

        try {
            Platform.runLater(() -> {
                ImageView imageView = Card.createCard(card.getColor().getColor(), card.getValue().getValue());
                box.getChildren().add( imageView );
            });
        } catch (Exception e) {
            System.out.println("Apparu a GameController - AjouterCard");
            System.out.println(e.getMessage());
        }

    }

}
