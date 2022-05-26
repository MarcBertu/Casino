package casino.client.controller;

import Model.StageSingleton;
import Model.UserSingleton;
import casino.client.task.LeavePartyTask;
import com.casino.entity.GameInfo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class GameController implements LeavePartyTask.JoinPartyResponse {

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

            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    String miseText = miseField.getText();
                    int mise = Integer.parseInt(miseText);
                    int argent = UserSingleton.getInstance().getArgent();
                    if ( mise <= argent ) {

                        miseLabel.setText(miseText + "€");
                        UserSingleton.getInstance().setMise(mise);
                        int totalApresMise = argent - mise;

                        UserSingleton.getInstance().setArgent(totalApresMise);

                        moneyLabel.setText( UserSingleton.getInstance().getArgent() + "€" );

                        miseLayout.setVisible(false);
                        actionLayout.setVisible(true);
                    }
                }
            });

        } catch ( Exception e ) {
            System.out.println("Apparu a GameController - miser");
            System.out.println(e.getMessage());
        }
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
}
