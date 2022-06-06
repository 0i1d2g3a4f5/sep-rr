package server;

import gamelogic.Game;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Sarp Cagin Erdogan
 */
public class Server {
    List<Client> clientList = new ArrayList<>();
    List<Client> clientInLobbyList = new ArrayList<>();
    Application application;
    ServerSocket serverSocket;
    int maxClients;
    int currentClients;
    int currentIndex;
    Game game;
boolean isTerminated;
    Server(Application application){
        this.application=application;
        isTerminated=true;
        maxClients=10;
        currentClients=0;
        currentIndex=1;
        System.out.println("Server object created.");
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
                            System.out.println("Waiting for client connections...");
                            socket = serverSocket.accept();
                            Client client = new Client(application.server, socket, currentIndex);
                            clientList.add(client);
                            currentIndex++;
                            currentClients++;
                            client.listen();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };

    void startServerSocket(){
        Thread shutDown = new Thread(shutDownActions);
        shutDown.setDaemon(true);
        Runtime.getRuntime().addShutdownHook(shutDown);
        System.out.println("Shutdownhook added.");

        isTerminated=false;

        try {
            serverSocket = new ServerSocket(1234);
            System.out.println("Server socket opened.");
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
