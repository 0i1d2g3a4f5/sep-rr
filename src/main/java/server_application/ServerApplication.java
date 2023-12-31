package server_application;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import server_package.Server;
import server_package.basicServer.BasicServer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger ;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Sarp Cagin Erdogan
 * includes
 *      launcher methods for all the server-side windows
 *      activation methods for all user inputs (e.g. starting point, register cards, etc.)
 */
public class ServerApplication extends Application{

    public static void main(String[] args){
        launch(args);
    }
    private BasicServer basicServer;
    private boolean isBasic;
    private List<Task> taskList;
    private TaskHandler taskHandler;
    public ServerSelectionController serverSelectionController;
    public ServerSelectionControllerVM serverSelectionControllerVM;

    /**
     * server launch
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        Server.serverLogger.info("Server started");
        taskHandler = new TaskHandler(this);
        taskList = new ArrayList<>();
        launchBeginning();
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
                    Server.serverLogger.info("Task executed");
                }
            }
        });
    }

    private void launchBeginning(){
        Stage selectionStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("serverSelection.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), 416, 500);
            serverSelectionController=fxmlLoader.getController();
            serverSelectionController.serverApplication=this;
            serverSelectionControllerVM = new ServerSelectionControllerVM(serverSelectionController);
            serverSelectionController.init(serverSelectionControllerVM);
            selectionStage.setResizable(false);
            selectionStage.setScene(scene);
            selectionStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

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
}
