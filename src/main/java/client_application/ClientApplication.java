package client_application;

import client_package.Client;
import client_package.basicClient.BasicClient;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Sarp Cagin Erdogan, Mark Ringer
 * includes
 *      launcher methods for all the client-side windows
 *      activation methods for all user inputs (e.g. starting point, register cards, etc.)
 */
public class ClientApplication extends Application {
    BasicClient basicClient;
    List<Task> taskList;
    TaskHandler taskHandler;
    ClientStartBasicController clientStartBasicController;
    ClientNameBasicController clientNameBasicController;
    ClientChatBasicController clientChatBasicController;
    ClientLobbyBasicController clientLobbyBasicController;
    ClientMapBasicController clientMapBasicController;
    ClientGameBasicController clientGameBasicController;
    Stage stageSelection, stageBasicStart, stageBasicName, stageBasicChat, stageBasicLobby, stageBasicMap, stageMapView, stageBasicGame;

    public boolean lobbyActive;

    /**
     * lobby launch
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Client.clientLogger.info("Client started");
        lobbyActive = false;
        taskHandler = new TaskHandler(this);
        taskList = new ArrayList<>();
        basicClient = new BasicClient(this);
        launchBasicStart();
    }

    /**
     * add task to taskHandler queue by adding to taskList, execute task and consequently remove from taskList
     * @param task
     */
    public void addAndExecuteTask(Task task){
        this.taskList.add(task);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                while (0 < taskList.size()) {
                    taskHandler.handleTask(taskList.get(0));
                    taskList.remove(0);
                }
            }
        });
    }

    public void selectCard(){
        clientGameBasicController.selectCard();
    }

    public void activateStartingPoint(boolean bo){
        clientGameBasicController.activateStartingPoint(bo);
    }

    public void activateAvailableProgrammingSelection(boolean bo){
        clientGameBasicController.activateProgrammingSelection(bo);
    }

    public void activateRegisterSelection(boolean bo){
        clientGameBasicController.activateRegisterSelection(bo);
    }

    public void resetRegisterCards(){
        clientGameBasicController.resetRegisterCards();
    }

    public void waitForGame(){
        while (clientGameBasicController==null){
            try {
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * launch clientStartBasic.fxml to insert
     */
    public void launchBasicStart(){
        stageBasicStart = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientStartBasic.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 675);
            clientStartBasicController=fxmlLoader.getController();
            clientStartBasicController.clientApplication=this;
            stageBasicStart.setScene(scene);
            stageBasicStart.setResizable(false);
            stageBasicStart.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @author Mark Ringer, Isabel Muhm
     */
    public void launchBasicName(){
        stageBasicName = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientNameBasic.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 675);
            clientNameBasicController=fxmlLoader.getController();
            clientNameBasicController.clientApplication=this;
            stageBasicName.setScene(scene);
            stageBasicName.setResizable(false);
            stageBasicName.show();
            stageBasicName.setOnCloseRequest(e -> {
                getClient().disconnect();
                Platform.exit();
                System.exit(0);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @author Mark Ringer, Vivian Kafadar
     */
    public void launchBasicChat(){
        stageBasicChat = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientChatBasic.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 675);
            clientChatBasicController=fxmlLoader.getController();
            clientChatBasicController.clientApplication=this;
            stageBasicChat.setScene(scene);
            stageBasicChat.setResizable(false);
            stageBasicChat.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @author Mark Ringer, Vivian Kafadar
     */
    public void launchBasicLobby(){
        stageBasicName.close();
        stageBasicLobby = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientLobbyBasic.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 600, 675);
            clientLobbyBasicController=fxmlLoader.getController();
            clientLobbyBasicController.clientApplication=this;
            stageBasicLobby.setScene(scene);
            stageBasicLobby.setResizable(false);
            stageBasicLobby.show();
            stageBasicLobby.setOnCloseRequest(e -> {
                getClient().disconnect();
                Platform.exit();
                System.exit(0);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lobbyActive=true;


    }

    /**
     * @author Mark Ringer, Isabel Muhm
     */
    public void launchBasicMap(){
        System.out.println("tries to launch map");
        stageBasicMap = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientMapBasic.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 632, 675);
            clientMapBasicController=fxmlLoader.getController();
            clientMapBasicController.clientApplication=this;
            stageBasicMap.setScene(scene);
            stageBasicMap.setResizable(false);
            stageBasicMap.show();
            stageBasicMap.setOnCloseRequest(e -> {
                getClient().disconnect();
                Platform.exit();
                System.exit(0);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @author Mark Ringer, Isabel Muhm
     */
    public void launchBasicGame(){
        System.out.println("tried to launch game");
        stageBasicGame = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientGameBasic.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1440, 810);
            clientGameBasicController=fxmlLoader.getController();
            clientGameBasicController.clientApplication=this;
            clientGameBasicController.init();
            stageBasicGame.setScene(scene);
            stageBasicGame.setResizable(false);
            stageBasicGame.show();
            stageBasicGame.setOnCloseRequest(e -> {
                getClient().disconnect();
                Platform.exit();
                System.exit(0);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * @author Sarp Erdogan
     * @return
     */
    public Client getClient(){
        return basicClient;

    }

    public void launchMapView(Scene scene){
        stageMapView = new Stage();
        stageMapView.setScene(scene);
        stageMapView.show();
    }

    /**
     * @author Isabel Muhm
     * @return
     */
    public ClientGameBasicController getGameController() {
        return clientGameBasicController;
    }
}