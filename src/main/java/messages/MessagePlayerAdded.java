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
     * @param clientID
     * @param name
     * @param figure
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
        //Server.serverLogger.info("Created Player Added Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessagePlayerAdded(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        name = content.get("name").getAsString();
        figure = content.get("figure").getAsInt();
        //Server.serverLogger.info("Created Player Added Message: " + this + " from JSON: " + jsonObject);
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
