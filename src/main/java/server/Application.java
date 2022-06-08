package server;

import com.google.gson.JsonObject;
import javafx.stage.Stage;
import newmessages.MessageProtocol;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sarp Cagin Erdogan
 */
public class Application extends javafx.application.Application {
    TaskHandler taskHandler;
    List<Task> taskList = new ArrayList<>();
    Server server;
    @Override
    public void start(Stage stage) throws Exception {
        server = new Server(this);
        server.startServerSocket();
        taskHandler = new TaskHandler(this, server);




    }
}
