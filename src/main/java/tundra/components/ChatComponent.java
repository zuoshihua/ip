package tundra.components;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import tundra.Tundra;
import tundra.utils.Theme;

/**
 * Provides logic for ChatComponent FXML.
 */
public class ChatComponent extends AnchorPane {

    @FXML
    private AnchorPane app;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox messageContainer;

    @FXML
    private TextField userInput;

    private Tundra tundra;

    private Theme theme;

    /**
     * Binds the scrollPane vvalue to the messageContainer height.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(messageContainer.heightProperty());
    }

    /**
     * Injects a Tundra instance, loads all tasks and sets application theme to dark.
     */
    public void setTundra(Tundra t) {
        addTundraMessage(t.getLastMessage());
        t.loadAllTasks();
        addTundraMessage(t.getLastMessage());
        tundra = t;
        setThemeDark();
    }

    /**
     * Creates two dialog boxes, one echoing input and the other containing Duke's reply
     * and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        String response = tundra.runCommand(input);
        messageContainer.getChildren().addAll(
                MessageComponent.getUserMessageComponent(input),
                MessageComponent.getTundraMessageComponent(response)
        );
        userInput.clear();

        if (tundra.shouldExit()) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        }
    }

    /**
     * Sets application theme to light.
     *
     * Peforms no action if application theme was light.
     */
    @FXML
    public void setThemeLight() {
        if (theme == Theme.LIGHT_THEME) {
            return;
        }

        Scene scene = (Scene) app.getScene();
        scene.getStylesheets().setAll(
            getClass().getResource("/css/theme-light.css").toExternalForm());

        theme = Theme.LIGHT_THEME;
    }

    /**
     * Sets application theme to dark.
     *
     * Peforms no action if application theme was dark.
     */
    @FXML
    public void setThemeDark() {
        if (theme == Theme.DARK_THEME) {
            return;
        }

        Scene scene = (Scene) app.getScene();
        scene.getStylesheets().setAll(
            getClass().getResource("/css/theme-dark.css").toExternalForm());

        theme = Theme.DARK_THEME;
    }

    private void addTundraMessage(String input) {
        messageContainer.getChildren().addAll(
                MessageComponent.getTundraMessageComponent(input)
        );
    }

}
