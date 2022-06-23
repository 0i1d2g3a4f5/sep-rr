package server_package;

import gamelogic.Game;
import server_application.ServerApplication;

import java.net.ServerSocket;
import java.util.List;
/**
 * @author Sarp Cagin Erdogan
 */

public abstract class Server {
    protected ServerApplication serverApplication;
    protected Game game = Game.getInstance();
    protected int maxClients, currentClients, currentIndex, startingAmount;
    protected List<Client> clientList;
    protected List<Client> readyList;
    protected boolean isTerminated, isBasic;
    protected ServerSocket serverSocket;


    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
    }
    public  Server(ServerApplication serverApplication){
        setServerApplication(serverApplication);
    }



    public Game getGame() {
        return game;
    }

    public Client clientFromID(int clientID){
        for (Client client:clientList) {
            if(client.getId()==clientID) return client;
        }
        return null;
    }
    public abstract void checkReady();
    public abstract void startServerSocket();
    public abstract void mapSelect();
    /* GETTER SETTER
     *
     *
     *
     *
     *
     *
     *
     *
     */


    public ServerApplication getServerApplication() {
        return serverApplication;
    }

    public void setServerApplication(ServerApplication serverApplication) {
        this.serverApplication = serverApplication;
    }

    public int getMaxClients() {
        return maxClients;
    }

    public void setMaxClients(int maxClients) {
        this.maxClients = maxClients;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getStartingAmount() {
        return startingAmount;
    }

    public void setStartingAmount(int startingAmount) {
        this.startingAmount = startingAmount;
    }

    public List<Client> getReadyList() {
        return readyList;
    }

    public void setReadyList(List<Client> readyList) {
        this.readyList = readyList;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public boolean isBasic() {
        return isBasic;
    }

    public void setBasic(boolean basic) {
        isBasic = basic;
    }
    public boolean getIsTerminated() {
        return isTerminated;
    }
    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
    }
    public int getCurrentClients(){
        return currentClients;
    }
    public void setCurrentClients(int i){
        currentClients=i;
    }


}
