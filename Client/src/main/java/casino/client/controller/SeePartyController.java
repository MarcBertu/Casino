package casino.client.controller;

import Model.StageSingleton;

public class SeePartyController {

    public void back() {
        StageSingleton.getInstance().changeScene("home-see-party-view.fxml");
    }
}
