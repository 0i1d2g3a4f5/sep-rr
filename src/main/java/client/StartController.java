package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class StartController {
    Application application;

    @FXML
    private TextField ipInput;

    @FXML
    private TextField portInput;

    @FXML
    void connectButton(ActionEvent event) {
        boolean number = false;
        int i=0;

        try {
            i = Integer.parseInt(portInput.getText().trim());
            number=true;

        } catch(NumberFormatException e){
            number=false;
            portInput.setText("WRITE NUMBER PLS :(");
        }
        if(number){
            connect(ipInput.getText().trim(), i);
        }

    }

    @FXML
    void connectEnter(KeyEvent event) {
        if(event.getCode()== KeyCode.ENTER){
            boolean number = false;
            int i=0;

            try {
                i = Integer.parseInt(portInput.getText().trim());
                number=true;

            } catch(NumberFormatException e){
                number=false;
                portInput.setText("WRITE NUMBER PLS :(");
            }
            if(number){
                connect(ipInput.getText().trim(), i);
            }
        }

    }
    void connect(String ip, int port){
        application.launchName();
    }

}
