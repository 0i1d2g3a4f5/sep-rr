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
    private boolean isActive=true;

    @FXML
    void launchAdvanced(ActionEvent event) {
        if(isActive) {
            setActive(false);
            serverApplication.setAdvancedServer(new AdvancedServer(serverApplication));
            serverApplication.setIsBasic(false);
        }
    }

    @FXML
    void launchBasic(ActionEvent event) {
        if(isActive){
            setActive(false);
            serverApplication.setBasicServer(new BasicServer(serverApplication));
            serverApplication.getBasicServer().startServerSocket();
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
