package server_package.basicServer;

import com.google.gson.JsonArray;
import newmessages.MessageSelectMap;
import server_application.ServerApplication;
import server_package.Client;
import server_package.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Sarp Cagin Erdogan
 */
public class BasicServer extends Server {


    public BasicServer(ServerApplication serverApplication){
        super(serverApplication);
        setTerminated(true);
        setMaxClients(10);
        setCurrentClients(0);
        setCurrentIndex(1);
        setStartingAmount(3);
    }
    Runnable shutDownActions = new Runnable() {
        @Override
        public void run() {
            shutDownServer();
        }
    };
    Runnable waitForClients = new Runnable() {
        @Override
        public void run() {
            while (!getIsTerminated()){
                try {
                    if(getCurrentClients()<getMaxClients()){
                        Socket socket = null;
                        socket = getServerSocket().accept();
                        BasicClient client = new BasicClient(getServerApplication().basicServer,getCurrentIndex(), socket);
                        getClientList().add(client);
                        setCurrentIndex(getCurrentIndex()+1);
                        setCurrentClients(getCurrentClients()+1);
                        client.listen();
                        client.sendProtocolCheck();
                        client.sendPreviousInfo();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };
    void shutDownServer(){


    }
    public void startServerSocket(){
        try {
            setServerSocket(new ServerSocket(1234));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setTerminated(false);
        Thread thread = new Thread(waitForClients);
        thread.setDaemon(true);
        thread.start();

    }
    public Client clientFromId(int number){
        for(Client a : getClientList()){
            if(a.getId()==number){
                return a;
            }
        }
        return null;
    }
    public void checkReady(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                boolean allAI = true;
                for(Client client : getClientList()){
                    if(!client.getIsAI()){
                        allAI=false;
                    }
                }
                if(!allAI){
                    if(getReadyList().size()==getClientList().size()){
                        mapSelect();
                    }
                }
                else if(getClientList().size()>getStartingAmount()){
                    //TRIGGER START
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
    }
    public void mapSelect(){
        for(int i=0; i<getReadyList().size(); i++){
            if(!getReadyList().get(i).getIsAI()){
                JsonArray jsonArray = new JsonArray();
                jsonArray.add("Dizzy Highway");
                jsonArray.add("Other Map");
                jsonArray.add("Placeholder");
                getReadyList().get(i).sendSelf(new MessageSelectMap(jsonArray));
                break;
            }
        }

    }

}
