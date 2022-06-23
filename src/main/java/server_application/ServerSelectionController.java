package server_application;

import server_package.advancedServer.AdvancedServer;
import server_package.basicServer.BasicServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
/**
 * @author Sarp Cagin Erdogan
 */
public class ServerSelectionController {
    private ServerApplication serverApplication;
    private boolean isActive;

    @FXML
    void launchAdvanced(ActionEvent event) {
        if(active) {
            active=false;
            serverApplication.setServer(new AdvancedServer(serverApplication));
        }
    }

    @FXML
    void launchBasic(ActionEvent event) {
        if(active){
            active=false;
            serverApplication.setServer(new BasicServer(serverApplication));
            serverApplication.getServer().;
        }

    }
    /* GETTER SETTER
    *
    *
    *
    *
    *
     */
    public ServerApplication getServerApplication(){
        return this.serverApplication;
    }

    public void setServerApplication(ServerApplication serverApplication) {
        this.serverApplication = serverApplication;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
