package server;

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
public class Application extends javafx.application.Application {
    TaskHandler taskHandler;
    List<Task> taskList = new ArrayList<>();
    Server server;
    ServerController serverController;
    Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = new Stage();
        mainStage.setOnCloseRequest(windowEvent -> shutDown());
        mainStage.show();
        launchWindow();





    }
    void startServer(){
        server = new Server(this);
        server.startServerSocket();
        taskHandler = new TaskHandler(this, server);
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
    void launchWindow(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("server.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 675);
            serverController=fxmlLoader.getController();
            serverController.application=this;
            mainStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    void shutDown(){
        server.shutDownServer();
        Platform.exit();
    }
}
