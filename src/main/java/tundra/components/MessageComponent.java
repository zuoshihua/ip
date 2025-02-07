package tundra.components;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Provides logic for MessageComponent FXML.
 */
public class MessageComponent extends HBox {

    @FXML
    private Label label;

    private MessageComponent(String text) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ChatComponent.class.getResource("/fxml/MessageComponent.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        label.setText(text);
    }

    private void flip() {
        setAlignment(Pos.TOP_LEFT);
        label.getStyleClass().add("reply-label");
    }

    /**
     * Returns a new chat message component sent by the user.
     *
     * @param text Content of the message.
     * @return The {@link MessageComponent} containing the provided content.
     */
    public static MessageComponent getUserMessageComponent(String text) {
        return new MessageComponent(text);
    }

    /**
     * Returns a new chat message component sent by Tundra.
     *
     * @param text Content of the message.
     * @return The {@link MessageComponent} containing the provided content.
     */
    public static MessageComponent getTundraMessageComponent(String text) {
        var mc = new MessageComponent(text);
        mc.flip();
        return mc;
    }
}
