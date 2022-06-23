package newmessages;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import client_package.Client;

import java.io.IOException;

/**
 * @author Sarp Cagin Erdogan
 */
public class MessageHelloClient extends Message{
    public String protocol;

    public MessageHelloClient(String string){
        super(string);
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
            if (this.protocol.equals("Version 0.1")) {
                System.out.println("EVENT :: Correct communication protocol verified.");
            } else {
                System.out.println("ERROR :: False communication protocol.");
            }
        }
        else{
            //ADVANCED
        }
    }

}
