package server_application;

import client_application.ClientApplication;
import gamelogic.Position;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
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
    private TextField aiText;

    @FXML
    private TextField nonAIText;
    @FXML
    public ListView<String> serverPlayerList;

    @FXML
    public Text textfield;
    @FXML
    void launchBasic(ActionEvent event) {
        if(isActive && !aiText.getText().trim().equals("") && !nonAIText.getText().trim().equals("")){
            isActive=false;
            boolean proper = false;
            int x = -1, y = -1;
            try {
                x = Integer.parseInt(aiText.getText().trim());
                y = Integer.parseInt(nonAIText.getText().trim());
                proper=true;
            } catch (NumberFormatException e) {
                isActive=true;
                textfield.setText("Please choose proper starting amounts.");
            }
            if(proper) {
                serverApplication.setBasicServer(new BasicServer(serverApplication, y, x));
                serverApplication.getBasicServer().startServerSocket();
                Server.serverLogger.info("Basic server launched");
            }

        }

    }

    void init(ServerSelectionControllerVM serverSelectionControllerVM) {
        this.serverSelectionControllerVM = serverSelectionControllerVM;
        this.textfield.textProperty().bindBidirectional(serverSelectionControllerVM.textFieldCopy.textProperty());
        this.serverPlayerList.cellFactoryProperty().bindBidirectional(serverSelectionControllerVM.serverPlayerListCopy.cellFactoryProperty());
        this.serverPlayerList.itemsProperty().bindBidirectional(serverSelectionControllerVM.serverPlayerListCopy.itemsProperty());
    }
}
