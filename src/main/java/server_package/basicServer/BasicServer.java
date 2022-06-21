package server_package.basicServer;

import com.google.gson.JsonArray;
import newmessages.MessageSelectMap;
import serverApplication.ServerApplication;
import server_package.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sarp Cagin Erdogan
 */
public class BasicServer extends Server {
    List<BasicClient> clientList = new ArrayList<>();
    List<BasicClient> readyList = new ArrayList<>();
    ServerApplication serverApplication;
    ServerSocket serverSocket;
    int maxClients, currentClients, currentIndex, startingAmount;
    boolean isTerminated;
    public BasicServer(ServerApplication serverApplication){
        this.serverApplication=serverApplication;
        this.isTerminated=true;
        this.maxClients=10;
        this.currentClients=0;
        this.currentIndex=1;
        this.startingAmount=3;
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
            while (!isTerminated){
                try {
                    if(currentClients<maxClients){
                        Socket socket = null;
                        socket = serverSocket.accept();
                        BasicClient client = new BasicClient(serverApplication.basicServer, currentIndex, socket);
                        clientList.add(client);
                        currentIndex++;
                        currentClients++;
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
            serverSocket = new ServerSocket(1234);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        isTerminated=false;
        Thread thread = new Thread(waitForClients);
        thread.setDaemon(true);
        thread.start();

    }
    public BasicClient clientFromId(int number){
        for(BasicClient a : clientList){
            if(a.id==number){
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
                for(BasicClient client : clientList){
                    if(!client.isAI){
                        allAI=false;
                    }
                }
                if(!allAI){
                    if(readyList.size()==clientList.size()){
                        mapSelect();
                    }
                }
                else if(clientList.size()>startingAmount){
                    //TRIGGER START
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();
    }
    public void mapSelect(){
        for(int i=0; i<readyList.size(); i++){
            if(!readyList.get(i).isAI){
                JsonArray jsonArray = new JsonArray();
                jsonArray.add("Dizzy Highway");
                jsonArray.add("Other Map");
                jsonArray.add("Placeholder");
                readyList.get(i).sendSelf(new MessageSelectMap(jsonArray));
                break;
            }
        }

    }

}
