package client;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sarp Cagin Erdogan
 */
public class Application extends javafx.application.Application {
    TaskHandler taskHandler;
    List<Task> taskList = new ArrayList<>();
    Client client;
    StartController startController;
    NameController nameController;
    GameController gameController;
    ReconnectController reconnectController;
    Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception {
        taskHandler = new TaskHandler(this, this.client);
        client = new Client(this);
        mainStage = new Stage();
        mainStage.setOnCloseRequest(windowEvent -> shutDown());
        mainStage.show();
        launchStart();
    }
    void executeTasks(){

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
    void addTask(Task task){
        this.taskList.add(task);
        executeTasks();
    }

    void launchStart(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientStart.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 675);
            startController=fxmlLoader.getController();
            startController.application=this;
            mainStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    void launchName(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientName.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 675);
            nameController=fxmlLoader.getController();
            nameController.application=this;
            mainStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        nameController.initialize();

    }
    void launchGame(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("game.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 675);
            gameController=fxmlLoader.getController();
            gameController.application=this;
            mainStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    void launchReconnect(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("reconnect.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 675);
            reconnectController=fxmlLoader.getController();
            reconnectController.application=this;
            mainStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void shutDown(){
        if(!client.isTerminated)
            client.shutDown();
        Platform.exit();
    }


}
