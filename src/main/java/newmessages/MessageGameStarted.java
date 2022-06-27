package newmessages;

import com.google.gson.JsonObject;
import server_package.Client;

import java.io.IOException;

public class MessageGameStarted extends Message{

    public MessageGameStarted(JsonObject jsonObject){
        super(jsonObject);
    }

    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInAIFrontend(client_package.AI.AIClient client, boolean isBasic) throws IOException, ClientNotFoundException{

    }
}