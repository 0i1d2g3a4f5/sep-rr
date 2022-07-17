package server_application;

import client_application.ClientApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import server_package.Server;
import server_package.basicServer.BasicServer;

public class ServerSelectionController {

    private ServerApplication serverApplication;
    private boolean isActive=true;


    @FXML
    private ImageView upperbar1;

    @FXML
    private ImageView upperbar2;

    @FXML
    private ImageView upperbar3;

    @FXML
    void launchBasic(ActionEvent event) {
        if(isActive){
            setActive(false);
            serverApplication.setBasicServer(new BasicServer(serverApplication));
            serverApplication.getBasicServer().startServerSocket();
            Server.serverLogger.info("Basic server launched");
        }

    }
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
