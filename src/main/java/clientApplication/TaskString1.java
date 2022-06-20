package clientApplication;
/**
 * @author Sarp Cagin Erdogan
 */
public class TaskString1 extends TaskContent{
    public String string1;
    public TaskString1(String inp1){
        super(inp1);
        string1=inp1;
    }
    public TaskString1(Task task){
        super(task);
        string1=content.get("1").getAsString();
    }

}
