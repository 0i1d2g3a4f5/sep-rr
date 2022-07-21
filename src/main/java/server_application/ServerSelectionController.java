package server_application;

import client_application.ClientApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import server_package.SClient;
import server_package.Server;
import server_package.basicServer.BasicServer;

public class ServerSelectionController {

    private ServerApplication serverApplication;
    private boolean isActive=true;

    @FXML
    private ListView<String> serverPlayerList;


    @FXML
    private Text textfield;

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

    public void setTextfield(String text){
        this.textfield.setText(text);
    }

    public void updateServerList() {
        serverPlayerList.getItems().clear();
        for (int i = 1; i < serverApplication.getBasicServer().getClientList().size() + 1; i++) {
            SClient temp = serverApplication.getBasicServer().getClientList().get(i - 1);
            serverPlayerList.getItems().add(i, "{" + temp.getId() + "} " + temp.getName() + " [" + temp.getIsReady() + "]");
        }
    }

}
