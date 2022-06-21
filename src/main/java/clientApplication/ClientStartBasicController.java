package clientApplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
/**
 * @author Sarp Cagin Erdogan
 */
public class ClientStartBasicController {
    ClientApplication clientApplication;
    boolean active = true;

    @FXML
    private Text feedBack;

    @FXML
    private TextField groupInput;

    @FXML
    private TextField ipInput;

    @FXML
    private TextField portInput;

    @FXML
    void connectButton(ActionEvent event) {
            checkIfNumber();
    }

    @FXML
    void connectEnter(KeyEvent event) {
        if(event.getCode()== KeyCode.ENTER){
            checkIfNumber();
        }
    }
    void checkIfNumber(){
        if(active){
            if (groupInput.getText().trim() == "") {
                feedBack.setText("Enter a group name.");
            } else if (ipInput.getText().trim() == "") {
                feedBack.setText("Enter an IP Address.");
            } else if (portInput.getText().trim() == "") {
                feedBack.setText("Enter a port number.");
            } else {
                int i = 0;
                try {
                    i = Integer.parseInt(portInput.getText().trim());
                    connect(ipInput.getText().trim(), i);

                } catch (NumberFormatException e) {
                    feedBack.setText("Input is an invalid port number.");
                }
            }
        }

    }
    void connect(String ip, int port){
        active=false;
        clientApplication.basicClient.startClient(ip, port, groupInput.getText().trim());
        clientApplication.basicClient.group=groupInput.getText().trim();
    }
    void failedReset(){
        ipInput.setText("");
        portInput.setText("");
        feedBack.setText("Socket couldn't be created.");
        active=true;
    }
    void createdSocket(){
        clientApplication.stageBasicStart.close();
        clientApplication.launchBasicName();
        clientApplication.launchBasicChat();
    }


}
