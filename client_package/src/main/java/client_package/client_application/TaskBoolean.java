package client_package.client_application;
/**
 * @author Sarp Cagin Erdogan
 */
public class TaskBoolean extends TaskContent{
    public boolean bool;
    public TaskBoolean(boolean inp1){
        super(inp1);
        bool=inp1;
    }
    public TaskBoolean(Task task){
        super(task);
        bool=content.get("1").getAsBoolean();
    }
}