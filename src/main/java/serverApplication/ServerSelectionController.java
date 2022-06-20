package serverApplication;

import advancedServer.AdvancedServer;
import basicServer.BasicServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ServerSelectionController {
    ServerApplication serverApplication;
    boolean active = true;

    @FXML
    void launchAdvanced(ActionEvent event) {
        if(active) {
            active=false;
            serverApplication.advancedServer = new AdvancedServer(serverApplication);
        }
    }

    @FXML
    void launchBasic(ActionEvent event) {
        if(active){
            active=false;
            serverApplication.basicServer = new BasicServer(serverApplication);
            serverApplication.basicServer.startServerSocket();
        }

    }

}
