package client;

import com.google.gson.JsonObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import newmessages.MessageReconnect;

/**
 * @author Sarp Cagin Erdogan
 */
public class ReconnectController {
    Application application;

    @FXML
    private Text feedBack;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField passInput;

    @FXML
    void connectEnter(KeyEvent event) {

    }

    @FXML
    void reconnect() {
        MessageReconnect messageReconnect = new MessageReconnect(nameInput.getText().trim(), application.client.getOldEncoded(passInput.getText().trim()));
        System.out.println("Name: "+ messageReconnect.name + " Hash: " + messageReconnect.hash);
        application.client.sendSelf(messageReconnect);

    }
    void setFeedBack(String string){
        feedBack.setText(string);
    }
    void reset(){
        nameInput.setText("");
        passInput.setText("");
    }

}
