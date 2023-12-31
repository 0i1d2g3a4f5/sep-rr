package client_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import messages.MessageSendChat;
/**
 * @author Sarp Cagin Erdogan
 */
public class ClientChatBasicController {
    ClientApplication clientApplication;
    boolean active = true;
    String def = "\"/chatall \" for general chat and \"/chatprivate PlayerID\" for private chat";
    @FXML
    private TextArea chatArea;
    @FXML
    private TextArea inputArea;
    @FXML
    void submitButton(ActionEvent event) {
        submit();

    }

    @FXML
    private static AnchorPane chatWindow;

    // ClientChatBasicController.chatWindow.setResizable(false);

    @FXML
    void submitKey(KeyEvent event) {
        if(event.getCode()== KeyCode.ENTER)
            submit();
    }

    /**
     * checks the purpose of a sent chat message and sends it properly
     */
    void submit(){
        if(active) {
            if (!inputArea.getText().trim().equals("")) {
                active=false;
                String text = inputArea.getText().trim();
                if (text.length()>9 && text.substring(0, 9).equals("/chatall ")) {
                    chatAll(text.substring(9));
                } else if (text.length()>13 && text.substring(0, 13).equals("/chatprivate ")) {
                    chatPrivate(text.substring(13));
                } else {
                    appendChat("ERROR :: Invalid format.");
                    active=true;
                }
            }
        }
    }

    /**
     * sends messages in public chat
     *
     * @param s
     */
    void chatAll(String s){
            clientApplication.basicClient.sendSelf(new MessageSendChat(s, -1));
            inputArea.setText("");
            inputArea.setPromptText(def);
            active = true;
    }

    /**
     * splits messages to receiver ID and message itself and sends messages in private chat
     *
     * @param s
     */

    void chatPrivate(String s){
        String[] subs = s.split(" ");
        int i=0;
        try {
            i = Integer.parseInt(subs[0]);
                clientApplication.basicClient.sendSelf(new MessageSendChat(s.substring(subs[0].length()), i));
                inputArea.setText("");
                inputArea.setPromptText(def);
                active=true;
        } catch (NumberFormatException e) {
            appendChat("ERROR :: Invalid receiver ID.");
            inputArea.setText("");
            inputArea.setPromptText(def);
            active=true;
        }
    }
    public void appendChat(String s){
        chatArea.appendText(s + "\n");
    }

}
