package client_application;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
/**
 * @author Sarp Cagin Erdogan
 */
public class TaskJsonObject extends TaskContent{
    public JsonObject jsonObject;
    public TaskJsonObject(JsonObject jsonObject){
        super(jsonObject);
        this.jsonObject=jsonObject;
    }
    public TaskJsonObject(Task task){
        super(task);
        jsonObject=content.get("1").getAsJsonObject();
    }
}
