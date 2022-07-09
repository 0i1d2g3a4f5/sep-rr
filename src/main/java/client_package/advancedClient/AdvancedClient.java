package client_package.advancedClient;

import client_application.ClientApplication;
import client_package.Client;
import com.google.gson.JsonObject;

/**
 * @author Sarp Cagin Erdogan
 */
public class AdvancedClient extends Client {
    ClientApplication clientApplication;
    public AdvancedClient(ClientApplication clientApplication){
        this.clientApplication=clientApplication;
    }

    @Override
    public void listen() {

    }

    @Override
    public void process(JsonObject jsonObject) {

    }
}
