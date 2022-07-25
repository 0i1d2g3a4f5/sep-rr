package server_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import server_package.SClient;
import server_package.Server;
import server_package.basicServer.BasicServer;

public class ServerSelectionControllerVM {

    public ServerSelectionController serverSelectionController;
    public ListView<String> serverPlayerListCopy;
    public Text textFieldCopy;
    public ServerSelectionControllerVM(ServerSelectionController serverSelectionController){
        this.serverSelectionController=serverSelectionController;
        this.serverPlayerListCopy=new ListView<String>();
        this.textFieldCopy=new Text();
    }

    public boolean isActive() {
        return serverSelectionController.isActive;
    }

    public void setActive(boolean active) {
        serverSelectionController.isActive = active;
    }

    public void setTextfield(String text){
        this.textFieldCopy.setText(text);
    }

    public void updateServerList() {
        serverPlayerListCopy.getItems().clear();
        for (int i = 0; i < serverSelectionController.serverApplication.getBasicServer().getClientList().size() ; i++) {
            SClient temp = serverSelectionController.serverApplication.getBasicServer().getClientList().get(i);
            serverPlayerListCopy.getItems().add(i, "{" + temp.getId() + "} " + temp.getName() + " [" + temp.getIsReady() + "]");
        }
        System.out.println("ALALALALALALA");
    }
}
