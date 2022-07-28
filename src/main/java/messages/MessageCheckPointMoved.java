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
import messages.Message;

public class MessageCheckPointMoved extends Message {

    public int checkpointID;
    public int x;
    public int y;

    public MessageCheckPointMoved(int checkpointID, int y, int x){
        this.checkpointID = checkpointID;
        this.x = x;
        this.y = y;
        type = "CheckPointMoved";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("checkpointID", new JsonPrimitive(checkpointID));
        jsonObject.add("x", new JsonPrimitive(x));
        jsonObject.add("y", new JsonPrimitive(y));
        content = jsonObject;
    }

    public MessageCheckPointMoved(JsonObject jsonObject){
        super(jsonObject);
        checkpointID = content.get("checkpointID").getAsInt();
        x = content.get("x").getAsInt();
        y = content.get("y").getAsInt();
    }

    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInFrontend(Client client) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {

    }
}
