package advancedServer;

import serverApplication.ServerApplication;

import java.util.List;

/**
 * @author Sarp Cagin Erdogan
 */
public class AdvancedServer {
    ServerApplication serverApplication;
    List<Client> clientList;
    public  AdvancedServer(ServerApplication serverApplication){
        this.serverApplication=serverApplication;
    }
}
