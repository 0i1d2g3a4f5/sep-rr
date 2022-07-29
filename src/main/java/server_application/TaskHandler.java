package server_application;

/**
 * @author Sarp Erdogan
 */
public class TaskHandler {
    private ServerApplication serverApplication;
    public TaskHandler(ServerApplication serverApplication){
        this.serverApplication = serverApplication;
    }
    public void handleTask(Task task){
        switch (task.taskType){
            case ERROR -> {}
            default -> {}
        }
    }
}