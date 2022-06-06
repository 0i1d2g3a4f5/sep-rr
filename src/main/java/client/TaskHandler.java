package client;


/**
 * @author Sarp Cagin Erdogan
 */
public class TaskHandler {
    Application application;
    Client client;

    TaskHandler(Application application, Client client){
        this.application=application;
        this.client=client;
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
