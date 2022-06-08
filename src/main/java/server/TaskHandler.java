package server;
/**
 * @author Sarp Cagin Erdogan
 */
public class TaskHandler {
    Application application;
    Server server;

    TaskHandler(Application application, Server server){
        this.application=application;
        this.server=server;
    }

    void handleTask(Task task){
        switch (task.type){
            case "Example" -> {

            }
            default -> {

            }
        }



    }
}
