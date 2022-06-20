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
        if(server!=null)
            server.shutDownServer();
        Platform.exit();
    }
}
