package newmessages;

import client_package.AI.AIClient;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import client_package.Client;

import java.io.IOException;
/**
 * @author Isabel Muhm, Vivian Kafadar, Sarp Cagin Erdogan
 */

public class MessageHelloClient extends Message{
    public String protocol;

    public MessageHelloClient(String string){
        protocol=string;
        type="HelloClient";
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("protocol", new JsonPrimitive(string));
        content=jsonObject;
        //System.out.println("Created Protocol Message: " + this);
    }
    public MessageHelloClient(JsonObject jsonObject){
        super(jsonObject);
        protocol=content.get("protocol").getAsString();
        //System.out.println("Created Protocol Message: " + this + " from JSON: " + jsonObject);
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
    public void activateMessageInFrontend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic) {
            if (this.protocol.equals("Version 1.0")) {
                System.out.println("EVENT :: Correct communication protocol verified.");
            } else {
                System.out.println("ERROR :: False communication protocol.");
            }
        }
        else{
            //ADVANCED
        }
    }
    public void activateMessageInAIFrontend(AIClient client, boolean isBasic) throws IOException, ClientNotFoundException {
        if(isBasic) {
            if (this.protocol.equals(client.getAiController().protocolVersion)) {
                System.out.println("EVENT :: Correct communication protocol verified.");
                client.sayHello(client.getAiController().groupName, client.getAiController().protocolVersion);
            } else {
                System.out.println("ERROR :: False communication protocol.");
            }
        }
        else{
            //ADVANCED
        }
    }

}
