package server_package;

import gamelogic.Game;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import server_application.ServerApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Sarp Cagin Erdogan
 */

public abstract class Server {

    public static Logger serverLogger = Logger.getLogger("Server");

    static {
        try {
            Properties properties= new Properties();
            properties.load(new FileInputStream("log4j.properties"));
            PropertyConfigurator.configure(properties);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    protected boolean gameStarted;
    protected ServerApplication serverApplication;
    protected Game game = Game.getInstance();
    protected int maxClients, currentClients, currentIndex, startingNonAIAmount, startingAIAmount;
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
        gameStarted=false;
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

    public int getStartingAIAmount() {
        return startingAIAmount;
    }

    public void setStartingAIAmount(int startingAIAmount) {
        this.startingAIAmount = startingAIAmount;
    }

    public int getStartingNonAIAmount() {
        return startingNonAIAmount;
    }

    public void setStartingNonAIAmount(int startingNonAIAmount) {
        this.startingNonAIAmount = startingNonAIAmount;
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

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }
}
