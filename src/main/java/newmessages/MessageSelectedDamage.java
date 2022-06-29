package newmessages;

import server_package.Client;

import java.io.IOException;

public class MessageSelectedDamage extends Message {

    //TODO implement constructor etc...
    @Override
    public void activateMessageInBackend(Client client, boolean isBasic) throws IOException, ClientNotFoundException {

        //TODO draw selected dmg cards

    }

    @Override
    public void activateMessageInFrontend(client_package.Client client, boolean isBasic) throws IOException, ClientNotFoundException {

    }
}
