package server;

import gamelogic.Game;
import javafx.util.Pair;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Sarp Cagin Erdogan
 */
public class Server {
    List<Client> clientList = new ArrayList<>();
    List<Client> clientInLobbyList = new ArrayList<>();
    HashMap<String, Pair<Integer, String>> idAndHashFromName = new HashMap<>();
    Application application;
    ServerSocket serverSocket;
    int maxClients;
    int currentClients;
    int currentIndex;
    Game game;
    boolean isTerminated;
    public Server(Application application){
        this.application=application;
        isTerminated=true;
        maxClients=10;
        currentClients=0;
        currentIndex=1;
    }
    //Only for Testing
    public Server(){

    }
    Runnable shutDownActions = new Runnable() {
        @Override
        public void run() {

        }
    };
    Runnable waitForClients = new Runnable() {
        @Override
        public void run() {
            while (!isTerminated){
                try {
                    if(currentClients<maxClients){

                            Socket socket = null;
                            //System.out.println("Waiting for client connections...");
                            socket = serverSocket.accept();
                            Client client = new Client(application.server, socket, currentIndex);
                            //System.out.println("Client " + currentIndex + " connected.");
                            clientList.add(client);
                            currentIndex++;
                            currentClients++;
                            client.listen();
                            client.sendProtocolCheck();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };
    void printList(){
        for(String name : idAndHashFromName.keySet()){
            Pair<Integer, String> pair = (Pair<Integer, String>) (idAndHashFromName.get(name));
            System.out.println("Name: \"" + name + "\", ID: \"" + pair.getKey() + "\", Hash: " + pair.getValue());
        }
    }

    void startServerSocket(){
        Thread shutDown = new Thread(shutDownActions);
        shutDown.setDaemon(true);
        Runtime.getRuntime().addShutdownHook(shutDown);

        isTerminated=false;

        try {
            serverSocket = new ServerSocket(1234);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Thread thread = new Thread(waitForClients);
        thread.setDaemon(true);
        thread.start();
    }
    void checkGameStart(){

    }
    void shutDownServer(){
        Thread thread = new Thread(shutDownActions);
        thread.setDaemon(true);
        thread.start();
    }




}
