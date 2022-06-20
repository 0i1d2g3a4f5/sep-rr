package serverApplication;

import advancedServer.AdvancedServer;
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
public class ServerApplication extends Application{
    public BasicServer basicServer;
    AdvancedServer advancedServer;
    List<Task> taskList;
    TaskHandler taskHandler;

    ServerSelectionController serverSelectionController;

    @Override
    public void start(Stage stage) throws Exception {
        taskHandler = new TaskHandler(this);
        taskList = new ArrayList<>();
        launchBeginning();
    }

    void addAndExecuteTask(Task task){
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
        Stage selectionStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("serverSelection.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 675);
            serverSelectionController=fxmlLoader.getController();
            serverSelectionController.serverApplication=this;
            selectionStage.setScene(scene);
            selectionStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
