package client_package.client_application;
/**
 * @author Sarp Cagin Erdogan
 */
public class Task {
    TaskType taskType;
    TaskContent taskContent;

    public Task(TaskType taskType, TaskContent taskContent){
        this.taskType=taskType;
        this.taskContent=taskContent;
    }

    public Task() {

    }
}
