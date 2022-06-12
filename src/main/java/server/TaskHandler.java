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
            case "Log" -> {
                application.serverController.setServerLog(task.task.get("Text").getAsString());
            }
            case "AddToList" -> {
                server.clientFromId(task.task.get("ID").getAsInt()).listIndex=application.serverController.addToClientList("ID: " + task.task.get("ID").getAsInt() + " | Unnamed");
                server.application.serverController.printIndexes();
                System.out.println("INDEXES PRINTED");
            }
            case "RemoveFromList" -> {
                application.serverController.removeFromClientList(task.task.get("Index").getAsInt());
            }
            case "UpdateList" -> {
                application.serverController.updateClientList(task.task.get("Index").getAsInt(), task.task.get("Text").getAsString());
            }
            default -> {

            }
        }



    }
}
