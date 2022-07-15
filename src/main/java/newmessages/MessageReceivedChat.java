package newmessages;

import client_application.Task;
import client_application.TaskString1;
import client_application.TaskType;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */


public class MessageReceivedChat extends Message{

    public String message;
    public int from;
    public boolean isPrivate;

    /**
     * @param message
     * @param from
     * @param isPrivate
     */
    public MessageReceivedChat(String message, int from, boolean isPrivate) {
        this.message = message;
        this.from = from;
        this.isPrivate = isPrivate;
        type = "ReceivedChat";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("message", new JsonPrimitive(message));
        jsonObject.add("from", new JsonPrimitive(from));
        jsonObject.add("isPrivate", new JsonPrimitive(isPrivate));
        content = jsonObject;
        //Server.serverLogger.info("Created Received Chat Message: " + this);
    }

    /**
     * @param jsonObject
     */
    public MessageReceivedChat(JsonObject jsonObject){
        super(jsonObject);
        message = content.get("message").getAsString();
        from = content.get("from").getAsInt();
        isPrivate = content.get("isPrivate").getAsBoolean();
        //Server.serverLogger.info("Created Received Chat Message: " + this + " from JSON: " + jsonObject);
    }

    /**
     * @param sClient
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInBackend(SClient sClient, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    /**
     * @param client
     * @param isBasic
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic){
            if(this.isPrivate){
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.CHATMESSAGE, new TaskString1("[Private] " + this.from + " :: " + this.message)));
            }
            else{
                client.getClientApplication().addAndExecuteTask(new Task(TaskType.CHATMESSAGE, new TaskString1(this.from + " :: " + this.message)));
            }
        }else{

        }

    }
}
