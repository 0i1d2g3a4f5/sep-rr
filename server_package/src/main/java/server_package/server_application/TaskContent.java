package server_package.server_application;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
/**
 * @author Sarp Cagin Erdogan
 */
public class TaskContent {
    public JsonObject content;
    public TaskContent(String string1){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("String1", new JsonPrimitive(string1));
        content=jsonObject;
    }
    public TaskContent(String string1, String string2){
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("String1", new JsonPrimitive(string1));
        jsonObject.add("String2", new JsonPrimitive(string2));
        content=jsonObject;
    }
}
