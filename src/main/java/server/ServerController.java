package server;

import gamelogic.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.io.IOException;

/**
 * @author Qinyi
 * @author Sarp Cagin Erdogan
 * ServerController connected to Application and Server
 */
public class ServerController {

    Application application;

    @FXML
    private Button disconnectChosen;
    @FXML
    private Button removeChosen;

    @FXML
    private TextArea serverLog;

    @FXML
    private Button startServer;

    @FXML
    private Button terminateServer;

    @FXML
    private ListView<String> playerList;

    @FXML
    void startServer(ActionEvent event) throws IOException {
        application.startServer();
    }

    @FXML
    void stopServer(ActionEvent event) {
        application.server.shutDownServer();
    }

    void setServerLog(String string){
        serverLog.appendText("\n"+string);
    }
    void updateClientList(int index, String string){
        playerList.getItems().set(index, string);
        System.out.println("CLIENT LIST UPDATED");
        printIndexes();
        System.out.println("INDEXES PRINTED");
    }
    void removeFromClientList(int index){
        playerList.getItems().remove(index);
        application.server.updateIndexes(index);
        System.out.println("CLIENT LIST GOT REMOVAL");
        printIndexes();
        System.out.println("INDEXES PRINTED");
    }
    int addToClientList(String string){
        playerList.getItems().add(string);
        System.out.println("CLIENT LIST GOT ADDITION");
        return playerList.getItems().size()-1;

    }
    void printIndexes(){
        for(Client client: application.server.clientList){
            if(!client.isTerminated)
                client.printIndex();
        }
    }
    @FXML
    void disconnectClient() {
        System.out.println("SELECTED: " + playerList.getSelectionModel().getSelectedIndex());
        int toDc = playerList.getSelectionModel().getSelectedIndex();
        for(Client client : application.server.clientList){
            if(client.listIndex==toDc){
                client.disconnect();
                break;
            }

        }

    }
    @FXML
    void removeClient() {
        System.out.println("SELECTED: " + playerList.getSelectionModel().getSelectedIndex());
        int toRemove = playerList.getSelectionModel().getSelectedIndex();
        for(Client client : application.server.clientList){
            if(client.listIndex==toRemove){
                client.shutDownClient();
                break;
            }

        }
    }
}

