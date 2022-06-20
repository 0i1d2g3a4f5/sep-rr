package clientApplication;
/**
 * @author Sarp Cagin Erdogan
 */
public class TaskInt1 extends TaskContent{
    public int int1;
    public TaskInt1(int inp1){
        super(inp1);
        int1=inp1;
    }
    public TaskInt1(Task task){
        super(task);
        int1=content.get("1").getAsInt();
    }
}
