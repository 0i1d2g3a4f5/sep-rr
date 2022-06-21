package clientApplication;

import client_package.advancedClient.AdvancedClient;
import client_package.basicClient.BasicClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
/**
 * @author Sarp Cagin Erdogan
 */
public class ClientSelectionController {
    ClientApplication clientApplication;

    @FXML
    void launchAdvanced(ActionEvent event) {
        clientApplication.stageSelection.close();
        clientApplication.advancedClient=new AdvancedClient(clientApplication);

    }

    @FXML
    void launchBasic(ActionEvent event) {
        clientApplication.stageSelection.close();
        clientApplication.basicClient = new BasicClient(clientApplication);
        clientApplication.launchBasicStart();



    }

}
