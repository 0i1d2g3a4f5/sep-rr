package server_package.server_application;

import server_package.advancedServer.AdvancedServer;
import server_package.basicServer.BasicServer;
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
    private BasicServer basicServer;
    private AdvancedServer advancedServer;
    private boolean isBasic;
    private List<Task> taskList;
    private TaskHandler taskHandler;

    private ServerSelectionController serverSelectionController;

    @Override
    public void start(Stage stage) throws Exception {
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

    private void launchBeginning(){
        Stage selectionStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("serverSelection.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 1200, 675);
            serverSelectionController=fxmlLoader.getController();
            serverSelectionController.setServerApplication(this);
            selectionStage.setScene(scene);
            selectionStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    /* GETTER SETTER
    *
    *
    *
    *
    *
    *
    *
     */



    public boolean isBasic() {
        return isBasic;
    }

    public void setIsBasic(boolean basic) {
        isBasic = basic;
    }

    public BasicServer getBasicServer() {
        return basicServer;
    }

    public void setBasicServer(BasicServer basicServer) {
        this.basicServer = basicServer;
    }

    public AdvancedServer getAdvancedServer() {
        return advancedServer;
    }

    public void setAdvancedServer(AdvancedServer advancedServer) {
        this.advancedServer = advancedServer;
    }
}
