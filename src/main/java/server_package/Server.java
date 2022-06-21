package server_package;

import serverApplication.ServerApplication;
import server_package.advancedServer.AdvancedClient;

import java.util.List;

public abstract class Server {
    ServerApplication serverApplication;


    private List<AdvancedClient> clientList;

    public List<AdvancedClient> getClientList() {
        return clientList;
    }

    public void setClientList(List<AdvancedClient> clientList) {
        this.clientList = clientList;
    }

    public Server(){

    }
    public  Server(ServerApplication serverApplication){
        this.serverApplication=serverApplication;
    }

    public AdvancedClient searchClient(int clientID){
        for (AdvancedClient client:clientList) {
            if(client.getId()==clientID) return client;
        }
        return null;
    }
}
