package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
/**
 * @author Sarp Cagin Erdogan
 */
public class StartController {
    Application application;


    @FXML
    private Text feedBack;
    @FXML
    private TextField ipInput;

    @FXML
    private TextField portInput;

    @FXML
    void connectButton(ActionEvent event) {
        if(!ipInput.getText().trim().equals("") && !portInput.getText().trim().equals("")) {
            checkIfNumber();
        }
    }

    @FXML
    void connectEnter(KeyEvent event) {
        if(event.getCode()== KeyCode.ENTER){
            if(!ipInput.getText().trim().equals("") && !portInput.getText().trim().equals("")) {
                checkIfNumber();
            }
        }

    }
    void checkIfNumber(){
        int i=0;

        try {
            i = Integer.parseInt(portInput.getText().trim());
            connect(ipInput.getText().trim(), i);

        } catch(NumberFormatException e){
            portInput.setText("WRITE NUMBER PLS :(");
        }


    }
    void connect(String ip, int port){
        application.client.startClient(ip, port);
    }
    void failedReset(){
        ipInput.setText("");
        portInput.setText("");
        feedBack.setText("Socket couldn't be created.");
    }

}
