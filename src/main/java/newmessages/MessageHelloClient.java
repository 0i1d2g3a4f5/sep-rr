package newmessages;

import client_package.sentient.SentientClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import client_package.Client;
import server_package.SClient;
import server_package.Server;

import java.io.IOException;

import static utility.GlobalParameters.PROTOCOL_VERSION;

/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageHelloClient extends Message{
    public String protocol;

    /**
     * @param string
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
     * @param jsonObject
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
     * @param client
     * @throws IOException
     * @throws ClientNotFoundException
     */
    @Override
    public void activateMessageInFrontend(Client client) throws IOException, ClientNotFoundException {


    }
    @Override
    public void activateMessageInAIFrontend(SentientClient sentientClient) throws IOException, ClientNotFoundException {
        if(protocol.equals(sentientClient.getProtocolVersion())){
            sentientClient.getLogger().info("Correct communication protocol verified : " + protocol + ".");
            sentientClient.sayHelloToServer();
        }
        else{
            sentientClient.getLogger().error("Communication protocol doesn't match with server.\nServer's protocol: " + protocol + "\nYour protocol: " + sentientClient.getProtocolVersion());
        }


    }

}
