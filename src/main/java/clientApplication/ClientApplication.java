package clientApplication;

import advancedClient.AdvancedClient;
import advancedServer.AdvancedServer;
import basicClient.BasicClient;
import basicServer.BasicServer;
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
    Stage stageSelection, stageBasicStart, stageBasicName, stageBasicChat, stageBasicLobby, stageBasicMap;
    public boolean lobbyActive;
    @Override
    public void start(Stage stage) throws Exception {
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
        stageBasicName.close();
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

}
