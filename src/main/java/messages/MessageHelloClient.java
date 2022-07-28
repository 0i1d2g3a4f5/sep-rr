package messages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import client_package.Client;
import server_package.SClient;

import java.io.IOException;

import static utility.GlobalParameters.PROTOCOL_VERSION;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageHelloClient extends Message{
    public String protocol;

    /**
     * converts message to json
     *
     * @param string
     * @author Isabel Muhm
     */
    public MessageHelloClient(String string){
        protocol=string;
        type="HelloClient";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("protocol", new JsonPrimitive(string));
        content=jsonObject;
        //Server.serverLogger.info("Created Hello Client Message: " + this);
    }

    /**
     * converts json to message
     *
     * @param jsonObject
     * @author Isabel Muhm
     */
    public MessageHelloClient(JsonObject jsonObject){
        super(jsonObject);
        protocol=content.get("protocol").getAsString();
        //Server.serverLogger.info("Created Hello Client Message: " + this + " from JSON: " + jsonObject);
    }

    @Override
    public void activateMessageInBackend(SClient sClient) throws IOException, ClientNotFoundException {

    }

    /**
     * checks if clients protocol version equals with servers protocol version
     *
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     * @author Isabel Muhm, Sarp Cagin Erdogan
     */
    @Override
    public void activateMessageInFrontend(Client client) throws IOException, ClientNotFoundException {
        if(protocol.equals(PROTOCOL_VERSION)){
            Client.clientLogger.info("Correct communication protocol verified : " + protocol + ".");
        }
        else{
            Client.clientLogger.error("Communication protocol doesn't match with server.\nServer's protocol: " + protocol + "\nYour protocol: " + PROTOCOL_VERSION);
        }

    }
    /**
     * checks if AIs protocol version equals with servers protocol version
     *
     * @param sentientClient
     * @throws IOException
     * @throws ClientNotFoundException
     * @author Isabel Muhm, Sarp Cagin Erdogan
     */
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        if(protocol.equals(PROTOCOL_VERSION)){
            sentientClient.getLogger().info("Correct communication protocol verified : " + protocol + ".");
            sentientClient.getSentientBehaviour().sayHelloToServer();
        }
        else{
            sentientClient.getLogger().error("Communication protocol doesn't match with server.\nServer's protocol: " + protocol + "\nYour protocol: " + PROTOCOL_VERSION);
        }
    }

}
