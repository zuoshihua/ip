package tundra.components;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tundra.App;
import tundra.Tundra;
import tundra.utils.Theme;

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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(messageContainer.heightProperty());
    }

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
    }

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
