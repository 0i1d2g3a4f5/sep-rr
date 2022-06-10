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
 * ServerController connected to Application and Server
 */
public class ServerController {

    Application application;

    @FXML
    private TextArea serverWindow;

    @FXML
    private Button startServer;

    @FXML
    private Button terminateServer;

    @FXML
    private ListView<String> playerList;

    @FXML
    void startServer(ActionEvent event) throws IOException {
        if(application.server.isTerminated) {
            application.server.startServerSocket();
        }
    }

    @FXML
    void stopServer(ActionEvent event) {
        Thread thread = new Thread(application.server.shutDownActions);
        thread.setDaemon(true);
        thread.start();
    }


}

