package server_package.basicServer;

import com.google.gson.JsonArray;
import messages.MessageSelectMap;
import server_application.ServerApplication;
import server_package.SClient;
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
        setMaxClients(6);
        setCurrentClients(0);
        setCurrentIndex(1);
        setStartingAmount(1);
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
                        BasicSClient client = new BasicSClient(getServerApplication().getBasicServer(),getCurrentIndex(), socket);
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
    @Override
    public void startServerSocket(){
        try {
            setServerSocket(new ServerSocket(1234));
            serverApplication.serverSelectionControllerVM.setTextfield("Server socket started.");
            Server.serverLogger.info("Server socket started");
        } catch (IOException e) {
            Server.serverLogger.error("Server socket not started");
            throw new RuntimeException(e);
        }
        setTerminated(false);
        Thread thread = new Thread(waitForClients);
        thread.setDaemon(true);
        thread.start();

    }
    @Override
    public void checkReady(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                boolean allAI = true;
                for(SClient client : getClientList()){
                    if(!client.getIsAI()){
                        allAI=false;
                    }
                }
                if(!allAI){
                    if(getReadyList().size()==getClientList().size() && getReadyList().size()>=startingAmount){
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
    @Override
    public void mapSelect(){
        for(int i=0; i<getReadyList().size(); i++){
            if(!getReadyList().get(i).getIsAI()){
                JsonArray jsonArray = new JsonArray();
                jsonArray.add("death_trap");
                jsonArray.add("dizzy_highway");
                jsonArray.add("extra_crispy");
                jsonArray.add("lost_bearings");
                jsonArray.add("twister");
                getReadyList().get(i).sendSelf(new MessageSelectMap(jsonArray));
                break;
            }
        }

    }

}
