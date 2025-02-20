package tundra;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tundra.components.ChatComponent;

/**
 * Models a JavaFX application.
 */
public class App extends Application {

    private Tundra tundra = new Tundra("./data/tundra.txt");

    /**
     * Loads ChatComponent FXML and launches the application.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/ChatComponent.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            primaryStage.setMinHeight(220);
            primaryStage.setMinWidth(417);
            primaryStage.setTitle("Tundra");
            fxmlLoader.<ChatComponent>getController().setTundra(tundra);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
