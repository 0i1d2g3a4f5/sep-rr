package client_application;

import client_package.Client;
import client_package.basicClient.BasicClient;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import newmessages.MessageSetStatus;
/**
 * @author Sarp Cagin Erdogan
 */
public class ClientLobbyBasicController {
    ClientApplication clientApplication;


    @FXML
    private Button notReady;

    @FXML
    private ListView<String> playerList;

    @FXML
    private Button ready;

    @FXML
    void clicked() {
        ready.setDisable(true);
        ready.setVisible(false);
        clientApplication.basicClient.sendSelf(new MessageSetStatus(true));

    }

    @FXML
    void notClicked() {
        notReady.setDisable(true);
        notReady.setVisible(false);
        clientApplication.basicClient.sendSelf(new MessageSetStatus(false));

    }
    void updateList(){
        playerList.getItems().clear();
        playerList.getItems().add(0, "{"+clientApplication.basicClient.getId()+"} " + clientApplication.basicClient.getName() + " [" + clientApplication.basicClient.isReady() + "]" );
        for(int i=1; i<clientApplication.basicClient.getPlayerList().size()+1; i++){
            Client temp = clientApplication.basicClient.getPlayerList().get(i-1);
            playerList.getItems().add(i, "{"+temp.getId()+"} " + temp.getName() + " [" + temp.isReady() + "]" );
        }


    }
    void activateReady(){
        System.out.println("ACTIVATED READY");
        ready.setVisible(true);
        ready.setDisable(false);
    }
    void activateNot(){
        System.out.println("ACTIVATED NOT READY");
        notReady.setVisible(true);
        notReady.setDisable(false);
    }

}
