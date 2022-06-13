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
                application.nameController.setFeedback(task.task.get("Text").getAsString());
            }
            case "FigureUnavailable" -> {
                application.nameController.setFeedback(task.task.get("Text").getAsString());
            }
            case "ValuesAccepted" -> {
                application.nameController.setFeedback(task.task.get("Text").getAsString());
                application.launchGame();
            }
            case "A" -> {
                application.nameController.setFeedback("Name \""+task.task.get("Text").getAsString()+"\" is set.");
            }
            case "WrongName" -> {
                client.application.reconnectController.setFeedBack("User with name \"" + task.task.get("Name").getAsString() + "\" doesn't exist." );
                client.application.reconnectController.reset();
            }case "WrongPass" -> {
                client.application.reconnectController.setFeedBack("Wrong password.");
                client.application.reconnectController.reset();
            }

            default -> {

            }
        }



    }
}
