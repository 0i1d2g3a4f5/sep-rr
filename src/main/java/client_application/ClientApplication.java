package client_application;

import client_package.Client;
import client_package.advancedClient.AdvancedClient;
import client_package.basicClient.BasicClient;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Sarp Cagin Erdogan
 */
public class ClientApplication extends Application {
    BasicClient basicClient;
    AdvancedClient advancedClient;
    List<Task> taskList;
    TaskHandler taskHandler;
    ClientSelectionController clientSelectionController;
    ClientStartBasicController clientStartBasicController;
    ClientNameBasicController clientNameBasicController;
    ClientChatBasicController clientChatBasicController;
    ClientLobbyBasicController clientLobbyBasicController;
    ClientMapBasicController clientMapBasicController;
    ClientGameBasicController clientGameBasicController;
    Stage stageSelection, stageBasicStart, stageBasicName, stageBasicChat, stageBasicLobby, stageBasicMap, stageMapView, stageBasicGame;
    public boolean lobbyActive;
    @Override
    public void start(Stage stage) throws Exception {
        Client.clientLogger.info("Client Started");
        lobbyActive=false;
        taskHandler = new TaskHandler(this);
        taskList = new ArrayList<>();
        launchBeginning();


    }
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
    public void activateCardSelection(boolean bo){
        clientGameBasicController.activateCardSelection(bo);
    }
    public void resetRegisterCards(){
        clientGameBasicController.resetRegisterCards();
    }


    public void launchBeginning(){
        stageSelection = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientSelection.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 675);
            clientSelectionController=fxmlLoader.getController();
            clientSelectionController.clientApplication=this;
            stageSelection.setScene(scene);
            stageSelection.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void launchBasicStart(){
        stageBasicStart = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientStartBasic.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 675);
            clientStartBasicController=fxmlLoader.getController();
            clientStartBasicController.clientApplication=this;
            stageBasicStart.setScene(scene);
            stageBasicStart.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void launchBasicName(){
        stageBasicName = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientNameBasic.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 675);
            clientNameBasicController=fxmlLoader.getController();
            clientNameBasicController.clientApplication=this;
            stageBasicName.setScene(scene);
            stageBasicName.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void launchBasicChat(){
        stageBasicChat = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientChatBasic.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 675);
            clientChatBasicController=fxmlLoader.getController();
            clientChatBasicController.clientApplication=this;
            stageBasicChat.setScene(scene);
            stageBasicChat.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void launchBasicLobby(){
        stageBasicLobby = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientLobbyBasic.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 675);
            clientLobbyBasicController=fxmlLoader.getController();
            clientLobbyBasicController.clientApplication=this;
            stageBasicLobby.setScene(scene);
            stageBasicLobby.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        lobbyActive=true;


    }
    public void launchBasicMap(){
        stageBasicMap = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientMapBasic.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 675);
            clientMapBasicController=fxmlLoader.getController();
            clientMapBasicController.clientApplication=this;
            stageBasicMap.setScene(scene);
            stageBasicMap.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public void launchBasicGame(){
        stageBasicGame = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientGameBasic.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1440, 810);
            clientGameBasicController=fxmlLoader.getController();
            clientGameBasicController.clientApplication=this;
            clientGameBasicController.init();
            stageBasicGame.setScene(scene);
            stageBasicGame.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    public Client getClient(){
        if(basicClient!=null){
            return basicClient;
        }
        if(advancedClient!=null){
            return advancedClient;
        }
        else {
            throw new NullPointerException("CLIENT NOT FOUND");
        }

    }
    public void launchMapView(Scene scene){
        stageMapView = new Stage();
        stageMapView.setScene(scene);
        stageMapView.show();
    }

}