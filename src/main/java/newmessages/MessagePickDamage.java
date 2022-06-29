package newmessages;

import server_package.Client;

import java.io.IOException;
import java.util.ArrayList;

public class MessagePickDamage extends Message {

    //TODO implement constructors etc...
    public MessagePickDamage(int remainingToDraw, ArrayList<String> aviablePiles) {
    }

    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
