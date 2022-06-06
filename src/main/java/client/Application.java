package client;

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
    Stage mainStage;
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = new Stage();
        mainStage.show();
        mainStage.setFullScreen(true);
        launchStart();



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
    void launchName() {
        mainStage.setScene(new Scene(new Group(), 1920, 1080));
        mainStage.setFullScreen(true);
    }


}
