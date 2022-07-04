package client_application;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
/**
 * @author Sarp Cagin Erdogan
 */
public class TaskContent {
    public JsonObject content;
    public TaskContent(){
        content=new JsonObject();
    }
    public TaskContent(Task task){
        content=task.taskContent.content;
    }
    public TaskContent(JsonObject jsonObject){
        JsonObject temp = new JsonObject();
        temp.add("1", jsonObject);
        content=temp;
    }

    public TaskContent(JsonArray jsonArray){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("1", jsonArray);
        content=jsonObject;
    }
    public TaskContent(boolean bool){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("1", new JsonPrimitive(bool));
        content=jsonObject;
    }
    public TaskContent(int int1){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("1", new JsonPrimitive(int1));
        content=jsonObject;
    }
    public TaskContent(String string1){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("1", new JsonPrimitive(string1));
        content=jsonObject;
    }
    public TaskContent(String string1, String string2){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("1", new JsonPrimitive(string1));
        jsonObject.add("2", new JsonPrimitive(string2));
        content=jsonObject;
    }
}
