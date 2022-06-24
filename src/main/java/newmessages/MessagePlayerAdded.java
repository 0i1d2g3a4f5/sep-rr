package newmessages;

import client_application.Task;
import client_application.TaskContent;
import client_application.TaskType;
import client_package.basicClient.BasicClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import client_package.Client;

import java.io.IOException;

/**
 * @author Isabel Muhm
 */

public class MessagePlayerAdded extends Message{

    public int clientID;
    public String name;
    public int figure;

    public MessagePlayerAdded(int clientID, String name, int figure) {
        this.clientID = clientID;
        this.name = name;
        this.figure = figure;
        type = "PlayerAdded";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("clientID", new JsonPrimitive(clientID));
        jsonObject.add("name", new JsonPrimitive(name));
        jsonObject.add("figure", new JsonPrimitive(figure));
        content = jsonObject;
        //System.out.println("Created PlayerAdded Message: " + this);
    }

    public MessagePlayerAdded(JsonObject jsonObject) {
        super(jsonObject);
        clientID = content.get("clientID").getAsInt();
        name = content.get("name").getAsString();
        figure = content.get("figure").getAsInt();
        //System.out.println("Created NameSet Message: " + this + " from JSON: " + jsonObject);
    }

    @Override
    public void activateMessageInBackend(server_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic) {
            if (this.clientID == client.getId()){
                client.setName(this.name);
                client.setFigure(this.figure);
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.LAUNCHLOBBY, new TaskContent()));
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
            }else{
                client.getPlayerList().add(new BasicClient(this.clientID, this.name, this.figure));
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.UPDATELOBBYLIST, new TaskContent()));
            }
        }
        else{
            //ADVANCED
        }

    }

}
