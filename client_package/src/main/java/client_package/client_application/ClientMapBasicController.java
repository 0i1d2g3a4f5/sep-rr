package client_package.client_application;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import server_package.newmessages.MessageMapSelected;
/**
 * @author Sarp Cagin Erdogan
 */
public class ClientMapBasicController {
    ClientApplication clientApplication;

    @FXML
    private Button choose;

    @FXML
    private ListView<String> mapList;

    @FXML
    void clicked(ActionEvent event) {
        if(!mapList.getSelectionModel().getSelectedItem().equals(null)){
            clientApplication.basicClient.sendSelf(new MessageMapSelected(mapList.getSelectionModel().getSelectedItem()));
        }

    }
    void initializeMaps(JsonArray jsonArray){
        mapList.getItems().clear();
        for(JsonElement temp : jsonArray){
            mapList.getItems().add(temp.getAsString());
        }
    }

}
