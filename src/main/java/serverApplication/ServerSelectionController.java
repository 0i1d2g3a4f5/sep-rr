package serverApplication;

import server_package.advancedServer.AdvancedServer;
import server_package.basicServer.BasicServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
/**
 * @author Sarp Cagin Erdogan
 */
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
