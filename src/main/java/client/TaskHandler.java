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
            case "SwitchToName" -> {
                application.launchName();
            }
            case "FailedSocket" -> {
                application.startController.failedReset();
            }
            case "NameUnavailable" -> {
                application.nameController.setFeedback(task.task);
            }
            case "FigureUnavailable" -> {
                application.nameController.setFeedback(task.task);
            }
            case "ValuesAccepted" -> {
                application.nameController.setFeedback(task.task);
            }
            default -> {

            }
        }



    }
}
