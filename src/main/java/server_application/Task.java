package server_application;

import server_package.Server;

/**
 * @author Sarp Cagin Erdogan
 */
public class Task {
    TaskType taskType;
    TaskContent taskContent;

    public Task(TaskType taskType, TaskContent taskContent){
        this.taskType = taskType;
        this.taskContent = taskContent;
        Server.serverLogger.info("Task type and content set");
    }

    public Task() {}
}
