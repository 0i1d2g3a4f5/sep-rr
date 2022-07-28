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

/**
 * @authors Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

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

    /**
     * checks if minimal amount of AI/nonAI players is set, if yes it launches the server
     * @author Sarp, Isabel
     * @param event
     */
    @FXML
    void launchBasic(ActionEvent event) {
        System.out.println("tries to launch");
        if(aiText.getText().trim().equals("") && nonAIText.getText().trim().equals("")) {
            textfield.setText("Please set the minimum amount of AI and non AI players first");
        }
        else if(aiText.getText().trim().equals("")){
            textfield.setText("Please set the minimum amount of AI players first");
        }
        else if(nonAIText.getText().trim().equals("")){
            textfield.setText("Please set the minimum amount of non AI players first");
        }
        System.out.println("aktiv: " + isActive);
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
            System.out.println("x: " + x + ", y: " + y);
            if(proper) {
                if(x+y<2){
                    isActive = true;
                    textfield.setText("The amount of minimal all in all players is 2");
                }
                else if(x+y>6){
                    isActive = true;
                    textfield.setText("The amount of maximal players is 6");
                }
                else {
                    serverApplication.setBasicServer(new BasicServer(serverApplication, y, x));
                    serverApplication.getBasicServer().startServerSocket();
                    disableSelection();
                    textfield.setText("Server started");
                    Server.serverLogger.info("Basic server launched");
                }
            }

        }

    }

    void init(ServerSelectionControllerVM serverSelectionControllerVM) {
        this.serverSelectionControllerVM = serverSelectionControllerVM;
        this.textfield.textProperty().bindBidirectional(serverSelectionControllerVM.textFieldCopy.textProperty());
        this.serverPlayerList.cellFactoryProperty().bindBidirectional(serverSelectionControllerVM.serverPlayerListCopy.cellFactoryProperty());
        this.serverPlayerList.itemsProperty().bindBidirectional(serverSelectionControllerVM.serverPlayerListCopy.itemsProperty());
    }

    void disableSelection(){
        nonAIText.setDisable(true);
        aiText.setDisable(true);
    }
}
