package Model;

import casino.client.CasinoApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageSingleton {

    private Stage stage = null;
    static StageSingleton instance = null;

    private StageSingleton() {
        this.stage = null;
    }

    public static StageSingleton getInstance() {
        if (instance == null) {
            instance = new StageSingleton();
        }

        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void changeScene(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CasinoApplication.class.getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
