package clientApplication;

import com.google.gson.JsonArray;
/**
 * @author Sarp Cagin Erdogan
 */
public class TaskJsonArray extends TaskContent{
    public JsonArray jsonArray;
    public TaskJsonArray(JsonArray jsonArray){
        super(jsonArray);
        this.jsonArray=jsonArray;
    }
    public TaskJsonArray(Task task){
        super(task);
        jsonArray=content.get("1").getAsJsonArray();
    }
}
