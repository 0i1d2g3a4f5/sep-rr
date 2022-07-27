package client_application;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import messages.MessageMapSelected;
/**
 * @author Sarp Cagin Erdogan
 */
public class ClientMapBasicController {
    ClientApplication clientApplication;
    boolean active = true;

    @FXML
    private Button choose;

    @FXML
    private ListView<String> mapList;


    /**
     * sends selected map choice to server
     *
     * @param event
     */
    @FXML
    void clicked(ActionEvent event) {
        if(!mapList.getSelectionModel().getSelectedItem().equals(null)){
            active=false;
            clientApplication.basicClient.sendSelf(new MessageMapSelected(mapList.getSelectionModel().getSelectedItem()));
        }

    }

    /**
     * shows all available maps on clickable list
     *
     * @param jsonArray
     */
    void initializeMaps(JsonArray jsonArray){
        mapList.getItems().clear();
        for(JsonElement temp : jsonArray){
            mapList.getItems().add(temp.getAsString());
        }
    }

}
