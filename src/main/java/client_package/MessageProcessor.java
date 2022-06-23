package client_package;
/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 */
public abstract class MessageProcessor {
    protected Client client;
    public MessageProcessor(Client client){
        setClient(client);
    }




    /* GETTER SETTER
    *
    *
    *
    *
    *
     */

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

