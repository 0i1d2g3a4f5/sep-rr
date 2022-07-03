package server_package.server_application;
/**
 * @author Sarp Cagin Erdogan
 */
public class TaskHandler {
    private ServerApplication serverApplication;
    public TaskHandler(ServerApplication serverApplication){
        this.serverApplication=serverApplication;
    }
    public void handleTask(Task task){
        switch (task.taskType){
            case ERROR -> {

            }
            default -> {

            }
        }
    }
}
