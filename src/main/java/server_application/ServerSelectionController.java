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

    public ServerSelectionControllerVM serverSelectionControllerVM;

    public ServerApplication serverApplication;
    public boolean isActive=true;

    @FXML
    public ListView<String> serverPlayerList;

    @FXML
    public Text textfield;
    @FXML
    void launchBasic(ActionEvent event) {
        if(isActive){
            isActive=false;
            serverApplication.setBasicServer(new BasicServer(serverApplication));
            serverApplication.getBasicServer().startServerSocket();
            Server.serverLogger.info("Basic server launched");
        }

    }

    void init(ServerSelectionControllerVM serverSelectionControllerVM) {
        this.serverSelectionControllerVM = serverSelectionControllerVM;
        this.textfield.textProperty().bindBidirectional(serverSelectionControllerVM.textFieldCopy.textProperty());
        this.serverPlayerList.cellFactoryProperty().bindBidirectional(serverSelectionControllerVM.serverPlayerListCopy.cellFactoryProperty());
        this.serverPlayerList.itemsProperty().bindBidirectional(serverSelectionControllerVM.serverPlayerListCopy.itemsProperty());
    }
}
