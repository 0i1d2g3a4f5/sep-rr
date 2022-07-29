package messages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.Client;
import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;

import java.io.IOException;


/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessagePlayerAdded extends Message{

    public int clientID;
    public String name;
    public int figure;

    /**
     * converts message to json
     *
     * @param clientID
     * @param name
     * @param figure
     * @author Isabel Muhm
     */
    public MessagePlayerAdded(int clientID, String name, int figure) {
        super();
        this.clientID = clientID;
        this.name = name;
        this.figure = figure;
        type = "PlayerAdded";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("name", new JsonPrimitive(name));
        jsonObject.add("figure", new JsonPrimitive(figure));
        content = jsonObject;
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessagePlayerAdded(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        name = content.get("name").getAsString();
        figure = content.get("figure").getAsInt();
    }

    /**
     * @param sClient
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {

    }

    /**
     * adds new client to ClientList or sets name, figure and id of existing client
     * creates tasks to launch lobby for new client and to update lobby list
     *
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(Client client) throws IOException, ClientNotFoundException {
        if(clientID!=client.getId()){
            client.getClientList().add(new Client(clientID, name, figure));
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
        }
        else{
            client.setId(clientID);
            client.setName(name);
            client.setFigure(figure);
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.LAUNCHLOBBY, new TaskContent()));
            client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
        }

    }

    /**
     * adds new AI to ClientList or sets name, figure and ID of existing AI client
     * sends message to server to set AIs ready status to true
     *
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        if(clientID!=sentientClient.getId()) {
            sentientClient.getClientList().add(new Client(clientID, name, figure));
        }
        else{
            sentientClient.setId(clientID);
            sentientClient.setName(name);
            sentientClient.setFigure(figure);
            sentientClient.sendSelf(new MessageSetStatus(true));
        }
    }



}
