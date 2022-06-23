package server_package.basicServer;

import com.google.gson.JsonObject;
import newmessages.*;
import server_package.Client;
import server_package.MessageProcessor;

import java.io.IOException;

/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 */
public class BasicMessageProcessor extends MessageProcessor {
    BasicClient client;

    public BasicMessageProcessor(Client client){
        super(client);
    }


    public void process(JsonObject jsonObject) throws ClientNotFoundException, IOException {
        process(jsonObject, true);

    }
}
