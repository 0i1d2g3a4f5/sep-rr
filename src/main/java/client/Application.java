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
    Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception {
        taskHandler = new TaskHandler(this, this.client);
        client = new Client(this);
        mainStage = new Stage();
        mainStage.show();
        mainStage.setFullScreen(true);
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
            Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
            startController=fxmlLoader.getController();
            startController.application=this;
            mainStage.setScene(scene);
            mainStage.setFullScreen(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    void launchName(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("clientName.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
            nameController=fxmlLoader.getController();
            nameController.application=this;
            mainStage.setScene(scene);
            mainStage.setFullScreen(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        nameController.initialize();

    }


}
