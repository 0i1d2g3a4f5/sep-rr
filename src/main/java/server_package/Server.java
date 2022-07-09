package server_package;

import gamelogic.Game;
import server_application.ServerApplication;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Sarp Cagin Erdogan
 */

public abstract class Server {
    protected ServerApplication serverApplication;
    protected Game game = Game.getInstance();
    protected int maxClients, currentClients, currentIndex, startingAmount;
    protected List<SClient> sClientList;
    protected List<SClient> readyList;
    protected boolean isTerminated, isBasic;
    protected ServerSocket serverSocket;


    public List<SClient> getClientList() {
        return sClientList;
    }

    public void setClientList(List<SClient> sClientList) {
        this.sClientList = sClientList;
    }
    public  Server(ServerApplication serverApplication){
        setServerApplication(serverApplication);
        setClientList(new ArrayList<>());
        setReadyList(new ArrayList<>());
    }



    public Game getGame() {
        return game;
    }

    public SClient clientFromID(int clientID){
        for (SClient sClient : sClientList) {
            if(sClient.getId()==clientID) return sClient;
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

    public List<SClient> getReadyList() {
        return readyList;
    }

    public void setReadyList(List<SClient> readyList) {
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
